package io.jprocess.impl;

import io.jprocess.hibernate.entities.ProcessInstanceEntity;
import io.jprocess.hibernate.entities.StateEntity;
import io.jprocess.mapper.ProcessInstanceMapper;
import io.jprocess.mapper.StateMapper;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessInstanceFactory;
import io.jstate.spi.ProcessInstanceQuery;
import io.jstate.spi.ProcessRepository;
import io.jstate.spi.State;
import io.jstate.spi.exception.AlreadyReservedException;
import io.jstate.spi.exception.ProcessInstanceNotExistsException;
import io.jstate.spi.exception.ProcessInstanceReservationNotExistsException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class DatabaseProcessRepository implements ProcessRepository {

    @Inject
    DatabaseProcessTemplateRepository templateRepository;

    @Inject
    private ProcessInstanceFactory factory;

    @Inject
    ProcessInstanceMapper processInstanceMapper;

    @Inject
    StateMapper stateMapper;

    @Override
    @Transactional
    public ProcessInstance updateProcessInstance(String reservationId, State state) {

        if (reservationId == null) {
            throw new RuntimeException("Error: Missing reservation id");
        }

        if (state == null) {
            throw new RuntimeException("Error: Missing state");
        }

        ProcessInstanceEntity process = getProcessInstanceEntityByReservation(reservationId);

        StateEntity stateEntity = stateMapper.toEntity(state);
        stateEntity.setProcessInstance(process);
        process.getStates().add(stateEntity);

        process.persistAndFlush();
        return this.processInstanceMapper.toModel(process);


    }

    @Override
    public ProcessInstance getProcessInstanceById(String instanceId) {
        ProcessInstanceEntity entity = ProcessInstanceEntity.findById(instanceId);
        return this.processInstanceMapper.toModel(entity);
    }

    @Override
    @Transactional
    public ProcessInstance getProcessInstanceByReservationId(String reservationId) {
        ProcessInstanceEntity instanceEntity = getProcessInstanceEntityByReservation(reservationId);
        return this.processInstanceMapper.toModel(instanceEntity);
    }

    @Override
    public boolean existsReservation(String reservationId) {
        return false;
    }

    @Override
    public void logInfo(String reservationId, String message) {

    }

    @Override
    public void logError(String reservationId, String message) {

    }

    @Override
    public void logWarning(String reservationId, String message) {

    }


    @Override
    @Transactional
    public ProcessInstance reserveProcessInstance(String instanceId) {
        String reservationId = UUID.randomUUID().toString();
        ProcessInstanceEntity instanceEntity = ProcessInstanceEntity.findById(instanceId, LockModeType.PESSIMISTIC_READ);
        if (instanceEntity != null) {
            if (instanceEntity.getReservationId() != null) {
                throw new AlreadyReservedException(this.processInstanceMapper.toModel(instanceEntity));
            } else {
                instanceEntity.setReservationId(reservationId);
                instanceEntity.setReservationTime(LocalDateTime.now());
                instanceEntity.persistAndFlush();
            }
        } else {
            throw new ProcessInstanceNotExistsException(instanceId);
        }
        return this.processInstanceMapper.toModel(instanceEntity);
    }

    @Override
    @Transactional
    public ProcessInstance cancelProcessInstanceReservation(String reservationId) {
        ProcessInstanceEntity instanceEntity = getProcessInstanceEntityByReservation(reservationId);
        instanceEntity.setReservationId(null);
        instanceEntity.setReservationTime(null);
        return this.processInstanceMapper.toModel(instanceEntity);
    }

    @Override
    @Transactional
    public ProcessInstance createProcessInstance(String processDefinitionId, Map<String, String> initialProperties) {
        ProcessTemplate processTemplate = this.templateRepository.getProcessTemplate(processDefinitionId);
        if (processTemplate == null) {
            // TODO define exception
            throw new RuntimeException("ProcessDefinition with id " + processDefinitionId + " does not exists");
        }
        ProcessInstance processInstance = factory.create(processTemplate, initialProperties);
        ProcessInstanceEntity processInstanceEntity = this.processInstanceMapper.toEntity(processInstance);
        processInstanceEntity.persist();
        return this.processInstanceMapper.toModel(processInstanceEntity);
    }

    @Override
    public List<ProcessInstance> findProcessInstance(ProcessInstanceQuery query) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    private ProcessInstanceEntity getProcessInstanceEntityByReservation(String reservationId) {
        ProcessInstanceEntity instanceEntity = ProcessInstanceEntity.find("reservationId", reservationId).firstResult();
        if (instanceEntity == null) {
            throw new ProcessInstanceReservationNotExistsException(reservationId);
        }
        return instanceEntity;
    }

}
