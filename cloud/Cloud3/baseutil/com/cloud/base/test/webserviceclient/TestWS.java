package com.cloud.base.test.webserviceclient;

import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class TestWS {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		testAxis();
		testCXF();
//		System.out.println(System.getProperty("java.endorsed.dirs"));

	}
	
	public static void testAxis() throws Exception {
		String endPoint = "http://127.0.0.1:8888/Cloud3/axisservices/DemoService?wsdl";
		String operation = "testDemoWS";
		Service service = new Service();
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endPoint));
			call.setOperationName(operation);	
			String xml = (String) call.invoke(new Object[] {});
			System.out.println("AXIS: "+URLDecoder.decode(xml,"UTF-8"));
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
	}
	
	public static void testCXF() throws Exception {
		JaxWsDynamicClientFactory clientFactory2 = JaxWsDynamicClientFactory.newInstance();  
		Client client2 = clientFactory2.createClient("http://127.0.0.1:8888/Cloud3/cxfservices/DemoService?wsdl");
		Object[] paramArr1 = {};
		Object[] result2 = client2.invoke("testDemoWS", paramArr1);  
		System.out.println("CXF:"+result2[0]);
	}

}
