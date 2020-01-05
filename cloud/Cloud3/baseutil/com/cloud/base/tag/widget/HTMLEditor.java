package com.cloud.base.tag.widget;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cloud.base.util.StringUtil;

public class HTMLEditor extends TagSupport{
	
	private String property;
	private String style;
	private String value;

	@Override
	public int doEndTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			sb.append("<script type=\"text/javascript\">");
			sb.append("		var " + property + ";");
			sb.append("		KindEditor.ready(function(K) {");
			sb.append("			" + property + " = K.create('textarea[name=\"" + property + "\"]', {");
			sb.append("				allowFileManager : true");
			sb.append("			});");
			sb.append("		});");
			sb.append("</script>");
			if(StringUtil.null2String(style).equals("")) {
				style = "width:800px;height:200px;visibility: hidden;";
			}
			sb.append("<textarea id=\"" + property + "\" name=\"" + property + "\" style=\"" + style + "\">" + value + "</textarea>");
			
			out.print(sb.toString());
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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
}
