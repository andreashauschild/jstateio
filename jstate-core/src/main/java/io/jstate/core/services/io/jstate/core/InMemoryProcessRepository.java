package io.jstate.core.services.io.jstate.core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

import io.jstate.spi.exception.AlreadyReservedException;
import io.jstate.spi.exception.ProcessInstanceNotExistsException;
import io.jstate.core.services.io.jstate.core.query.FindByCurrentState;
import io.jstate.core.services.io.jstate.core.query.GetAllUnReserved;
import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessInstanceFactory;
import io.jstate.spi.ProcessTemplateRepository;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessInstanceQuery;
import io.jstate.spi.ProcessRepository;
import io.jstate.spi.State;
import io.jstate.spi.exception.ProcessInstanceReservationNotExistsException;

import static io.jstate.core.services.io.jstate.core.misc.JStateUtil.cloneObject;

public class InMemoryProcessRepository implements io.jstate.spi.ProcessRepository {

    private Map<String, ProcessInstance> instances = new HashMap<>();

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
            this.instances.get(first.get().getId()).getStates().add(state);
            this.instances.get(first.get().getId()).setLastUpdate(LocalDateTime.now());
            return update(this.instances.get(first.get().getId()));
        }
        // TODO define exception
        throw new RuntimeException("ProcessInstance with reservationId " + reservationId + " does not exists");

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
    public synchronized ProcessInstance reserveProcessInstance(String instanceId) {

        ProcessInstance processInstance = getProcessInstanceById(instanceId);

        if (processInstance.getReservationId() != null) {
            throw new AlreadyReservedException(processInstance);
        } else {
            processInstance.setReservationId(UUID.randomUUID().toString());
            processInstance.setReservationTime(LocalDateTime.now());
            processInstance.setLastUpdate(LocalDateTime.now());

            return update(processInstance);
        }

    }

    @Override
    public void cancelProcessInstanceReservation(String reservationId) {

        if (reservationId == null) {
            throw new IllegalArgumentException("Error: Missing reservation id");
        }

        Optional<ProcessInstance> first = instances.values().stream().filter(p -> reservationId.equalsIgnoreCase(p.getReservationId())).findFirst();

        if (first.isPresent()) {
            this.instances.get(reservationId).setReservationId(null);
            this.instances.get(reservationId).setReservationTime(null);
        }
        // TODO define exception
        throw new ProcessInstanceReservationNotExistsException(reservationId);
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

    private ProcessInstance update(ProcessInstance instance) {

        ProcessInstance cloned = cloneObject(instance);
        this.instances.put(cloned.getId(), cloned);
        return cloned;
    }

}
