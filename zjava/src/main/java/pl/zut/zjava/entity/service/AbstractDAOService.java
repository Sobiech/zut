package pl.zut.zjava.entity.service;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pl.zut.zjava.RdbmsConnectionManager;
import pl.zut.zjava.entity.annotation.Column;
import pl.zut.zjava.reflections.EntityHelper;
import pl.zut.zjava.reflections.ReflectionUtils;

public abstract class AbstractDAOService<E> implements DAOService<E> {
	
	/**
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public E getResult(String query,  Class<E> clazz) throws SQLException {
		
		Connection con = RdbmsConnectionManager.GetConnection();
		E instance = null;
		
		try {
		
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while ( resultSet.next() ) {
				if ( resultSet.getMetaData().getColumnCount() > 0 ) {
					instance = reflectResultSet(resultSet, clazz); 
				}
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return instance;
	}

	
	/**
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	/**public List<E> getResultList(String query,  Class<E> clazz, Integer limit) throws SQLException {
		
		Connection con = RdbmsConnectionManager.GetConnection();
		List<E> result = new ArrayList<>();
		
		try {
		
			PreparedStatement statement = con.prepareStatement(query);
			if ( limit != null ) {
				statement.setMaxRows(limit);
			}
			
			ResultSet resultSet = statement.executeQuery();
			
			while ( resultSet.next() ) {
				
				E instance = reflectResultSet(resultSet, clazz); 
				if ( instance != null ) {
					result.add(instance);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	 **/
	
	
	/**
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public Boolean executeQuery(String query) throws SQLException {
		
		Connection con = RdbmsConnectionManager.GetConnection();
		Boolean result = false;
		
		try {
		
			PreparedStatement statement = con.prepareStatement(query);
			result = statement.execute();
			con.commit();
		} catch (SQLException e) {
			
			con.rollback();
		}
		
		return result; 
	}
	

	/**
	 * 
	 * @param resultSet
	 * @param clazz
	 * @return
	 * @throws SQLException
	 */
	private E reflectResultSet(ResultSet resultSet, Class<E> clazz) throws SQLException {
		
		try {
			
			E instance = clazz.newInstance();
			for ( Field field : instance.getClass().getDeclaredFields() ) {
				
				Column column = field.getAnnotation(Column.class);
				if ( column != null ) {
					Object fieldValue = resultSet.getObject(column.name());
					ReflectionUtils.SetField(instance, field.getName(), fieldValue);
				}
			}
			
			for ( Field field : instance.getClass().getSuperclass().getDeclaredFields() ) {
				
				Column column = field.getAnnotation(Column.class);
				if ( column != null ) {
					Object fieldValue = resultSet.getObject(column.name());
					ReflectionUtils.SetField(instance, field.getName(), fieldValue);
				}
			}
			
			return instance;
		} catch (InstantiationException | IllegalAccessException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see pl.zut.zjava.entity.service.DAOService#findById(java.lang.Object, java.lang.Class)
	 */
	@Override
	public E findById(String id, Class<E> clazz) {
		
		E result = null;
		
		try {

			String query = EntityHelper.BuildSelectQuery(String.valueOf(id), clazz);
			result = getResult(query, clazz);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see pl.zut.zjava.entity.service.DAOService#save(java.lang.Object)
	 */
	@Override
	public E save(E entity) {
		
		try {

			String query = EntityHelper.BuildInsertQuery(entity);
			executeQuery(query);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}


	/*
	 * (non-Javadoc)
	 * @see pl.zut.zjava.entity.service.DAOService#delete(java.lang.Object)
	 */
	@Override
	public Boolean delete(E entity) {
		
		try {

			String query = EntityHelper.BuildDeleteQuery(entity);
			return executeQuery(query);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see pl.zut.zjava.entity.service.DAOService#update(java.lang.Object)
	 */
	@Override
	public E update(E entity) {
		
		try {

			String query = EntityHelper.BuildUpdateQuery(entity);
			executeQuery(query);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
}
