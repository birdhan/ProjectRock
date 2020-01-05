package com.cloud.base.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

	public static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat yyyy_MM_dd_HHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat yyyy_MM_dd_HHmmssSSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public static final SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyy-MM");
	public static final SimpleDateFormat yyyyMMddHHmmssWithout_ = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat SSS = new SimpleDateFormat("SSS");
	
	/**
	 * 通过字符串的长度对日期串进行不同的格式化
	 * 
	 * @param dateValue
	 *            :日期�?
	 * @return
	 */
	public static Date dateFormat(String dateValue) {
		Date returnDate = null;
		try {
			if(dateValue.toString().length() == 10) {
				returnDate = yyyy_MM_dd.parse(dateValue);
			} else if(dateValue.toString().length() > 10) {
				returnDate = yyyy_MM_dd_HHmmss.parse(dateValue);
			} else if(dateValue.toString().length() == 7) {
				returnDate = yyyyMM.parse(dateValue);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}
	
	/**
	 * 得到当前时间，返回Date类型
	 * 
	 * @param format
	 * @return
	 */
	public static Date getCurDate(String format) {
		Date returnDate = null;
		try {
			if(format.toString().equalsIgnoreCase("yyyy-MM-dd HH:mm:ss")) {
				returnDate = yyyy_MM_dd.parse(yyyy_MM_dd.format(new Date()));
			} else if(format.toString().length() > 10) {
				returnDate = yyyy_MM_dd_HHmmss.parse(yyyy_MM_dd_HHmmss.format(new Date()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}

	public static Date getCurDateSSS(String format) {
		Date returnDate = null;
		try {
			if(format.toString().equalsIgnoreCase("yyyy-MM-dd HH:mm:ss.SSS")) {
				returnDate = yyyy_MM_dd.parse(yyyy_MM_dd.format(new Date()));
			} else if(format.toString().length() > 10) {
				returnDate = yyyy_MM_dd_HHmmss.parse(yyyy_MM_dd_HHmmss.format(new Date()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}
	public static String getSSSS(Date date){
		if (date==null) {
			return "000";
		}
		return SSS.format(date);
	}
	/**
	 * 通过毫秒数得到日期类型数�?
	 * 
	 * @param milles
	 * @return
	 */
	public static Date getDateByMilles(long milles) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(milles);
		return cal.getTime();
	}

	/**
	 * 获取当前电脑时间返回String类型 创建时间�?
	 * 
	 * 2014-8-21 下午3:00:14
	 * 
	 * @author ljx
	 * @return
	 */
	public static String getComputerNowTime() {
		return yyyy_MM_dd_HHmmss.format(new Date());
	}

	/**
	 * 
	 * 创建时间�?014-8-22
	 * 
	 * 下午2:37:28
	 * 
	 * @author ljx
	 * @return
	 */
	public static String getComputerNowTimeString() {
		String temp_str = "";
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		temp_str = sdf.format(dt);
		return temp_str;
	}
	/**
	 * @author ljx
	 * 
	 *         获取当前时间字符串精确到毫秒
	 */
	public static String getNotimeString() {
		String time = "";
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		time = sdf.format(dt);
		return time;
	}
	
	/**
	 * 向前或向后 增加指定月数
	 * @param date
	 * @param interval
	 * @return
	 */
	public static Date addMonth(Date date, int interval) {
		Calendar c = Calendar.getInstance();
		if (date != null) {
			c.setTime(date);
		}

		c.add(Calendar.MONTH, interval);
		return c.getTime();
	}
	/**
	 * 向前或向后 增加指定天数
	 * @param date
	 * @param interval
	 * @return
	 */
	public static Date addDay(Date date, int interval) {
		Calendar c = Calendar.getInstance();
		if (date != null) {
			c.setTime(date);
		}

		c.add(Calendar.DATE, interval);
		return c.getTime();
	}
	/**
	 * 向前或向后 增加指定分钟
	 * @param date
	 * @param interval
	 * @return
	 */
	public static Date addMinute(Date date, int interval) {
		Calendar c = Calendar.getInstance();
		if (date != null) {
			c.setTime(date);
		}
		
		c.add(Calendar.MINUTE, interval);
		return c.getTime();
	}
	
	/**
	 * 向前或向后 增加指定分钟
	 * @param date
	 * @param interval
	 * @return
	 */
	public static Date addSecond(Date date, int interval) {
		Calendar c = Calendar.getInstance();
		if (date != null) {
			c.setTime(date);
		}

		c.add(Calendar.SECOND, interval);
		return c.getTime();
	}
	
	public static Date addMilliSecond(Date date, int interval) {
		Calendar c = Calendar.getInstance();
		if (date != null) {
			c.setTime(date);
		}

		c.add(Calendar.MILLISECOND, interval);
		return c.getTime();
	}
	
	/**
	 * 向前或向后 增加指定小时
	 * @param date
	 * @param interval
	 * @return
	 */
	public static Date addHour(Date date, int interval) {
		Calendar c = Calendar.getInstance();
		if (date != null) {
			c.setTime(date);
		}

		c.add(Calendar.HOUR_OF_DAY, interval);
		return c.getTime();
	}
	
	public static String getYYYYMMDDHHmmSS(Date date) {
		if(date == null)
			date = new Date();
		return yyyy_MM_dd_HHmmss.format(date);
	}
	public static String getYYYYMMDDHHmmssSS(Date date) {
		if(date == null)
			date = new Date();
		return yyyy_MM_dd_HHmmssSSS.format(date);
	}

	public static String getYYYYMMDDHHmmssSSNull(Date date) {
		if(date == null)
			return "";
		return yyyy_MM_dd_HHmmssSSS.format(date);
	}
	/**
	 * 时间字符串 格式化
	 * @param dateStr
	 * @return
	 */
	public static String getYYYYMMDDHHmmssSSS(String dateStr) {
		Date date=getCurDateSSS(dateStr);
		if(date  == null)
			date = new Date();
		return yyyy_MM_dd_HHmmssSSS.format(date);
	}
	
	/**
	 * 这里是用当前时间 
	 * @param dateStr 这是时间格式！！！
	 * @return
	 */
	public static String getYYYYMMDDHHmmssSS(String dateStr) {
		Date date=getCurDateSSS(dateStr);
		if(date  == null)
			date = new Date();
		return yyyy_MM_dd_HHmmssSSS.format(date);
	}
	
	/**
	 * 增加传入数据库的java.sql.Timestamp转换方法
	 * @param date
	 * @return
	 */
	public static Timestamp getSqlTimestampByDate(Date date){
		return new Timestamp(date.getTime());
	}
	
	/**
	 * 增加传入数据库的java.sql.Timestamp转换方法
	 * @param String
	 * @return 
	 */
	public static Timestamp getSqlTimestampByString(String date){
		Date nowTime = new Date();
		if(!"".equals(date) &&  date!=null){
			try{
				nowTime = dateFormat(date);
			}catch(Exception e){
				LoggerUtil.error(DateHelper.class, "时间格式转换异常");
			}
		}
		return new Timestamp(nowTime.getTime());
	}
	
	/* 
	 * 毫秒转化时分秒毫秒 
	 */  
	public static String formatTime_day_hour_m_s_m(Long ms) {  
	    Integer ss = 1000;  
	    Integer mi = ss * 60;  
	    Integer hh = mi * 60;  
	    Integer dd = hh * 24;  
	  
	    Long day = ms / dd;  
	    Long hour = (ms - day * dd) / hh;  
	    Long minute = (ms - day * dd - hour * hh) / mi;  
	    Long second = (ms - day * dd - hour * hh - minute * mi) / ss;  
	    Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;  
	      
	    StringBuffer sb = new StringBuffer();  
	    if(day > 0) {  
	        sb.append(day+"天");  
	    }  
	    if(hour > 0) {  
	        sb.append(hour+"小时");  
	    }  
	    if(minute > 0) {  
	        sb.append(minute+"分");  
	    }  
	    if(second > 0) {  
	        sb.append(second+"秒");  
	    }  
	    if(milliSecond > 0) {  
	        sb.append(milliSecond+"毫秒");  
	    }  
	    return sb.toString();  
	}  
	
	public static void main(String[] args) {
		String d = "1941279432";
		System.out.println(yyyy_MM_dd_HHmmss.format(getDateByMilles(-1941279236)));
	}
}
