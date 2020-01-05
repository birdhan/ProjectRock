package com.cloud.base.tag.other;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cloud.base.cache.SysCache;

/**
 * 上传控件标签
 * @author cuiyp
 * 2018-03-29 12:54
 */
public class UploadAttachmentTag extends TagSupport {

	// 隐藏域控件ID值 *
	private String hiddenId;
	
	// 实体类对象名称 *
	private String modelName;
	
	// 存储上传附件的数据库ID值 *
	private String value;
	
	// 是否编辑模式
	private String isEdit = "false";
	
	// 允许上传文件数量
	private String attachmentNums = "1";
	
	// 允许上传的文件类型
	private String attachmentType = "";
	
	// 文件大小
	private String attachmentSize = "";
	
	@Override
	public int doStartTag() throws JspException {
		StringBuffer sb = new StringBuffer();
		sb.append("<input type='hidden' id='" + hiddenId + "' name='"+ hiddenId + "' value='" + value + "' style='background:red;width:300px;'/>");
		
		if(isEdit.equalsIgnoreCase("true"))
			sb.append("<button type='button' class='btn btn-warning' onclick='openAttachmentWin_" + hiddenId + "()'>附件操作</button>");
		
		sb.append("<table class='dataListTable' style='margin-top: 10px;width:100%;'>");
			sb.append("<thead>");
				sb.append("<tr>");
					sb.append("<th style='width: 60px;text-align:center;'>序号</th>");
					sb.append("<th style='text-align:center;'>原文件名</th>");
					sb.append("<th style='text-align:center;'>文件大小</th>");
					sb.append("<th style='text-align:center;'>上传时间</th>");
				sb.append("</tr>");
			sb.append("</thead>");
			
			sb.append("<tbody id='" + hiddenId + "TBody'>");
			sb.append("</tbody>");
		sb.append("</table>");
		
		sb.append("<script>");
			sb.append("function openAttachmentWin_" + hiddenId + "() {");
				sb.append("var hid = $('#" + hiddenId + "').val();");
				sb.append("var " + hiddenId + "Win = layer.open({");
					sb.append("id: '" + hiddenId + "Layer',");
					sb.append("maxmin: true,");
					sb.append("type: 2,");
					sb.append("title: '附件上传窗口',");
					sb.append("area: ['80%', '80%'],");
					// attachmentIds：附件ID集合多个以英文逗号分隔;attachmentNums：允许上传附件数量；attachmentType：允许上传附件格式；attachmentSize：每个附件最大容量，默认为5MB
					sb.append("content: '" + SysCache.getInstance().getContextPath()
							+ "/attachment/openAttachmentWin.action?attachmentIds='+$('#"+hiddenId+"').val()+'&attachmentNums=" + attachmentNums
							+ "&funmodulename=" + modelName + "&hiddenId=" + hiddenId 
							+ "&attachmentType=" + attachmentType + "&attachmentSize=" + attachmentSize + "',");
					sb.append("success: function(layero, index) {");
						sb.append("curPicWinIndex = index;");
					sb.append("}");
				sb.append("});");
			sb.append("}");
			
			sb.append("$(function(){");
				sb.append("var " + hiddenId + " = $('#" + hiddenId + "').val();");
				sb.append("if(" + hiddenId + ".length == 0) {");
					sb.append("$('#" + hiddenId + "TBody').append('<tr><td colspan=\"4\">暂时没有数据</td></tr>');");
				sb.append("} else {");
					sb.append("var param = new Object();");
					sb.append("param.ids = " + hiddenId + ";");
					sb.append("var url = '" + SysCache.getInstance().getContextPath() + "/attachment/getAllAttachmentFiles.action';");
					sb.append("$.ajaxJSON(url , param , function(data){");
						sb.append("$('#" + hiddenId + "TBody').html('');");
						sb.append("var attachmentDataArray = data.attachmentDataArray;");
						sb.append("for(var i=0;i<attachmentDataArray.length;i++) {");
							sb.append("var attach = attachmentDataArray[i];");
							sb.append("$('#" + hiddenId + "TBody').append('<tr><td>' + (i+1) + '</td><td><a href=\"" + SysCache.getInstance().getContextPath() + "/attachment/downLoadAttachment.action?id=' + attach.id + '\">' + attach.orgfilename + '</a></td><td>' + attach.filesize + '</td><td>' + attach.uploadTimeStr + '</td></tr>');");
						sb.append("}");
					sb.append("},function(e){});");
				sb.append("}");
			sb.append("});");
		sb.append("</script>");
		
		try {
			JspWriter out = pageContext.getOut();
			out.print(sb.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getHiddenId() {
		return hiddenId;
	}

	public void setHiddenId(String hiddenId) {
		this.hiddenId = hiddenId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	public String getAttachmentNums() {
		return attachmentNums;
	}

	public void setAttachmentNums(String attachmentNums) {
		this.attachmentNums = attachmentNums;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public String getAttachmentSize() {
		return attachmentSize;
	}

	public void setAttachmentSize(String attachmentSize) {
		this.attachmentSize = attachmentSize;
	}
}
