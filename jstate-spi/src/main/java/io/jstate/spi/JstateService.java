package io.jstate.spi;

import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.State;

import java.util.Map;

public interface JstateService {

    ProcessTemplate getProcessTemplate(String templateId);

    ProcessInstance getProcessInstance(String templateId);

    ProcessInstance newProcessInstance(String templateId, Map<String, String> properties);

    void cancelReservation(String processInstanceId);

    ProcessInstance reserveProcessInstance(String instanceId);

    ProcessInstance executeProcess(String processInstanceId);

    ProcessInstance transition(String reservationId, State toState);

    ProcessInstance forceTransition(String reservationId, State toState);

    ProcessInstance toErrorState(String reservationId, String errorMessage, Map<String, String> data);

    ProcessInstance toErrorState(String reservationId, Throwable e, Map<String, String> data);

    ProcessInstance toCloseState(String reservationId, String message, Map<String, String> data);

    ProcessInstance toFinalState(String reservationId, Map<String, String> data);
}
