package com.cloud.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * json的辅助工具，将对象转成json串
 * @author cloud7
 *
 */
public class JSONUtil {

	/**
	 * 将model对象转成json串
	 * @param obj
	 * @return
	 */
	public static JSONObject object2JSONObject(Object obj) {
		JSONObject jsonObject = new JSONObject();
		jsonObject = JSONObject.fromObject(obj);
		return jsonObject; 
	}
	
	/**
	 * 将list里的对象转成json串
	 * @param list
	 * @return
	 */
	public static JSONArray list2JSONArray(List list) {
		JSONArray jsonArray = new JSONArray();
		jsonArray = JSONArray.fromObject(list);
		return jsonArray; 
	}
	
	/**
	 * 通过json串转成相应的实体类
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static Object toBean(String json , Class clazz) {
		return JSONObject.toBean(JSONObject.fromObject(json), clazz);
	}
	
	/**
	 * 通过json串转成相应的实体类
	 * @param json
	 * @return
	 */
	public static Object json2Object(String json ,String className) {
		Object obj = new Object();
		JSONObject jo = object2JSONObject(json);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Class clazz = Class.forName(className);						//得到该类的class
			obj = clazz.newInstance();
			
			Field[] field_arr = obj.getClass().getDeclaredFields();
			for(int i=0;i<field_arr.length;i++) {						//遍历所有属性
				String fidledType = field_arr[i].getType().getName();
				Method method = clazz.getMethod("set"+StringUtil.replaceFirstStr2UpperCase(field_arr[i].getName()), new Class[]{field_arr[i].getType()});
				if(fidledType.equals("java.lang.String")) {				//字符串
					method.invoke(obj, new Object[]{new String(StringUtil.null2String(jo.getString(field_arr[i].getName())))});
				} else if(fidledType.equals("int")) {					//整型
					method.invoke(obj, new Object[]{new Integer(jo.getInt(field_arr[i].getName()))});
				} else if(fidledType.equals("java.lang.Integer")) {		//整型
					method.invoke(obj, new Object[]{new Integer(jo.getInt(field_arr[i].getName()))});
				} else if(fidledType.equals("java.util.Date")) {		//日期	
					java.util.Date datePro = DateHelper.getDateByMilles((Long)jo.getJSONObject((field_arr[i].getName())).get("time"));
					method.invoke(obj, new Object[]{datePro});
				}				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			obj = json;
		} catch (InstantiationException e) {
			e.printStackTrace();
			obj = json;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			obj = json;
		} catch (SecurityException e) {
			e.printStackTrace();
			obj = json;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			obj = json;
		} finally {
			return obj;
		}
	}
}
