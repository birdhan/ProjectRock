package com.cloud.base.tag.other;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class BreakPageTag extends TagSupport {

	private String curPage;
	private String pageSize;
	private String url;
	private String formId;
	private String totalNum;
	
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
			StringBuffer tag = new StringBuffer("<script type=\"text/javascript\">");
			tag.append("var curPage = parseInt("+curPage+");");
			tag.append("var pageSize = parseInt("+pageSize+");");
			tag.append("var url = \""+url+"\";");
			tag.append("var formId = \""+formId+"\";");
			tag.append("var totalNum = \""+totalNum+"\";");
			tag.append("commonBreakPage(url,curPage,pageSize,formId,totalNum);");
			tag.append("</script>");
			out.print(tag.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	
}
