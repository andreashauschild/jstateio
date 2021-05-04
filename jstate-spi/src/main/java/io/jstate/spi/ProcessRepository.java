package io.jstate.spi;

import io.jstate.model.configuration.ProcessDefinition;

import java.util.List;

public interface ProcessRepository {

    ProcessInstance updateProcessInstance(String reservationId, State state);

    ProcessInstance getProcessInstance(String instanceId);

    ProcessInstance reserveProcessInstance(String instanceId);

    void cancelProcessInstanceReservation(String reservationId);

    ProcessInstance createProcessInstance(String processDefinitionId);

    List<ProcessInstance> findProcessInstance(ProcessInstanceQuery query);

}
