package pl.zut.zjava.entity.service;

public interface AbstractDbService<T> {

    T refresh(T entity);

    T findById(Object id);

    T saveAndFlush(T entityToSave);

    T save(T entityToSave);

    T update(T entityToUpdate);

    T updateAndFlush(T entityToUpdate);

    void removeAndFlush(Object entityToRemove);

    void remove(Object entityToRemove);


}
