package com.cloud.base.test.other;

import org.apache.commons.httpclient.NameValuePair;

import com.cloud.base.util.HttpUtil;

public class HttpTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		HttpClient client = new HttpClient();
//        GetMethod get = new GetMethod("http://127.0.0.1:8080/Haier/customer/regeditCustomer.action?name=qqq&password=098");
//        client.executeMethod(get);
//        System.out.println(get.getResponseBodyAsString());
        
//        String jsonData = "{\"departNo\":\"001\",\"id\":\"e6694846-ef9f-47ad-bf49-3dde5ed4adb4\",\"password\":\"jet\",\"userId\":\"jet\",\"userName\":\"李连杰\"}";
//        NameValuePair[] nvp = {new NameValuePair("jsonData",jsonData)};
//        System.out.println(HttpUtil.postExecute("http://127.0.0.1:8080/Cloud3/systemuser/saveSystemUser4Json.action",nvp));
        
//		NameValuePair[] nvp = {new NameValuePair("id","e6694846-ef9f-47ad-bf49-3dde5ed4adb41")};
//        System.out.println(HttpUtil.postExecute("http://127.0.0.1:8080/Cloud3/systemuser/editSystemUser4Json.action",nvp));
        
//        NameValuePair[] nvp = {new NameValuePair("ids","2bceebc1-d2ac-475b-b37e-48e7138078a0,1f9caf01-4a05-4b72-a2d7-cd283b596946")};
//        System.out.println(HttpUtil.postExecute("http://127.0.0.1:8080/Cloud3/systemuser/delSystemUserByIds4Json.action",nvp));
		
        NameValuePair[] nvp = {new NameValuePair("p","1")};
        System.out.println(HttpUtil.postExecute("http://127.0.0.1:8080/Cloud3/systemuser/searchSystemUser4Json.action",nvp));
	}

}
