package io.jstate.spi.exception;

public class ProcessInstanceNotExistsException extends RuntimeException {

    public ProcessInstanceNotExistsException(String processInstanceId) {

        super("The process instance with id "
                + processInstanceId
                + " does not exists.");
    }
}
