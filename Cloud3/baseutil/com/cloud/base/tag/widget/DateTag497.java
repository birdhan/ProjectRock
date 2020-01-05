package com.cloud.base.tag.widget;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.DateHelper;
import com.cloud.base.util.StringUtil;

public class DateTag497 extends TagSupport {

	private String property;
	private String value;
	private String style;
	private String format;
	private String validate;
	
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
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String d_value = StringUtil.null2String(value);
			
			if(!d_value.equals("")) {
				if(d_value.toUpperCase().indexOf("CST") != -1) {		//表示是Sat Mar 09 11:15:20 CST 2013格式的
					SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH); 
					Date date = df1.parse(d_value);   
			        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");   
					d_value = df.format(date);
				}
				Date dd = DateHelper.dateFormat(d_value);
				d_value = sdf.format(dd);
			}
			
			String contextPath = SysCache.getInstance().getContextPath();
			if(StringUtil.null2String(style).equals("")) {
				style = "background:#fff url("+contextPath+"/javascript/My97DatePicker/skin/datePicker.gif) no-repeat right;";
			} else {
				style = "background:#fff url("+contextPath+"/javascript/My97DatePicker/skin/datePicker.gif) no-repeat right;" + style;
			}
			
			JspWriter out = pageContext.getOut();
			String tag = "<input class=\"\" type=\"text\" name=\""+property+"\" id=\""+property+"\" readonly=\"readonly\" onClick=\"WdatePicker({dateFmt:'"+format+"'})\" value=\""+d_value+"\" style=\""+StringUtil.null2String(style)+"\" validate=\"" + validate + "\"/>";
			out.print(tag);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
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
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getValidate() {
		return validate;
	}
	public void setValidate(String validate) {
		this.validate = validate;
	}
}
