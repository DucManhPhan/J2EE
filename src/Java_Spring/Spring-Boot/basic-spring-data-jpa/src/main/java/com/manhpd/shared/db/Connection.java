package com.manhpd.shared.db;

import javax.persistence.EntityManager;

public abstract class Connection {

    public abstract EntityManager getEntityManager();

    public abstract void setEntityManager(EntityManager entityManager);

    public <T> T getEntityFromDatabase(Class<T> mappedEntityClass, Object primaryKeyObject) {
        return getEntityManager().find(mappedEntityClass, primaryKeyObject);
    }

}
