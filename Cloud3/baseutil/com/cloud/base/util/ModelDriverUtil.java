package com.cloud.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class ModelDriverUtil {

	/**
	 * 利用java反射原理,将modelMap转成model类
	 * @param modelMap
	 * @param model
	 * @return
	 */
	public static Object modelMap2Model(Map modelMap,String modelType) {
		Object obj_instance = null;
		try {
			Class c = Class.forName(modelType);//java反射获取该类的对象
			obj_instance = c.newInstance();//动态创建这个对象s
			Field[] fieldsArr = obj_instance.getClass().getDeclaredFields();//获取该类下面的所有属性
			Method[] methodsArr = obj_instance.getClass().getDeclaredMethods();//获取该类下面的所有方法
			
			Map paramsMap = new HashMap();//用于保存modelSet方法中所有参数类型
			for(int m=0;m<methodsArr.length;m++) {
				Class[] clazz_paramArr = methodsArr[m].getParameterTypes();//获取指定参数类型
				if(clazz_paramArr != null && clazz_paramArr.length != 0) {
					String paramType = clazz_paramArr[0].getName();//参数类型
					paramsMap.put(paramType, paramType+"");
				}				
			}
			
			//遍历参数map
			Set<String> key = paramsMap.keySet();
	        for (Iterator it = key.iterator(); it.hasNext();) {
	            String s = (String) it.next();
	            if(s.equals("java.lang.String")) {//如果参数中是String类型
	            	for(int j=0;j<fieldsArr.length;j++) {
						Method method = null;
						try {
							method = c.getMethod("set"+StringUtil.replaceFirstStr2UpperCase(fieldsArr[j].getName()), new Class[]{String.class});
							Set<String> keySet = modelMap.keySet();
							for(Iterator itModel = keySet.iterator(); itModel.hasNext();) {
								String modelKey = (String)itModel.next();
								if(fieldsArr[j].getName().equals(modelKey)) {//如果属性值和map的key值相同
									method.invoke(obj_instance, new Object[]{new String(StringUtil.null2String((String)modelMap.get(modelKey)))});//访问某方法给指定方法设置值
								}
							}
						} catch (NoSuchMethodException e) {
							continue;
						} 
					}		
	            } else if(s.equals("float")) {//如果参数中是float类型
	            	for(int j=0;j<fieldsArr.length;j++) {
						Method method = null;
						try {
							method = c.getMethod("set"+StringUtil.replaceFirstStr2UpperCase(fieldsArr[j].getName()), new Class[]{float.class});
							Set<String> keySet = modelMap.keySet();
							for(Iterator itModel = keySet.iterator(); itModel.hasNext();) {
								String modelKey = (String)itModel.next();
								if(fieldsArr[j].getName().equals(modelKey)) {
									method.invoke(obj_instance, new Object[]{new Float(Float.valueOf(StringUtil.null2zero((String)modelMap.get(modelKey))))});//访问某方法给指定方法设置值
								}
							}
						} catch (NoSuchMethodException e) {
							continue;
						}
					}
	            }else if(s.equals("java.lang.Integer")) {//如果参数中是Integer类型
	            	for(int j=0;j<fieldsArr.length;j++) {
						Method method = null;
						try {
							method = c.getMethod("set"+StringUtil.replaceFirstStr2UpperCase(fieldsArr[j].getName()), new Class[]{Integer.class});
							Set<String> keySet = modelMap.keySet();
							for(Iterator itModel = keySet.iterator(); itModel.hasNext();) {
								String modelKey = (String)itModel.next();
								if(fieldsArr[j].getName().equals(modelKey)) {
									method.invoke(obj_instance, new Object[]{new Integer(Integer.parseInt((StringUtil.null2zero((String)modelMap.get(modelKey)))))});//访问某方法给指定方法设置值
								}
							}
						} catch (NoSuchMethodException e) {
							continue;
						}
					}
	            }else if(s.equals("java.util.Date")) {//如果参数中是日期类型
	            	for(int j=0;j<fieldsArr.length;j++) {
						Method method = null;
						try {
							method = c.getMethod("set"+StringUtil.replaceFirstStr2UpperCase(fieldsArr[j].getName()), new Class[]{java.util.Date.class});
							Set<String> keySet = modelMap.keySet();
							for(Iterator itModel = keySet.iterator(); itModel.hasNext();) {
								String modelKey = (String)itModel.next();
								if(fieldsArr[j].getName().equals(modelKey)) {
									method.invoke(obj_instance, new Object[]{ DateHelper.dateFormat(StringUtil.null2String((String)modelMap.get(modelKey)))});
								}
							}
						} catch (NoSuchMethodException e) {
							continue;
						}
					}
	            } else if(s.equals("double")) {//如果参数中是double类型
	            	for(int j=0;j<fieldsArr.length;j++) {
						Method method = null;
						try {
							method = c.getMethod("set"+StringUtil.replaceFirstStr2UpperCase(fieldsArr[j].getName()), new Class[]{double.class});
							Set<String> keySet = modelMap.keySet();
							for(Iterator itModel = keySet.iterator(); itModel.hasNext();) {
								String modelKey = (String)itModel.next();
								if(fieldsArr[j].getName().equals(modelKey)) {
									method.invoke(obj_instance, new Object[]{new Double(Double.valueOf(StringUtil.null2zero((String)modelMap.get(modelKey))))});//访问某方法给指定方法设置值
								}
							}
						} catch (NoSuchMethodException e) {
							continue;
						}
					}
	            }	            
	        }
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj_instance;
	}
	
	/**
	 * 将一行的rowMap转成modelMap,key值由原来的数据变为属性值为keys
	 * @param model
	 * @param rowMap
	 * @return
	 */
	public static Map rowMap2ModelMap(Map rowMap,Object model) {
		Map modelMap = new TreeMap();
		Field[] fieldArr = model.getClass().getDeclaredFields();
		int field_startIndex = 0;		
		if(fieldArr[0].getName().equals("id")) {//如果第一个元素是id
			field_startIndex = 1;
		}
		for(int i=0;i<rowMap.size();i++) {
			
			String value = (String)rowMap.get(String.valueOf(i));//获取i列的值			
			String fieldName = fieldArr[field_startIndex].getName();//因为model属性第一个属性是id,所以model类要从第二个开始匹配
			modelMap.put(fieldName, value);
			field_startIndex++;
		}
		return modelMap;
	}
}
