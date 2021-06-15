package io.jprocess.hibernate.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STATES")
public class StateEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private ProcessInstanceEntity processInstanceEntity;

    @Column(name = "STATE_NAME")
    private String name;

    @Column(name = "STATE_BEGIN")
    private LocalDateTime begin;

    @Column(name = "STATE_END")
    private LocalDateTime end;

    @OneToMany(
            mappedBy = "state",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<StatePropertiesEntity> properties = new ArrayList<>();

    public String getName() {
        return name;
    }

    public StateEntity setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public StateEntity setBegin(LocalDateTime begin) {
        this.begin = begin;
        return this;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public StateEntity setEnd(LocalDateTime end) {
        this.end = end;
        return this;
    }

    public List<StatePropertiesEntity> getProperties() {
        return properties;
    }

    public StateEntity setProperties(List<StatePropertiesEntity> properties) {
        this.properties = properties;
        return this;
    }

    public ProcessInstanceEntity getProcessInstanceEntity() {
        return processInstanceEntity;
    }

    public StateEntity setProcessInstanceEntity(ProcessInstanceEntity processInstanceEntity) {
        this.processInstanceEntity = processInstanceEntity;
        return this;
    }
}
