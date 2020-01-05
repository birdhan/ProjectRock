package com.cloud.base.tag.widget;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.SpringContextHolder;
import com.cloud.base.util.StringUtil;
import com.cloud.department.service.DepartmentServiceImpl;

/**
 * 部门树
 *
 */
public class DeptTree extends TagSupport {
	
	private String property;
	private String value; 
	private String single;
	private String autoCheckboxEven;
	private String checkbox;
	
	@Override
	public int doEndTag() throws JspException {
		try {
		} catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			String divHTML = "";
			if(!StringUtil.null2String(value.trim()).equals("")) {
				DepartmentServiceImpl dsi = (DepartmentServiceImpl)SpringContextHolder.getApplicationContext().getBean("departmentServiceImpl");
				String deptName = dsi.deptNo2deptName(value);
				if(!deptName.equals("")) {
					for(String dn : deptName.split(",")) {
						divHTML += "<img src='"+SysCache.getInstance().getContextPath()+"/images/dept.png' style='margin-right:2px;'/>&nbsp;"+dn+"<br/>";
					}
				}
			} else {
				divHTML = "请选择部门";
			} 
			sb.append("<div id=\""+property+"Div\" style=\"display: block;color:#666666;\">"+divHTML+"</div>");
			sb.append("<input type=\"button\" value=\"选择部门\" class=\"btn_list\" onclick=\"openDeptTree('"+property+"','"+single+"','"+autoCheckboxEven+"','"+checkbox+"')\" style=\"margin-top: 3px;\"/>");
			sb.append("<input name=\""+property+"\" type=\"hidden\" id=\""+property+"\" value=\""+value+"\"/>");
			out.print(sb.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
	
	public String getCheckbox() {
		return checkbox;
	}
	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}
	public String getAutoCheckboxEven() {
		return autoCheckboxEven;
	}
	public void setAutoCheckboxEven(String autoCheckboxEven) {
		this.autoCheckboxEven = autoCheckboxEven;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getSingle() {
		return single;
	}
	public void setSingle(String single) {
		this.single = single;
	}
}
