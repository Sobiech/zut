package pl.zut.zjava.entity.service.impl;

import org.slf4j.Logger;
import pl.zut.zjava.entity.PersistenceUnitFactory;
import pl.zut.zjava.entity.service.AbstractDbService;

import javax.persistence.EntityManager;

public abstract class AbstractDbServiceImpl<T> implements AbstractDbService<T> {

    private Class<T> clazz;

    private EntityManager entityManager = getEntityManager();

    AbstractDbServiceImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T refresh(T entity) {
        long startTime = System.currentTimeMillis();
        try {
            entityManager.refresh(entity);
            return entity;
        } finally {
            getLogger().debug("Refresh in {} [ms]", (System.currentTimeMillis() - startTime) );
        }
    }

    @Override
    public T findById(Object id) {

        long startTime = System.currentTimeMillis();
        try {

            return entityManager.find(clazz, id);
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
            entityManager.getTransaction().begin();
            entityManager.persist(entityToSave);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return entityToSave;
        } finally {
            getLogger().debug("Save and flush in {} [ms]", (System.currentTimeMillis() - startTime) );
        }
    }

    @Override
    public T save(T entityToSave) {
        long startTime = System.currentTimeMillis();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entityToSave);
            entityManager.getTransaction().commit();
            return entityToSave;
        } finally {
            getLogger().debug("Save in {} [ms]", (System.currentTimeMillis() - startTime) );
        }
    }

    @Override
    public T update(T entityToUpdate) {
        long startTime = System.currentTimeMillis();
        try {
            entityManager.getTransaction().begin();
            entityToUpdate = entityManager.merge(entityToUpdate);
            entityManager.getTransaction().commit();
            return entityToUpdate;
        } finally {
            getLogger().debug("Update in {} [ms]", (System.currentTimeMillis() - startTime));
        }
    }

    @Override
    public T updateAndFlush(T entityToUpdate) {
        long startTime = System.currentTimeMillis();
        try {
            entityManager.getTransaction().begin();
            entityToUpdate = entityManager.merge(entityToUpdate);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return entityToUpdate;
        } finally {
            getLogger().debug("Update and flush in {} [ms]", (System.currentTimeMillis() - startTime) );
        }
    }

    @Override
    public void removeAndFlush(Object entityToRemove) {
        long startTime = System.currentTimeMillis();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityToRemove);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } finally {
            getLogger().debug("Remove and flush in {} [ms]", (System.currentTimeMillis() - startTime) );
        }
    }

    @Override
    public void remove(Object entityToRemove) {
        long startTime = System.currentTimeMillis();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityToRemove);
            entityManager.getTransaction().commit();
        } finally {
            getLogger().debug("Remove in {} [ms]", (System.currentTimeMillis() - startTime) );
        }
    }

    EntityManager getEntityManager() {
        return PersistenceUnitFactory.ZJAVA_UNIT.createEntityManager();
    }

    protected abstract Logger getLogger();
}
