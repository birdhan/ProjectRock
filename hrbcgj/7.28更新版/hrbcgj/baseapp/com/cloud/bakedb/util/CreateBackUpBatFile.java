package com.cloud.bakedb.util;

import java.io.File;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.FileUtil;
import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.PropertyFileUtil;
import com.cloud.base.util.StringUtil;

/**
 * 创建bat备份脚本文件
 * @author cloud7
 *
 */
public class CreateBackUpBatFile {

	public static void create() {
		LoggerUtil.info(CreateBackUpBatFile.class, "===准备创建bat文件===");
		String config_database = CreateBackUpBatFile.class.getResource("/").getPath() + "/config-database.properties";
		String config_other = CreateBackUpBatFile.class.getResource("/").getPath() + "/config-other.properties";
		
		String driverUrl = PropertyFileUtil.getValue(config_database, "driverUrl");															//数据库url			
		String sid = driverUrl.substring(driverUrl.lastIndexOf(":")+1);																		//sid
		String user = PropertyFileUtil.getValue(config_database, "user");																	//用户名
		String password = PropertyFileUtil.getValue(config_database, "password");															//密码
		
		String dataBaseType = SysCache.getInstance().getDataBase();
		if(dataBaseType.equalsIgnoreCase("oracle")) {
			String batFilePath = CreateBackUpBatFile.class.getResource("/").getPath().replace("WEB-INF/classes/", "sql/bakeup_bat/bake.bat");	//bat文件生成路径，固定路径方便定时任务去找。
			String os = System.getProperty("os.name").toLowerCase();
			if(os.indexOf("windows") != -1) {																									//表示是windows
				batFilePath = CreateBackUpBatFile.class.getResource("/").getPath().replace("WEB-INF/classes/", "sql/bakeup_bat/bake.bat");	//bat文件生成路径，固定路径方便定时任务去找。
			} else if(os.indexOf("linux") != -1 || os.indexOf("unix") != -1) {
				batFilePath = CreateBackUpBatFile.class.getResource("/").getPath().replace("WEB-INF/classes/", "sql/bakeup_bat/bake.sh");	//bat文件生成路径，固定路径方便定时任务去找。
			}
			LoggerUtil.info(CreateBackUpBatFile.class, "batFilePath:"+batFilePath);
			
			String dmpSavePath = PropertyFileUtil.getValue(config_other, "dmpSavePath");														//dmp保存路径
			if(StringUtil.null2String(dmpSavePath).equals("")) {
				dmpSavePath = CreateBackUpBatFile.class.getResource("/").getPath().replace("WEB-INF/classes/", "sql/bakeup_dmp/");				//备份dmp数据库文件，如果没有配置存放在默认路径。
			} else {																															//如果有存放路径
				File dsp = new File(dmpSavePath);
				if(!dsp.isDirectory()) {																										//创建文件夹
					dsp.mkdirs();
				}
			} 
			
			String batCon = "exp " + user + "/" + password + "@" + sid + " file=" + dmpSavePath + "%date:~0,4%%date:~5,2%%date:~8,2%.dmp";		//bat文件内容
			batCon = batCon.replace("file=/", "file=");		
			FileUtil.writeBatFile(batFilePath, batCon);
		} else if(dataBaseType.equalsIgnoreCase("mysql")) {
			
		}
				
		LoggerUtil.info(CreateBackUpBatFile.class, "===成功创建bat文件===");
	}
}
