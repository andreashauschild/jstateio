package io.jstate.spi;

import io.jstate.spi.exception.AlreadyReservedException;
import io.jstate.spi.exception.ProcessInstanceNotExistsException;

import java.util.List;
import java.util.Map;

public interface ProcessRepository {

    ProcessInstance updateProcessInstance(String reservationId, State state);

    ProcessInstance getProcessInstanceById(String instanceId);

    ProcessInstance getProcessInstanceByReservationId(String reservationId);

    /**
     *
     * @param instanceId
     * @return
     * @exception AlreadyReservedException
     *                if the given instance has a reservation
     * @exception ProcessInstanceNotExistsException
     *                if the given instance does not exists
     */
    ProcessInstance reserveProcessInstance(String instanceId);

    ProcessInstance cancelProcessInstanceReservation(String reservationId);

    ProcessInstance createProcessInstance(String processDefinitionId, Map<String, String> initialProperties);

    List<ProcessInstance> findProcessInstance(ProcessInstanceQuery query);

}
