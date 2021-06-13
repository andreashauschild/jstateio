package io.jprocess.hibernate.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STATES")
public class StateEntity extends BaseEntity {

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
    private List<StateEntityProperties> properties = new ArrayList<>();

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

    public List<StateEntityProperties> getProperties() {
        return properties;
    }

    public StateEntity setProperties(List<StateEntityProperties> properties) {
        this.properties = properties;
        return this;
    }
}
