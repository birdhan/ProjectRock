package com.cloud.systemconfig.sort;

import java.util.Comparator;
import java.util.Map;

/**
 * 排序数据库备份文件内部类
 * @author cloud7
 *
 */
public class SortByCreateTime implements Comparator<Map> {
	
	public int compare(Map s1,Map s2) {
		Long l1 = (Long)s1.get("time");
		Long l2 = (Long)s2.get("time");
		return l2.intValue() - l1.intValue();
	}
}
