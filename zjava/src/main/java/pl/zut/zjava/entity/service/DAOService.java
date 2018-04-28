package pl.zut.zjava.entity.service;

import java.sql.SQLException;
import java.util.List;

public interface DAOService<E> {
	
	E findById(String obj, Class<E> clazz) throws SQLException;
	
	E save(E entity) throws SQLException;
	
	E update(E entity) throws SQLException, IllegalAccessException;
	
	Boolean delete(E entity) throws IllegalAccessException, SQLException;

	List<E> getList() throws SQLException;
}