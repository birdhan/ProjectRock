package com.cloud.coder.writecontent;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.cloud.base.util.FileUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class IWSFile {

	public static void write(Map paramMap , String filePath) {
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
        content.append("package " + coder.getPackageName() + ".webservice;" + enter + enter);
        
        content.append("import javax.jws.WebService;"+enter+enter);
        
        content.append("/**"+enter);
        content.append(" * 定时任务类"+enter);
        content.append(" * @author "+coder.getAuthor()+enter);
        content.append(" *"+enter);
        content.append(" */"+enter);
        content.append("@WebService"+enter);
        content.append("public interface I"+coder.getModelName()+"WS {"+enter+enter);
        
        content.append("	public String test"+coder.getModelName()+"WS();"+enter+enter);
        
        content.append("}"+enter);
    	
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
