package io.jstate.model.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StateDefinition implements Serializable {

    private String id;

    private String description;

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
     * @return the {@link StateDefinition}
     */
    public StateDefinition setId(String id) {

        this.id = id;
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
     * @return the {@link StateDefinition}
     */
    public StateDefinition setProcessors(List<String> processors) {

        this.processors = processors;
        return this;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is {@link String}
     */
    public String getDescription() {

        return description;
    }

    /**
     * Sets the value of the description property
     *
     * @param description
     *            allowed object is {@link String }
     * @return the {@link StateDefinition}
     */
    public StateDefinition setDescription(String description) {

        this.description = description;
        return this;
    }
}
