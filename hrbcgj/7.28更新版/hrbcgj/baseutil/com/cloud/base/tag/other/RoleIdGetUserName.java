package com.cloud.base.tag.other;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cloud.base.util.SpringContextHolder;
import com.cloud.roleuser.dao.RoleUserDaoHibernate;

public class RoleIdGetUserName extends TagSupport {

	private String value;
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			String result = "";
			if(!value.equals("")) {
				RoleUserDaoHibernate rudh = (RoleUserDaoHibernate)SpringContextHolder.getApplicationContext().getBean("roleUserDaoHibernate");
				result = rudh.getUserNamesByRoleId(value);
			}
			out.print(result);			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
