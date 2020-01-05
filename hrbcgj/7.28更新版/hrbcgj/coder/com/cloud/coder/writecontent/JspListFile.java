package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;
import com.cloud.coder.widget.GetWidget;

public class JspListFile {

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
        content.append("		<script type=\"text/javascript\" src=\"${ctx}/click/"+coder.getNameSpace()+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"ListClick.js\"></script> <!-- 列表事件 -->"+enter);
        content.append("	</head>"+enter);
        
        content.append("<body>"+enter);
        content.append("	<div class=\"right_title\"><p>位置："+coder.getModelDesc()+"列表</p></div>"+enter);
        content.append("	<div class=\"formDiv\">"+enter);
        int queryCount = 0;
        for(int i=0;i<pmcList.size();i++) {
        	ProMappingCol pmc = pmcList.get(i);
        	if(pmc.getIsQuery().equals("yes")) {
        		queryCount++;
        		break;
        	}
        }
        
        if(queryCount > 0) {
        	
	        content.append("		<div class=\"query\">"+enter);
	        content.append("			<form id=\""+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Form\" name=\""+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Form\" action=\"${ctx}/"+coder.getNameSpace()+"/search"+coder.getModelName()+".do?isSearch=yes\" method=\"post\">"+enter);
	    	content.append("				<table class=\"query_table\">"+enter);
	    	int queryFlag = 0;
	    	for(int i=0;i<pmcList.size();i++) {
	        	ProMappingCol pmc = pmcList.get(i);
	        	if(pmc.getIsQuery().equals("yes")) {
	        		String searchPic = "&nbsp;"; 
	        		if(queryFlag == 0) {
	        			searchPic = "<img src=\"${ctx}/images/search2.png\" width=\"18\" height=\"18\" style=\"margin-top:3px;margin-left:5px;\" />";
	        		}
	        		
	        		content.append("					<tr>"+enter);            		
	        		content.append("						<td style=\" width:40px;\">"+searchPic+"</td>"+enter);
	            	content.append("						<th>"+pmc.getProdesc()+"：</th>"+enter);
	                content.append("						<td>"+enter);
	                content.append("							"+GetWidget.get(coder, pmc)+enter);
	                content.append("						</td>"+enter);
	                content.append("					</tr>"+enter);
	                queryFlag++;
	        	}	        	
	    	}
	    	content.append("				</table>"+enter+enter);
	        
	        content.append("				<table class=\"query_btn_table\">"+enter);
	        content.append("					<tr>"+enter);
	        content.append("						<td>"+enter);
	        content.append("							<input type=\"submit\" value=\"\" class=\"btn_search\"/>"+enter);
	        content.append("							<input type=\"button\"  class=\"btn_default_search\" onclick=\"javascript:goToUrl('${ctx}/"+coder.getNameSpace()+"/search"+coder.getModelName()+".do')\"/>"+enter);
	        content.append("						</td>"+enter);
	        content.append("					</tr>"+enter);
	        content.append("				</table>"+enter);
	        content.append("			</form>"+enter);
	        content.append("		</div>"+enter);
        }
        
        content.append("		<div class=\"listDiv\">"+enter);
        content.append("			<display:table requestURI=\"${ctx}/"+coder.getNameSpace()+"/search"+coder.getModelName()+".do\" id=\""+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"List\""+enter);
        content.append("				name=\"list\" pagesize=\"${pageSize}\" partialList=\"true\" size=\"${resultSize}\" cellspacing=\"0\" class=\"listtable\">"+enter+enter);
        
