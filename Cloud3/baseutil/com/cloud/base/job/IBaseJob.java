package com.cloud.base.job;

import org.quartz.Job;

/**
 * 基本任务类均实现该接口，实现executeJob方法，在此方法里写逻辑内容
 * @author cloud7
 *
 */
public interface IBaseJob extends Job {

	/**
	 * 方便其它类中调用此方法
	 */
	public void executeJob();
}
