package io.jstate.spi;

import io.jstate.spi.exception.TransitionNotAllowedException;

public interface JstateValidationService {

    void checkTransition(ProcessInstance processInstance, State from, State to) throws TransitionNotAllowedException;
}
