package pl.zut.zjava.entity.service;

public interface DAOService<E> {
	
	public Integer save(E entity); 
	
	public Integer delete(E entity);

	public Integer update(E entity);
	
}