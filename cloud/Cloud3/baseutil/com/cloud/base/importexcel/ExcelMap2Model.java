package com.cloud.base.importexcel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.Transient;

import com.cloud.base.annotation.propertydesc.PropertyDesc;
import com.cloud.base.util.DateHelper;
import com.cloud.base.util.StringUtil;

public class ExcelMap2Model {

	/**
	 * 将map对象转成相应的model对象，此方法为通用的方法，不得随意修改
	 * @param rowMap
	 * @param className
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static Object[] excleMap2Model(Map excelMap,Class clazz) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		Object[] result = new Object[4];												//返回值
		result[3] = "";

		boolean flag = true;
		List saveList = new ArrayList();												//即将保存的对象
		List failedList = new ArrayList();												//验证消息的对象
		
		Map fieldMap = new TreeMap();
		Map comparedTitleMap = new HashMap();
		Field[] fieldArr = clazz.getDeclaredFields();									//得到定义的属性
		for(Field field : fieldArr) {													//field.getName()可得到属性值
			Annotation annotation = field.getAnnotation(PropertyDesc.class);			//得到属性上的注解串
			Annotation transientDesc = field.getAnnotation(Transient.class);
			if(annotation != null  && transientDesc == null) {
				String desc = annotation.toString();
				if(!StringUtil.null2String(desc).equals("")) {
					desc = desc.substring(desc.indexOf("name=")+5,desc.length()-1);		//得到属性描述
					fieldMap.put(field.getName(),desc);									//key为model类的属性值，value为中文描述
					comparedTitleMap.put(desc,field.getName());
				} 
			}			
		}
		
		Map temp_title = (Map)excelMap.get(String.valueOf(0));	//表头
		Set<String> key_temp_title = temp_title.keySet();
        for (Iterator it = key_temp_title.iterator(); it.hasNext();) {					//遍历所有表头
        	String s = (String) it.next();
        	String eachColumn = (String)temp_title.get(s);								//每一列的表头值
        	if(!eachColumn.equals("")) {
        		boolean comparedFlag = comparedTitleMap.containsKey(eachColumn);
            	if(!comparedFlag) {
            		flag = false;
            		result[0] = flag;
            		result[1] = null;
            		result[2] = null;
            		result[3] = "您上传不是正确的模板文件";
            		return result;
            	}
        	} 
        }
		
		Map titleMap = new HashMap();													//标题map即将要保存，例如：0=name,1=createTime,2=age。方便与下面的数据体中的key值做属性映射
		int excelRowNum = 2;															//excel起始行号
		for(int i=0;i<excelMap.size();i++) {
			Map rowMap = (Map)excelMap.get(String.valueOf(i));
			if(i == 0) {																//表示是表头
				Set<String> key = rowMap.keySet();
		        for (Iterator it = key.iterator(); it.hasNext();) {						//遍历表头列
		            String s = (String) it.next();
		            Set<String> key_fieldMap = fieldMap.keySet();
		            for(Iterator it_fieldMap = key_fieldMap.iterator(); it_fieldMap.hasNext();) {
		            	String s_fieldMap = (String) it_fieldMap.next();
		            	if(String.valueOf(rowMap.get(s)).equals(String.valueOf(fieldMap.get(s_fieldMap)))) {
		            		titleMap.put(s, s_fieldMap);
		            		break;
			            }
		            }		            
		        }
			} else {																	//表示是数据行
				Class c = Class.forName(clazz.getName());								//要转的类型
	            Object obj_instance = c.newInstance();									//即将保存的对象，存入saveList
	            String[] messgaeObjects = new String[2];								//验证消息的对象，存入failedList
	            messgaeObjects[1] = "";													//验证消息初始值
	            
				Set<String> key = rowMap.keySet();
		        for (Iterator it = key.iterator(); it.hasNext();) {						//遍历每行的数据转成model
		            String s = (String) it.next();										//key值，例：0,1,2....
		            Set<String> key_titleMap = titleMap.keySet();						//遍历titleMap，准备属性映射
		            for(Iterator it_titleMap = key_titleMap.iterator(); it_titleMap.hasNext();) {
		            	String s_titleMap = (String) it_titleMap.next();	
		            	if(s.equals(s_titleMap)) {										//如果两个key相等了，准备向相应的set方法注入值
		            		for(Field field : fieldArr) {								//遍历属性，找到该属性的类型
		            			String proName = field.getName();						//属性名称
		            			if(proName.equals(titleMap.get(s_titleMap))) {			//找到该属性
		            				String proType = field.getGenericType().toString().replaceAll("class ", "");				//属性的类型
		            				String columnValue = (String)rowMap.get(s);			//要设置进属性的值
		            				
		            				String[] valObj = ValidationProperty.validateColVal(field,columnValue); 					//数据验证，验证每个属性是否正确
		            				if(!StringUtil.null2String(valObj[0]).equals("false")) {				//验证标识不为false		            					
		            					if(proType.equals("java.lang.String")) {		//判断属性属于哪个类型的
			            					Method method = c.getMethod("set"+StringUtil.replaceFirstStr2UpperCase(String.valueOf(titleMap.get(s_titleMap))), new Class[]{String.class});
						            		method.invoke(obj_instance, new Object[]{ new String(StringUtil.null2String(columnValue))});			//访问某方法给指定方法设置值
						            		break;
			            				} else if(proType.equals("java.util.Date")) {
			            					Method method = c.getMethod("set"+StringUtil.replaceFirstStr2UpperCase(String.valueOf(titleMap.get(s_titleMap))), new Class[]{java.util.Date.class});			            					
						            		method.invoke(obj_instance, new Object[]{ DateHelper.dateFormat(StringUtil.null2String(columnValue))});
						            		break;
			            				} else if(proType.equals("int")) {
			            					Method method = c.getMethod("set"+StringUtil.replaceFirstStr2UpperCase(String.valueOf(titleMap.get(s_titleMap))), new Class[]{int.class});
						            		method.invoke(obj_instance, new Object[]{ new Integer(Integer.parseInt((StringUtil.null2zero(columnValue))))});
						            		break;
			            				} else if(proType.equals("float")) {
			            					Method method = c.getMethod("set"+StringUtil.replaceFirstStr2UpperCase(String.valueOf(titleMap.get(s_titleMap))), new Class[]{Float.class});
						            		method.invoke(obj_instance, new Object[]{ new Float(Float.parseFloat((StringUtil.null2zero(columnValue))))});
						            		break;
			            				} else if(proType.equals("double")) {
			            					Method method = c.getMethod("set"+StringUtil.replaceFirstStr2UpperCase(String.valueOf(titleMap.get(s_titleMap))), new Class[]{Double.class});
						            		method.invoke(obj_instance, new Object[]{ new Double(Double.parseDouble((StringUtil.null2zero(columnValue))))});
						            		break;
			            				} else if(proType.equals("java.lang.Integer")) {
			            					Method method = c.getMethod("set"+StringUtil.replaceFirstStr2UpperCase(String.valueOf(titleMap.get(s_titleMap))), new Class[]{Integer.class});
						            		method.invoke(obj_instance, new Object[]{ new Integer(Integer.parseInt((StringUtil.null2zero(columnValue))))});
						            		break;
			            				}
			            				break;
		            				} else {											//验证错误
		            					messgaeObjects[1] += StringUtil.null2String(valObj[1].toString());						//拼接验证消息串
		            					flag = false;
		            				}
		            			}
		            		}	            			
		            	}
		            }
		            messgaeObjects[0] = String.valueOf(excelRowNum);					//记录excel行号 
		        }
		        excelRowNum++;                                                          //Excel行号自加
		        
		        if(messgaeObjects[1].equals("")) {										//如果验证消息为空表示数据填写要求都正确
		        	saveList.add(obj_instance);											//将对象存入saveList中
		        } 
		        if(!messgaeObjects[1].equals("")) {										//只有验证消息不为空才会将消息对象添加到集合中去
		        	failedList.add(messgaeObjects);										//将验证消息存入failedList中
		        }		        
			}			
		}
		
		if(saveList.size() == 0 && failedList.size() == 0) {							//表示没有任何数据
			flag = false;
    		result[0] = flag;
    		result[1] = null;
    		result[2] = null;
    		result[3] = "您上传可能没有填写数据，请仔细检查";
    		return result;
		}
		result[0] = flag;
		result[1] = saveList;
		result[2] = failedList;
		
		return result;
	}

}
