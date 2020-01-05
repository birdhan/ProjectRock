package com.cloud.webservice.webservice;

import javax.jws.WebService;

/**
 * @author cloudwork
 *
 */
@WebService
public interface IWebServiceWS {

	/**
	 * webservice的总入口方法
	 * @param interfaceNo：接口编号
	 * @param param：参数
	 * @return
	 */
	public String webServiceIndex(String interfaceNo ,String method , String param);

}
