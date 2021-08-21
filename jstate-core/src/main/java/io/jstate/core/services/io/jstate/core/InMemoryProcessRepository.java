package io.jstate.core.services.io.jstate.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import io.jstate.spi.LogEntry;
import io.jstate.spi.LogLevel;
import io.jstate.spi.exception.AlreadyReservedException;
import io.jstate.spi.exception.ProcessInstanceNotExistsException;
import io.jstate.core.services.io.jstate.core.query.FindByCurrentState;
import io.jstate.core.services.io.jstate.core.query.GetAllUnReserved;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessInstanceFactory;
import io.jstate.spi.ProcessTemplateRepository;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessInstanceQuery;
import io.jstate.spi.State;
import io.jstate.spi.exception.ProcessInstanceReservationNotExistsException;

import static io.jstate.core.services.io.jstate.core.misc.JStateUtil.cloneObject;

public class InMemoryProcessRepository implements io.jstate.spi.ProcessRepository {

    private Map<String, ProcessInstance> instances = new HashMap<>();

    private Map<String, List<LogEntry>> logs = new HashMap<>();

    private ProcessTemplateRepository definitionRepository;

    private ProcessInstanceFactory factory;

    public InMemoryProcessRepository(ProcessTemplateRepository definitionRepository, ProcessInstanceFactory factory) {

        this.factory = factory;
        this.definitionRepository = definitionRepository;
    }

    @Override
    public ProcessInstance updateProcessInstance(String reservationId, State state) {
        if (reservationId == null) {
            throw new RuntimeException("Error: Missing reservation id");
        }

        if (state == null) {
            throw new RuntimeException("Error: Missing state");
        }

        Optional<ProcessInstance> first = instances.values().stream().filter(p -> reservationId.equalsIgnoreCase(p.getReservationId())).findFirst();

        if (first.isPresent()) {
            if (this.logs.containsKey(reservationId)) {
                state.getLogEntries().addAll(this.logs.get(reservationId));
            }

            this.instances.get(first.get().getId()).getStates().add(state);
            this.instances.get(first.get().getId()).setLastUpdate(LocalDateTime.now());
            ProcessInstance instance = updateAndClone(this.instances.get(first.get().getId()));
            this.logs.put(reservationId, new ArrayList<>());
            return instance;
        }
        throw new ProcessInstanceReservationNotExistsException(reservationId);

    }

    @Override
    public ProcessInstance getProcessInstanceById(String instanceId) {

        if (!instances.containsKey(instanceId)) {
            throw new ProcessInstanceNotExistsException(instanceId);
        }
        return cloneObject(instances.get(instanceId));
    }

    @Override
    public ProcessInstance getProcessInstanceByReservationId(String reservationId) {

        Optional<ProcessInstance> first = this.instances.values().stream()
                .filter(p -> p.getReservationId() != null && p.getReservationId().equals(reservationId)).findFirst();
        if (!first.isPresent()) {
            throw new ProcessInstanceReservationNotExistsException(reservationId);
        }
        return cloneObject(first.get());
    }

    @Override
    public boolean existsReservation(String reservationId) {
        Optional<ProcessInstance> first = this.instances.values().stream()
                .filter(p -> p.getReservationId() != null && p.getReservationId().equals(reservationId)).findFirst();
        if (!first.isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public void logInfo(String reservationId, String message) {
        checkReservationOrThrow(reservationId);
        addLogEntry(reservationId, new LogEntry(LogLevel.INFO, message));

    }

    @Override
    public void logError(String reservationId, String message) {
        checkReservationOrThrow(reservationId);
        addLogEntry(reservationId, new LogEntry(LogLevel.INFO, message));
    }

    @Override
    public void logWarning(String reservationId, String message) {
        checkReservationOrThrow(reservationId);
        addLogEntry(reservationId, new LogEntry(LogLevel.INFO, message));
    }

    private void addLogEntry(String processInstanceId, LogEntry entry) {
        if (!this.logs.containsKey(processInstanceId)) {
            this.logs.put(processInstanceId, new ArrayList<>());
        }
        this.logs.get(processInstanceId).add(entry);
    }

    @Override
    public synchronized ProcessInstance reserveProcessInstance(String instanceId) {

        ProcessInstance processInstance = getProcessInstanceById(instanceId);

        if (processInstance.getReservationId() != null) {
            throw new AlreadyReservedException(processInstance);
        } else {
            processInstance.setReservationId(UUID.randomUUID().toString());
            processInstance.setReservationTime(LocalDateTime.now());
            processInstance.setLastUpdate(LocalDateTime.now());

            return updateAndClone(processInstance);
        }

    }

    @Override
    public ProcessInstance cancelProcessInstanceReservation(String reservationId) {

        if (reservationId == null) {
            throw new IllegalArgumentException("Error: Missing reservation id");
        }

        ProcessInstance instance = getProcessInstanceByReservationId(reservationId);
        instance.setReservationId(null);
        instance.setReservationTime(null);
        return updateAndClone(instance);

    }

    @Override
    public ProcessInstance createProcessInstance(String processDefinitionId, Map<String, String> initialProperties) {

        ProcessTemplate processTemplate = this.definitionRepository.getProcessTemplate(processDefinitionId);
        if (processTemplate == null) {
            // TODO define exception
            throw new RuntimeException("ProcessDefinition with id " + processDefinitionId + " does not exists");
        }
        ProcessInstance processInstance = factory.create(processTemplate, initialProperties);
        this.instances.put(processInstance.getId(), processInstance);
        return cloneObject(processInstance);
    }

    @Override
    public List<ProcessInstance> findProcessInstance(ProcessInstanceQuery query) {

        if (query == null) {
            throw new IllegalArgumentException("Error: Missing query");
        }

        if (query instanceof FindByCurrentState) {
            FindByCurrentState q = (FindByCurrentState) query;
        } else if (query instanceof GetAllUnReserved) {
            return toCloneList(new ArrayList<>(this.instances.values()));
        }
        // TODO
        throw new IllegalArgumentException("Unknown query type: " + query.getClass());
    }

    List<ProcessInstance> toCloneList(List<ProcessInstance> list) {

        return list.stream().map(p -> cloneObject(p)).collect(Collectors.toList());
    }

    private ProcessInstance updateAndClone(ProcessInstance instance) {

        ProcessInstance cloned = cloneObject(instance);
        this.instances.put(cloned.getId(), cloned);
        return cloned;
    }

    private void checkReservationOrThrow(String reservationId) {
        if (!existsReservation(reservationId)) {
            throw new ProcessInstanceReservationNotExistsException(reservationId);
        }
    }

}
