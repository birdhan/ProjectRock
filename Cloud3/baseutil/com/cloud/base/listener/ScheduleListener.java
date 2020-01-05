package com.cloud.base.listener;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.SpringContextHolder;
import com.cloud.schedulemanager.dao.ScheduleDaoHibernate;
import com.cloud.schedulemanager.model.Schedule;

public class ScheduleListener implements ServletContextListener {

	private static Scheduler sched = null;
	private StdSchedulerFactory sf = null;
	private String defaultGroup = "defaultGroup";
	private String realtimeGroup = "realtimeGroup";
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		LoggerUtil.info(getClass(), "===轮旬监听程序销毁===");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		LoggerUtil.info(getClass(), "===轮旬监听程序启动===");
		init();//初始化sched对象
		run();//开始执行轮旬数据
	}

	/**
	 * 初始化方法
	 */
	public void init() {
		LoggerUtil.info(getClass(), "===初始化轮旬类集合===");
		try {
			sf = new StdSchedulerFactory();
			sched = sf.getScheduler();
			String[] groups = sched.getTriggerGroupNames();
			for (int i = 0; i < groups.length; i++) {
				String[] names = sched.getTriggerNames(groups[i]);
				for (int j = 0; j < names.length; j++) {
					sched.unscheduleJob(names[j], groups[i]);
				}
			}

			groups = sched.getJobGroupNames();
			for (int i = 0; i < groups.length; i++) {
				String[] names = sched.getJobNames(groups[i]);
				for (int j = 0; j < names.length; j++) {
					sched.deleteJob(names[j], groups[i]);
				}
			}
			LoggerUtil.info(getClass(), "===初始化结束===");
		} catch (SchedulerException e) {
			
		}
	}
	
	/**
	 * 运行轮迿
	 */
	public void run() {
		
		ScheduleDaoHibernate sdh = (ScheduleDaoHibernate)SpringContextHolder.getApplicationContext().getBean("scheduleDaoHibernate");
		List ssdList = sdh.getAllDataByWhere(" and schedule.status='1'");
		try {
			if(ssdList != null && ssdList.size() != 0) {
				for(int i=0;i<ssdList.size();i++) {
					Schedule ss = (Schedule)ssdList.get(i);//得到每一个订制的任务
					String subId = ss.getId();//订制的类id
					String frequency = ss.getFrequency();//"0 0 23 * * ?";//*/1 * * * * ?
					JobDetail job = new JobDetail(subId, defaultGroup, Class.forName(ss.getClassType()).newInstance().getClass());
					
					String triggername = "trigg_"+subId;
					CronTrigger trigger = new CronTrigger(triggername,defaultGroup, subId, defaultGroup, frequency);
					CloudWorkJobListener ccrlListener = new CloudWorkJobListener();
					ccrlListener.setName(subId);
					
					sched.addJobListener(ccrlListener);
					job.addJobListener(ccrlListener.getName());
					Date ft = sched.scheduleJob(job, trigger);
					LoggerUtil.info(getClass(), "==="+ss.getClassType()+" 轮迿类加载成功===");
				}
			}
			
			sched.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除job
	 * @param job_name订制的id
	 */
	public void deleteJob(String job_name) {
		try {
			sf = new StdSchedulerFactory();
			sched = sf.getScheduler();
			String[] groups = { defaultGroup, realtimeGroup};
			String trigname = "trigg_" + job_name;
			for (int i = 0; i < groups.length; i++) {
				sched.unscheduleJob(trigname, groups[i]);
			}
			for (int j = 0; j < groups.length; j++) {
				sched.deleteJob(job_name, groups[j]);
			}
		} catch (Exception e) {
			LoggerUtil.error(getClass(), "==="+job_name+"删除轮迿失败===");
		}
	}
	
	/**
	 * 修改job
	 * @param scheduleSub
	 */
	public void modifyJob(Schedule schedule) {
		try {
			String jobSubId = schedule.getId();
			this.deleteJob(jobSubId);
			this.addJob(schedule);
		} catch (Exception e) {
			LoggerUtil.error(getClass(), "轮迿修改失败");
		}
	}
	
	/**
	 * 添加job
	 * @param scheduleSub
	 */
	public void addJob(Schedule schedule) {
		try {
			ScheduleDaoHibernate sDao = (ScheduleDaoHibernate)SpringContextHolder.getApplicationContext().getBean("scheduleDaoHibernate");
			Schedule s = sDao.getScheduleById(schedule.getId());
			String subId = schedule.getId();//订制id
			String frequency = schedule.getFrequency();
			JobDetail job = new JobDetail(subId, defaultGroup, Class.forName(s.getClassType()).newInstance().getClass());
			
			String triggername = "trigg_"+subId;
			CronTrigger trigger = new CronTrigger(triggername,defaultGroup, subId, defaultGroup, frequency);
			CloudWorkJobListener ccrlListener = new CloudWorkJobListener();
			ccrlListener.setName(subId);
			
			sched.addJobListener(ccrlListener);
			job.addJobListener(ccrlListener.getName());
			Date ft = sched.scheduleJob(job, trigger);
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
