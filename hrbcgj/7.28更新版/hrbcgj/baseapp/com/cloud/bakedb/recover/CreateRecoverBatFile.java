package com.cloud.bakedb.recover;

import java.io.File;
import java.io.IOException;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.PropertyFileUtil;
import com.cloud.base.util.StringUtil;

/**
 * 创建bat备份脚本文件
 * @author cloud7
 *
 */
public class CreateRecoverBatFile {

	public static String create(String sqlFileName , String dmpFilePath , String tempFileName) {
		String batPath = "";
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        StringBuffer content = new StringBuffer("");
        
		LoggerUtil.info(CreateRecoverBatFile.class, "===准备创建bat文件===");
		String config_database = CreateRecoverBatFile.class.getResource("/").getPath() + "/config-database.properties";
		String config_other = CreateRecoverBatFile.class.getResource("/").getPath() + "/config-other.properties";
		
		String driverUrl = PropertyFileUtil.getValue(config_database, "driverUrl");															//数据库url			
		String sid = driverUrl.substring(driverUrl.lastIndexOf(":")+1);																		//sid
		String user = PropertyFileUtil.getValue(config_database, "user");																	//用户名
		String password = PropertyFileUtil.getValue(config_database, "password");															//密码
		
		String superAdmin = PropertyFileUtil.getValue(config_other, "superAdmin");
        String suerAdminPwd = PropertyFileUtil.getValue(config_other, "suerAdminPwd");
        
		String batFilePath = CreateRecoverBatFile.class.getResource("/").getPath().replace("WEB-INF/classes/", "sql/recover/"+tempFileName+".bat");	//bat文件生成路径，固定路径方便定时任务去找。
		String os = System.getProperty("os.name").toLowerCase();
		if(os.indexOf("windows") != -1) {																									//表示是windows
			batFilePath = CreateRecoverBatFile.class.getResource("/").getPath().replace("WEB-INF/classes/", "sql/recover/"+tempFileName+".bat");	//bat文件生成路径，固定路径方便定时任务去找。
		} else if(os.indexOf("linux") != -1 || os.indexOf("unix") != -1) {
			batFilePath = CreateRecoverBatFile.class.getResource("/").getPath().replace("WEB-INF/classes/", "sql/recover/"+tempFileName+".sh");	//bat文件生成路径，固定路径方便定时任务去找。
		}
		
		content.append("sqlplus  "+superAdmin+"/"+suerAdminPwd+" @"+sqlFileName+enter+enter);
		content.append("imp "+superAdmin+"/"+suerAdminPwd+"@"+sid+" fromuser="+user+" touser="+user+" file="+dmpFilePath+enter);
		FileUtil.writeBatFile(batFilePath, content.toString());		
		LoggerUtil.info(CreateRecoverBatFile.class, "===成功创建bat文件===");
		
		File f = new File(batFilePath);
		if(f.isFile()) {
			try {
				batPath = f.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return batPath;
	}
}
