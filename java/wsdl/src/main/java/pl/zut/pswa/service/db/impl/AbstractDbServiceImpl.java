package pl.zut.pswa.service.db.impl;

import org.slf4j.Logger;
import pl.zut.pswa.service.db.AbstractDbService;

import javax.persistence.EntityManager;

public abstract class AbstractDbServiceImpl<T> implements AbstractDbService<T> {

    private Class<T> clazz;

    AbstractDbServiceImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T refresh(T entity) {
        long startTime = System.currentTimeMillis();
        try {
            getEntityManager().refresh(entity);
            return entity;
        } finally {
            getLogger().debug("Refresh in {} [ms]", (System.currentTimeMillis() - startTime) );
        }
    }

    @Override
    public T findById(Object id) {

        long startTime = System.currentTimeMillis();
        try {
            return getEntityManager().find(clazz, id);
        } catch (IllegalArgumentException e) {
            getLogger().error(e.getLocalizedMessage(), e);
            return null;
        } finally {
            getLogger().debug("findById in {} [ms]", (System.currentTimeMillis() - startTime) );
        }
    }

    @Override
    public T saveAndFlush(T entityToSave) {
        long startTime = System.currentTimeMillis();
        try {
            getEntityManager().persist(entityToSave);
            getEntityManager().flush();
            return entityToSave;
        } finally {
            getLogger().debug("Save and flush in {} [ms]", (System.currentTimeMillis() - startTime) );
        }
    }

    @Override
    public T save(T entityToSave) {
        long startTime = System.currentTimeMillis();
        try {
            getEntityManager().persist(entityToSave);
            return entityToSave;
        } finally {
            getLogger().debug("Save in {} [ms]", (System.currentTimeMillis() - startTime) );
        }
    }

    @Override
    public T update(T entityToUpdate) {
        long startTime = System.currentTimeMillis();
        try {
            return getEntityManager().merge(entityToUpdate);
        } finally {
            getLogger().debug("Update in {} [ms]", (System.currentTimeMillis() - startTime));
        }
    }

    @Override
    public T updateAndFlush(T entityToUpdate) {
        long startTime = System.currentTimeMillis();
        try {
            entityToUpdate = getEntityManager().merge(entityToUpdate);
            getEntityManager().flush();
            return entityToUpdate;
        } finally {
            getLogger().debug("Update and flush in {} [ms]", (System.currentTimeMillis() - startTime) );
        }
    }

    @Override
    public void removeAndFlush(Object entityToRemove) {
        long startTime = System.currentTimeMillis();
        try {
            getEntityManager().remove(entityToRemove);
            getEntityManager().flush();
        } finally {
            getLogger().debug("Remove and flush in {} [ms]", (System.currentTimeMillis() - startTime) );
        }
    }

    @Override
    public void remove(Object entityToRemove) {
        long startTime = System.currentTimeMillis();
        try {
            getEntityManager().remove(entityToRemove);
        } finally {
            getLogger().debug("Remove in {} [ms]", (System.currentTimeMillis() - startTime) );
        }
    }

    protected abstract EntityManager getEntityManager();

    protected abstract Logger getLogger();
}
