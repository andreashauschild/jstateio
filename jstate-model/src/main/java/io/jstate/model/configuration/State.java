package io.jstate.model.configuration;

import java.util.ArrayList;
import java.util.List;

public class State {

    private String id;
    private String name;
    private List<String> processors = new ArrayList<>();

    /**
     * Gets the value of the id property.
     *
     * @return possible object is {@link String}
     */
    public String getId() {

        return id;
    }

    /**
     * Sets the value of the id property
     *
     * @param id
     *            allowed object is {@link String }
     * @return the {@link State}
     */
    public State setId(String id) {

        this.id = id;
        return this;
    }

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
     * @return the {@link State}
     */
    public State setName(String name) {

        this.name = name;
        return this;
    }

    /**
     * Gets the value of the processors property.
     *
     * @return possible object is {@link List< String>}
     */
    public List<String> getProcessors() {

        return processors;
    }

    /**
     * Sets the value of the processors property
     *
     * @param processors
     *            allowed object is {@link List< String> }
     * @return the {@link State}
     */
    public State setProcessors(List<String> processors) {

        this.processors = processors;
        return this;
    }
}
