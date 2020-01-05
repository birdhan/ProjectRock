package com.cloud.base.cache;

import java.util.ArrayList;
import java.util.List;

import com.cloud.base.util.PropertyFileUtil;
import com.cloud.base.util.SpringContextHolder;
import com.cloud.callwebservice.dao.CallWebServiceDaoHibernate;
import com.cloud.callwebservice.model.CallWebService;
import com.cloud.datadict.dao.DataDictDaoHibernate;
import com.cloud.datadict.model.DataDict;
import com.cloud.sendemail.model.EmailConfig;
import com.cloud.systemconfig.dao.SystemConfigDaoHibernate;
import com.cloud.systemconfig.model.SystemConfig;

public class SysCache {

	private static SysCache cache = null;

	public static SysCache getInstance() {
		if(cache == null) {
			cache = new SysCache();
		}
		return cache;
	}
	
	/**
	 * 数据库类型:oracle\mysql\sqlser
	 */
	private String dataBase;
	
	public String getDataBase() {
		if(dataBase == null) {
			initDataBase();
		}
		return dataBase;
	}

	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}
	
	public void initDataBase() {
		String proFile = SysCache.class.getResource("/").getPath().replaceAll("com/cloud/base/cache/", "")+"config-other.properties";
		String datatype = PropertyFileUtil.getValue(proFile, "datatype");
		dataBase = datatype;
	}

	/**
	 * 系统根路径名称
	 */
	private String contextPath;

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	/**
	 * 数据字典集合
	 */
	private List<DataDict> dictList;

	public List<DataDict> getDictList() {
		if(dictList == null) {
			dictList = new ArrayList();
			initDictList();
		}
		return dictList;
	}

	public void setDictList(List<DataDict> dictList) {
		this.dictList = dictList;
	}
	
	public void initDictList() {
		if(dictList != null) {
			dictList.clear();
		}
		DataDictDaoHibernate ddh = (DataDictDaoHibernate)SpringContextHolder.getApplicationContext().getBean("dataDictDaoHibernate");
		
		List allList = ddh.getAllDataByWhere("");
		List tempList = null;
		if(allList != null && allList.size() != 0) {
			tempList = new ArrayList();
			dictList = new ArrayList();
			for(int i=0;i<allList.size();i++) {
				tempList.add(((DataDict)allList.get(i)));
			}
			dictList.addAll(tempList);
		}
	}
	
	public void addDataDict(DataDict dataDict) {
		dictList.add(dataDict);
	}
	
	public void updateDataDict(DataDict dataDict) {
		for(int i=0;i<dictList.size();i++) {
			DataDict dd = dictList.get(i);
			if(dd != null) {
				if(dd.getId().equals(dataDict.getId())) {
					dictList.remove(i);
					dictList.add(dataDict);
					break;
				}
			}
		}	
	}
	
	public void deleteDataDice(DataDict dataDict) {
		for(int i=0;i<dictList.size();i++) {
			DataDict dd = dictList.get(i);
			if(dd != null) {
				if(dd.getId().equals(dataDict.getId())) {
					dictList.remove(i);
					break;
				}
			}
		}		
	}
	
	/**
	 *调用Webservice的缓存集合
	 */
	private List<CallWebService> cwsList;

	public List<CallWebService> getCwsList() {
		if(cwsList == null) {
			initCWSList();
		}
		return cwsList;
	}

	public void setCwsList(List<CallWebService> cwsList) {
		this.cwsList = cwsList;
	}
	
	public void initCWSList() {
		CallWebServiceDaoHibernate cwsdh = (CallWebServiceDaoHibernate)SpringContextHolder.getApplicationContext().getBean("callWebServiceDaoHibernate");
		List allList  = cwsdh.getAllDataByWhere("");
		
		List tempList = null;
		if(allList != null && allList.size() != 0) {
			tempList = new ArrayList();
			cwsList = new ArrayList();
			for(int i=0;i<allList.size();i++) {
				tempList.add(((CallWebService)allList.get(i)));
			}
			cwsList.addAll(tempList);
		}
	}
	
	public void addCallWebService(CallWebService cws) {
		System.out.println("添加缓存");
		cwsList.add(cws);
	}
	
	public void updateCallWebService(CallWebService cws) {
		System.out.println("更新缓存");
		for(int i=0;i<cwsList.size();i++) {
			CallWebService dd = cwsList.get(i);
			if(dd != null) {
				if(dd.getId().equals(cws.getId())) {
					cwsList.remove(i);
					cwsList.add(cws);
					break;
				}
			}
		}	
	}
	
	public void deleteCallWebService(CallWebService cws) {
		System.out.println("删除缓存");
		for(int i=0;i<cwsList.size();i++) {
			CallWebService dd = cwsList.get(i);
			if(dd != null) {
				if(dd.getId().equals(cws.getId())) {
					cwsList.remove(i);
					break;
				}
			}
		}		
	}
	
	private SystemConfig systemConfig;

	public SystemConfig getSystemConfig() {
		if(systemConfig == null) {
			initSystemConfig();
		}
		return systemConfig;
	}

	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}
	
	public void initSystemConfig() {
		SystemConfigDaoHibernate scdh = (SystemConfigDaoHibernate)SpringContextHolder.getApplicationContext().getBean("systemConfigDaoHibernate");
		List<SystemConfig> list = scdh.getAllDataByWhere("");
		if(list != null) {
			if(list.size() != 0) {
				systemConfig = list.get(0);
			}			
		}	
	}
	
	private EmailConfig emailConfig;

	public EmailConfig getEmailConfig() {
		if(emailConfig == null) {
			initEmailConfig();
		}
		return emailConfig;
	}

	public void setEmailConfig(EmailConfig emailConfig) {
		this.emailConfig = emailConfig;
	}	
	
	public void initEmailConfig() {
		emailConfig = new EmailConfig();
		String config_email = SysCache.class.getResource("/").getPath()+"config-email.properties";	
		String serverHost = PropertyFileUtil.getValue(config_email, "serverHost");
		String serverPort = PropertyFileUtil.getValue(config_email, "serverPort");
		String fromAddress = PropertyFileUtil.getValue(config_email, "fromAddress");
		String pwd = PropertyFileUtil.getValue(config_email, "pwd");
		emailConfig.setServerHost(serverHost);
		emailConfig.setServerPort(serverPort);
		emailConfig.setFromAddress(fromAddress);
		emailConfig.setPwd(pwd);
	}
}
