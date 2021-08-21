package io.jprocess.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STATE_PROPERTIES")
public class StatePropertiesEntity extends BasePropertyEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private StateEntity state;

    public StatePropertiesEntity(String key, String value) {
        super(key, value);
    }

    public StatePropertiesEntity() {

    }

    public StateEntity getState() {
        return state;
    }

    public StatePropertiesEntity setState(StateEntity state) {
        this.state = state;
        return this;
    }
}
