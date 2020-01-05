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
@Table(name="SYSTEM_ATTACHMENT")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SYSTEM_ATTACHMENT",			//数据表名
	indexes={
		@Index(name="SYSTEM_ATTACHMENT_orgfilename",columnNames={"orgfilename"})
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
	 * 原文件名称
	 */
	@PropertyDesc(name="原文件名称")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="orgfilename",columnDefinition="VARCHAR(100)")
	public String orgfilename;


	/**
	 * 服务器文件名称
	 */
	@PropertyDesc(name="服务器文件名称")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="serverfilename",columnDefinition="VARCHAR(100)")
	public String serverfilename;


	/**
	 * 存储路径
	 */
	@PropertyDesc(name="存储路径")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="savepath",columnDefinition="VARCHAR(200)")
	public String savepath;


	/**
	 * 上传时间
	 */
	@PropertyDesc(name="上传时间")
	@ValidatePro(validate="{required:'false'}")
	public java.util.Date uploadtime;
	
	/**
	 * 文件大小
	 */
	@PropertyDesc(name="文件大小")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="filesize",columnDefinition="VARCHAR(50)")
	public String filesize;
	
	/**
	 * 相对路径
	 */
	@PropertyDesc(name="相对路径")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="relativepath",columnDefinition="VARCHAR(200)")
	public String relativepath;

	public String getOrgfilename() {
		return orgfilename;
	}

	public void setOrgfilename(String orgfilename) {
		this.orgfilename = orgfilename;
	}

	public String getServerfilename() {
		return serverfilename;
	}

	public void setServerfilename(String serverfilename) {
		this.serverfilename = serverfilename;
	}

	public String getSavepath() {
		return savepath;
	}

	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}

	public java.util.Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(java.util.Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getRelativepath() {
		return relativepath;
	}

	public void setRelativepath(String relativepath) {
		this.relativepath = relativepath;
	}
	
	@Transient
	private String uploadTimeStr;

	public String getUploadTimeStr() {
		return uploadTimeStr;
	}

	public void setUploadTimeStr(String uploadTimeStr) {
		this.uploadTimeStr = uploadTimeStr;
	}

}
