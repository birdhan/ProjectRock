package com.cloud.bakedb.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cloud.bakedb.util.CreateBackUpBatFile;
import com.cloud.base.cache.SysCache;
import com.cloud.base.job.IBaseJob;
import com.cloud.base.util.CallBatFile;
import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.PropertyFileUtil;
import com.cloud.base.util.StringUtil;

/**
 * 定时备份数据库
 * @author cloud7
 *
 */
public class BakeDataBaseJob implements IBaseJob {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		executeJob();
	}
	
	public void executeJob() {
		LoggerUtil.info(getClass(), "准备备份数据库...");
		String dataBaseType = SysCache.getInstance().getDataBase();
		
		if(dataBaseType.equalsIgnoreCase("oracle")) {
			String batFilePath = getClass().getResource("/").getPath().replace("WEB-INF/classes/", "sql/bakeup_bat/bake.bat");
			String os = System.getProperty("os.name").toLowerCase();
			if(os.indexOf("windows") != -1) {
				batFilePath = getClass().getResource("/").getPath().replace("WEB-INF/classes/", "sql/bakeup_bat/bake.bat");
				CallBatFile.call(batFilePath);
			} else if(os.indexOf("linux") != -1 || os.indexOf("unix") != -1) {
				batFilePath = getClass().getResource("/").getPath().replace("WEB-INF/classes/", "sql/bakeup_bat/bake.sh");
			}
		} else if(dataBaseType.equalsIgnoreCase("mysql")) {
			try {
				String config_other = BakeDataBaseJob.class.getResource("/").getPath() + "/config-other.properties";					//获取该属性文件下的配置
				String superAdmin4Mysql = PropertyFileUtil.getValue(config_other, "superAdmin4Mysql");									//得到mysql的管理帐号
				String superAdminPwd4Mysql = PropertyFileUtil.getValue(config_other, "superAdminPwd4Mysql");							//得到管理员帐号的密码
				String mysqlBinPath = PropertyFileUtil.getValue(config_other, "mysqlBinPath");											//得到数据库所安装的bin路径
				
				String config_database = BakeDataBaseJob.class.getResource("/").getPath() + "/config-database.properties";
				String driverUrl = PropertyFileUtil.getValue(config_database, "driverUrl");												//得到mysql的url串
				String database = driverUrl.substring(driverUrl.lastIndexOf("/")+1);													//截取最后的数据库名称
				
				Runtime rt = Runtime.getRuntime();
				
				StringBuffer command = new StringBuffer("");																			//命令缓冲串
				
				String lastChar = mysqlBinPath.charAt(mysqlBinPath.length()-1)+"".trim();												
				if(lastChar.equals("/") || lastChar.equals("\\")) {
					command.append(mysqlBinPath);
				} else {
					command.append(mysqlBinPath + "/");
				}
				command.append("mysqldump -h localhost");
				command.append(" -u"+superAdmin4Mysql);
				command.append(" -p"+superAdminPwd4Mysql);
				command.append(" "+database);
				System.out.println(command.toString());
				Process child = rt.exec(command.toString());
				InputStream in = child.getInputStream();
				InputStreamReader xx = new InputStreamReader(in, "utf-8");

				String inStr;
				StringBuffer sb = new StringBuffer("");
				String outStr;
				BufferedReader br = new BufferedReader(xx);
				while ((inStr = br.readLine()) != null) {
					sb.append(inStr + "\r\n");
				}
				outStr = sb.toString();
				
				String dmpSavePath = PropertyFileUtil.getValue(config_other, "dmpSavePath");														//dmp保存路径
				if(StringUtil.null2String(dmpSavePath).equals("")) {
					dmpSavePath = CreateBackUpBatFile.class.getResource("/").getPath().replace("WEB-INF/classes/", "sql/bakeup_dmp/");				//备份dmp数据库文件，如果没有配置存放在默认路径。
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");				
				String sqlFile = dmpSavePath + File.separator + sdf.format(new Date()) + ".sql";
				FileOutputStream fout = new FileOutputStream(sqlFile);
				OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
				writer.write(outStr);
				writer.flush();
				in.close();
				xx.close();
				br.close();
				writer.close();
				fout.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
		LoggerUtil.info(getClass(), "成功备份数据库...");
	}

}
