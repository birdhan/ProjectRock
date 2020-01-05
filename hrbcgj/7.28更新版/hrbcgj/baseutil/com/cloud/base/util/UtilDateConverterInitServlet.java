package com.cloud.base.util;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.beanutils.ConvertUtils;

public class UtilDateConverterInitServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UtilDateConverterInitServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
	}

	@Override
	public void init() throws ServletException {
		ConvertUtils.register(new UtilDateConverter(), Date.class);
	}

}
