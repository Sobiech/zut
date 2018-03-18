package pl.zut.zjava.reflections;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import pl.zut.zjava.entity.annotation.Column;
import pl.zut.zjava.entity.annotation.Table;

public class EntityHelper {
	
	

	private static final String PARAMETER_SEPARATOR = ",";

	
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <E> String BuildInsertQuery(E obj) throws IllegalArgumentException, IllegalAccessException {
		
		//TODO: typowanie po refleksji do budowania kwerendy
		
		Table table = obj.getClass().getAnnotation(Table.class);
		if ( table == null ) {
			throw new IllegalArgumentException("Given class is not instance of entity");
		}
		
		StringBuilder insertQuery = 
			new StringBuilder("INSERT INTO ")
				.append(table.schema())
				.append(".")
				.append(table.name())
				.append(" ( ");
		
		StringBuilder values = 
			new StringBuilder( " VALUES ( ");
		
		List<Field[]> fieldsList = 
			Arrays.asList( obj.getClass().getDeclaredFields(), obj.getClass().getSuperclass().getDeclaredFields());
		
		fieldsList.forEach( fields -> {
		
			for ( Field field : fields ) {
				
				Column col = field.getAnnotation(Column.class);
				if ( col == null ) {
					continue;
				}
				
				try {
					
					String value = GetValueByField(field, obj);
					if ( value != null ) {
					
						insertQuery
							.append(col.name())
							.append(PARAMETER_SEPARATOR);
						
						if ( !field.getType().equals(String.class) ) {
							values.append(value);
						} else {
							values.append("'").append(value).append("'");
						}
						values.append(PARAMETER_SEPARATOR);
					}
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		});		
		
		String insertParsed = insertQuery.toString().substring(0, insertQuery.length() - 1);
		String valuesParsed = values.toString().substring(0, values.length() - 1);		
				
		insertQuery.append(values);
		return 
			new StringBuilder(insertParsed)
				.append(" )")
				.append(valuesParsed)
				.append(" );")
				.toString();
				
	}
	
	

	/**
	 * 
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <E> String BuildUpdateQuery(E obj) throws IllegalArgumentException, IllegalAccessException {
		
		Table table = obj.getClass().getAnnotation(Table.class);
		if ( table == null ) {
			throw new IllegalArgumentException("Given class is not instance of entity");
		}
		
		StringBuilder updateQuery = 
			new StringBuilder("UPDATE ")
				.append(table.schema())
				.append(".")
				.append(table.name())
				.append(" SET ");
		
		List<Field[]> fieldsList = 
			Arrays.asList( obj.getClass().getDeclaredFields(), obj.getClass().getSuperclass().getDeclaredFields());
		
		fieldsList.forEach( fields -> {
		
			for ( Field field : fields ) {
				
				Column col = field.getAnnotation(Column.class);
				if ( col == null || ( col != null && col.primaryKey() == Column.PRIMARY_KEY )) {
					continue;
				}
				
				try {
					
					String value = GetValueByField(field, obj);
					if ( value != null ) {
					
						updateQuery.append(col.name()).append("=");
						if ( !field.getType().equals(String.class) ) {
							updateQuery.append(value);
						} else {
							updateQuery.append("'").append(value).append("'");
						}
						updateQuery.append(PARAMETER_SEPARATOR);
					}
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		});		
		
		String updateParsed = 
			updateQuery
				.toString()
				.substring(0, updateQuery.length() - 1);
					
		return 
			new StringBuilder(updateParsed)
				.append(";")
				.toString();
				
	}
	
	
	/**
	 * 
	 * @param id
	 * @param clazz
	 * @return
	 */
	public static <E> String BuildSelectQuery(String id, Class<E> clazz) {
		
		Table table = clazz.getAnnotation(Table.class);
		if ( table == null ) {
			throw new IllegalArgumentException("Given object is invalid, requires Table interface");
		}
		
		StringBuilder stb = 
			new StringBuilder("SELECT * FROM ")
				.append(table.schema())
				.append(".")
				.append(table.name())
				.append(" WHERE ");
		
		Field pkField = null;
		Column column = null;
		for ( Field field : clazz.getSuperclass().getDeclaredFields() ) {
			column = field.getAnnotation(Column.class);
			if ( column != null && column.primaryKey() == Column.PRIMARY_KEY ) {
				pkField = field;
				break;
			}
		}
		
		
		if ( pkField == null ) {
			for ( Field field : clazz.getDeclaredFields() ) {
				column = field.getAnnotation(Column.class);
				if (column != null  &&  column.primaryKey() == Column.PRIMARY_KEY ) {
					pkField = field;
					break;
				}
			}
		}
		
		if ( pkField == null ) {
			throw new IllegalArgumentException("No primaryKey found in given object!");
		}

		stb.append(column.name())
		   .append("=");
		
		pkField.setAccessible(true);
		if ( pkField.getType().equals(Integer.class) ) {
			stb.append(id);
		} else { 
			stb.append("'").append(id).append("'");
		}
		
		return stb.append(";").toString();
	}
	
	
	/**
	 * 
	 * @param entity
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static <E> String BuildDeleteQuery(E entity) throws IllegalArgumentException, IllegalAccessException {
		
		Table table = entity.getClass().getAnnotation(Table.class);
		if ( table == null ) {
			throw new IllegalArgumentException("Given object is invalid, requires Table interface");
		}
		
		StringBuilder stb = 
			new StringBuilder("DELETE FROM ")
				.append(table.schema())
				.append(".")
				.append(table.name())
				.append(" WHERE ");
		
		Field pkField = null;
		Column column = null;
		for ( Field field : entity.getClass().getSuperclass().getDeclaredFields() ) {
			column = field.getAnnotation(Column.class);
			if ( column != null && column.primaryKey() == Column.PRIMARY_KEY ) {
				pkField = field;
				break;
			}
		}
		
		if ( pkField == null ) {
			for ( Field field : entity.getClass().getDeclaredFields() ) {
				column = field.getAnnotation(Column.class);
				if ( column != null && column.primaryKey() == Column.PRIMARY_KEY ) {
					pkField = field;
					break;
				}
			}
		}
		
		if ( pkField == null ) {
			throw new IllegalArgumentException("No primaryKey found in given object!");
		}

		stb.append(column.name())
		   .append("=");
		
		pkField.setAccessible(true);
		String str = String.valueOf(pkField.get(entity));
		if ( pkField.getType().equals(Integer.class) ) {
			stb.append(str);
		} else { 
			stb.append("'").append(str).append("'");
		}
		
		return stb.append(";").toString();
	}
	
	
	/**
	 * 
	 * @param field
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private static <E> String GetValueByField(Field field, E obj) 
			throws IllegalArgumentException, IllegalAccessException {
		
		field.setAccessible(true);
		Object fieldValue = field.get(obj);
		return 
			fieldValue != null ? 
				String.valueOf(fieldValue) :
				null;
	}
	
	
}
