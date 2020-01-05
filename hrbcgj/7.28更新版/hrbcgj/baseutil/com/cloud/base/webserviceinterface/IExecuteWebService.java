package com.cloud.base.webserviceinterface;

public interface IExecuteWebService {

	/**
	 * webservice入口，由此方法调用相应的逻辑函数
	 * @param interfaceNo
	 * @param method
	 * @param param
	 * @return
	 */
	public String executeWebService(String interfaceNo , String method , String param);		
	
}
