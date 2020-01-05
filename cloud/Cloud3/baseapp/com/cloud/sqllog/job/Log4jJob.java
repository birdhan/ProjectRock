package com.cloud.sqllog.job;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cloud.base.cache.SysCache;
import com.cloud.base.job.IBaseJob;
import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.PropertyFileUtil;

public class Log4jJob implements IBaseJob {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		executeJob();
	}
	
	public void executeJob() {	
		try {
			LoggerUtil.info(getClass(), "===准备清理log日志文件===");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			int slfd = SysCache.getInstance().getSystemConfig().getSaveLogFileDay();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -slfd);
			
			String config_log4j = Log4jJob.class.getResource("/").getPath()+"/log4j.properties";		// log4j属性文件地址
			
			String fileName = PropertyFileUtil.getValue(config_log4j, "log4j.appender.file.File");		// 文件全路径
			String logFileName = fileName.substring(fileName.lastIndexOf("/")+1);						// 日志文件名
			String folders = fileName.substring(fileName.indexOf("/")+1,fileName.lastIndexOf("/"));		// 中间多级文件夹
			String comcatPath = getClass().getResource("/").getPath()+"../../../../" + folders;			// 最终日志所在路径
			
			File logFileFolder = new File(comcatPath);
			if(logFileFolder.isDirectory()) {															// 如果是一个路径
				for(File log : logFileFolder.listFiles()) {					
					String lf = log.getName();
					if(lf.indexOf(fileName) != -1){
						if(lf.equalsIgnoreCase(logFileName) == false) {
							String hz = lf.substring(lf.lastIndexOf(".")+1);
							Date d = sdf.parse(hz);
							if(d.getTime() < cal.getTime().getTime()) {
								LoggerUtil.info(getClass(), "删除文件："+lf);
								log.delete();
							}
						}	
					}														
				}
			}
			LoggerUtil.info(getClass(), "===成功清理log日志文件===");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
