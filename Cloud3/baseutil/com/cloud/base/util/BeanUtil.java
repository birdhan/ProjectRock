package com.cloud.base.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.cloud.demo.model.Demo;

/**
 * 克隆对象工具类
 * @author cloud7
 *
 */
public class BeanUtil {

	/**
	 * 静态克隆对象方法
	 * @param obj
	 * @return
	 */
	public static Object cloneBean(Object obj) {
		Object result = null;
		try {
			result = BeanUtils.cloneBean(obj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			LoggerUtil.error(BeanUtil.class, "复制对象异常："+e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			LoggerUtil.error(BeanUtil.class, "复制对象异常："+e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			LoggerUtil.error(BeanUtil.class, "复制对象异常："+e.getMessage());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			LoggerUtil.error(BeanUtil.class, "复制对象异常："+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 使用方法
	 * @param args
	 */
	public static void main(String[] args) {
		Demo d = new Demo();
		d.setDepart("14213412");
		Demo cloneDemo = (Demo)cloneBean(d);
		System.out.println(cloneDemo.getDepart());		
	}
}
