package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class ClickListJsFile {

	public static void write(Map paramMap , String filePath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
        content.append("var ctx = getRootPath();"+enter+enter);
        
        content.append("$().ready(function() {"+enter);
        content.append("	$(\"#"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Form\").validate({"+enter);
        content.append("		rules: {//验证规则"+enter);
        
        for(int i=0;i<pmcList.size();i++) {
        	ProMappingCol pmc = pmcList.get(i);
        	String douhao = ",";
        	if(i == pmcList.size()-1) {
        		douhao = "";
        	}
        	if(!pmc.getPro().equals("id")) {
        		String proValidation = pmc.getProValidation();
        		proValidation = proValidation.replace("date:true,", "").replace("date:true", "").replace("required:true,", "").replace("required:true", "") + ",maxlength:"+pmc.getColTypeLength();	//过滤日期验证
        		
        		String defaultRequired = "required:false,";
        		String val = defaultRequired+proValidation;
        		val = val.replaceAll(",,", ",");
        		
        		String normalText = "";
        		if(pmc.getJspInput().equals("text") || pmc.getJspInput().equals("textarea")) {
        			 normalText = ",normalText:true";
        		}
        		content.append("			"+pmc.getPro()+":{"+val+normalText+"}"+douhao+enter);
        	}
        }
        
        content.append("		},"+enter);
        content.append("		messages:{//验证消息内容"+enter);
        for(int i=0;i<pmcList.size();i++) {
        	ProMappingCol pmc = pmcList.get(i);
        	String douhao = ",";
        	if(i == pmcList.size()-1) {
        		douhao = "";
        	}
        	if(!pmc.getPro().equals("id")) {
        		String proValidation = pmc.getProValidation();
        		content.append("			"+pmc.getPro()+":{maxlength:'最大长度不能超过"+pmc.getColTypeLength()+"'}"+douhao+enter);
        	}
        }
        
        content.append("		}"+enter);
        content.append("	})"+enter);
        content.append("});"+enter+enter);
        
        content.append("/**"+enter);
        content.append(" * 数据导入"+enter);
        content.append(" */"+enter);
        content.append("var importWin;"+enter);
        content.append("function importData() {"+enter);
        content.append("	importWin = $.ligerDialog.open("+enter);
        content.append("		{title:'"+coder.getModelDesc()+"数据导入', url: ctx+'/"+coder.getNameSpace()+"/open"+coder.getModelName()+"Import.do', height: 265, width: 370, name:'importIframe',isResize: true}"+enter);
        content.append("	);"+enter);
        content.append("}"+enter+enter);
        
        content.append("/**"+enter);
        content.append(" * 关闭导入窗口"+enter);
        content.append(" */"+enter);
        content.append("function closeImportWin() {"+enter);
        content.append("	if(importWin != null) {"+enter);
        content.append("		importWin.close();"+enter);
        content.append("	}"+enter);
        content.append("}"+enter+enter);
        
        content.append("/**"+enter);
        content.append(" * 数据导出"+enter);
        content.append(" */"+enter);
        content.append("var exportWin;"+enter);
        content.append("function exportData() {"+enter);
        content.append("	var ids = \"\";"+enter);
        content.append("	var objs = document.getElementsByName(\"ids\");"+enter);
        content.append("	for(var i=0; i<objs.length; i++) {"+enter);
        content.append("		if(objs[i].type.toLowerCase() == \"checkbox\") {"+enter);
        content.append("			if(objs[i].checked == true) {"+enter);
        content.append("				ids += objs[i].value+\",\";"+enter);
        content.append("			}"+enter);
        content.append("		}"+enter);
        content.append("	}"+enter);
        content.append("	if(ids.Trim() != \"\") {"+enter);
        content.append("		ids = ids.substring(0,ids.length-1);"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	exportWin = $.ligerDialog.open("+enter);
        content.append("		{title:'"+coder.getModelDesc()+"数据导出', url: ctx+'/"+coder.getNameSpace()+"/open"+coder.getModelName()+"Export.do?ids='+ids, height: 340, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}"+enter);
        content.append("	);"+enter);
        content.append("}"+enter+enter);
        
        content.append("/**"+enter);
        content.append(" * 关闭导出窗口"+enter);
        content.append(" */"+enter);
        content.append("function colseExportWin() {"+enter);
        content.append("	if(exportWin != null) {"+enter);
        content.append("		exportWin.close();"+enter);
        content.append("	}"+enter);
        content.append("}"+enter);
        
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
