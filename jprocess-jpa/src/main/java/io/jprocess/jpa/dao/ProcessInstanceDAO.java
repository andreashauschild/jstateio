package io.jprocess.jpa.dao;

import io.jprocess.jpa.entity.ProcessInstanceEntity;

import javax.persistence.EntityManager;

public class ProcessInstanceDAO extends BaseDAO<ProcessInstanceEntity> {

    public ProcessInstanceDAO(EntityManager entityManager) {
        super(entityManager, ProcessInstanceEntity.class);
    }
}
