package io.jstate.spi;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class State implements Serializable {

    private String id;
    private String name;

    private LocalDateTime begin;
    private LocalDateTime end;

    Map<String, String> properties;

    /**
     * Gets the value of the properties property.
     *
     * @return possible object is {@link Map< String, String>}
     */
    public Map<String, String> getProperties() {

        if (this.properties == null) {
            this.properties = new HashMap<>();
        }
        return properties;
    }

    /**
     * Sets the value of the properties property
     *
     * @param properties
     *            allowed object is {@link Map< String, String> }
     * @return the {@link State}
     */
    public State setProperties(Map<String, String> properties) {

        this.properties = properties;
        return this;
    }

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
     * Gets the value of the begin property.
     *
     * @return possible object is {@link LocalDateTime}
     */
    public LocalDateTime getBegin() {

        return begin;
    }

    /**
     * Sets the value of the begin property
     *
     * @param begin
     *            allowed object is {@link LocalDateTime }
     * @return the {@link State}
     */
    public State setBegin(LocalDateTime begin) {

        this.begin = begin;
        return this;
    }

    /**
     * Gets the value of the end property.
     *
     * @return possible object is {@link LocalDateTime}
     */
    public LocalDateTime getEnd() {

        return end;
    }

    /**
     * Sets the value of the end property
     *
     * @param end
     *            allowed object is {@link LocalDateTime }
     * @return the {@link State}
     */
    public State setEnd(LocalDateTime end) {

        this.end = end;
        return this;
    }
}
