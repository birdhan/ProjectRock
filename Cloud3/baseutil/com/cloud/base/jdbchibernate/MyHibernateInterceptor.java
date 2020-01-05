package com.cloud.base.jdbchibernate;

import org.hibernate.EmptyInterceptor;

import com.cloud.base.util.LoggerUtil;

/**
 * hibernate拦截器
 * @author cloud7
 *
 */
public class MyHibernateInterceptor extends EmptyInterceptor {

	/**
	 * 可在此处定义其它属性对sql语句进行调整
	 */
	
	
	@Override
	public String onPrepareStatement(String sql) {
		LoggerUtil.info(MyHibernateInterceptor.class, "Interceptor_sql:"+sql);
		return super.onPrepareStatement(sql);
	}
	
}
