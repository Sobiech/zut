package pl.zut.zjava.reflections;

import java.lang.reflect.Field;

public class ReflectionUtils {
	
	/**
	 * 
	 * @param targetObject
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public static boolean SetField(Object targetObject, String fieldName, Object fieldValue) {
	    
		Field field;
	    
		try {
	        field = targetObject.getClass().getDeclaredField(fieldName);
	    } catch (NoSuchFieldException e) {
	        field = null;
	    }
		
	    Class<?> superClass = targetObject.getClass().getSuperclass();
	    while (field == null && superClass != null) {
	    	
	        try {
	        	
	            field = superClass.getDeclaredField(fieldName);
	        } catch (NoSuchFieldException e) {
	        	
	            superClass = superClass.getSuperclass();
	        }
	    }
	    
	    if (field == null) {
	        return false;
	    }
	    
	    field.setAccessible(true);
	    
	    try {
	    	
	        field.set(targetObject, fieldValue);
	        return true;
	    } catch (IllegalAccessException e) {
	    	
	        return false;
	    }
	    
	}
	
	
	
	
}
