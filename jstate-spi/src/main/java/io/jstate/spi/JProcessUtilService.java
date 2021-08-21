package io.jstate.spi;

import java.util.Map;

public interface JProcessUtilService {

    ProcessInstance toError(String reservationId, String reason, Map<String, String> data);

    ProcessInstance toError(String reservationId, Throwable e, Map<String, String> data);

    ProcessInstance toClose(String reservationId, String reason, Map<String, String> data);

    ProcessInstance toPause(String reservationId, String reason, Map<String, String> data);

    ProcessInstance toFinal(String reservationId, Map<String, String> data);

    void logError(String reservationId, String message);

    void logInfo(String reservationId, String message);

    void logWarning(String reservationId, String message);
}