        content.append("				<display:column title=\"<input type='checkbox' onclick='javascript:choose();'>\" style=\"text-align:center; width:20px;border-left:none;\">"+enter);
        content.append("					<input id=\"ids\" name=\"ids\" type=\"checkbox\" value=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"List.id}\"/>"+enter);
        content.append("				</display:column>"+enter+enter);
        
        content.append("				<display:column title=\"序号\" style=\"width:30px;text-align:center;\">"+enter);
        content.append("					${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"List_rowNum}"+enter);
        content.append("				</display:column>"+enter+enter);
        
        for(int i=0;i<pmcList.size();i++) {
        	ProMappingCol pmc = pmcList.get(i);
        	if(pmc.getListShow().equals("yes")) {									//表示列表展现
        		String sortable = "";
    			if(pmc.getIsSort().equals("yes")) {								//表示排序
    				sortable = "sortable=\"true\"";
    			}
    			
    			String maxLength = "";
    			if(!pmc.getMaxLength().equals("0") && !pmc.getMaxLength().equals("")) {	//表示长度截取
    				maxLength = "maxLength=\""+pmc.getMaxLength()+"\"";
    			}
    			
        		if(pmc.getJspInput().equals("datadict:inputRadio")) {			//数据字典radio
        			content.append("				<display:column "+sortable+" title=\""+pmc.getProdesc()+"\" style=\"text-align:center;\" "+maxLength+">"+enter);
        			content.append("					<datadict:value2label dictType=\"radio\" moduleName=\""+coder.getNameSpace()+"\" dictValue=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"List."+pmc.getPro()+"}\" property=\""+pmc.getPro()+"\"/>"+enter);
        	        content.append("				</display:column>"+enter+enter);
        		} else if(pmc.getJspInput().equals("datadict:inputCheckbox")) {
        			content.append("				<display:column "+sortable+" title=\""+pmc.getProdesc()+"\" style=\"text-align:center;\" "+maxLength+">"+enter);
        			content.append("					<datadict:value2label dictType=\"checkbox\" moduleName=\""+coder.getNameSpace()+"\" dictValue=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"List."+pmc.getPro()+"}\" property=\""+pmc.getPro()+"\"/>"+enter);
        	        content.append("				</display:column>"+enter+enter);
        		} else if(pmc.getJspInput().equals("datadict:select")) {
        			content.append("				<display:column "+sortable+" title=\""+pmc.getProdesc()+"\" style=\"text-align:center;\" "+maxLength+">"+enter);
        			content.append("					<datadict:value2label dictType=\"select\" moduleName=\""+coder.getNameSpace()+"\" dictValue=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"List."+pmc.getPro()+"}\" property=\""+pmc.getPro()+"\"/>"+enter);
        	        content.append("				</display:column>"+enter+enter);
        		} else if(pmc.getJspInput().equals("datadict:select2")) {
        			content.append("				<display:column "+sortable+" title=\""+pmc.getProdesc()+"\" style=\"text-align:center;\" "+maxLength+">"+enter);
        			content.append("					<datadict:value2label dictType=\"select2\" moduleName=\""+coder.getNameSpace()+"\" dictValue=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"List."+pmc.getPro()+"}\" property=\""+pmc.getPro()+"\"/>"+enter);
        	        content.append("				</display:column>"+enter+enter);
        		} else if(pmc.getJspInput().indexOf("yyyy-MM-dd") !=-1) {		//表示是日期 
        			content.append("				<display:column "+sortable+" title=\""+pmc.getProdesc()+"\" style=\"text-align:center;\" "+maxLength+">"+enter);
        			content.append("					<fmt:formatDate value=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"List."+pmc.getPro()+"}\" pattern=\""+pmc.getJspInput()+"\"/>"+enter);
        	        content.append("				</display:column>"+enter+enter);
        		} else {														//普通文本和其它
        			content.append("				<display:column property=\""+pmc.getPro()+"\" "+sortable+" title=\""+pmc.getProdesc()+"\" style=\"text-align:center;\" "+maxLength+"/>"+enter+enter);
        		}
        	}
        }        
        
        content.append("				<display:column title=\"编辑\" style=\"text-align:center\">"+enter);
        content.append("					<img src=\"${ctx}/images/cog_edit.png\" title=\"编辑\" style=\"cursor: pointer;\""+enter);
        content.append("						onclick=\"goToUrl('${ctx}/"+coder.getNameSpace()+"/edit"+coder.getModelName()+".do?id=${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"List.id}')\"/>"+enter);
        content.append("				</display:column>"+enter+enter);
        
        content.append("				<display:column title=\"删除\" style=\"text-align:center\">"+enter);
        content.append("					<img src=\"${ctx}/images/delete.png\" title=\"删除\" style=\"cursor: pointer;\""+enter);
        content.append("						onclick=\"delDataById('${ctx}/"+coder.getNameSpace()+"/del"+coder.getModelName()+"ById.do?id=${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"List.id}')\"/>"+enter);
        content.append("				</display:column>"+enter+enter);
        
        content.append("				<display:column title=\"详细\" style=\"text-align:center\">"+enter);
        content.append("					<img src=\"${ctx}/images/detail.png\" title=\"详细\" style=\"cursor: pointer;\""+enter);
        content.append("						onclick=\"goToUrl('${ctx}/"+coder.getNameSpace()+"/detail"+coder.getModelName()+".do?id=${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"List.id}')\"/>"+enter);
        content.append("				</display:column>"+enter);
        content.append("			</display:table>"+enter+enter);
        
        content.append("			<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" class=\"btnTableList\">"+enter);
        content.append("				<tr>"+enter);
        content.append("					<td>"+enter);
        content.append("						<input type=\"button\" value=\"新增\" class=\"btn_list\" onclick=\"goToUrl('${ctx}/"+coder.getNameSpace()+"/add"+coder.getModelName()+".do')\"/>"+enter);
        content.append("						<input type=\"button\" value=\"删除\" class=\"btn_list\" onclick=\"delMoreByIds('${ctx}/"+coder.getNameSpace()+"/del"+coder.getModelName()+"ByIds.do')\" <c:if test=\"${fn:length(list)==0}\">disabled=disabeld title=\"没有数据不可操作\"</c:if>/>"+enter);
        content.append("						<input type=\"button\" value=\"导入数据\" class=\"btn_list\" onclick=\"importData()\"/>"+enter);
        content.append("						<input type=\"button\" value=\"导出数据\" class=\"btn_list\" onclick=\"exportData()\" <c:if test=\"${fn:length(list)==0}\">disabled=disabeld title=\"没有数据不可操作\"</c:if>/>"+enter);
        content.append("					</td>"+enter);
        content.append("				</tr>"+enter);
        content.append("			</table>"+enter);
        content.append("		</div>"+enter);
        content.append("	</div>"+enter);
        content.append("	</div>"+enter);
        content.append("</body>"+enter);
        content.append("<%@ include file=\"../../../commonjsp/common-bottom.jsp\" %>"+enter);
        
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
