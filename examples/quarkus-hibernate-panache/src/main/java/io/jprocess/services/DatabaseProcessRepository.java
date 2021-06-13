package io.jprocess.services;

import io.jprocess.hibernate.entities.ProcessInstanceEntity;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.ProcessInstanceQuery;
import io.jstate.spi.ProcessRepository;
import io.jstate.spi.State;
import io.jstate.spi.exception.ProcessInstanceReservationNotExistsException;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class DatabaseProcessRepository implements ProcessRepository {

    @Override
    @Transactional
    public ProcessInstance updateProcessInstance(String reservationId, State state) {

        if (reservationId == null) {
            throw new RuntimeException("Error: Missing reservation id");
        }

        if (state == null) {
            throw new RuntimeException("Error: Missing state");
        }

        ProcessInstanceEntity process = ProcessInstanceEntity.find("reservationId").firstResult();
        if (process != null) {
            this.instances.get(first.get().getId()).getStates().add(state);
            this.instances.get(first.get().getId()).setLastUpdate(LocalDateTime.now());
            return updateAndClone(this.instances.get(first.get().getId()));
        }
        throw new ProcessInstanceReservationNotExistsException(reservationId);
        return null;
    }

    @Override
    public ProcessInstance getProcessInstanceById(String instanceId) {
        return null;
    }

    @Override
    public ProcessInstance getProcessInstanceByReservationId(String reservationId) {
        return null;
    }

    @Override
    public ProcessInstance reserveProcessInstance(String instanceId) {
        return null;
    }

    @Override
    public ProcessInstance cancelProcessInstanceReservation(String reservationId) {
        return null;
    }

    @Override
    public ProcessInstance createProcessInstance(String processDefinitionId, Map<String, String> initialProperties) {
        return null;
    }

    @Override
    public List<ProcessInstance> findProcessInstance(ProcessInstanceQuery query) {
        return null;
    }
}
