package io.jstate.core.services.io.jstate.core.query;

import io.jstate.spi.ProcessInstanceQuery;

public class FindByCurrentState implements ProcessInstanceQuery {

    String state;

    public FindByCurrentState(String state) {

        this.state = state;
    }

    /**
     * Gets the value of the state property.
     *
     * @return possible object is {@link String}
     */
    public String getState() {

        return state;
    }
}
