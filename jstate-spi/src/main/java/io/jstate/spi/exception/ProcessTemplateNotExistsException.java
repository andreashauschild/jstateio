package io.jstate.spi.exception;

public class ProcessTemplateNotExistsException extends RuntimeException {

    public ProcessTemplateNotExistsException(String templateId) {

        super("The process template with id "
                + templateId
                + " does not exists.");
    }
}
