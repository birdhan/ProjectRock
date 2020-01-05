package com.cloud.coder.writecontent;

import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class WSImplFile {

	public static void write(Map paramMap , String filePath) {
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
        content.append("package " + coder.getPackageName() + ".webservice;" + enter + enter);
        
        content.append("public class "+coder.getModelName()+"WSImpl implements I"+coder.getModelName()+"WS {"+enter+enter);
        
        content.append("	@Override"+enter);
        content.append("	public String test"+coder.getModelName()+"WS(){"+enter+enter);
        content.append("		return \"this is test\";"+enter);
        content.append("	}"+enter+enter);
        content.append("}"+enter);
    	
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
