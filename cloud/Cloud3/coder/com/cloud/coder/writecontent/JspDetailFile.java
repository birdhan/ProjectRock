package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class JspDetailFile {

	public static void write(Map paramMap , String filePath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
        content.append("<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>"+enter);
        content.append("<%@ include file=\"../../../commonjsp/common-top.jsp\" %>"+enter);
        content.append("	<head>"+enter);
        content.append("		<base href=\"<%=basePath%>\">"+enter);
        content.append("	</head>"+enter+enter);
        
        content.append("<body>"+enter);
        content.append("<div class=\"right_title\"><p>位置："+coder.getModelDesc()+"详细</p></div>"+enter);
        content.append("<div class=\"tableContentDiv\">"+enter);
        content.append("<div class=\"formDiv\">"+enter);
        content.append("	<table class=\"tableHead\">"+enter);
        content.append("		<tr>"+enter);
        content.append("			<td><img src=\"${ctx}/images/formTableTitle.png\" class=\"formTableTitle\"/>"+coder.getModelDesc()+"详细</td>"+enter);
        content.append("		</tr>"+enter);
        content.append("	</table>"+enter+enter);
        
        content.append("	<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"formTable\">"+enter);
        for(int i=0;i<pmcList.size();i++) {													
        	ProMappingCol pmc = pmcList.get(i);
        	if(!pmc.getPro().toLowerCase().equals("id")) {					//过滤id
        		content.append("		<tr>"+enter);
        		content.append("			<th>"+pmc.getProdesc()+"：</th>"+enter);
                content.append("			<td align=\"left\" class=\"formTable_td\">"+enter);
                if(pmc.getJspInput().equals("text") || pmc.getJspInput().equals("textarea") || pmc.getJspInput().equals("htmleditor")) {
                	content.append("				${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}"+enter);
                } else if(pmc.getJspInput().equals("datadict:inputRadio")){
                	content.append("				<datadict:value2label dictType=\"radio\" moduleName=\""+coder.getNameSpace()+"\" dictValue=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}\" property=\""+pmc.getPro()+"\"/>"+enter);
                } else if(pmc.getJspInput().equals("datadict:inputCheckbox")){
                	content.append("				<datadict:value2label dictType=\"checkbox\" moduleName=\""+coder.getNameSpace()+"\" dictValue=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}\" property=\""+pmc.getPro()+"\"/>"+enter);
                } else if(pmc.getJspInput().equals("datadict:select")){
                	content.append("				<datadict:value2label dictType=\"select\" moduleName=\""+coder.getNameSpace()+"\" dictValue=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}\" property=\""+pmc.getPro()+"\"/>"+enter);
                } else if(pmc.getJspInput().equals("datadict:select2")){
                	content.append("				<datadict:value2label dictType=\"select2\" moduleName=\""+coder.getNameSpace()+"\" dictValue=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}\" property=\""+pmc.getPro()+"\"/>"+enter);
                } else if(pmc.getJspInput().equals("yyyy-MM-dd HH:mm:ss") || pmc.getJspInput().equals("yyyy-MM-dd HH:mm")
                		|| pmc.getJspInput().equals("yyyy-MM-dd HH") || pmc.getJspInput().equals("yyyy-MM-dd")){
                	content.append("				<fmt:formatDate value=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}\" pattern=\""+pmc.getJspInput()+"\"/>"+enter);
                } else {
                	content.append("				${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}"+enter);
                }
                content.append("			</td>"+enter);
                content.append("		</tr>"+enter);
        	}
        }
        content.append("	</table>"+enter+enter);
        
        content.append("	<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" class=\"btnTable\">"+enter);
        content.append("		<tr>"+enter);
        content.append("			<td>"+enter);
        content.append("				<input type=\"button\" value=\"返回列表\" class=\"btn\" onclick=\"javascript:goToUrl('${ctx}/"+coder.getNameSpace()+"/search"+coder.getModelName()+".do')\"/>"+enter);
        content.append("			</td>"+enter);
        content.append("		</tr>"+enter);
        content.append("	</table>"+enter);
        content.append("</div>"+enter);
        content.append("</div>"+enter);
        content.append("</div>"+enter);
        content.append("</body>"+enter);
        content.append("<%@ include file=\"../../../commonjsp/common-bottom.jsp\" %>"+enter);
        
        FileUtil.writeFileByContent(filePath, content.toString());
        
	}
}
