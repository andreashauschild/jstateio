package io.jstate.spi;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessInstance implements Serializable {

    private String id;

    private String processTemplateId;

    private String reservationId;

    private LocalDateTime reservationTime;

    private LocalDateTime created;

    private LocalDateTime lastUpdate;

    private List<State> states;

    private Map<String, String> properties;

    public Map<String, String> getStateProperties() {

        Map<String, String> result = new HashMap<>();
        for (State s : getStates()) {
            result.putAll(s.getProperties());
        }
        return result;
    }

    public <T extends State> T getCurrentState() {

        if (!getStates().isEmpty()) {
            return (T) getStates().get(getStates().size() - 1);
        }
        return null;
    }

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
    public ProcessInstance setProperties(Map<String, String> properties) {

        this.properties = properties;
        return this;
    }

    /**
     * Gets the value of the states property.
     *
     * @return possible object is {@link List<? extends State>}
     */
    public List<State> getStates() {

        if (this.states == null) {
            this.states = new ArrayList<>();
        }
        return states;
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
     * @return the {@link ProcessInstance}
     */
    public ProcessInstance setId(String id) {

        this.id = id;
        return this;
    }

    /**
     * Gets the value of the reservationId property.
     *
     * @return possible object is {@link String}
     */
    public String getReservationId() {

        return reservationId;
    }

    /**
     * Sets the value of the reservationId property
     *
     * @param reservationId
     *            allowed object is {@link String }
     * @return the {@link ProcessInstance}
     */
    public ProcessInstance setReservationId(String reservationId) {

        this.reservationId = reservationId;
        return this;
    }

    /**
     * Gets the value of the reservationTime property.
     *
     * @return possible object is {@link LocalDateTime}
     */
    public LocalDateTime getReservationTime() {

        return reservationTime;
    }

    /**
     * Sets the value of the reservationTime property
     *
     * @param reservationTime
     *            allowed object is {@link LocalDateTime }
     * @return the {@link ProcessInstance}
     */
    public ProcessInstance setReservationTime(LocalDateTime reservationTime) {

        this.reservationTime = reservationTime;
        return this;
    }

    /**
     * Sets the value of the states property
     *
     * @param states
     *            allowed object is {@link List< State> }
     * @return the {@link ProcessInstance}
     */
    public ProcessInstance setStates(List<State> states) {

        this.states = states;
        return this;
    }

    /**
     * Gets the value of the processDefinitionId property.
     *
     * @return possible object is {@link String}
     */
    public String getProcessTemplateId() {

        return processTemplateId;
    }

    /**
     * Sets the value of the processDefinitionId property
     *
     * @param processTemplateId
     *            allowed object is {@link String }
     * @return the {@link ProcessInstance}
     */
    public ProcessInstance setProcessTemplateId(String processTemplateId) {

        this.processTemplateId = processTemplateId;
        return this;
    }

    /**
     * Gets the value of the created property.
     *
     * @return possible object is {@link LocalDateTime}
     */
    public LocalDateTime getCreated() {

        return created;
    }

    /**
     * Sets the value of the created property
     *
     * @param created
     *            allowed object is {@link LocalDateTime }
     * @return the {@link ProcessInstance}
     */
    public ProcessInstance setCreated(LocalDateTime created) {

        this.created = created;
        return this;
    }

    /**
     * Gets the value of the lastUpdate property.
     *
     * @return possible object is {@link LocalDateTime}
     */
    public LocalDateTime getLastUpdate() {

        return lastUpdate;
    }

    /**
     * Sets the value of the lastUpdate property
     *
     * @param lastUpdate
     *            allowed object is {@link LocalDateTime }
     * @return the {@link ProcessInstance}
     */
    public ProcessInstance setLastUpdate(LocalDateTime lastUpdate) {

        this.lastUpdate = lastUpdate;
        return this;
    }

    @Override
    public String toString() {

        return "ProcessInstance{"
                + "id='"
                + id
                + '\''
                + ", processDefinitionId='"
                + processTemplateId
                + '\''
                + ", reservationId='"
                + reservationId
                + '\''
                + ", reservationTime="
                + reservationTime
                + ", created="
                + created
                + ", lastUpdate="
                + lastUpdate
                + ", states="
                + states
                + ", properties="
                + properties
                + '}';
    }
}
