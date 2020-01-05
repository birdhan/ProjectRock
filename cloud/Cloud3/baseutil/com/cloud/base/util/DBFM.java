package com.cloud.base.util;

import com.cloud.base.cache.SysCache;

/**
 * 数据库函数映射，进而实现跨数据库使用各个数据库中不同的函数，此类中的方法命名是以oracle函数名称为例
 * 此工具类仅仅为数据库函数名之间的转换
 * DataBaseFunctionMapping的缩写
 * @author mac-cloud
 *
 */
public class DBFM {

	/**
	 * 将纵向查询出的数据以“逗号”分隔拼成横向数据
	 * 例如：查询结果为
	 *       1
	 *       2
	 *       3
	 *       4
	 * 调用数据库函数后，结果为1,2,3,4
	 * @return
	 */
	public static String WM_CONCAT() {
		String funName = "";
		String dataBase = SysCache.getInstance().getDataBase().toLowerCase();
		if(dataBase.equalsIgnoreCase("mysql")) {
			funName = "group_concat";
		} else if(dataBase.equalsIgnoreCase("oracle")) {
			funName = "WMSYS.WM_CONCAT";
		}
		return funName;
	}
	
	/**
	 * 将字符转换成日期函数
	 * mysql日期格式：%Y-%m-%d %H:%i:%s
	 * oracle日期格式：yyyy-mm-dd hh24:mi:ss
	 * @return
	 */
	public static String TO_DATE() {
		String funName = "";
		String dataBase = SysCache.getInstance().getDataBase().toLowerCase();
		if(dataBase.equalsIgnoreCase("mysql")) {
			funName = "STR_TO_DATE";
		} else if(dataBase.equalsIgnoreCase("oracle")) {
			funName = "TO_DATE";
		}
		return funName;
	}
	
	/**
	 * 日期转换成字符函数
	 * @return
	 */
	public static String TO_CHAR() {
		String funName = "";
		String dataBase = SysCache.getInstance().getDataBase().toLowerCase();
		if(dataBase.equalsIgnoreCase("mysql")) {
			funName = "DATE_FORMAT";
		} else if(dataBase.equalsIgnoreCase("oracle")) {
			funName = "TO_CHAR";
		}
		return funName;
	}
}
