package com.cloud.base.tag.widget;

import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import net.sf.json.JSONObject;

public class DateTag extends TagSupport {
	
	private String property;
	private String value;
	private String validate;
	private String ligerui;
	
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
			if(ligerui == null || ligerui.equals("")) {
				ligerui = "{format:'yyyy-MM-dd hh:mm:ss'}";
			}
			if(!value.equals("")) {
				JSONObject object = JSONObject.fromObject(ligerui);
				String format = (String)object.get("format");
				format = format.replaceAll("hh", "HH");
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				value = sdf.format(sdf.parse(value));
			}
			sb.append("<input type=\"text\" id=\""+property+"\" name=\""+property+"\" value=\"" + value + "\" ligerui=\"" +ligerui+ "\" validate=\""+validate+"\" ltype=\"date\"/>");
			out.print(sb.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
	public String getLigerui() {
		return ligerui;
	}
	public void setLigerui(String ligerui) {
		this.ligerui = ligerui;
	}
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
