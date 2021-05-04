package io.jstate.model.configuration;

public class Transition {

    private String fromStateId;
    private String toStateId;

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
     * Gets the value of the toStateId property.
     *
     * @return possible object is {@link String}
     */
    public String getToStateId() {

        return toStateId;
    }

    /**
     * Sets the value of the toStateId property
     *
     * @param toStateId
     *            allowed object is {@link String }
     * @return the {@link Transition}
     */
    public Transition setToStateId(String toStateId) {

        this.toStateId = toStateId;
        return this;
    }
}
