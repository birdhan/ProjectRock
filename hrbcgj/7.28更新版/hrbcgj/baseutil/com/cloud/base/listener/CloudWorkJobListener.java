package com.cloud.base.listener;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class CloudWorkJobListener implements JobListener {

	private String listenerName;
	
	public void setName(String listenerName) {
		this.listenerName = listenerName;
	}
	
	@Override
	public String getName() {
		return listenerName;
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext inContext) {
		JobDetail jobDetail = inContext.getJobDetail();
		String jobSubId = jobDetail.getName();
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext inContext) {
		JobDetail jobDetail = inContext.getJobDetail();
		String jobSubId = jobDetail.getName();
	}

	@Override
	public void jobWasExecuted(JobExecutionContext inContext,JobExecutionException exception) {
		JobDetail jobDetail = inContext.getJobDetail();
		String jobSubId = jobDetail.getName();
	}

}
