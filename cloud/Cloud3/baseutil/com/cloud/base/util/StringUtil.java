package com.cloud.base.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class StringUtil {

	/**
	 * 将一个字符串的首字母转成大写，例如：userName-->UserName
	 * @param replaceStr:需要转换的字符串
	 * @return
	 */
	public static String replaceFirstStr2UpperCase(String replaceStr) {
		char firstStr = replaceStr.charAt(0);
		String fristStrUpper = (firstStr+"").toUpperCase();
		return replaceStr.replaceFirst((firstStr+""), fristStrUpper);
	}
	
	/**
	 * 将一个字符串的首字母转成小写，例如：User-->user
	 * @param replaceStr:需要转换的字符串
	 * @return
	 */
	public static String replaceFirstStr2LowerCase(String replaceStr) {
		char firstStr = replaceStr.charAt(0);
		String firstStrLower = (firstStr+"").toLowerCase();
		return replaceStr.replaceFirst((firstStr+""), firstStrLower);
	}
	
	/**
	 * 将空或null的字符串转成"0"
	 * @param value
	 * @return
	 */
	public static String null2zero(String value) {
		if(value == null || value.equals("")) {
			return "0";
		} else {
			return value;
		}
	}
	
	/**
	 * 将空值返回一个空字符串
	 * @return
	 */
	public static String null2String(String value) {
		if(value == null || value.equals("null")) {
			return "";
		} else {
			return value;
		}
	}
	
	/**
	 * 如果一个属性是html格式的内容，那么在获取该值的时候就要将其特殊的字符进行转换
	 * @param str
	 * @return
	 */
	public static String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}
	
	/**
	 * 转一个字符串URLDecoder，默认转成UTF-8
	 * @param str
	 * @return
	 */
	public static String str2URLDecoder(String str){
		str = null2String(str);		
		try {
			if(str != null && !str.equals("")) {
				return URLDecoder.decode(str, "UTF-8");
			} else {
				return str;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return str;
		} 
	}
	
	/**
	 * 转一个字符串URLDecoder，默认转成UTF-8
	 * @param str
	 * @return
	 */
	public static String str2URLEncoder(String str){
		str = null2String(str);		
		try {
			if(str != null && !str.equals("")) {
				return URLEncoder.encode(str, "UTF-8");
			} else {
				return str;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return str;
		} 
	}
	
	/**
	 * 处理文件名特殊字符
	 * @param str
	 * @return
	 */
	public static String dealStr(String str) {
		String returnValue = "";
		returnValue = str.replaceAll("ts1", "%23").replaceAll("%5C", "/");	//#
		return returnValue;
	}
	
	/**
	 * 处理文件名特殊字符
	 * @param str
	 * @return
	 */
	public static String dealCarNoStr(String str) {
		String returnValue = "";
		returnValue = str.replaceAll("ts1", "#");	//#
		return returnValue;
	}
	
	public static InputStream String2InputStream(String str){
	    ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
	    return stream;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public boolean matcher(String value,String regPattern) {
		boolean flag = false;
		Pattern p = Pattern.compile(regPattern);
		Matcher m = p.matcher(value);
		flag = m.matches();
		return flag;		
	}
	
	/**
	 * 将空值返回一个空字符串
	 * @return
	 */
	public static String nullStringToChar(String value) {
		if(value == null || value.equals("null") || value.equals("")) {
			return "无";
		} else {
			return value;
		}
	}
	
	public static Object null2null(String value) {
		if(value == null || value.equals("null") || value.equals("")) {
			return null;
		} else {
			return value;
		}
	}
	
	public static String null2nullstr(String value){
		if(value == null || value.equals("")) {
			return "null";
		} else {
			return value;
		}
	}
	
	/**
	 * 格式化xml字符串
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String formatXml(String str){
		Document document = null;
		String result = "";
		try {
			document = DocumentHelper.parseText(str);
			// 格式化输出格式
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setNewLineAfterDeclaration(false);		
			StringWriter writer = new StringWriter();
			// 格式化输出流
			XMLWriter xmlWriter = new XMLWriter(writer, format);
			// 将document写入到输出流
			xmlWriter.write(document);
			xmlWriter.close();
			result = writer.toString();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return result;
		}			
	}
	
	/**
	 * 格式化xml字符串，去掉<?xml?>头
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String formatXmlTrimXML(String str){
		Document document = null;
		String result = "";
		try {
			document = DocumentHelper.parseText(str);
			// 格式化输出格式
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setNewLineAfterDeclaration(false);		
			StringWriter writer = new StringWriter();
			// 格式化输出流	
			XMLWriter xmlWriter = new XMLWriter(writer, format);
			// 将document写入到输出流
			xmlWriter.write(document);
			xmlWriter.close();
			result = writer.toString();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return result.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "").trim();
		}			
	}
}
