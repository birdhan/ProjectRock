package com.cloud.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 将对象转成xml
 * @author cloud7
 *
 */
public class XMLModelUtil {
	
	/**
	 * 将list封装好的对象集合拼接成xml串，xml串中有属性值
	 * @param list
	 * @return
	 */
	public static String toXML4Attribute(List list) {
		return toXML4Attribute(list,null);
	}
	
	/**
	 * 将list封装好的对象集合拼接成xml串，xml串中有属性值
	 * @param list
	 * @param ignorePropertyArr：忽略的列，以字符串数组形式传入
	 * @return
	 */
	public static String toXML4Attribute(List list,String[] ignorePropertyArr) {
		String xml = "";
		if(list != null && list.size() != 0) {								//如果list不为空并且个数不为0
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("root");
			String simpleName = list.get(0).getClass().getSimpleName();		//得到类的文件名字
			Element simpleNames = root.addElement(simpleName+"s");			//root下的标签
			
			for(Object obj : list) {
				Element sn = simpleNames.addElement(simpleName);
				Field[] fileld_arr = obj.getClass().getDeclaredFields();	//得到定义的所有属性数组
				for(Field filed : fileld_arr) {
					boolean flag = true;									//true为显示该字段									
					if(ignorePropertyArr != null && ignorePropertyArr.length != 0) {//过滤忽略字段属性
						for(String ignoreProperty : ignorePropertyArr) {
							if(ignoreProperty.equalsIgnoreCase(filed.getName())) {	//只要忽略的字段
								flag = false;
								break;
							}
						}
					}
					try {
						if(flag) {
							if(filed.getAnnotation(Transient.class) == null) {	//过滤透明字段
								Method m = obj.getClass().getDeclaredMethod("get"+StringUtil.replaceFirstStr2UpperCase(filed.getName()), null);		//取值找到相应的get方法
								Object objValue = m.invoke(obj, null);			//得到该属性的值
								String value = StringUtil.null2String(String.valueOf(objValue));
								if(objValue != null) {
									if(objValue.getClass().getName().equalsIgnoreCase("java.sql.Timestamp") || objValue.getClass().getName().equalsIgnoreCase("java.util.Date")) {
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										value = sdf.format((Date)objValue);
									}
								}
								sn.addAttribute(filed.getName(),value);			//设置xml串的属性
							}
						}											
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}					
				}					
			}
			xml = StringUtil.formatXml(document.asXML());
		}
		return xml;
	}
	
	/**
	 * 将list封装好的对象集合拼接成xml串，xml串中有text值
	 * @param list
	 * @return
	 */
	public static String toXML4Text(List list) {
		return toXML4Text(list,null,false,true,false);
	}
	
	/**
	 * 将list封装好的对象集合拼接成xml串，xml串中有text值
	 * @param list
	 * @param hasRoot：是否有root根标签
	 * @return
	 */
	public static String toXML4Text(List list,boolean hasRoot) {
		return toXML4Text(list,null,hasRoot,true,false);
	}
	
	/**
	 * 将list封装好的对象集合拼接成xml串，xml串中有text值
	 * @param list
	 * @param hasRoot：是否有root根标签
	 * @param hasXML：是否有<?xml>标签
	 * @return
	 */
	public static String toXML4Text(List list,boolean hasRoot,boolean hasXML) {
		return toXML4Text(list,null,hasRoot,hasXML,false);
	}
	
	/**
	 * 将list封装好的对象集合拼接成xml串，xml串中有text值
	 * @param list
	 * @param hasRoot：是否有root根标签
	 * @param hasXML：是否有<?xml>标签
	 * @param firestIsLow：首字母是否是小，默认大写(false)
	 * @return
	 */
	public static String toXML4Text(List list,boolean hasRoot,boolean hasXML,boolean firestIsLow) {
		return toXML4Text(list,null,hasRoot,hasXML,firestIsLow);
	}

