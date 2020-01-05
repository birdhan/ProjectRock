package com.cloud.base.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cloud.bakedb.job.DelDmpFileJob;
import com.cloud.bakedb.util.CreateBackUpBatFile;
import com.cloud.base.cache.SysCache;
import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.base.util.LoggerUtil;
import com.cloud.sqllog.job.Log4jJob;
import com.cloud.sqllog.util.InitJdbMonitorConfig;

public class CacheInitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		LoggerUtil.info(getClass(), "===缓存已销毁===");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
//		InitJdbMonitorConfig.xmlUpdateDemo();									//初始化jdbmonitorconfig文件
		LoggerUtil.info(getClass(), "===缓存已监听===");
		System.out.println(System.getProperty("java.endorsed.dirs"));
		JdbcHibernateUtil.getInstance();										//hibernate先获取实例
		
		//添加其它缓存	
		String contextPath = arg0.getServletContext().getContextPath();			//根路径名称		
		SysCache.getInstance().setContextPath(contextPath);						//设置根路径名称
		SysCache.getInstance().initDataBase();									//数据库类型oracle还是mysql
		SysCache.getInstance().initDictList();									//基本参数
		SysCache.getInstance().initCWSList();									//调用Webservice的集合
		SysCache.getInstance().initSystemConfig();								//系统设置配置
		SysCache.getInstance().initEmailConfig();								//邮件配置
		
		startServerExecute();													//启动时执行的方法
	}
	
	/**
	 * 系统启动时需要执行的函数
	 */
	public void startServerExecute() {
		CreateBackUpBatFile.create();											//创建bat数据库备份脚本文件
		
		DelDmpFileJob ddfj = new DelDmpFileJob();								//创建删除过期数据库备份文件对象
		ddfj.executeJob();														//删除文件
		
		Log4jJob l4j = new Log4jJob();											//创建删除删除日志文件对象
		l4j.executeJob();														//删除log日志文件
	}
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.endorsed.dirs"));
	}

}
