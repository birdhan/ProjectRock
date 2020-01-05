package com.cloud.demo.hibernateinterceptor;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class DemoInterceptor extends EmptyInterceptor {

	
	@Override
	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		System.out.println("onDelete");
		super.onDelete(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		System.out.println("onFlushDirty");
		return super.onFlushDirty(entity, id, currentState, previousState,
				propertyNames, types);
	}

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		System.out.println("onLoad");
		return super.onLoad(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		System.out.println("onSave");
		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public void postFlush(Iterator entities) {
		System.out.println("postFlush");
		super.postFlush(entities);
	}

	@Override
	public void preFlush(Iterator entities) {
		System.out.println("preFlush");
		super.preFlush(entities);
	}

	@Override
	public String onPrepareStatement(String sql) {
		System.out.println("onPrepareStatement : " + sql);
		return super.onPrepareStatement(sql);
	}
}
