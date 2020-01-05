package com.cloud.callwebservice.util;

import java.util.List;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.AxisWebServiceUtil;
import com.cloud.base.util.CXFWebServiceUtil;
import com.cloud.base.util.LoggerUtil;
import com.cloud.callwebservice.model.CallWebService;
import com.cloud.datadict.util.DataDictUtil;

/**
 * 调用webservice的辅助工具类
 * @author cuiyp
 * 调用例子：
 * CallWSUtil.callInvoke("axisDemoServiceId", "testDemoWS", new Object[]{});
 */
public class CallWSUtil {

	/**
	 * 通过beanId得到缓存中的对象
	 * @param beanId
	 * @return
	 */
	public static CallWebService getBean(String beanId) {
		CallWebService cws = null;
		List<CallWebService> list = SysCache.getInstance().getCwsList();
		if(list != null) {
			if(list.size() != 0) {
				for(CallWebService ws : list) {
					if(ws.getBeanId().equals(beanId)) {					//表示找到了调用的webservice对象
						cws = ws;
						break;
					}
				}
			}				
		}	
		
		if(cws == null) {
			LoggerUtil.info(CallWSUtil.class, "没有找到beanId为"+beanId+"的对象");
		}
		return cws;
	}
	
	/**
	 * 调用webservice
	 * @param beanId
	 * @param methodName
	 * @param paramArr
	 * @return
	 */
	public static String callInvoke(String beanId , String methodName , Object[] paramArr) {
		String callResult = "";
		CallWebService cws = getBean(beanId);
		if(cws != null) {
			String value = DataDictUtil.value2label(cws.getCallType(), "callwebservice", "radio", "callType");
			if(value.equalsIgnoreCase("CXF")) {			//表示是CXF方式调用
				callResult = CXFWebServiceUtil.callInvoke(cws.getWsdl(), methodName, paramArr);
			} else if(value.equalsIgnoreCase("AXIS")) {	//表示是AXIS
				callResult = AxisWebServiceUtil.callInvoke(cws.getWsdl(), methodName, paramArr);
			}
		}
		return callResult;
	}
}
