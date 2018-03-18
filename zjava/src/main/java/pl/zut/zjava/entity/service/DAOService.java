package pl.zut.zjava.entity.service;

public interface DAOService<E> {
	
	public E findById(String obj, Class<E> clazz);
	
	public E save(E entity); 
	
	public E update(E entity);
	
	public Boolean delete(E entity);
	
}