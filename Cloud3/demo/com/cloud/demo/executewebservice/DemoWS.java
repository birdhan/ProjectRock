package com.cloud.demo.executewebservice;

import com.cloud.base.util.SpringContextHolder;
import com.cloud.base.util.XMLModelUtil;
import com.cloud.base.webserviceinterface.IExecuteWebService;
import com.cloud.demo.dao.DemoDaoHibernate;

public class DemoWS implements IExecuteWebService {

	@Override
	public String executeWebService(String interfaceNo, String method,String param) {
		String result = "";		
		try {
			/**
			 * 此处写调用逻辑
			 */
			if(method.equals("getAllDataByWhere")) {
				result = getAllDataByWhere(param);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}
	
	public String getAllDataByWhere(String where) {
		String result = "";
		DemoDaoHibernate dmh = (DemoDaoHibernate)SpringContextHolder.getApplicationContext().getBean("demoDaoHibernate");
		try {			
			result = XMLModelUtil.toXML4Text(dmh.getAllDataByWhere(where));
		} catch (Exception e) {
			result = e.getLocalizedMessage();
		}		
		return result;
	}

}
