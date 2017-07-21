package com.sixin.controller.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME) 
public @interface DBField {

	/**
	 * 标注的字段所对应的列名
	 * @return
	 */
	public String columnName() default "";
	
	/**
	 * 标注的字段对应的数据库版本号
	 * @return
	 */
	public int version() default 1;
	
	/**
	 * 字段类型
	 * @return
	 */
	public String type() default " text ";
}
