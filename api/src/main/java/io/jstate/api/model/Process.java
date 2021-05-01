package io.jstate.api.model;

/**
 * Basic model for a process
 *
 * @author Andreas Hauschild
 */
public class Process {

    private String name;
    private String type;

    /**
     * Gets the value of the name property.
     *
     * @return possible object is {@link String}
     */
    public String getName() {

        return name;
    }

    /**
     * Sets the value of the name property
     *
     * @param name
     *            allowed object is {@link String }
     * @return the {@link Process}
     */
    public Process setName(String name) {

        this.name = name;
        return this;
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is {@link String}
     */
    public String getType() {

        return type;
    }

    /**
     * Sets the value of the type property
     *
     * @param type
     *            allowed object is {@link String }
     * @return the {@link Process}
     */
    public Process setType(String type) {

        this.type = type;
        return this;
    }
}
