package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class DepolyFile {

	public static void write(Map paramMap , String filePath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
        content.append("代码生成时间："+sdf.format(new Date())+enter);
        content.append("代码开发人员："+coder.getAuthor()+enter+enter);
        
        content.append("部署模块说明："+enter);
        content.append("一、将"+coder.getFilePath()+"下的com文件夹复制到工程相应的源下，如果是基础功能模块请放到baseapp下，"+enter);
        content.append("如果是业务功能模块，请放到servicesapp下。"+enter+enter);
        
        content.append("二、将"+coder.getFilePath()+"下的WebRoot文件夹，相应放到工程的WebRoot文件夹下。"+enter+enter);
        
        content.append("三、(推荐步骤)开发人员最好是先执行创建数据表的脚本，该工程是hibernate自动创建表结构以及索引的。"+enter);
        content.append("但是还是建议手动执行数据脚本，脚本所在路径：该代码模块下的sql包下。"+enter+enter);
        
        content.append("四、加载配置文件"+enter);
//        content.append("1.加载Struts2文件，将以下配置放入config源下的struts.xml文件中"+enter);
//        content.append("<!-- "+coder.getModelDesc()+" -->"+enter);
//        content.append("<include file=\"/"+coder.getPackageName().replaceAll("\\.", "/")+"/config/struts-"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo())+".xml\"/>"+enter+enter);
        
//        content.append("2.加载hibernate实体类配置，将以下配置放入config源的hibernate.cfx.xml文件中"+enter);
//        content.append("<!-- "+coder.getModelDesc()+" -->"+enter);
//        content.append("<mapping class=\""+coder.getPackageName()+".model."+coder.getModelName()+"\"/>"+enter+enter);
        
        content.append("加载mybatis文件，虽然系统数据层使用的是hibernate，但是已经将mybatis和jdbctemplate都封装在内"+enter);
        content.append("将以下配置放入mybatis_config.xml中"+enter);
        content.append("将此配置放在typeAliases标签下"+enter);
        content.append("<!-- "+coder.getModelDesc()+" -->"+enter);
        content.append("<typeAlias alias=\""+coder.getModelName()+"\" type=\""+coder.getPackageName()+".model."+coder.getModelName()+"\"/>"+enter+enter);
        
        content.append("将此配置放在mappers标签下"+enter);
        content.append("<!-- "+coder.getModelDesc()+" -->"+enter);
        content.append("<mapper resource=\"/"+coder.getPackageName().replaceAll("\\.", "/")+"/config/"+coder.getModelName()+"_sqlmap.xml\"/>"+enter+enter);
        
        content.append("五、菜单url数据列表地址"+enter);
        content.append("/"+coder.getNameSpace()+"/search"+coder.getModelName()+".do"+enter);
        content.append(""+enter);
        
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
