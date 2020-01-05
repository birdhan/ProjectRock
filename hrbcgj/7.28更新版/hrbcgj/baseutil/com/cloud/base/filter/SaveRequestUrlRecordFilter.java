package com.cloud.base.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.cityinspector.registeruser.model.RegisterUser;
import com.cloud.base.util.SpringContextHolder;
import com.cloud.requestrecord.model.RequestRecord;
import com.cloud.requestrecord.service.RequestRecordServiceImpl;
import com.cloud.systemuser.model.SystemUser;

public class SaveRequestUrlRecordFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		
		String requestUri = request.getRequestURI();
		if(requestUri.indexOf("/requestrecord/searchRequestRecord.do") != -1 
				|| requestUri.indexOf("/requestrecord/detailRequestRecord.do") != -1
				|| requestUri.indexOf("/requestrecord/delRequestRecordById.do") != -1
				|| requestUri.indexOf("/requestrecord/delRequestRecordByIds.do") != -1
				|| requestUri.indexOf("/requestrecord/success.do") != -1) {
			arg2.doFilter(arg0, arg1);
			return;
		}
		
		if(requestUri.indexOf(".do") != -1 || requestUri.indexOf(".action") != -1 || requestUri.indexOf(".") == -1) {
			RequestRecord rr = new RequestRecord();
			SystemUser su = (SystemUser)request.getSession().getAttribute("user");
			if(su != null) {
				rr.setUseraccount(su.getUserId());
				rr.setUsertype("后台用户");
			} else {
				RegisterUser ru = (RegisterUser)request.getSession().getAttribute("webSiteLoginUser");
				if(ru == null) {
					rr.setUseraccount("未登录用户");
				} else {
					rr.setUseraccount(ru.getAccount());
					rr.setUsertype("前台用户");
				}
			}
			
			RequestRecordServiceImpl rrsi = (RequestRecordServiceImpl)SpringContextHolder.getApplicationContext().getBean("requestRecordServiceImpl");
			String queryStr = request.getQueryString();
			if(StringUtils.isBlank(queryStr)) {
				rr.setRequesturi(requestUri);
			} else {
				rr.setRequesturi(requestUri + "?" + queryStr);
			}
			rr.setFormip(request.getRemoteHost());
			rr.setReqtime(new Date());
			rrsi.saveRequestRecord(rr);
		}
		
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
