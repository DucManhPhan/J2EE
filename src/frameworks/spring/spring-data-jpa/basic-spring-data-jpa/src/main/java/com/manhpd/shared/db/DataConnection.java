package com.manhpd.shared.db;

import com.manhpd.shared.exception.TargetNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Component
public class DataConnection extends Connection implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insertEntity(Object entity) {
        entityManager.persist(entity);
    }

    public void updateEntity(Object entity) {
        entityManager.merge(entity);
    }

    public <T> void updateWithCheck(T entity, Object primaryKey) {
        this.checkIfExists(entity.getClass(), primaryKey);
        this.updateEntity(entity);
    }

    public void removeEntityById(Class entityClass, Object primaryKey) {
        this.removeEntityFromDatabase(this.getEntityFromDatabase(entityClass, primaryKey));
    }

    public <T> void removeWithCheck(Class<T> entityClass, Object primaryKey) {
        this.checkIfExists(entityClass, primaryKey);
        this.removeEntityById(entityClass, primaryKey);
    }

    public boolean removeEntityFromDatabase(Object entity) {
        entityManager.remove(entity);
        return true;
    }

    private <T> void checkIfExists(Class<T> entityClass, Object primaryKey) {
        if (this.entityManager.find(entityClass, primaryKey) == null) {
            throw new TargetNotFoundException();
        }
    }

    public int update(final String queryString, final List<QueryParameter> parameters) {
        final Query query = entityManager.createQuery(queryString);
        int result = 0;
        if (Objects.nonNull(parameters)) {
            parameters.stream().forEach(p -> query.setParameter(p.getParameterName(), p.getParameterValue()));
        }

        result = query.executeUpdate();
        return result;
    }

    public Query createQuery(String query) {
        return this.entityManager.createQuery(query);
    }

    public Query createNativeQuery(String nativeQuery) {
        return this.entityManager.createNativeQuery(nativeQuery);
    }

    public TypedQuery createQuery(String query, Class clazz) {
        return this.entityManager.createQuery(query, clazz);
    }
}

