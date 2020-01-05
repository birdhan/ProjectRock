package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class ClickImportJsFile {

	public static void write(Map paramMap , String filePath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
        content.append("/**"+enter);
        content.append(" * 将选择的文件上传"+enter);
        content.append(" */"+enter);
        
        content.append("function uploadFile() {"+enter);
        content.append("	var importFile = $(\"#importExcelFileAll\").val().Trim();"+enter);
        content.append("	if(importFile.length != 0) {																	//表示文件不空"+enter);
        content.append("		var extendName = importFile.substring(importFile.indexOf(\".\")+1);							//得到文件扩展名"+enter);
        content.append("		if(extendName.toLowerCase() != \"xls\") {														//表示上传的不是xls文件"+enter);
        content.append("			getEleById(\"fileDivInfo\").innerHTML = \"<font color='red'>请上传正确的模板文件</font>\";"+enter);
        content.append("		} else {"+enter);
        content.append("			getEleById(\"fileDivInfo\").innerHTML = \"<font color='#009966'>数据上传中，请您耐心等候...</font>\";"+enter);
        content.append("			getEleById(\"importForm\").submit();"+enter);
        content.append("			getEleById(\"importBtn\").disabled = \"disabeld\";"+enter);
        content.append("			getEleById(\"importBtn\").title = \"正在导入数据...\";"+enter);
        content.append("		}"+enter);
        content.append("	} else {																						//表示文件为空"+enter);
        content.append("		getEleById(\"fileDivInfo\").innerHTML = \"<font color='red'>请选择要上传的文件</font>\";"+enter);
        content.append("	}"+enter);
        content.append("}"+enter);
        
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}