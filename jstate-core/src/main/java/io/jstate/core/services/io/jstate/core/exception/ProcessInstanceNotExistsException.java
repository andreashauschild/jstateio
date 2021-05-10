package io.jstate.core.services.io.jstate.core.exception;

import io.jstate.spi.ProcessInstance;

public class ProcessInstanceNotExistsException extends RuntimeException {

    public ProcessInstanceNotExistsException(String processInstanceId) {

        super("The process instance with id "
                + processInstanceId
                + " does not exists.");
    }
}
