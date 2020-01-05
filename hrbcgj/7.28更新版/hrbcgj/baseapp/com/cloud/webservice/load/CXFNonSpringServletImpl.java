package com.cloud.webservice.load;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.SpringContextHolder;
import com.cloud.webservice.dao.WebServiceDaoHibernate;
import com.cloud.webservice.model.WebService;

/**
 * 服务启动的时候加载webservice服务
 * @author cuiyp
 *
 */
public class CXFNonSpringServletImpl extends CXFNonSpringServlet {

    @Override
    public void loadBus(ServletConfig servletConfig) {
        super.loadBus(servletConfig);
        LoggerUtil.info(getClass(), "===加载webservice===");
        
        Bus bus = getBus();
        BusFactory.setDefaultBus(bus);
        
        WebServiceDaoHibernate wsdh = (WebServiceDaoHibernate)SpringContextHolder.getApplicationContext().getBean("webServiceDaoHibernate");
        
        List list = wsdh.getAllDataByWhere(" and webService.status = '1'");												//获取开启状态的接口1:表示开启
        
        try {
        	for(int i = 0; i<list.size();i++) {
        		WebService ws = (WebService)list.get(i);
        		String className = ws.getClassName();
        		String serviceName = ws.getServiceName();
        		Object obj = Class.forName(className).newInstance();
    			Endpoint.publish(serviceName, obj);
        	}			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }    
}