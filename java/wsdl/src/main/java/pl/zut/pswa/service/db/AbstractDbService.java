package pl.zut.pswa.service.db;

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
