package com.cloud.base.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TJUtil {

	/**
	 * 得到本月起止日期
	 * @return
	 */
	public static String[] getCurMonthOfDayStartEnd() {
		String[] result = new String[2];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar calStart = Calendar.getInstance();
		calStart.set(Calendar.DAY_OF_MONTH, 1);
		result[0] = sdf.format(calStart.getTime());
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.add(Calendar.MONTH, +1);
		calEnd.set(Calendar.DAY_OF_MONTH, 1);
		calEnd.add(Calendar.DAY_OF_MONTH, -1);
		result[1] = sdf.format(calEnd.getTime());
		return result;
	}
	
	public static String[] getCurYearOfMonthStartEnd() {
		String[] result = new String[2];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calStart = Calendar.getInstance();
		calStart.set(Calendar.MONTH, 0);
		result[0] = sdf.format(calStart.getTime());
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.set(Calendar.MONTH, 11);
		result[1] = sdf.format(calEnd.getTime());
		
		return result;
	}
	
	/**
	 * 得到某一年的起始月份
	 * @param year
	 * @return
	 */
	public static String[] getYearOfMonthStartEnd(String year) {
		String[] result = new String[2];
		try {
			SimpleDateFormat yearSdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Calendar calStart = Calendar.getInstance();
			calStart.setTime(yearSdf.parse(year));
			calStart.set(Calendar.MONTH, 0);
			result[0] = sdf.format(calStart.getTime());
			
			Calendar calEnd = Calendar.getInstance();
			calEnd.setTime(yearSdf.parse(year));
			calEnd.set(Calendar.MONTH, 11);
			result[1] = sdf.format(calEnd.getTime());
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	/**
	 * 从d1得到d2的日期，df为日期格式，yyyy-MM或者yyyy-MM-dd
	 * @param d1
	 * @param d2
	 * @param df
	 * @return
	 */
	public static List<String> getDateListStr(String d1 , String d2 , String df) {
		List list = new ArrayList();
		try {
			list.add(d1);
			SimpleDateFormat sdf = new SimpleDateFormat(df);		
			Calendar cal1 = Calendar.getInstance();		
			Date date1 = sdf.parse(d1);
			cal1.setTime(date1);
			
			Calendar cal2 = Calendar.getInstance();		
			Date date2 = sdf.parse(d2);
			cal2.setTime(date2);
			
			while(!cal1.equals(cal2)) {
				if(df.equals("yyyy-MM")) {
					cal1.add(Calendar.MONTH, +1);
				} else if(df.equals("yyyy-MM-dd")) {
					cal1.add(Calendar.DAY_OF_MONTH, +1);
				}
				list.add(sdf.format(cal1.getTime()));			
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			return list;
		}	
		
	}
	
	/**
	 * 月份加1
	 * @param date
	 * @return
	 */
	public static String addMonthByDate(String date) {
		String result = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date d = sdf.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.MONTH, +1);
			result = sdf.format(cal.getTime());
		} catch (Exception e) {
			
		} finally {
			return result;
		}		
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("本月起止日期:"+getCurMonthOfDayStartEnd()[0] + "——"+getCurMonthOfDayStartEnd()[1]);
		
//		String d1 = "2013-07";		
//		String d2 = "2013-11";
//		String df = "yyyy-MM";
		
		String d1 = "2013-07-01";		
		String d2 = "2013-11-05";
		String df = "yyyy-MM-dd";
		
		List list = new ArrayList();
		
		list.add(d1);
		SimpleDateFormat sdf = new SimpleDateFormat(df);		
		Calendar cal1 = Calendar.getInstance();		
		Date date1 = sdf.parse(d1);
		cal1.setTime(date1);
		
		Calendar cal2 = Calendar.getInstance();		
		Date date2 = sdf.parse(d2);
		cal2.setTime(date2);
		
		while(!cal1.equals(cal2)) {
			if(df.equals("yyyy-MM")) {
				cal1.add(Calendar.MONTH, +1);
			} else if(df.equals("yyyy-MM-dd")) {
				cal1.add(Calendar.DAY_OF_MONTH, +1);
			}
			list.add(sdf.format(cal1.getTime()));			
		}
		list.add(d2);
		System.out.println(list);
	}
}
