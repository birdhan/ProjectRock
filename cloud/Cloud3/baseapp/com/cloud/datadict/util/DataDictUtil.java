package com.cloud.datadict.util;

import java.util.List;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.StringUtil;
import com.cloud.datadict.model.DataDict;

public class DataDictUtil {

	/**
	 * 数据字典value值转显示值
	 * @param dictValue：字典值
	 * @param moduleName：模块名称
	 * @param dictType：字典控件类型
	 * @param property：属性名称
	 * @return
	 */
	public static String value2label(String dictValue,String moduleName,String dictType,String property) {
		String dictLabel = "";
		if(!StringUtil.null2String(dictValue).equals("")) {
			String[] dv_arr = dictValue.split(",");
			StringBuffer sb = new StringBuffer();
			List<DataDict> ddList =  SysCache.getInstance().getDictList();
			for(DataDict dd : ddList) {
				for(String val : dv_arr) {
					if(dd.getModuleName().equals(moduleName) && dd.getDictType().equals(dictType) 
						&& dd.getProperty().equals(property) && dd.getDictValue().equals(val.trim())) {
						if(dictLabel.equals("")) {
							dictLabel += dd.getDictLabel();
						} else {
							dictLabel += ","+dd.getDictLabel();
						}
						
						if(dv_arr.length == dictLabel.split(",").length) {
							break;
						}
					}
				}
			}
			if(dictLabel.equals("")) {
				dictLabel = "<font color='red'>字典类型为：" + dictType + "，属性为："+property+"，模块名称为："+moduleName+"，值为：" +dictValue+ "没有在数据字典中配置</font>";
			}
		} else {
			dictLabel = "";
		}
		return dictLabel;
	}
}
