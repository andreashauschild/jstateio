package io.jstate.core.services.io.jstate.core;

import java.time.LocalDateTime;
import java.util.Map;

import io.jstate.core.services.io.jstate.core.state.CloseState;
import io.jstate.core.services.io.jstate.core.state.ErrorState;
import io.jstate.core.services.io.jstate.core.state.FinalState;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.JstateService;
import io.jstate.spi.JstateValidationService;
import io.jstate.spi.ProcessExecutor;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessRepository;
import io.jstate.spi.ProcessTemplateRepository;
import io.jstate.spi.ProcessorFactory;
import io.jstate.spi.State;
import io.jstate.spi.exception.AlreadyReservedException;
import io.jstate.spi.exception.ProcessInstanceNotExistsException;

public class DefaultJstateService implements JstateService {

    private ProcessExecutor processExecutor;
    private ProcessTemplateRepository templateRepository;
    private ProcessRepository processRepository;
    private JstateValidationService validationService;

    public DefaultJstateService(ProcessExecutor processExecutor, ProcessTemplateRepository templateRepository, ProcessRepository processRepository,
            JstateValidationService validationService) {

        this.processExecutor = processExecutor;
        this.templateRepository = templateRepository;
        this.processRepository = processRepository;
        this.validationService = validationService;
    }

    @Override
    public ProcessTemplate getProcessTemplate(String templateId) {

        return this.templateRepository.getProcessTemplate(templateId);
    }

    @Override
    public ProcessInstance getProcessInstance(String templateId) {

        return this.processRepository.getProcessInstanceById(templateId);
    }

    @Override
    public ProcessInstance newProcessInstance(String templateId, Map<String, String> properties) {

        ProcessTemplate processTemplate = this.templateRepository.getProcessTemplate(templateId);

        return processRepository.createProcessInstance(templateId, properties);
    }

    @Override
    public void cancelReservation(String processInstanceId) {

        this.processRepository.cancelProcessInstanceReservation(processInstanceId);
    }

    /**
     *
     * @param instanceId
     * @return
     * @exception AlreadyReservedException
     *                if the given instance has a reservation
     * @exception ProcessInstanceNotExistsException
     *                if the given instance does not exists
     */
    @Override
    public ProcessInstance reserveProcessInstance(String instanceId) {

        return this.processRepository.reserveProcessInstance(instanceId);
    }

    @Override
    public ProcessInstance executeProcess(String processInstanceId) {

        this.processExecutor.execute(processInstanceId);
        return getProcessInstance(processInstanceId);
    }

    @Override
    public ProcessInstance transition(String reservationId, State toState) {

        ProcessInstance process = this.processRepository.getProcessInstanceByReservationId(reservationId);
        if (toState.getBegin() == null) {
            toState.setBegin(process.getReservationTime());
        }
        if (toState.getEnd() == null) {
            toState.setEnd(LocalDateTime.now());
        }

        this.validationService.checkTransition(process, process.getCurrentState(), toState);
        return this.processRepository.updateProcessInstance(reservationId, toState);

    }

    @Override
    public ProcessInstance forceTransition(String reservationId, State toState) {

        ProcessInstance process = this.processRepository.getProcessInstanceByReservationId(reservationId);
        return this.processRepository.updateProcessInstance(reservationId, toState);

    }

    @Override
    public ProcessInstance toErrorState(String reservationId, String errorMessage, Map<String, String> data) {

        ProcessInstance process = this.processRepository.getProcessInstanceByReservationId(reservationId);
        return this.processRepository.updateProcessInstance(reservationId, new ErrorState(errorMessage, data));
    }

    @Override
    public ProcessInstance toErrorState(String reservationId, Throwable e, Map<String, String> data) {

        ProcessInstance process = this.processRepository.getProcessInstanceByReservationId(reservationId);
        return this.processRepository.updateProcessInstance(reservationId, new ErrorState(e, data));
    }

    @Override
    public ProcessInstance toCloseState(String reservationId, String message, Map<String, String> data) {

        ProcessInstance process = this.processRepository.getProcessInstanceByReservationId(reservationId);
        return this.processRepository.updateProcessInstance(reservationId, new CloseState(message, data));
    }

    @Override
    public ProcessInstance toFinalState(String reservationId, Map<String, String> data) {

        ProcessInstance process = this.processRepository.getProcessInstanceByReservationId(reservationId);
        FinalState finalState = new FinalState(data);
        this.validationService.checkTransition(process, process.getCurrentState(), finalState);
        return this.processRepository.updateProcessInstance(reservationId, finalState);
    }

}
