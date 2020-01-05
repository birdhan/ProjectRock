package com.cloud.base.test.other;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.PropertyFileUtil;
import com.cloud.base.util.StringUtil;


public class TestApache {

	public static void main(String[] args) {
		//启动服务时创建bat文件		
		String config_database = TestApache.class.getResource("/").getPath() + "/config-database.properties";
		String config_other = TestApache.class.getResource("/").getPath() + "/config-other.properties";
		
		String driverUrl = PropertyFileUtil.getValue(config_database, "driverUrl");															//数据库url			
		String sid = driverUrl.substring(driverUrl.lastIndexOf(":")+1);																		//sid
		String user = PropertyFileUtil.getValue(config_database, "user");																	//用户名
		String password = PropertyFileUtil.getValue(config_database, "password");															//密码
		
		String batFilePath = TestApache.class.getResource("/").getPath().replace("WEB-INF/classes/", "sql/bakeup_bat/bake.bat");			//bat文件生成路径，固定路径方便定时任务去找。
		
		String dmpSavePath = PropertyFileUtil.getValue(config_other, "dmpSavePath");														//dmp保存路径
		if(StringUtil.null2String(dmpSavePath).equals("")) {
			dmpSavePath = TestApache.class.getResource("/").getPath().replace("WEB-INF/classes/", "sql/bakeup_dmp/");						//备份dmp数据库文件，如果没有配置存放在默认路径。
		} 
		String batCon = "exp " + user + "/" + password + "@" + sid + " file=" + dmpSavePath + "%date:~0,4%%date:~5,2%%date:~8,2%.dmp";		//bat文件内容
		FileUtil.writeBatFile(batFilePath, batCon);																							//创建bat文件
	}

}
