package com.cloud.base.tag.widget;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.SpringContextHolder;
import com.cloud.base.util.StringUtil;
import com.cloud.department.service.DepartmentServiceImpl;
import com.cloud.systemuser.service.SystemUserServiceImpl;

/**
 * 部门树
 *
 */
public class UserTree extends TagSupport {
	
	private String property;
	private String value; 
	private String single;
	private String autoCheckboxEven;
	private String checkbox;
	private String confirmBtnClick;
	
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
			if(!StringUtil.null2String(value.trim()).equals("")) {				//初始化人员值，如果value不为空，那么就查询其name值，显示在页面中
				SystemUserServiceImpl susi = (SystemUserServiceImpl)SpringContextHolder.getApplicationContext().getBean("systemUserServiceImpl");
				String userName = susi.userId2UserName(value);
				if(!userName.equals("")) {
					for(String dn : userName.split(",")) {
						divHTML += "<img src='"+SysCache.getInstance().getContextPath()+"/images/user.gif' style='margin-right:2px;'/>&nbsp;"+dn+"<br/>";
					}
				}
			} else {
				divHTML = "请选择人员";
			} 
			confirmBtnClick = StringUtil.null2String(confirmBtnClick);
			if(confirmBtnClick.trim().equals("")) {
				confirmBtnClick = "confirmSelected()";
			}
			sb.append("<div id=\""+property+"Div\" style=\"display: block;color:#666666;\">"+divHTML+"</div>");
			sb.append("<input type=\"button\" value=\"选择人员\" class=\"btn_list\" onclick=\"openUserTree('"+property+"','"+single+"','"+autoCheckboxEven+"','"+checkbox+"','"+confirmBtnClick+"')\" style=\"margin-top: 3px;\"/>");
			sb.append("<input name=\""+property+"\" type=\"hidden\" id=\""+property+"\" value=\""+value+"\"/>");
			out.print(sb.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getConfirmBtnClick() {
		return confirmBtnClick;
	}
	public void setConfirmBtnClick(String confirmBtnClick) {
		this.confirmBtnClick = confirmBtnClick;
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
