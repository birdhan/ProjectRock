package com.cloud.attachment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.cloud.base.annotation.propertydesc.PropertyDesc;
import com.cloud.base.annotation.validatepro.ValidatePro;

/**
 * 说明：model实体类，类的属性上都要写上两个注解。
 * @PropertyDesc：表示是属性的描述，这个注解是给excel导出使用的。
 * @ValidatePro：表示是该属性在excel导入时候的验证json串
 * @author 丛宇滋
 * 代码创建时间：2014-04-18 15:24:17
 * 
 * ValidatePro解释：
 * required: true	--表示必填项，要求true或false
 * maxLength:10	   	--表示内容的最大长度，要求是数字
 * date:true		--表示是否为日期，要求是true或false
 * isInt:true		--表示是否为正整数，要求是大于1的正整数
 *
 */
@Entity
//表名
@Table(name="ATTACHMENT")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="ATTACHMENT",			//数据表名
	indexes={
		@Index(name="ATTACHMENT_FILENAME",columnNames={"FILENAME"})
	}
)
public class Attachment {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@PropertyDesc(name="主键")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	/**
	 * 附件名称
	 */
	@PropertyDesc(name="附件名称")
	@ValidatePro(validate="{required:'true',maxlength:'100'}")
	@Column(name="FILENAME",columnDefinition="VARCHAR(100)")
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName){
		this.fileName = fileName;
	}

	/**
	 * 附件路径
	 */
	@PropertyDesc(name="附件路径")
	@ValidatePro(validate="{required:'true',maxlength:'500'}")
	@Column(name="FILEPATH",columnDefinition="VARCHAR(500)")
	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath){
		this.filePath = filePath;
	}

	/**
	 * 附件大小
	 */
	@PropertyDesc(name="附件大小")
	@ValidatePro(validate="{required:'true',maxlength:'30'}")
	@Column(name="FILESIZE",columnDefinition="NUMBER")
	private long fileSize;

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize){
		this.fileSize = fileSize;
	}

	/**
	 * 附件类型
	 */
	@PropertyDesc(name="附件类型")
	@ValidatePro(validate="{required:'true',maxlength:'30'}")
	@Column(name="FILETYPE",columnDefinition="VARCHAR(30)")
	private String fileType;

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType){
		this.fileType = fileType;
	}

	/**
	 * 附件上传人
	 */
	@PropertyDesc(name="附件上传人")
	@ValidatePro(validate="{required:'true',maxlength:'30'}")
	@Column(name="UPLOADUSERID",columnDefinition="VARCHAR(30)")
	private String uploadUserId;

	public String getUploadUserId() {
		return uploadUserId;
	}

	public void setUploadUserId(String uploadUserId){
		this.uploadUserId = uploadUserId;
	}

	/**
	 * 附件上传时间
	 */
	@PropertyDesc(name="附件上传时间")
	@ValidatePro(validate="{date:'true',maxlength:'50'}")
	@Column(name="UPLOADTIME",columnDefinition="TIMESTAMP")
	private java.util.Date uploadTime;

	public java.util.Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(java.util.Date uploadTime){
		this.uploadTime = uploadTime;
	}

	/**
	 * 工程上下文根
	 */
	@PropertyDesc(name="工程上下文根")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="CONTEXTPATH",columnDefinition="VARCHAR(50)")
	private String contextPath;

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath){
		this.contextPath = contextPath;
	}

}
