package io.jstate.model.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transition implements Serializable {

    private String fromState;
    private List<String> toState = new ArrayList<>();

    /**
     * Gets the value of the fromStateId property.
     *
     * @return possible object is {@link String}
     */
    public String getFromState() {

        return fromState;
    }

    /**
     * Sets the value of the fromStateId property
     *
     * @param fromState
     *            allowed object is {@link String }
     * @return the {@link Transition}
     */
    public Transition setFromState(String fromState) {

        this.fromState = fromState;
        return this;
    }

    /**
     * Gets the value of the toStateIds property.
     *
     * @return possible object is {@link List< String>}
     */
    public List<String> getToState() {

        return toState;
    }

    /**
     * Sets the value of the toStateIds property
     *
     * @param toState
     *            allowed object is {@link List< String> }
     * @return the {@link Transition}
     */
    public Transition setToState(List<String> toState) {

        this.toState = toState;
        return this;
    }
}
