package com.cloud.base.tag.id2name;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cloud.base.util.SpringContextHolder;
import com.cloud.menumanager.dao.MenuDaoHibernate;
import com.cloud.menumanager.model.Menu;

public class MenuId2Name extends TagSupport {

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
			if(value.equals("root")) {
				result = "菜单根路径";
			} else {
				MenuDaoHibernate mdh = (MenuDaoHibernate)SpringContextHolder.getApplicationContext().getBean("menuDaoHibernate");
				Menu menu = mdh.getMenuById(value);
				result = menu.getMenuName(); 
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
