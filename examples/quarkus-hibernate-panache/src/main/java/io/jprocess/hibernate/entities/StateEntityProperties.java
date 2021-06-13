package io.jprocess.hibernate.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STATE_PROPERTIES")
public class StateEntityProperties extends BasePropertyEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private StateEntity state;

    public StateEntityProperties(String key, String value) {
        super(key, value);
    }

    public StateEntityProperties() {

    }

    public StateEntity getState() {
        return state;
    }

    public StateEntityProperties setState(StateEntity state) {
        this.state = state;
        return this;
    }
}
