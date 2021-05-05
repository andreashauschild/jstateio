package io.jstate.spi;

import java.util.List;
import java.util.Map;

public interface ProcessRepository {

    ProcessInstance updateProcessInstance(String reservationId, State state);

    ProcessInstance getProcessInstance(String instanceId);

    ProcessInstance reserveProcessInstance(String instanceId);

    void cancelProcessInstanceReservation(String reservationId);

    ProcessInstance createProcessInstance(String processDefinitionId, Map<String, String> initialProperties);

    List<ProcessInstance> findProcessInstance(ProcessInstanceQuery query);

}
