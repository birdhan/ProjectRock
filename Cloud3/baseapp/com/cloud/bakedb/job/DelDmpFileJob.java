package com.cloud.bakedb.job;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cloud.base.cache.SysCache;
import com.cloud.base.job.IBaseJob;
import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.PropertyFileUtil;
import com.cloud.base.util.StringUtil;

public class DelDmpFileJob implements IBaseJob {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		executeJob();
	}
	
	public void executeJob() {
		LoggerUtil.info(getClass(), "===准备清理数据库过期的备份文件===");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			int sdfd = SysCache.getInstance().getSystemConfig().getSaveDmpFileDay();					//保存dmp文件天数
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -sdfd);													
			
			String config_other = getClass().getResource("/").getPath()+"/config-other.properties";
			String dmpSavePath = PropertyFileUtil.getValue(config_other, "dmpSavePath");				//dmp保存路径
			if(StringUtil.null2String(dmpSavePath).equals("")) {										//如果保存路径为空，那么删除默认路径
				dmpSavePath = getClass().getResource("/").getPath().replace("WEB-INF/classes/", "sql/bakeup_dmp/");
			}
			File dmpFolder = new File(dmpSavePath);
			File[] dmpFiles = dmpFolder.listFiles();													//得到所有dmp文件
			if(dmpFiles.length > 0) {
				for(File dmp : dmpFiles) {
					String name = dmp.getName();
					Date d = sdf.parse(name);
					if(d.getTime() < cal.getTime().getTime()) {											//如果文件名小于规定的文件时间，那么就要删除这样的文件
						dmp.delete();
						LoggerUtil.info(getClass(), "删除文件："+name);
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		LoggerUtil.info(getClass(), "===数据库备份文件清理结束===");
	}

}
