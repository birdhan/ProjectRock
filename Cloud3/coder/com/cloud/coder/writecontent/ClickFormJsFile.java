package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class ClickFormJsFile {

	public static void write(Map paramMap , String filePath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
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
        		proValidation = proValidation.replace("date:true,", "").replace("date:true", "") + ",maxlength:"+pmc.getColTypeLength();	//过滤日期验证
        		
        		String defaultRequired = "";
        		if(pmc.getProValidation().indexOf("required:true") == -1) {
        			defaultRequired = "required:false,";     			
        		} 
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
        		content.append("			"+pmc.getPro()+":{required:'"+pmc.getProdesc()+"不能为空',maxlength:'最大长度不能超过"+pmc.getColTypeLength()+"'}"+douhao+enter);
        	}
        }
        
        content.append("		}"+enter);
        content.append("	})"+enter);
        content.append("});"+enter);
        
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
