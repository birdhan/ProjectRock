package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;
import com.cloud.coder.widget.GetWidget;

public class JspFormFile {

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
        content.append("		<script type=\"text/javascript\" src=\"${ctx}/click/"+coder.getNameSpace()+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"FormClick.js\"></script> <!-- js事件文件 -->"+enter);
        content.append("	</head>"+enter+enter);
        
        content.append("<body>"+enter);
        content.append("<div class=\"right_title\"><p>位置："+coder.getModelDesc()+"表单</p></div>"+enter);
        content.append("<div class=\"tableContentDiv\">"+enter);
        content.append("<div class=\"formDiv\">"+enter);
        content.append("	<form action=\"${ctx}/"+coder.getNameSpace()+"/save"+coder.getModelName()+".do\" method=\"post\" name=\""+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Form\" id=\""+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Form\">"+enter);
        content.append("		<input type=\"hidden\" id=\"id\" name=\"id\" value=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+".id}\"/>"+enter);
        content.append("		<table class=\"tableHead\">"+enter);
        content.append("			<tr>"+enter);
        content.append("				<td><img src=\"${ctx}/images/formTableTitle.png\" class=\"formTableTitle\"/>"+coder.getModelDesc()+"表单</td>"+enter);
        content.append("			</tr>"+enter);
        content.append("		</table>"+enter+enter);
        
        content.append("		<table class=\"formTable\">"+enter);
        for(int i=0;i<pmcList.size();i++) {													
        	ProMappingCol pmc = pmcList.get(i);
        	if(!pmc.getPro().toLowerCase().equals("id")) {					//过滤id
        		content.append("			<tr>"+enter);
        		String flagRequired = "";
        		if(pmc.getProValidation().indexOf("required:true") != -1) {
        			flagRequired = "<font color=\"red\">*</font>";
        		}
        		content.append("				<th>"+flagRequired+pmc.getProdesc()+"：</th>"+enter);
                content.append("				<td>"+enter);
                content.append("					"+GetWidget.get(coder, pmc)+enter);
                content.append("				</td>"+enter);
                content.append("			</tr>"+enter);
        	}
        }
        
        content.append("		</table>"+enter+enter);
        
        content.append("		<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" class=\"btnTable\">"+enter);
        content.append("			<tr>"+enter);
        content.append("				<td>"+enter);
        content.append("					<input type=\"submit\" value=\"提交\" class=\"btn\"/>"+enter);
        content.append("					<input type=\"button\" value=\"返回列表\" class=\"btn\" onclick=\"javascript:goToUrl('${ctx}/"+coder.getNameSpace()+"/search"+coder.getModelName()+".do')\"/>"+enter);
        content.append("					<input type=\"reset\" value=\"重置\" class=\"btn\"/>"+enter);
        content.append("				</td>"+enter);
        content.append("			</tr>"+enter);
        content.append("		</table>"+enter);
        content.append("	</form>"+enter);
        content.append("</div>"+enter);
        content.append("</div>"+enter);
        content.append("</div>"+enter);
        content.append("</body>"+enter);
        content.append("<%@ include file=\"../../../commonjsp/common-bottom.jsp\" %>"+enter);
        
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
