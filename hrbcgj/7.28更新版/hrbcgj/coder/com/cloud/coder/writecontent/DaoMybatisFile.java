package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class DaoMybatisFile {

	public static void write(Map paramMap , String filePath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
        content.append("package " + coder.getPackageName() + ".dao;" + enter + enter);
        
        content.append("import java.util.List;"+enter+enter);
        
        content.append("import org.apache.ibatis.annotations.Param;"+enter);
        content.append("import org.apache.ibatis.annotations.Select;"+enter+enter);
        
        content.append("import " + coder.getPackageName() + ".model."+coder.getModelName()+";"+enter+enter);
        
        content.append("/**"+enter);
        content.append(" * mybatis接口层，每个方法就是其相应的sqlmap中的id对应的sql语句。"+enter);
        content.append(" * 在此接口中，有两种写法：1.映射其sqlmap写法;2.注解写法，sqlmap的映射文件中不需要有此方法的id映射。"+enter);
        content.append(" * 其映射文件在该模块的config包下的User_sqlmap.xml"+enter);
        content.append(" * @author "+coder.getAuthor()+enter);
        content.append(" *"+enter);
        content.append(" */"+enter);
        content.append("public interface I"+coder.getModelName()+"DaoMybatis {"+enter+enter);
        
        content.append("	public List selectAll"+coder.getModelName()+"();"+enter+enter);
        
        content.append("	@Select(\"SELECT * FROM "+coder.getTableName()+" WHERE ID = #{id}\")"+enter);
        content.append("	public "+coder.getModelName()+" get"+coder.getModelName()+"ById(@Param(\"id\") String id);"+enter+enter);
        
        content.append("}"+enter);
    	
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
