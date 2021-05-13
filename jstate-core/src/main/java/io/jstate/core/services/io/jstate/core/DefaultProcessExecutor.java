package io.jstate.core.services.io.jstate.core;

import static io.jstate.spi.DefaultStates.CLOSED;
import static io.jstate.spi.DefaultStates.FINAL;
import static java.util.Arrays.asList;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.model.configuration.ProcessorDefinition;
import io.jstate.spi.JstateService;
import io.jstate.spi.ProcessExecutor;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.Processor;
import io.jstate.spi.ProcessorFactory;
import io.jstate.spi.State;
import io.jstate.spi.exception.AlreadyReservedException;
import io.jstate.spi.exception.TransitionNotAllowedException;

public class DefaultProcessExecutor implements ProcessExecutor {

    static final Logger logger = Logger.getLogger(DefaultProcessExecutor.class.getName());

    private Set<String> stopExecutionState = new HashSet<>(asList(CLOSED, FINAL));

    private JstateService jstateService;

    private ProcessorFactory processorFactory;

    public DefaultProcessExecutor(JstateService jstateService, ProcessorFactory processorFactory) {

        this.jstateService = jstateService;
        this.processorFactory = processorFactory;
    }

    @Override
    public void execute(String processInstanceId) {

        State currentState;
        State newState = null;
        ProcessInstance reserved = null;
        try {
            reserved = this.jstateService.reserveProcessInstance(processInstanceId);
            do {
                currentState = reserved.getCurrentState();
                newState = runProcessors(reserved);
                if (newState != null) {
                    reserved = this.jstateService.transition(reserved.getReservationId(), newState);
                }
            } while (newStateAllowsFurtherProcessing(newState, currentState));

        } catch (AlreadyReservedException e) {
            logger.info("Execution of process with id '" + processInstanceId + "' is skipped. Process has already a reservation.");
        } catch (TransitionNotAllowedException e) {
            this.jstateService.toErrorState(reserved.getReservationId(), e, newState.getProperties());
        } finally {
            if (reserved != null) {
                this.jstateService.cancelReservation(reserved.getId());
            }
        }
    }

    private State runProcessors(ProcessInstance reserved) {

        State result = null;
        ProcessTemplate processTemplate = this.jstateService.getProcessTemplate(reserved.getId());

        for (ProcessorDefinition processor : processTemplate.getProcessors()) {
            LocalDateTime start = LocalDateTime.now();
            Optional<Processor> o = this.processorFactory.create(processor, Processor.class);
            if (o.isPresent()) {
                LocalDateTime end = LocalDateTime.now();
                State newState = o.get().process(reserved);

                // The first state transition will be used as result, but all processors will be executed
                if (result == null && newState != null) {
                    newState.setBegin(start);
                    newState.setEnd(end);
                    result = newState;
                }
            } else {
                logger.info("Processor '" + processor.getClazz() + "' was not found in this VM and will be ignored.");
            }
        }

        return result;
    }

    private boolean newStateAllowsFurtherProcessing(State newState, State oldState) {

        if (newState == null) {
            return false;
        }

        if (stopExecutionState.contains(newState.getId())) {
            return false;
        }

        return newState.getId() != oldState.getId();
    }

}
