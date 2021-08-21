package io.jprocess.jpa.dao;

import io.jprocess.jpa.entity.ProcessInstanceEntity;
import io.jprocess.jpa.entity.StateEntity;

import javax.persistence.EntityManager;

public class StateDAO extends BaseDAO<StateEntity> {

    public StateDAO(EntityManager entityManager) {
        super(entityManager, StateEntity.class);
    }
}
