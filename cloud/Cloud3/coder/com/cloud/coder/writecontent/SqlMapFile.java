package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class SqlMapFile {

	public static void write(Map paramMap , String filePath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
        content.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+enter);
        content.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">"+enter+enter);
        
        content.append("<mapper namespace=\""+coder.getPackageName()+".dao.I"+coder.getModelName()+"DaoMybatis\">"+enter+enter);
        
        content.append("	<select id=\"selectAll"+coder.getModelName()+"\" resultType=\""+coder.getModelName()+"\">"+enter);
        content.append("		select * from "+coder.getTableName()+enter);
        content.append("	</select>"+enter+enter);
        
        content.append("</mapper>"+enter);
    	
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
