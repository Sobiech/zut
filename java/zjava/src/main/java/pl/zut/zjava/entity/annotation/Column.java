package pl.zut.zjava.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	
	public static final int PRIMARY_KEY = 0;
	
	public static final int IS_NOT_PRIMARY_KEY = 1;
	
	
	String name();
	
	String description();
	
	/**
	 * 0 - TRUE
	 * 1 - FALSE
	 * 
	 * @return
	 */
	int primaryKey() default IS_NOT_PRIMARY_KEY;
	
}
