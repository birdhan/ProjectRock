package com.cloud.webservice.util;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.Locale;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import com.cloud.base.util.StringUtil;

/**
 * 通用解析xml
 * @author cloud-black
 *
 */
public class DigesterUtil {

	/**
	 * 解析xml后，并返回对象
	 * @param digester
	 * @param xml
	 * @return
	 */
	public static Object parseXML(Digester digester , String xml) {
		
		xml = StringUtil.str2URLDecoder(xml);
		
		String pattern = "yyyy-MM-dd HH:mm:ss";
		Locale locale = Locale.getDefault();
		DateLocaleConverter converter = new DateLocaleConverter(locale, pattern);
		converter.setLenient(true);
		ConvertUtils.register(converter, java.util.Date.class);								//若是日期类型为其自动转换
		
		StringReader xmlReader = new StringReader(xml);
		Object obj = null;
		try {
			obj = digester.parse(xmlReader);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public static Digester initRule(Class clazz) {
		return initRule(clazz,false);
	}
	
	/**
	 * 自动定义xml中text的值与model类的属性值映射
	 * 
	 * XML规则必须为：
	 * <?xml version='1.0' encoding='UTF-8'?>
	 * <Model类>
	 * <属性1>属性1值</属性1>
	 * <属性2>属性2值</属性2>
	 * <属性3>属性3值</属性3>
	 * <属性4>属性4值</属性4>
	 * </Model类>
	 * 
	 * @param clazz
	 * @param firstIsLow：首字母是否为小写
	 * @return
	 */
	public static Digester initRule(Class clazz,boolean firstIsLow) {
		Digester digester = new Digester();
		digester.setValidating(false);
		String gsn = clazz.getSimpleName();
		if(firstIsLow) {
			gsn = StringUtil.replaceFirstStr2LowerCase(clazz.getSimpleName());
		} 
		digester.addObjectCreate(gsn + "/", clazz);
		
		Field[] filedArr = clazz.getDeclaredFields();
		for(Field filed : filedArr) {									//遍历所有属性，为其xml中的text值与model类的属性值映射规则
			digester.addBeanPropertySetter(gsn+"/"+filed.getName(),filed.getName());
		}		
		return digester;
	}
	
}
