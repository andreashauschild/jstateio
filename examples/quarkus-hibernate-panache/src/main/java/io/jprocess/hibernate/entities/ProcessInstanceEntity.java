package io.jprocess.hibernate.entities;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessInstanceEntity extends BaseEntity {



    private String processTemplateId;

    private String reservationId;

    private LocalDateTime reservationTime;

    private LocalDateTime created;

    private LocalDateTime lastUpdate;

    @OrderColumn
    @OneToMany
    @JoinColumn(name = "PROCESS_INSTANCE_ID")
    private List<StateEntity> states;

    private int errorCount = 0;

    private boolean stopped = false;

    /**
     * Overall runtime of the process
     */
    private long runTime;

    /**
     * Sum of time that was need for executing a state
     */
    private long executionTime;

    public Map<String, String> getProperties() {

        Map<String, String> result = new HashMap<>();
        for (StateEntity s : getStates()) {
            result.putAll(s.getProperties());
        }
        return result;
    }

    public <T extends StateEntity> T getCurrentState() {

        if (!getStates().isEmpty()) {
            return (T) getStates().get(getStates().size() - 1);
        }
        return null;
    }

    /**
     * Gets the value of the states property.
     *
     * @return possible object is {@link List<? extends StateEntity>}
     */
    public List<StateEntity> getStates() {

        if (this.states == null) {
            this.states = new ArrayList<>();
        }
        return states;
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
     * @return the {@link ProcessInstanceEntity}
     */
    public ProcessInstanceEntity setReservationId(String reservationId) {

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
     * @return the {@link ProcessInstanceEntity}
     */
    public ProcessInstanceEntity setReservationTime(LocalDateTime reservationTime) {

        this.reservationTime = reservationTime;
        return this;
    }

    /**
     * Sets the value of the states property
     *
     * @param states
     *            allowed object is {@link List< StateEntity> }
     * @return the {@link ProcessInstanceEntity}
     */
    public ProcessInstanceEntity setStates(List<StateEntity> states) {

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
     * @return the {@link ProcessInstanceEntity}
     */
    public ProcessInstanceEntity setProcessTemplateId(String processTemplateId) {

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
     * @return the {@link ProcessInstanceEntity}
     */
    public ProcessInstanceEntity setCreated(LocalDateTime created) {

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
     * @return the {@link ProcessInstanceEntity}
     */
    public ProcessInstanceEntity setLastUpdate(LocalDateTime lastUpdate) {

        this.lastUpdate = lastUpdate;
        return this;
    }

    /**
     * Gets the value of the failCount property.
     *
     * @return possible object is int
     */
    public int getErrorCount() {

        return errorCount;
    }

    /**
     * Sets the value of the failCount property
     *
     * @param errorCount
     *            allowed object is int
     * @return the {@link ProcessInstanceEntity}
     */
    public ProcessInstanceEntity setErrorCount(int errorCount) {

        this.errorCount = errorCount;
        return this;
    }

    /**
     * Gets the value of the runtime property.
     *
     * @return possible object is long
     */
    public long getRunTime() {

        return runTime;
    }

    /**
     * Sets the value of the runtime property
     *
     * @param runTime
     *            allowed object is long
     * @return the {@link ProcessInstanceEntity}
     */
    public ProcessInstanceEntity setRunTime(long runTime) {

        this.runTime = runTime;
        return this;
    }

    /**
     * Gets the value of the executionTime property.
     *
     * @return possible object is long
     */
    public long getExecutionTime() {

        return executionTime;
    }

    /**
     * Sets the value of the executionTime property
     *
     * @param executionTime
     *            allowed object is long
     * @return the {@link ProcessInstanceEntity}
     */
    public ProcessInstanceEntity setExecutionTime(long executionTime) {

        this.executionTime = executionTime;
        return this;
    }

    /**
     * Gets the value of the stopped property.
     *
     * @return possible object is boolean
     */
    public boolean isStopped() {

        return stopped;
    }

    /**
     * Sets the value of the stopped property
     *
     * @param stopped
     *            allowed object is boolean
     * @return the {@link ProcessInstanceEntity}
     */
    public ProcessInstanceEntity setStopped(boolean stopped) {

        this.stopped = stopped;
        return this;
    }
}
