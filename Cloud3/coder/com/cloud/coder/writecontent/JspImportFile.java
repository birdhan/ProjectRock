package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class JspImportFile {

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
        content.append("		<script type=\"text/javascript\" src=\"${ctx}/click/"+coder.getNameSpace()+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"ImportClick.js\"></script> <!-- js事件文件 -->"+enter);
        content.append("	</head>"+enter+enter);
        
        content.append("<body style=\"padding: 10px;\">"+enter);
        content.append("	<form id=\"importForm\" action=\"${ctx}/"+coder.getNameSpace()+"/importExcel.do?module="+coder.getNameSpace()+"\" method=\"post\" enctype=\"multipart/form-data\">"+enter);
        content.append("		<table class=\"importTable\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">"+enter);
        content.append("			<tr>"+enter);
        content.append("				<td>数据导入说明：</td>"+enter);
        content.append("			</tr>"+enter);
        content.append("			<tr>"+enter);
        content.append("				<td height=\"50\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请先<a href=\"javascript:window.location.href='${ctx}/"+coder.getNameSpace()+"/downLoadFile.do?filePath=/template/"+coder.getNameSpace()+"/"+coder.getNameSpace()+".xls';\">下载模板</a>文件，然后填写正确的数据后，方可将数据批量导入进系统中。</td>"+enter);
        content.append("			</tr>"+enter);
        content.append("			<tr>"+enter);
        content.append("				<td>"+enter);
        content.append("					选择导入文件：<input type=\"file\" id=\"importExcelFileAll\" name=\"importExcelFileAll\"/>"+enter);
        content.append("				</td>"+enter);
        content.append("			</tr>"+enter);
        content.append("			<tr>"+enter);
        content.append("				<td>"+enter);
        content.append("					<div id=\"fileDivInfo\" style=\"margin-top: 3px;\">&nbsp;</div>"+enter);
        content.append("				</td>"+enter);
        content.append("			</tr>"+enter);
        content.append("		</table>"+enter+enter);
        
        content.append("		<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" class=\"btnTable\">"+enter);
        content.append("			<tr>"+enter);
        content.append("				<td>"+enter);
        content.append("					<input type=\"button\" id=\"importBtn\" value=\"导入\" class=\"btn_list\" onclick=\"uploadFile()\"/>"+enter);
        content.append("					<input type=\"button\" value=\"关闭\" class=\"btn_list\" onclick=\"parent.closeImportWin()\"/>"+enter);
        content.append("				</td>"+enter);
        content.append("			</tr>"+enter);
        content.append("		</table>"+enter);
        content.append("	</form>"+enter);
        content.append("</body>"+enter);
        content.append("<%@ include file=\"../../../commonjsp/common-bottom.jsp\" %>"+enter);
        
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
