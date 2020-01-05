package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class SqlFile {

	public static void write(Map paramMap , String filePath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
        content.append("--建表语句，此仅为参考，开发人员可根据需要自行修改脚本"+enter);
        content.append("create table "+coder.getTableName() + " ("+enter);
        for(int i=0;i<pmcList.size();i++) {
        	ProMappingCol pmc = pmcList.get(i);
        	String douhao = ",";
        	if(i == pmcList.size()-1) {
        		douhao = "";
        	}
        	if(pmc.getCol().toUpperCase().equals("ID")) {		//表示是主键
        		content.append("	"+pmc.getCol()+" "+pmc.getColType()+" primary key"+douhao+enter);
        	} else {
        		content.append("	"+pmc.getCol()+" "+pmc.getColType()+douhao+enter);
        	}        	
        }
        content.append(");"+enter+enter);
        
        content.append("--创建索引"+enter);
        for(int i=0;i<pmcList.size();i++) {
        	ProMappingCol pmc = pmcList.get(i);
        	if(!pmc.getCol().toUpperCase().equals("ID")) {
        		content.append("create index "+coder.getTableName()+"_"+pmc.getCol()+" on "+coder.getTableName()+" ("+pmc.getCol()+");"+enter);
        	}
        }
        
        content.append(enter);
        content.append("--创建注释"+enter);
        for(int i=0;i<pmcList.size();i++) {
        	ProMappingCol pmc = pmcList.get(i);
        	content.append("comment on column "+coder.getTableName()+"."+pmc.getCol()+" is '"+pmc.getColdesc()+"';"+enter);
        }
        
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
