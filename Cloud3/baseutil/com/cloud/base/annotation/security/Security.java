package com.cloud.base.annotation.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface Security {
	public String privName() default "DEFAULT";			//权限名称
		
	public String privKey() default "DEFAULT";				//权限编号
	
	public String roleType() default "COMMON";				//默认是公用URL ，只允许写2种值，COMMON(公用)和PRIV(权限)
}
