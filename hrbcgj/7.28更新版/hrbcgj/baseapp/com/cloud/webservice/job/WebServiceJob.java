package com.cloud.webservice.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cloud.base.job.IBaseJob;

/**
 * 定时任务类
 * @author cloudwork
 *
 */
public class WebServiceJob implements IBaseJob {

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
		System.out.println("====正在执行DemoJob.executeJob()====");
	}

}
