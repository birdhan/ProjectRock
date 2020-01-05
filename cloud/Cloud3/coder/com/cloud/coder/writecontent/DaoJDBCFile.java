package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class DaoJDBCFile {

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
        
        content.append("import java.util.List;" + enter + enter);
        
        content.append("import javax.annotation.Resource;"+enter);
        content.append("import javax.sql.DataSource;"+enter+enter);
        
        content.append("import org.springframework.jdbc.core.support.JdbcDaoSupport;"+enter);
        content.append("import org.springframework.stereotype.Repository;"+enter+enter);
        
        content.append("@Repository"+enter);
        content.append("public class "+coder.getModelName()+"DaoJDBC extends JdbcDaoSupport{"+enter+enter);
        
        content.append("	@Resource"+enter);
        content.append("	public void setDaoDataSource(DataSource dataSource){"+enter);
        content.append("		super.setDataSource(dataSource);"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	public List getAll"+coder.getModelName()+"Data() {"+enter);
        content.append("		String sql = \"select * from "+coder.getTableName()+"\";"+enter);
        content.append("		List list = getJdbcTemplate().queryForList(sql);"+enter);
        content.append("		return list;"+enter);
        content.append("	}"+enter+enter);
        content.append("}"+enter);
        
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
