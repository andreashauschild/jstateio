package io.jstate.model.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transition implements Serializable {

    private String fromStateId;
    private List<String> toStateIds = new ArrayList<>();

    /**
     * Gets the value of the fromStateId property.
     *
     * @return possible object is {@link String}
     */
    public String getFromStateId() {

        return fromStateId;
    }

    /**
     * Sets the value of the fromStateId property
     *
     * @param fromStateId
     *            allowed object is {@link String }
     * @return the {@link Transition}
     */
    public Transition setFromStateId(String fromStateId) {

        this.fromStateId = fromStateId;
        return this;
    }

    /**
     * Gets the value of the toStateIds property.
     *
     * @return possible object is {@link List< String>}
     */
    public List<String> getToStateIds() {

        return toStateIds;
    }

    /**
     * Sets the value of the toStateIds property
     *
     * @param toStateIds
     *            allowed object is {@link List< String> }
     * @return the {@link Transition}
     */
    public Transition setToStateIds(List<String> toStateIds) {

        this.toStateIds = toStateIds;
        return this;
    }
}
