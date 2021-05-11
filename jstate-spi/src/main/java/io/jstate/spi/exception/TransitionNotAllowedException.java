package io.jstate.spi.exception;

import io.jstate.spi.ProcessInstance;
import io.jstate.spi.State;

public class TransitionNotAllowedException extends RuntimeException {

    private State stateFrom;
    private State stateTo;

    public TransitionNotAllowedException(ProcessInstance processInstance, State stateFrom, State stateTo) {

        super("Transition from '"
                + stateFrom
                + "' to '"
                + stateTo
                + "' is not allowed. Occurred on process instance '"
                + processInstance.getId()
                + "' with process template '"
                + processInstance.getProcessTemplateId()
                + "'");
        this.stateFrom = stateFrom;
        this.stateTo = stateTo;

    }

    public TransitionNotAllowedException(String reason, State stateFrom, State stateTo) {

        super("Transition from '" + stateFrom + "' to '" + stateTo + "' is not allowed. Reason: '" + reason + "'");
        this.stateFrom = stateFrom;
        this.stateTo = stateTo;
    }

    public TransitionNotAllowedException(String reason) {

        super(reason);
    }

    /**
     * Gets the value of the stateFrom property.
     *
     * @return possible object is {@link State}
     */
    public State getStateFrom() {

        return stateFrom;
    }

    /**
     * Gets the value of the stateTo property.
     *
     * @return possible object is {@link State}
     */
    public State getStateTo() {

        return stateTo;
    }
}
