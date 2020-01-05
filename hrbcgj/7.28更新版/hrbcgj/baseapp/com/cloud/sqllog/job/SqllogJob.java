package com.cloud.sqllog.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cloud.base.cache.SysCache;
import com.cloud.base.job.IBaseJob;
import com.cloud.base.util.ApplicationContextHolder;
import com.cloud.base.util.DBFM;

/**
 * 定时任务类
 * @author cloudwork
 *
 */
public class SqllogJob implements IBaseJob {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		executeJob();
	}

	/**
 	 * 方便其它类中调用此方法
 	 * @author cloudwork
 	 *
 	 */
	public void executeJob() {
		System.out.println("====正在执行SqllogJob.executeJob()====");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59"); 
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -SysCache.getInstance().getSystemConfig().getDelLogDay());					//删除前n天的数据
		
		String sql = "delete from T_LOG_SQLLOG where FBEGINTIME <= "+DBFM.TO_DATE()+"('"+sdf.format(cal.getTime())+"','yyyy-mm-dd hh24:mi:ss')";
		
		JdbcTemplate jdbc = (JdbcTemplate)ApplicationContextHolder.getInstance().getBean("jdbcTemplate");
		jdbc.execute(sql);
	}

}
