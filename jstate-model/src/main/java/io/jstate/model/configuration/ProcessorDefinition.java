package io.jstate.model.configuration;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ProcessorDefinition implements Serializable {

    private String clazz;
    private String description;
    private Map<String, String> properties = new HashMap<>();

    /**
     * Gets the value of the clazz property.
     *
     * @return possible object is {@link String}
     */
    public String getClazz() {

        return clazz;
    }

    /**
     * Sets the value of the clazz property
     *
     * @param clazz
     *            allowed object is {@link String }
     * @return the {@link ProcessorDefinition}
     */
    public ProcessorDefinition setClazz(String clazz) {

        this.clazz = clazz;
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
     * @return the {@link ProcessorDefinition}
     */
    public ProcessorDefinition setDescription(String description) {

        this.description = description;
        return this;
    }

    /**
     * Gets the value of the properties property.
     *
     * @return possible object is {@link Map< String, String>}
     */
    public Map<String, String> getProperties() {

        return properties;
    }

    /**
     * Sets the value of the properties property
     *
     * @param properties
     *            allowed object is {@link Map< String, String> }
     * @return the {@link ProcessorDefinition}
     */
    public ProcessorDefinition setProperties(Map<String, String> properties) {

        this.properties = properties;
        return this;
    }
}
