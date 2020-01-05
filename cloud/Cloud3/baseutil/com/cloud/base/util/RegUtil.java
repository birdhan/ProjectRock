package com.cloud.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * @author cloud7
 *
 */
public class RegUtil {
	
	/**
	 * 验证日期
	 * @param value
	 * @return
	 */
	public static boolean isDate(String value) {
		Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher matcher = pattern.matcher(value);
		
		Pattern pattern2 = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))))?$");
		Matcher matcher2 = pattern2.matcher(value);
		
		Pattern pattern3 = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])))?$");
		Matcher matcher3 = pattern3.matcher(value);
		
		return matcher.matches() || matcher2.matches() || matcher3.matches();
	}
	
	/**
	 * 验证数字
	 * @param value
	 * @return
	 */
	public static boolean isNum(String value) {
		Pattern pattern = Pattern.compile("^[0-9]+\\.{0,1}[0-9]{0,2}$");
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
	
	/**
	 * 验证长度不允许超过某长度
	 * @param value
	 * @return
	 */
	public static boolean isMoreThan(String value,int length) {
		if(value.length() > length) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 验证非0开头的整数
	 * @param value
	 * @return
	 */
	public static boolean isInt(String value) {
		Pattern pattern = Pattern.compile("^(0|[1-9][0-9]*)$");
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
	
	/**
	 * 验证是否为邮箱
	 * @param value
	 * @return
	 */
	public static boolean isEamil(String value) {
		Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
	
	/**
	 * 验证IP不带端口号
	 * @param value
	 * @return
	 */
	public static boolean isIPNoPort(String value) {
		Pattern pattern = Pattern.compile("^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])*$");
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Pattern pattern = Pattern.compile("^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])*$");
//		Matcher matcher = pattern.matcher("192.168.138.111");
//		System.out.println(matcher.matches());
		System.out.println(isDate("2014-01-01 12:11"));	
		//System.out.println(isMoreThan("中从轻2",3));
	}

}
