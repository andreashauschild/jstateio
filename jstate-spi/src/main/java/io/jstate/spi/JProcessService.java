package io.jstate.spi;

import java.util.Map;

public interface JProcessService {

    ProcessInstance create(String templateId, Map<String, String> properties);

    ProcessInstance cancel(String reservationId);

    ProcessInstance reserve(String instanceId);

    ProcessInstance get(String instanceId);

    void executeAsync(String processInstanceId);

    ProcessInstance executeSync(String processInstanceId);

    ProcessInstance transition(String reservationId, State toState);

    ProcessInstance forceTransition(String reservationId, State toState);

}
