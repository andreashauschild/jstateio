package io.jstate.spi;

import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.State;

import java.util.Map;

public interface JProcessService {

    ProcessInstance create(String templateId, Map<String, String> properties);

    ProcessInstance cancel(String reservationId);

    ProcessInstance reserve(String instanceId);

    ProcessInstance get(String instanceId);

    void execute(String processInstanceId);

    ProcessInstance transition(String reservationId, State toState);

    ProcessInstance forceTransition(String reservationId, State toState);

    ProcessInstance toError(String reservationId, String errorMessage, Map<String, String> data);

    ProcessInstance toError(String reservationId, Throwable e, Map<String, String> data);

    ProcessInstance toClose(String reservationId, String message, Map<String, String> data);

    ProcessInstance toFinal(String reservationId, Map<String, String> data);
}
