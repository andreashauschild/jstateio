package io.jstate.spi.exception;

import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.ProcessInstance;
import io.jstate.spi.State;

public class UnknownProcessorException extends RuntimeException {

    public UnknownProcessorException(String clazz) {

        super("Process Error. Processor '" + clazz + "' could not be found.");
    }


}
