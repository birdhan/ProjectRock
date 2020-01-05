package com.cloud.base.tag.other;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.SpringContextHolder;
import com.cloud.uploadpic.dao.UploadPicDaoHibernate;
import com.cloud.uploadpic.model.UploadPic;

/**
 * 上传图片标签插件
 * @author cloud7
 *
 */
public class UploadPicTag extends TagSupport {

	private String imgId;							//隐藏域的id属性值
	
	private String picFileId;						//图片上传后保存在数据库的唯一标识id
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			StringBuffer result = new StringBuffer();
			result.append("<script language=\"javascript\">");
			result.append("var uploadPicWin_"+imgId+";");
			result.append("function uploadPic_"+imgId+"() {");
			result.append("	var pfi = getEleById('"+imgId+"').value;");
			result.append("	uploadPicWin_"+imgId+" = $.ligerDialog.open(");
			result.append("	{title:\"上传图片\", url: \""+SysCache.getInstance().getContextPath()+"/uploadpic/toUploadPic.do?picFileId=\"+pfi+\"&imgId="+imgId+"&closeWinMethod=closeUploadPicWin_"+imgId+"\", height: 370, width: 500, name:'uploadPicWin_"+imgId+"Iframe',isResize: true,isDrag:true,id:'uploadPicWin_"+imgId+"Dialog'}");
			result.append("	);");
			result.append("}");
			result.append("function closeUploadPicWin_"+imgId+"() {");
			result.append("	if(uploadPicWin_"+imgId+" != null) {");
			result.append("		uploadPicWin_"+imgId+".close();");
			result.append("	}");
			result.append("}");
			result.append("</script>");
			
			String src = "src=\""+SysCache.getInstance().getContextPath()+"/images/uppic.jpg\"";
			if(!picFileId.equals("")) {
				UploadPicDaoHibernate updh = (UploadPicDaoHibernate)SpringContextHolder.getApplicationContext().getBean("uploadPicDaoHibernate");
				UploadPic up = updh.getUploadPicById(picFileId);
				if(up != null) {
					src = "src=\""+SysCache.getInstance().getContextPath()+"/uploadpic/getPic.do?id="+picFileId+"\"";
				}				
			}
			result.append("<img id=\""+imgId+"Img\" "+src+" style=\"cursor: pointer;width:232px;height:162px;\" onclick=\"uploadPic_"+imgId+"()\" title=\"点击图片可更换\"/>");
			result.append("<input name=\""+imgId+"\" type=\"hidden\" id=\""+imgId+"\" value=\""+picFileId+"\"/>");
			
			out.print(result.toString());			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getPicFileId() {
		return picFileId;
	}

	public void setPicFileId(String picFileId) {
		this.picFileId = picFileId;
	}

}
