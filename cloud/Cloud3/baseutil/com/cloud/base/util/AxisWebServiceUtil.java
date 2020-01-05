package com.cloud.base.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.Logger;

/**
 * webservice工具类
 * @author cyp
 * @param <stub>
 *
 */
public class AxisWebServiceUtil {

	private static final Logger logger = Logger.getLogger(AxisWebServiceUtil.class);
	
	/**
	 * 调用webservice公共方法
	 * @param url
	 * @param methodName
	 * @param paramStr
	 * @return
	 */
	public static String callInvoke(String url,String methodName,Object[] paramArr) {
		Service service = new Service();
		String result = "";
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(url));
			call.setOperationName(methodName);
			
			if(paramArr != null) {
				result = (String) call.invoke(paramArr);
			} else {
				result = (String) call.invoke(new Object[] {});
			}			
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
		Object[] paramArr0 = {"001","getAllDataByWhere",""};
		String getAllDataByWhere_result = AxisWebServiceUtil.callInvoke("http://127.0.0.1:8888/Cloud3/axisservices/webServiceIndex?wsdl", "webServiceIndex", paramArr0);
		System.out.println(getAllDataByWhere_result);
	}
}
