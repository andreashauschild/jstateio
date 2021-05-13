package io.jstate.spi;

import io.jstate.model.configuration.ProcessTemplate;
import io.jstate.spi.exception.TransitionNotAllowedException;

public interface JstateValidationService {

    void checkTransition(ProcessInstance processInstance, State from, State to) throws TransitionNotAllowedException;

    void checkTransition(ProcessTemplate processTemplate, State from, State to) throws TransitionNotAllowedException;
}
