package com.cloud.menumanager.executewebservice;

import com.cloud.base.util.SpringContextHolder;
import com.cloud.base.util.XMLModelUtil;
import com.cloud.base.webserviceinterface.IExecuteWebService;
import com.cloud.menumanager.dao.MenuDaoHibernate;

public class MenuWS implements IExecuteWebService {

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
		MenuDaoHibernate dmh = (MenuDaoHibernate)SpringContextHolder.getApplicationContext().getBean("menuDaoHibernate");
		try {			
			result = XMLModelUtil.toXML4Text(dmh.getAllDataByWhere(where));
		} catch (Exception e) {
			result = e.getLocalizedMessage();
		}		
		return result;
	}

}
