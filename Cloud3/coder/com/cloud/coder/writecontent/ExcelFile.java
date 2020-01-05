package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.ExcelUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class ExcelFile {

	public static void write(Map paramMap , String filePath,String fileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");
		
		List columnList = new ArrayList();
		for(ProMappingCol pmc : pmcList) {
			if(pmc.getIsImport().equals("yes")) {
				columnList.add(pmc.getProdesc());
			}
		}
		if(columnList.size() == 0) {
			columnList.add("没有需要录入的任何字段");
		}
		ExcelUtil.writeExcelTemplate(filePath, fileName, "模板", columnList);
	}
}
