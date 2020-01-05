package com.cloud.base.util;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.log4j.Logger;

/**
 * cxf客户端调用webservice
 * @author cloud7
 *
 */
public class CXFWebServiceUtil {

	private static final Logger logger = Logger.getLogger(CXFWebServiceUtil.class);
	
	/**
	 * 调用webservice公共方法
	 * @param url
	 * @param methodName
	 * @param paramStr
	 * @return
	 */
	public static String callInvoke(String url,String methodName,Object[] paramArr) {
		String result = "";
		try {
			JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();  
			Client client = clientFactory.createClient(url);
			Object[] obj = client.invoke(methodName, paramArr);  
			result = (String)obj[0];
			
		} catch (ServiceException e) {
			e.printStackTrace();	
			logger.error(e.getMessage());
			result = "error: "+e.getMessage();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error: "+e.getMessage();
		} catch (RemoteException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = "error: "+e.getMessage();
		} finally {
			return result;
		}
	}
	
	public static void main(String[] args) {
		Object[] paramArrM = {"003","getAllDataByWhere"," and menu.parentId='root'"};
		String paramArrM_result = CXFWebServiceUtil.callInvoke("http://127.0.0.1:8888/Cloud3/cxfservices/webServiceIndex?wsdl", "webServiceIndex", paramArrM);
		System.out.println(paramArrM_result);
	}
}
