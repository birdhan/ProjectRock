package com.cloud.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloud.systemuser.model.SystemUser;

public class ValidationLoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		SystemUser su = (SystemUser)req.getSession().getAttribute("user");
		if(su == null || su.getId() == null) {
			res.getWriter().print("<script language='javascript'>alert('会话超时，请重新登录！');parent.location.href='"+req.getContextPath()+"'</script>");
		} else {
			chain.doFilter(request, response);
		}		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
