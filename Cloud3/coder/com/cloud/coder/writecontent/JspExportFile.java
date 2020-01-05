package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class JspExportFile {

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
        content.append("		<script type=\"text/javascript\" src=\"${ctx}/click/"+coder.getNameSpace()+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"ExportClick.js\"></script> <!-- js事件文件 -->"+enter);
        content.append("	</head>"+enter+enter);
        
        content.append("<body style=\"padding: 10px;\">"+enter);
        content.append("	<form id=\"exportForm\" action=\"${ctx}/"+coder.getNameSpace()+"/exportData.do\" method=\"post\">"+enter);
        content.append("		<input type=\"hidden\" id=\"ids\" name=\"ids\" value=\"${ids}\"/>"+enter);
        content.append("		<input type=\"hidden\" id=\"columns\" name=\"columns\"/>"+enter);
        content.append("		<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" style=\"margin-bottom: 10px;\">"+enter);
        content.append("			<tr>"+enter);
        content.append("				<td colspan=\"2\" height=\"20\" align=\"center\" style=\"border-bottom-style:dashed; border-bottom-width:1px; border-bottom-color:#3399FF;\">"+enter);
        content.append("					<font style=\"font-size: 15px;font-weight: bold;\">筛选条件</font>"+enter);
        content.append("				</td>"+enter);
        content.append("			</tr>"+enter);
        content.append("		</table>"+enter+enter);
        
        content.append("		<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"exportTable\" style=\"margin-top: 10px;\">"+enter);
        content.append("			<tr>"+enter);
        content.append("				<td colspan=\"2\"><input type=\"checkbox\" onclick=\"chooseAllPro(this)\" class=\"chx\"/>全选</td>"+enter);
        content.append("			</tr>"+enter);
        content.append("			<tr>"+enter);
        content.append("				<td colspan=\"2\">"+enter);
        content.append("					<table width=\"100%\" border=\"0\" id=\"proTable\" name=\"proTable\">"+enter);
        content.append("						<tr>"+enter);
        content.append("						<c:forEach items=\"${fieldMap}\" var=\"fm\" varStatus=\"status\">"+enter);
        content.append("							<td height=\"25\" width=\"25%\">"+enter);
        content.append("								<input type=\"checkbox\" class=\"chx\" name=\"proCheckbox\" id=\"pro_${status.index}\" value=\"{name:'${fm.key}',value:'${fm.value}'}\" <c:if test=\"${fm.value != 'id'}\">checked=\"checked\"</c:if>/>${fm.key}"+enter);
        content.append("							</td>"+enter+enter);
        
        content.append("						<c:if test=\"${(status.index+1) % 4 == 0}\"><!-- 显示4列就换行 -->"+enter);
        content.append("						</tr>"+enter);
        content.append("						<tr>"+enter);
        content.append("						</c:if>"+enter+enter);
        
        content.append("						</c:forEach>"+enter+enter);
        
        content.append("						<c:if test=\"${4-fn:length(fieldMap)%4 != 4}\">"+enter);
        content.append("							<c:forEach begin=\"1\" end=\"${4-fn:length(fieldMap)%4}\" step=\"1\" varStatus=\"vs\">"+enter);
        content.append("								<td>&nbsp;</td>"+enter);
        content.append("							</c:forEach>"+enter);
        content.append("						</c:if>"+enter);
        content.append("						</tr>"+enter);
        content.append("					</table>"+enter);
        content.append("				</td>"+enter);
        content.append("			</tr>"+enter);
        content.append("			<tr>"+enter);
        content.append("				<td colspan=\"2\">"+enter);
        content.append("					<div id=\"infoDiv\" style=\"margin-top: 2px;\">&nbsp;</div>"+enter);
        content.append("				</td>"+enter);
        content.append("			</tr>"+enter);
        content.append("		</table>"+enter+enter);
        
        content.append("		<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" class=\"btnTable\">"+enter);
        content.append("			<tr>"+enter);
        content.append("				<td>"+enter);
        content.append("					<input type=\"button\" id=\"exportBtn\" value=\"导出\" class=\"btn_list\" onclick=\"beginExportData()\"/>"+enter);
        content.append("					<input type=\"button\" value=\"关闭\" class=\"btn_list\" onclick=\"parent.colseExportWin()\"/>"+enter);
        content.append("				</td>"+enter);
        content.append("			</tr>"+enter);
        content.append("		</table>"+enter+enter);
        
        content.append("		<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" style=\"margin-bottom: 10px;margin-top:10px;\">"+enter);
        content.append("			<tr>"+enter);
        content.append("				<td colspan=\"2\">"+enter);
        content.append("					<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">"+enter);
        content.append("						<tr>"+enter);
        content.append("							<td style=\"width:10px;text-align: left;vertical-align: top;padding-top: 3px;padding-right:3px;\"><font color=\"red\">*</font></td>"+enter);
        content.append("							<td style=\"color:#B2B2B2;\">"+enter);
        content.append("								默认选择所有列(除主键外)，也可对要导出的列进行手动选择。<br/>"+enter);
        content.append("								<c:if test=\"${ids != ''}\">"+enter);
        content.append("									您当前已经选择了${idsSize}条记录，若不选择，系统会将数据全部导出。"+enter);
        content.append("								</c:if>"+enter);
        content.append("								<c:if test=\"${ids == ''}\">"+enter);
        content.append("									您当前没有选择任何数据，系统将为您将数据全部导出。"+enter);
        content.append("								</c:if>"+enter);
        content.append("							</td>"+enter);
        content.append("						</tr>"+enter);
        content.append("					</table>"+enter);
        content.append("				</td>"+enter);
        content.append("			</tr>"+enter);
        content.append("		</table>"+enter);
        content.append("	</form>"+enter);
        content.append("</body>"+enter);
        content.append("<%@ include file=\"../../../commonjsp/common-bottom.jsp\" %>"+enter);
        
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
