package com.cloud.base.tag.other;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.SpringContextHolder;
import com.cloud.uploadpic.dao.UploadPicDaoHibernate;
import com.cloud.uploadpic.model.UploadPic;

/**
 * 截取
 * @author cloud7
 *
 */
public class SubString extends TagSupport {

	private String value;							
	
	private String begin;
	
	private String end;
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			if(value.length() > Integer.parseInt(end)) {
				String result = "<span title='"+value+"'>"+value.substring(Integer.parseInt(begin), Integer.parseInt(end))+"...</span>";
				out.print(result);
			} else {
				out.print(value);
			}
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

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
}
