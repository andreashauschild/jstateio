package io.jstate.core.services.io.jstate.core;

import io.jstate.core.services.io.jstate.core.state.CloseState;
import io.jstate.core.services.io.jstate.core.state.ErrorState;
import io.jstate.core.services.io.jstate.core.state.FinalState;
import io.jstate.core.services.io.jstate.core.state.PauseState;
import io.jstate.spi.JProcessService;
import io.jstate.spi.JProcessUtilService;
import io.jstate.spi.JstateValidationService;
import io.jstate.spi.ProcessExecutor;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessRepository;
import io.jstate.spi.ProcessTemplateRepository;
import io.jstate.spi.State;
import io.jstate.spi.exception.AlreadyReservedException;
import io.jstate.spi.exception.ProcessInstanceNotExistsException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.Executor;

public class DefaultJProcessUtilService implements JProcessUtilService {

    private ProcessRepository processRepository;
    private JstateValidationService validationService;


    @Override
    public ProcessInstance toError(String reservationId, String reason, Map<String, String> data) {
        return this.processRepository.updateProcessInstance(reservationId, new ErrorState(reason, data));
    }

    @Override
    public ProcessInstance toError(String reservationId, Throwable e, Map<String, String> data) {
        return this.processRepository.updateProcessInstance(reservationId, new ErrorState(e, data));
    }

    @Override
    public ProcessInstance toClose(String reservationId, String reason, Map<String, String> data) {
        return this.processRepository.updateProcessInstance(reservationId, new CloseState(reason, data));
    }

    @Override
    public ProcessInstance toPause(String reservationId, String reason, Map<String, String> data) {
        return this.processRepository.updateProcessInstance(reservationId, new PauseState(reason, data));
    }

    @Override
    public ProcessInstance toFinal(String reservationId, Map<String, String> data) {

        ProcessInstance process = this.processRepository.getProcessInstanceByReservationId(reservationId);
        FinalState finalState = new FinalState(data);
        this.validationService.checkTransition(process, process.getCurrentState(), finalState);
        return this.processRepository.updateProcessInstance(reservationId, finalState);
    }

    @Override
    public void logError(String reservationId, String message) {
        this.processRepository.logError(reservationId, message);
    }

    @Override
    public void logInfo(String reservationId, String message) {
        this.processRepository.logInfo(reservationId, message);
    }

    @Override
    public void logWarning(String reservationId, String message) {
        this.processRepository.logWarning(reservationId, message);
    }


    public DefaultJProcessUtilService setProcessRepository(ProcessRepository processRepository) {
        this.processRepository = processRepository;
        return this;
    }


    public DefaultJProcessUtilService setValidationService(JstateValidationService validationService) {
        this.validationService = validationService;
        return this;
    }
}
