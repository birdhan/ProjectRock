package com.cloud.demo.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cloud.base.job.IBaseJob;

/**
 * 定时任务类
 * @author cuiyp
 *
 */
public class DemoJob implements IBaseJob {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		executeJob();
	}
	
	/**
	 * 方便其它类中调用此方法
	 */
	public void executeJob() {
		System.out.println("====正在执行DemoJob.executeJob()====");
	}

}