	/**
	 * 将list封装好的对象集合拼接成xml串，xml串中有text值
	 * @param list
	 * @param ignorePropertyArr：忽略的列，以字符串数组形式传入
	 * @return
	 */
	public static String toXML4Text(List list,String[] ignorePropertyArr,boolean hasRoot,boolean hasXML,boolean firestIsLow) {
		String xml = "";
		if(list != null && list.size() != 0) {								//如果list不为空并且个数不为0
			Document document = DocumentHelper.createDocument();
			String simpleName = list.get(0).getClass().getSimpleName();		//得到类的文件名字
			if(firestIsLow) {
				simpleName = StringUtil.replaceFirstStr2LowerCase(simpleName);
			}
			
			Element root = null;
			Element sn = null;
			Element simpleNames = null;
			if(hasRoot) {												//有root
				root = document.addElement("root");
				simpleNames = root.addElement(simpleName+"s");			//root下的标签
			} else {													//没有root
				simpleNames = document.addElement(simpleName+"s");			//root下的标签
			}		
			
			
			for(Object obj : list) {
				Element sns = simpleNames.addElement(simpleName);
				Field[] fileld_arr = obj.getClass().getDeclaredFields();	//得到定义的所有属性数组
				for(Field filed : fileld_arr) {
					boolean flag = true;									//true为显示该字段									
					if(ignorePropertyArr != null && ignorePropertyArr.length != 0) {//过滤忽略字段属性
						for(String ignoreProperty : ignorePropertyArr) {
							if(ignoreProperty.equalsIgnoreCase(filed.getName())) {	//只要忽略的字段
								flag = false;
								break;
							}
						}
					}
					
					try {
						if(flag) {
							if(filed.getAnnotation(Transient.class) == null) {	//过滤透明字段
								Element filedEle = sns.addElement(filed.getName());
								Method m = obj.getClass().getDeclaredMethod("get"+StringUtil.replaceFirstStr2UpperCase(filed.getName()), null);		//取值找到相应的get方法
								Object objValue = m.invoke(obj, null);			//得到该属性的值								
								String value = StringUtil.null2String(String.valueOf(objValue));
								if(objValue != null) {
									if(objValue.getClass().getName().equalsIgnoreCase("java.sql.Timestamp") || objValue.getClass().getName().equalsIgnoreCase("java.util.Date")) {
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										value = sdf.format((Date)objValue);
									}
								}
								filedEle.setText(value);						//设置xml值
							}	
						}
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}					
				}					
			}
			xml = StringUtil.formatXml(document.asXML());
			if(hasXML == false) {
				xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "").trim();
			}
		}
		return xml;
	}
	
	/**
	 * 将model类转成xml，默认不显示root根
	 * @param object
	 * @return
	 */
	public static String toXML4Attribute(Object object) {
		return toXML4Attribute(object,null,false);
	}
	
	/**
	 * 将model类转成xml
	 * @param object
	 * @return
	 */
	public static String toXML4Attribute(Object object,boolean hasRoot) {
		return toXML4Attribute(object,null,hasRoot);
	}
	
	/**
	 * 将model转成xml
	 * @param object
	 * @param ignorePropertyArr，需要过滤的属性
	 * @return
	 */
	public static String toXML4Attribute(Object object,String[] ignorePropertyArr,boolean hasRoot) {
		String xml = "";
		Document document = DocumentHelper.createDocument();		
		String simpleName = object.getClass().getSimpleName();		//得到类的文件名字
		
		Element root = null;
		Element sn = null;
		if(hasRoot) {												//有root
			root = document.addElement("root");
			sn = root.addElement(simpleName);
		} else {													//没有root
			sn = document.addElement(simpleName);
		}
		
		Field[] fileld_arr = object.getClass().getDeclaredFields();	//得到定义的所有属性数组
		for(Field filed : fileld_arr) {
			boolean flag = true;									//true为显示该字段									
			if(ignorePropertyArr != null && ignorePropertyArr.length != 0) {//过滤忽略字段属性
				for(String ignoreProperty : ignorePropertyArr) {
					if(ignoreProperty.equalsIgnoreCase(filed.getName())) {	//只要忽略的字段
						flag = false;
						break;
					}
				}
			}
			try {
				if(flag) {
					if(filed.getAnnotation(Transient.class) == null) {	//过滤透明字段
						Method m = object.getClass().getDeclaredMethod("get"+StringUtil.replaceFirstStr2UpperCase(filed.getName()), null);		//取值找到相应的get方法
						Object objValue = m.invoke(object, null);			//得到该属性的值
						String value = StringUtil.null2String(String.valueOf(objValue));
						if(objValue != null) {
							if(objValue.getClass().getName().equalsIgnoreCase("java.sql.Timestamp") || objValue.getClass().getName().equalsIgnoreCase("java.util.Date")) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								value = sdf.format((Date)objValue);
							}
						}
						sn.addAttribute(filed.getName(),value);			//设置xml串的属性
					}
				}											
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}					
		}
		xml = StringUtil.formatXml(document.asXML());
		return xml;
	}
	
	/**
	 * 将list封装好的对象集合拼接成xml串，xml串中有text值
	 * @param list
	 * @return
	 */
	public static String toXML4Text(Object object) {
		return toXML4Text(object,null,false,true,false);
	}

	/**
	 * 将model转xml
	 * @param object
	 * @param hasRoot
	 * @return
	 */
	public static String toXML4Text(Object object,boolean hasRoot) {
		return toXML4Text(object,null,hasRoot,true,false);
	}
	
	/**
	 * 将model转xml
	 * @param object
	 * @param hasRoot
	 * @param hasXML
	 * @return
	 */
	public static String toXML4Text(Object object,boolean hasRoot,boolean hasXML) {
		return toXML4Text(object,null,hasRoot,hasXML,false);
	}
	
	/**
	 * 将model转xml
	 * @param object
	 * @param hasRoot
	 * @return
	 */
	public static String toXML4Text(Object object,boolean hasRoot,boolean hasXML,boolean firestIsLow) {
		return toXML4Text(object,null,hasRoot,hasXML,firestIsLow);
	}
	/**
	 * 将list封装好的对象集合拼接成xml串，xml串中有text值
	 * @param list
	 * @param ignorePropertyArr：忽略的列，以字符串数组形式传入
	 * @return
	 */
	public static String toXML4Text(Object object,String[] ignorePropertyArr,boolean hasRoot,boolean hasXML,boolean firestIsLow) {
		String xml = "";
		Document document = DocumentHelper.createDocument();
		String simpleName = object.getClass().getSimpleName();		//得到类的文件名字
		
		if(firestIsLow) {
			simpleName = StringUtil.replaceFirstStr2LowerCase(simpleName);
		}
		
		Element root = null;
		Element sn = null;
		if(hasRoot) {												//有root
			root = document.addElement("root");
			sn = root.addElement(simpleName);
		} else {													//没有root
			sn = document.addElement(simpleName);
		}
		
		Field[] fileld_arr = object.getClass().getDeclaredFields();	//得到定义的所有属性数组
		for(Field filed : fileld_arr) {
			boolean flag = true;									//true为显示该字段									
			if(ignorePropertyArr != null && ignorePropertyArr.length != 0) {//过滤忽略字段属性
				for(String ignoreProperty : ignorePropertyArr) {
					if(ignoreProperty.equalsIgnoreCase(filed.getName())) {	//只要忽略的字段
						flag = false;
						break;
					}
				}
			}
			
			try {
				if(flag) {
					if(filed.getAnnotation(Transient.class) == null) {	//过滤透明字段
						Element filedEle = sn.addElement(filed.getName());
						Method m = object.getClass().getDeclaredMethod("get"+StringUtil.replaceFirstStr2UpperCase(filed.getName()), null);		//取值找到相应的get方法
						Object objValue = m.invoke(object, null);			//得到该属性的值
						String value = StringUtil.null2String(String.valueOf(objValue));
						if(objValue != null) {
							if(objValue.getClass().getName().equalsIgnoreCase("java.sql.Timestamp") || objValue.getClass().getName().equalsIgnoreCase("java.util.Date")) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								value = sdf.format((Date)objValue);
							}
						}				
						
						filedEle.setText(value);						//设置xml值
					}	
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}					
		}					
		xml = StringUtil.formatXml(document.asXML());
		if(hasXML == false) {
			xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "").trim();
		}
		return xml;
	}
}
