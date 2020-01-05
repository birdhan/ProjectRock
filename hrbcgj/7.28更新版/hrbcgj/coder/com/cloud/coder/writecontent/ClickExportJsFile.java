package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class ClickExportJsFile {

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
        content.append(" * 全选方法"+enter);
        content.append(" */"+enter);
        content.append("function chooseAllPro(e) {"+enter);
        content.append("	var proCheckbox = getElesByName(\"proCheckbox\");"+enter);
        content.append("	if(e.checked) {					//表示为选中"+enter);
        content.append("		for(var i=0;i<proCheckbox.length;i++) {"+enter);
        content.append("			proCheckbox[i].checked = true;"+enter);
        content.append("		}"+enter);
        content.append("	} else {						//表示为非选中"+enter);
        content.append("		for(var i=0;i<proCheckbox.length;i++) {"+enter);
        content.append("			proCheckbox[i].checked = false;"+enter);
        content.append("		}"+enter);
        content.append("	}"+enter);
        content.append("}"+enter+enter);
        
        content.append("/**"+enter);
        content.append(" * 开始导出数据"+enter);
        content.append(" */"+enter);
        content.append("function beginExportData() {"+enter);
        content.append("	getEleById(\"infoDiv\").innerHTML = \"&nbsp;\";"+enter);
        content.append("	var columns = \"\";"+enter);
        content.append("	var proCheckbox = getElesByName(\"proCheckbox\");"+enter);
        content.append("	for(var i=0;i<proCheckbox.length;i++) {"+enter);
        content.append("		if(proCheckbox[i].checked) {		//将选中的值拼出来"+enter);
        content.append("			columns += proCheckbox[i].value + \",\";"+enter);
        content.append("		}"+enter);
        content.append("	}"+enter);
        content.append("	if(columns != \"\") {"+enter);
        content.append("		//过滤-start"+enter);
        content.append("		var ids = getEleById(\"ids\").value;"+enter);
        content.append("		//代码略，可在此处拼写导出的参数条件，可参考demo"+enter);
        content.append("		//过滤-end"+enter+enter);
        
        content.append("		columns = columns.substring(0,columns.length-1);"+enter);
        content.append("		getEleById(\"columns\").value = columns;"+enter);
        //content.append("		window.location.href = \"${ctx}/"+coder.getNameSpace()+"/exportData.do?ids=\" + ids + \"&columns=\"+encodeURI(encodeURI(columns));		//可将过滤条件参数拼在此处"+enter);
        content.append("		getEleById(\"infoDiv\").innerHTML = \"<font color='#009966'>正在为您导出数据，稍后请检查下载的文件。</font>\";"+enter);
        content.append("		getEleById(\"exportBtn\").disabled = \"disabeld\";"+enter);
        content.append("		getEleById(\"exportBtn\").title = \"正在导出数据...\";"+enter);
        content.append("		getEleById(\"exportForm\").submit();"+enter);
        content.append("	} else {"+enter);
        content.append("		getEleById(\"infoDiv\").innerHTML = \"<font color='red'>请选择要导出的列</font>\";"+enter);
        content.append("	}"+enter);
        content.append("}"+enter);
        
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
