package com.cloud.base.tag.id2name;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cloud.base.util.SpringContextHolder;
import com.cloud.menumanager.model.Menu;
import com.cloud.systemuser.dao.SystemUserDaoHibernate;

public class UserId2Name extends TagSupport {

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
			SystemUserDaoHibernate sudh = (SystemUserDaoHibernate)SpringContextHolder.getApplicationContext().getBean("systemUserDaoHibernate");
			String[] userId_arr = value.split(",");
			for(String uid : userId_arr) {
				result += sudh.getUserNameByUserId(uid)+",";
			}	
			if(!result.equals("")) {
				result = result.substring(0, result.length()-1);
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
