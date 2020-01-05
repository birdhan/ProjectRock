package com.cloud.base.annotation.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ ElementType.TYPE, ElementType.METHOD,ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IYTS {
	
	public enum IYtsType {
		util, entity, service, model
	};

	public IYtsType classType() default IYtsType.util;
}
