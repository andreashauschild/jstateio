package io.jprocess.jpa.dao;

import io.jprocess.jpa.entity.ProcessInstanceEntity;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseDAO<T> {
    EntityManager entityManager;
    Class entityClass;

    public BaseDAO(EntityManager entityManager, Class<T> clazz) {
        this.entityManager = entityManager;
        this.entityClass = clazz;
    }

    public List<T> list() {
        Query query = entityManager.createQuery("SELECT e FROM " + this.entityClass.getSimpleName() + " e");
        return query.getResultList();
    }

    public List<T> list(int firstResult, int maxResult) {
        Query query = entityManager.createQuery("SELECT e FROM " + this.entityClass.getSimpleName() + " e");
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        return query.getResultList();
    }

    public Optional<T> get(UUID id) {
        return (Optional<T>) Optional.ofNullable(entityManager.find(entityClass, id));
    }


    public Optional<T> get(UUID id, LockModeType lockModeType) {
        return (Optional<T>) Optional.ofNullable(entityManager.find(entityClass, id, lockModeType));
    }

    public List<T> find(String query, Map<String, Object> params) {
        TypedQuery typedQuery = entityManager.createQuery(query, entityClass);
        params.forEach((key, value) -> typedQuery.setParameter(key, value));
        return typedQuery.getResultList();
    }

    public List<T> find(String query, Map<String, Object> params, int firstResult, int maxResult) {
        TypedQuery typedQuery = entityManager.createQuery(query, entityClass);
        params.forEach((key, value) -> typedQuery.setParameter(key, value));
        typedQuery.setFirstResult(firstResult);
        typedQuery.setMaxResults(maxResult);
        return typedQuery.getResultList();
    }

    public void save(T entity) {
        this.entityManager.persist(entity);
    }

    public void update(T entity) {
        this.entityManager.merge(entity);
    }

    public void delete(T entity) {
        this.entityManager.remove(entity);
    }

    public void delete(UUID id) {
        get(id).ifPresent(entity -> this.entityManager.remove(id));
    }


}
