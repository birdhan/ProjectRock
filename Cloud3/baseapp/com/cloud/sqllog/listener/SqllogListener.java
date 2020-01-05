package com.cloud.sqllog.listener;

import com.cloud.base.util.SpringContextHolder;
import com.cloud.sqllog.dao.SqllogDaoHibernate;
import com.cloud.sqllog.model.Sqllog;
import com.cownew.JDBMonitor.common.LoggerException;
import com.cownew.JDBMonitor.common.SQLInfo;
import com.cownew.JDBMonitor.listenerImpl.DataBaseDBListener;

public class SqllogListener extends DataBaseDBListener {

	@Override
	public void init(String arg) throws LoggerException {
		
	}

	@Override
	public synchronized void logSql(SQLInfo info) throws LoggerException {
		if(info.getSqlType().getValue() != 1 && info.getSqlType().getValue() != 2 && info.getSqlType().getValue() != 4) {
			Sqllog sqllog = new Sqllog();
			
			sqllog.setFsql(info.getSql());
			sqllog.setFsqltype(info.getSqlType().getValue());
			sqllog.setFbegintime(info.getBeginTime());
			sqllog.setFendtime(info.getEndTime());
			sqllog.setFparameters(info.getParameterGroup().toString());
			
			SqllogDaoHibernate sdh = (SqllogDaoHibernate)SpringContextHolder.getApplicationContext().getBean("sqllogDaoHibernate");
			sdh.saveData(sqllog);
		}
		
	}

	
}
