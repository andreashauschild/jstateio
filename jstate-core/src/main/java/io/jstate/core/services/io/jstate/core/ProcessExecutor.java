package io.jstate.core.services.io.jstate.core;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import io.jstate.spi.exception.AlreadyReservedException;
import io.jstate.spi.exception.TransitionNotAllowedException;
import io.jstate.core.services.io.jstate.core.state.ErrorState;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.model.configuration.ProcessorDefinition;
import io.jstate.spi.JstateEnvironmentProvider;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.Processor;
import io.jstate.spi.State;

import static io.jstate.spi.DefaultStates.CLOSED;
import static io.jstate.spi.DefaultStates.FINAL;
import static java.util.Arrays.asList;

public class ProcessExecutor {

    static final Logger logger = Logger.getLogger(ProcessExecutor.class.getName());

    private Set<String> stopExecutionState = new HashSet<>(asList(CLOSED, FINAL));

    private JstateEnvironmentProvider env;

    public ProcessExecutor(JstateEnvironmentProvider environmentProvider) {

        this.env = environmentProvider;
    }

    public void execute(String processInstanceId) {

        State currentState;
        State newState;
        ProcessInstance reserved = null;
        try {
            reserved = env.getProcessRepository().reserveProcessInstance(processInstanceId);
            do {
                currentState = reserved.getCurrentState();
                newState = runProcessors(reserved);
                if (newState != null) {
                    reserved = this.env.getProcessRepository().updateProcessInstance(reserved.getReservationId(), newState);
                }
            } while (isExecutable(newState, currentState));

        } catch (AlreadyReservedException e) {
            logger.info("Execution of process with id '" + processInstanceId + "' is skipped. Process has already a reservation.");
        } finally {
            if (reserved != null) {
                env.getProcessRepository().cancelProcessInstanceReservation(reserved.getReservationId());
            }
        }
    }

    private State runProcessors(ProcessInstance reserved) {

        State result = null;
        ProcessTemplate processTemplate = env.getProcessTemplateRepository().getProcessTemplate(reserved.getProcessTemplateId());
        try {
            for (ProcessorDefinition processor : processTemplate.getProcessors()) {
                LocalDateTime start = LocalDateTime.now();
                Processor o = env.getProcessorFactory().create(processor, Processor.class);
                LocalDateTime end = LocalDateTime.now();
                State newState = o.process(reserved);

                // The first state transition will be used as result, but all processors will be executed
                if (result == null && newState != null) {
                    newState.setBegin(start);
                    newState.setEnd(end);
                    result = newState;
                }
            }
            if (result != null) {
                this.env.getJstateValidationService().checkTransition(reserved, reserved.getCurrentState(), result);
            }

        } catch (TransitionNotAllowedException e) {
            return new ErrorState(e, result.getProperties());
        } catch (Exception e) {
            return new ErrorState(e, null);
        }
        return result;
    }

    private boolean isExecutable(State newState, State oldState) {

        if (newState == null) {
            return false;
        }

        if (stopExecutionState.contains(newState.getId())) {
            return false;
        }

        return newState.getId() != oldState.getId();
    }

}
