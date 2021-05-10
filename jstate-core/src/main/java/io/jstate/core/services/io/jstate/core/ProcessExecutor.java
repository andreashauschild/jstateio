package io.jstate.core.services.io.jstate.core;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import io.jstate.core.services.io.jstate.core.exception.AlreadyReservedException;
import io.jstate.core.services.io.jstate.core.exception.TransitionNotAllowedException;
import io.jstate.core.services.io.jstate.core.state.ErrorState;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.model.configuration.ProcessorDefinition;
import io.jstate.spi.DefaultStates;
import io.jstate.spi.JstateEnvironmentProvider;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.Processor;
import io.jstate.spi.State;

public class ProcessExecutor {

    static final Logger logger = Logger.getLogger(ProcessExecutor.class.getName());

    private final JstateValidationService validationService;

    private Set<String> stopExecutionState = new HashSet<>(Arrays.asList(DefaultStates.CLOSED, DefaultStates.FINAL));

    private final JstateEnvironmentProvider env;

    ProcessExecutor(JstateEnvironmentProvider environmentProvider) {

        this.env = environmentProvider;
        this.validationService = new JstateValidationService(this.env);
    }

    public void execute(String processInstanceId) {

        State currentState;
        State newState;
        ProcessInstance reserved = null;
        try {
            reserved = env.getProcessRepository().reserveProcessInstance(processInstanceId);
            do {
                currentState = reserved.getCurrentState();
                LocalDateTime start = LocalDateTime.now();
                newState = process(reserved);
                LocalDateTime end = LocalDateTime.now();
                if (newState != null) {
                    newState.setBegin(start);
                    newState.setEnd(end);
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

    private State process(ProcessInstance reserved) {

        State result = null;
        ProcessTemplate processTemplate = env.getProcessTemplateRepository().getProcessTemplate(reserved.getProcessTemplateId());
        try {
            for (ProcessorDefinition processor : processTemplate.getProcessors()) {
                Processor o = env.getProcessorFactory().create(processor, Processor.class);
                State newState = o.process(reserved);

                // The first state transition will be used as result, but all processors will be executed
                if (result == null && newState != null) {
                    result = newState;
                }
            }
            this.validationService.checkTransition(reserved, reserved.getCurrentState(), result);
        } catch (TransitionNotAllowedException e) {
            return new ErrorState(e, result.getProperties());
        } catch (Exception e) {
            return new ErrorState(e, null);
        }
        return result;
    }

    private boolean isExecutable(State newState, State oldState) {

        if (stopExecutionState.contains(newState.getId())) {
            return false;
        }
        if (newState == null) {
            return false;
        }
        return newState.getId() != oldState.getId();
    }

}
