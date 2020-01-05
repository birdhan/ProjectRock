package com.cloud.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.cloud.base.cache.SysCache;

public class DateHelper {

	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 通过字符串的长度对日期串进行不同的格式化
	 * @param dateValue:日期值
	 * @return
	 */
	public static Date dateFormat(String dateValue) {
		Date returnDate = null;
		try {
			if(dateValue.toString().length() == 10) {
				returnDate = sdf1.parse(dateValue);
			} else if(dateValue.toString().length() > 10) {
				returnDate = sdf2.parse(dateValue);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}
	
	/**
	 * 得到当前时间，返回Date类型
	 * @param format
	 * @return
	 */
	public static Date getCurDate(String format) {
		Date returnDate = null;
		try {
			if(format.toString().equalsIgnoreCase("yyyy-MM-dd HH:mm:ss")) {
				returnDate = sdf1.parse(sdf1.format(new Date()));
			} else if(format.toString().length() > 10) {
				returnDate = sdf2.parse(sdf2.format(new Date()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}
	
	/**
	 * 通过毫秒数得到日期类型数据
	 * @param milles
	 * @return
	 */
	public static Date getDateByMilles(long milles) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(milles);
		return cal.getTime();
	}
	
	/**
	 * 自动转换日期格式函数
	 * @param dateType
	 * @return
	 */
	public static String autoChangDateType(String dateType) {
		String dataBaseType = SysCache.getInstance().getDataBase();						//数据库类型
		if(dataBaseType.equals("mysql")) {												//如果数据库是mysql
			if(dateType.equals("yyyy-MM-dd")) {
				dateType = "%Y-%m-%d";
			} else if(dateType.equals("yyyy-MM")) {
				dateType = "%Y-%m";
			} else if(dateType.equals("yyyy")) {
				dateType = "%Y";
			} else if(dateType.equals("yyyy-MM-dd HH:mm:ss")) {
				dateType = "%Y-%m-%d %H-%i-%S";
			} else if(dateType.equals("yyyy-MM-dd HH:mm")) {
				dateType = "%Y-%m-%d %H-%i";
			} else if(dateType.equals("yyyy-MM-dd HH")) {
				dateType = "%Y-%m-%d %H";
			}
		}
		return dateType;
	}
	
}
