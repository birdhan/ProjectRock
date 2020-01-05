package com.cloud.bakedb.recover;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.PropertyFileUtil;

/**
 * 创建等调用的sql文件，此sql被批处理文件所调用
 * @author cloud7
 *
 */
public class CreateSQL {

	/**
	 * 创建sql脚本文件
	 * @param request
	 * @return
	 */
	public static String[] create(HttpServletRequest request) {
		String[] sqlFileName = new String[2];
		String folder = request.getSession().getServletContext().getRealPath("/").replace("/WEB-INF/classes/", "") + "/sql/recover/";
		File fo = new File(folder);
		if(!fo.isDirectory()) {
			fo.mkdirs();
		}
		sqlFileName[0] = folder + System.currentTimeMillis() + ".sql";		
		FileUtil.createFile(sqlFileName[0]);									// 创建文件
		WriteContent(request,sqlFileName[0]);									// 向sql文件写内容
		File f = new File(sqlFileName[0]);
		if(f.isFile()) {
			try {
				sqlFileName[0] = f.getCanonicalPath();
				sqlFileName[1] = f.getName();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sqlFileName;
	}
	
	/**
	 * 向sql脚本文件里面写内容
	 * @param request
	 * @param filePath
	 */
	public static void WriteContent(HttpServletRequest request,String filePath) {
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        String config_other = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/config-other.properties");
        String superAdmin = PropertyFileUtil.getValue(config_other, "superAdmin");
        String suerAdminPwd = PropertyFileUtil.getValue(config_other, "suerAdminPwd");	
        
        String config_database = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/config-database.properties");
        String user = PropertyFileUtil.getValue(config_database, "user");	
        String password = PropertyFileUtil.getValue(config_database, "password");
        
        StringBuffer content = new StringBuffer("");
        content.append("conn "+superAdmin+"/"+suerAdminPwd+" as sysdba;"+enter);
        content.append("shutdown immediate;"+enter);
        content.append("startup;"+enter);
        content.append("drop user "+user+" cascade;"+enter);
        content.append("create user "+user+" identified by \""+password+"\" default tablespace USERS temporary tablespace TEMP profile DEFAULT account unlock;"+enter);
        content.append("grant CONNECT to "+user+" ;"+enter);
        content.append("grant RESOURCE to "+user+";"+enter);
        content.append("alter user "+user+" default role all;"+enter);
        content.append("grant UNLIMITED TABLESPACE to "+user+" ;"+enter);
        content.append("grant resource,connect to "+user+";"+enter);
        content.append("quit");        
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
