package io.jstate.core.services.io.jstate.core;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.Executor;

import io.jstate.core.services.io.jstate.core.state.CloseState;
import io.jstate.core.services.io.jstate.core.state.ErrorState;
import io.jstate.core.services.io.jstate.core.state.FinalState;
import io.jstate.core.services.io.jstate.core.state.PauseState;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.JProcessService;
import io.jstate.spi.JstateValidationService;
import io.jstate.spi.ProcessExecutor;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessRepository;
import io.jstate.spi.ProcessTemplateRepository;
import io.jstate.spi.State;
import io.jstate.spi.exception.AlreadyReservedException;
import io.jstate.spi.exception.ProcessInstanceNotExistsException;
import io.jstate.spi.exception.ProcessInstanceReservationNotExistsException;

public class DefaultJProcessService implements JProcessService {

    private Executor executor;
    private ProcessExecutor processExecutor;
    private ProcessTemplateRepository templateRepository;
    private ProcessRepository processRepository;
    private JstateValidationService validationService;

    @Override
    public ProcessInstance create(String templateId, Map<String, String> properties) {

        return processRepository.createProcessInstance(templateId, properties);
    }

    @Override
    public ProcessInstance cancel(String reservationId) {

        return this.processRepository.cancelProcessInstanceReservation(reservationId);
    }

    /**
     * @param instanceId
     * @return
     * @throws AlreadyReservedException          if the given instance has a reservation
     * @throws ProcessInstanceNotExistsException if the given instance does not exists
     */
    @Override
    public ProcessInstance reserve(String instanceId) {

        return this.processRepository.reserveProcessInstance(instanceId);
    }

    @Override
    public ProcessInstance get(String instanceId) {
        return this.processRepository.getProcessInstanceById(instanceId);
    }

    @Override
    public void executeAsync(String processInstanceId) {
        this.executor.execute(() -> {
            this.processExecutor.execute(processInstanceId);
        });
    }

    @Override
    public ProcessInstance executeSync(String processInstanceId) {
        this.processExecutor.execute(processInstanceId);
        return this.processRepository.getProcessInstanceById(processInstanceId);
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
        return this.processRepository.updateProcessInstance(reservationId, toState);
    }

    /**
     * Gets the value of the processExecutor property.
     *
     * @return possible object is {@link ProcessExecutor}
     */
    public ProcessExecutor getProcessExecutor() {

        return processExecutor;
    }

    /**
     * Sets the value of the processExecutor property
     *
     * @param processExecutor allowed object is {@link ProcessExecutor }
     * @return the {@link DefaultJProcessService}
     */
    public DefaultJProcessService setProcessExecutor(ProcessExecutor processExecutor) {

        this.processExecutor = processExecutor;
        return this;
    }

    /**
     * Gets the value of the templateRepository property.
     *
     * @return possible object is {@link ProcessTemplateRepository}
     */
    public ProcessTemplateRepository getTemplateRepository() {

        return templateRepository;
    }

    /**
     * Sets the value of the templateRepository property
     *
     * @param templateRepository allowed object is {@link ProcessTemplateRepository }
     * @return the {@link DefaultJProcessService}
     */
    public DefaultJProcessService setTemplateRepository(ProcessTemplateRepository templateRepository) {

        this.templateRepository = templateRepository;
        return this;
    }

    /**
     * Gets the value of the processRepository property.
     *
     * @return possible object is {@link ProcessRepository}
     */
    public ProcessRepository getProcessRepository() {

        return processRepository;
    }

    /**
     * Sets the value of the processRepository property
     *
     * @param processRepository allowed object is {@link ProcessRepository }
     * @return the {@link DefaultJProcessService}
     */
    public DefaultJProcessService setProcessRepository(ProcessRepository processRepository) {

        this.processRepository = processRepository;
        return this;
    }

    /**
     * Gets the value of the validationService property.
     *
     * @return possible object is {@link JstateValidationService}
     */
    public JstateValidationService getValidationService() {

        return validationService;
    }

    /**
     * Sets the value of the validationService property
     *
     * @param validationService allowed object is {@link JstateValidationService }
     * @return the {@link DefaultJProcessService}
     */
    public DefaultJProcessService setValidationService(JstateValidationService validationService) {

        this.validationService = validationService;
        return this;
    }

    public DefaultJProcessService setExecutor(Executor executor) {
        this.executor = executor;
        return this;
    }


}
