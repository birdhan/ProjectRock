package com.cloud.webservice.webservice;

import java.lang.reflect.Method;
import java.util.List;

import com.cloud.base.util.AxisWebServiceUtil;
import com.cloud.base.util.CXFWebServiceUtil;
import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.SpringContextHolder;
import com.cloud.base.util.StringUtil;
import com.cloud.regwebservice.dao.RegWebServiceDaoHibernate;
import com.cloud.regwebservice.model.RegWebService;

/**
 * 所有Webservice总入口，系统由此一个接口与其它系统进行数据交互。
 * 注意：
 * 1.由webServiceIndex方法调用不同模块的业务数据
 * 2.注册的类中必须要有这样的方法 public String executeWebService(String interfaceNo , String method , String param)
 * @author cloud7
 *
 */
public class WebServiceWSImpl implements IWebServiceWS {

	@Override
	public synchronized String webServiceIndex(String interfaceNo ,String method , String param){
		LoggerUtil.info(getClass(), "interfaceNo:"+interfaceNo+",method:"+method+",param:"+StringUtil.str2URLDecoder(param));
		String result = "";
		try {
			/**
			 * 通过接口编号获取接口信息，如果有该接口信息并且接口状态开启
			 */
			RegWebServiceDaoHibernate rwsdh = (RegWebServiceDaoHibernate)SpringContextHolder.getApplicationContext().getBean("regWebServiceDaoHibernate");
			List<RegWebService> list = rwsdh.getAllDataByWhere(" and regWebService.interfaceNo = '"+interfaceNo+"'");
			if(list != null && list.size() != 0) {
				RegWebService rws = list.get(0);																					//得到注册类
				if(rws.getStatus().equals("0")) {																					//状态表示开启
					Class c = Class.forName(rws.getClassName());
					Object obj = c.newInstance();																					//实例对象
					Method[] methodArr = obj.getClass().getDeclaredMethods();														//找到该对象下声明的方法
					boolean hasMethod = false;																						//默认没有该方法
					for(Method m : methodArr) {																						//遍历方法找到与要调用的方法一致的名称
						if(m.getName().equals(method)) {
							hasMethod = true;
							break;
						}
					}
					if(hasMethod) {
						Method indexMethod = c.getMethod("executeWebService", new Class[]{String.class,String.class,String.class});//调用注册类中的executeWebService方法
						Object[] paramArr = {interfaceNo,method,param};
						result = (String)indexMethod.invoke(obj, paramArr);	
					} else {																										//该类下没有要调用的方法
						result = "接口编号为"+interfaceNo+"的接口类下没有"+method+"的方法";
					}
				} else {																											//状态表示关闭
					result = "接口编号为"+interfaceNo+"的接口处于关闭状态，暂时不可调用";
				}				
			} else {																												//没有找到接口编号对应的注册类
				result = "没有找到编号为"+interfaceNo+"的接口";
			}						
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}
	
	public static void main(String[] args) {
		
		Object[] paramArr0 = {"001","getAllDataByWhere",""};
		String getAllDataByWhere_result = AxisWebServiceUtil.callInvoke("http://127.0.0.1:8080/Cloud3/axisservices/webServiceIndex?wsdl", "webServiceIndex", paramArr0);
		System.out.println(getAllDataByWhere_result);
		
		Object[] paramArrM = {"003","getAllDataByWhere"," and menu.parentId='root'"};
		String paramArrM_result = CXFWebServiceUtil.callInvoke("http://127.0.0.1:8080/Cloud3/cxfservices/webServiceIndex?wsdl", "webServiceIndex", paramArrM);
		System.out.println(paramArrM_result);
		
	}

}
