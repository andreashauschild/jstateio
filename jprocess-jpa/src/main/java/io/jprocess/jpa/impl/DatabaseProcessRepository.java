package io.jprocess.jpa.impl;


import io.jprocess.jpa.dao.ProcessInstanceDAO;
import io.jprocess.jpa.entity.ProcessInstanceEntity;
import io.jprocess.jpa.entity.ProcessTemplateEntity;
import io.jprocess.jpa.entity.StateEntity;
import io.jprocess.jpa.mapper.ProcessInstanceMapper;
import io.jprocess.jpa.mapper.StateMapper;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessInstanceFactory;
import io.jstate.spi.ProcessInstanceQuery;
import io.jstate.spi.ProcessRepository;
import io.jstate.spi.State;
import io.jstate.spi.exception.AlreadyReservedException;
import io.jstate.spi.exception.ProcessInstanceNotExistsException;
import io.jstate.spi.exception.ProcessInstanceReservationNotExistsException;
import io.jstate.spi.exception.ProcessTemplateNotExistsException;
import org.mapstruct.factory.Mappers;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class DatabaseProcessRepository implements ProcessRepository {

    public static final String QUERY_BY_RESERVATION_ID = "select e from ProcessInstanceEntity e where reservationId = :reservationId";

    private final ProcessInstanceDAO processInstanceDAO;

    private ProcessInstanceFactory factory;

    private EntityManager entityManager;

    private DatabaseProcessTemplateRepository templateRepository;

    private ProcessInstanceMapper processInstanceMapper = Mappers.getMapper(ProcessInstanceMapper.class);

    private StateMapper stateMapper = Mappers.getMapper(StateMapper.class);

    public DatabaseProcessRepository(EntityManager entityManager, ProcessInstanceFactory factory) {
        this.entityManager = entityManager;
        this.templateRepository = new DatabaseProcessTemplateRepository(entityManager);
        this.processInstanceDAO = new ProcessInstanceDAO(this.entityManager);
        this.factory = factory;

    }

    @Override
    public ProcessInstance updateProcessInstance(String reservationId, State state) {
        this.entityManager.getTransaction().begin();
        if (reservationId == null) {
            throw new ProcessInstanceReservationNotExistsException(reservationId);
        }

        if (state == null) {
            throw new NullPointerException("Error: State is null");
        }

        ProcessInstanceEntity process = getProcessInstanceEntityByReservation(reservationId);

        StateEntity stateEntity = stateMapper.toEntity(state);
        stateEntity.setProcessInstance(process);
        process.getStates().add(stateEntity);
        processInstanceDAO.save(process);
        this.entityManager.getTransaction().commit();
        this.entityManager.flush();
        return this.processInstanceMapper.toModel(process);


    }

    @Override
    public ProcessInstance getProcessInstanceById(String instanceId) {

        ProcessInstanceEntity entity = processInstanceDAO.get(UUID.fromString(instanceId)).orElse(null);
        return this.processInstanceMapper.toModel(entity);
    }

    @Override
    public ProcessInstance getProcessInstanceByReservationId(String reservationId) {
        this.entityManager.getTransaction().begin();
        ProcessInstanceEntity instanceEntity = getProcessInstanceEntityByReservation(reservationId);
        this.entityManager.getTransaction().commit();
        return this.processInstanceMapper.toModel(instanceEntity);
    }

    @Override
    public boolean existsReservation(String reservationId) {
        Map<String, Object> params = new HashMap<>();
        params.put("reservationId", reservationId);
        List<ProcessInstanceEntity> processInstanceEntities = this.processInstanceDAO.find(QUERY_BY_RESERVATION_ID, params);
        return !processInstanceEntities.isEmpty();
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
    public ProcessInstance reserveProcessInstance(String instanceId) {
        this.entityManager.getTransaction().begin();
        String reservationId = UUID.randomUUID().toString();
        ProcessInstanceEntity instanceEntity = this.processInstanceDAO.get(UUID.fromString(instanceId), LockModeType.PESSIMISTIC_READ).orElse(null);
        if (instanceEntity != null) {
            if (instanceEntity.getReservationId() != null) {
                throw new AlreadyReservedException(this.processInstanceMapper.toModel(instanceEntity));
            } else {
                instanceEntity.setReservationId(reservationId);
                instanceEntity.setReservationTime(LocalDateTime.now());

            }
        } else {
            throw new ProcessInstanceNotExistsException(instanceId);
        }
        this.entityManager.getTransaction().commit();
        return this.processInstanceMapper.toModel(instanceEntity);
    }

    @Override
    public ProcessInstance cancelProcessInstanceReservation(String reservationId) {
        this.entityManager.getTransaction().begin();
        ProcessInstanceEntity instanceEntity = getProcessInstanceEntityByReservation(reservationId);
        instanceEntity.setReservationId(null);
        instanceEntity.setReservationTime(null);
        this.entityManager.getTransaction().commit();
        return this.processInstanceMapper.toModel(instanceEntity);
    }

    @Override
    public ProcessInstance createProcessInstance(String processTemplateId, Map<String, String> initialProperties) {

        ProcessTemplate processTemplate = this.templateRepository.getProcessTemplate(processTemplateId);
        if (processTemplate == null) {
            // TODO define exception
            throw new ProcessTemplateNotExistsException(processTemplateId);
        }
        this.entityManager.getTransaction().begin();
        ProcessInstance processInstance = factory.create(processTemplate, initialProperties);
        ProcessInstanceEntity processInstanceEntity = this.processInstanceMapper.toEntity(processInstance);
        processInstanceEntity.setProcessTemplate(this.entityManager.find(ProcessTemplateEntity.class, UUID.fromString(processTemplateId)));
        this.processInstanceDAO.save(processInstanceEntity);
        this.entityManager.getTransaction().commit();
        return this.processInstanceMapper.toModel(processInstanceEntity);
    }

    @Override
    public List<ProcessInstance> findProcessInstance(ProcessInstanceQuery query) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    private ProcessInstanceEntity getProcessInstanceEntityByReservation(String reservationId) {
        Map<String, Object> params = new HashMap<>();
        params.put("reservationId", reservationId);
        List<ProcessInstanceEntity> processInstanceEntities = this.processInstanceDAO.find(QUERY_BY_RESERVATION_ID, params);
        if (processInstanceEntities.isEmpty()) {
            throw new ProcessInstanceReservationNotExistsException(reservationId);
        }
        return processInstanceEntities.get(0);
    }

}
