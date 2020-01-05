package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class StrtusFile {

	public static void write(Map paramMap , String filePath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
        
        content.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+enter);
        content.append("<!DOCTYPE struts PUBLIC"+enter);
        content.append("	\"-//Apache Software Foundation//DTD Struts Configuration 2.0//EN\""+enter);
        content.append("	\"http://struts.apache.org/dtds/struts-2.0.dtd\">"+enter+enter);
        
        content.append("<struts>"+enter);
        content.append("	<package name=\""+coder.getNameSpace()+"\" extends=\"globalPackage\" namespace=\"/"+coder.getNameSpace()+"\">"+enter);
        content.append("		<action name=\"*\" class=\""+coder.getPackageName()+".action."+coder.getModelName()+"Action\" method=\"{1}\">"+enter);
        content.append("			<result name=\"edit\">/WEB-INF/pages/"+coder.getNameSpace()+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Form.jsp</result>"+enter);
        content.append("			<result name=\"detail\">/WEB-INF/pages/"+coder.getNameSpace()+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Detail.jsp</result>"+enter);
        content.append("			<result name=\"list\">/WEB-INF/pages/"+coder.getNameSpace()+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"List.jsp</result>"+enter);
        content.append("			<result name=\"import\">/WEB-INF/pages/"+coder.getNameSpace()+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Import.jsp</result>"+enter);
        content.append("			<result name=\"export\">/WEB-INF/pages/"+coder.getNameSpace()+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"Export.jsp</result>"+enter);
        content.append("			<result name=\"redirectSuccess\" type=\"redirect\">/"+coder.getNameSpace()+"/success.do</result>"+enter);
        content.append("		</action>"+enter);
        content.append("	</package>"+enter);
        content.append("</struts>"+enter);
    	
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
