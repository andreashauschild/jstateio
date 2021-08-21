package io.jprocess.jpa.dao;

import io.jprocess.jpa.entity.ProcessInstanceEntity;
import io.jprocess.jpa.entity.ProcessTemplateEntity;

import javax.persistence.EntityManager;

public class ProcessTemplateDAO extends BaseDAO<ProcessTemplateEntity> {

    public ProcessTemplateDAO(EntityManager entityManager) {
        super(entityManager, ProcessTemplateEntity.class);
    }
}
