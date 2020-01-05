package com.cloud.uploadpic.model;

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
 * @author cuiyunpeng
 * 代码创建时间：2014-05-07 11:36:23
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
@Table(name="SYSTEM_UPLOADPIC")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SYSTEM_UPLOADPIC",			//数据表名
	indexes={
		@Index(name="SYSTEM_UPLOADPIC_ORINAME",columnNames={"ORINAME"}),
		@Index(name="SYSTEM_UPLOADPIC_UPLOADUSERID",columnNames={"UPLOADUSERID"})
	}
)
public class UploadPic {

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
	 * 原文件名
	 */
	@PropertyDesc(name="原文件名")
	@ValidatePro(validate="{required:'true',maxlength:'300'}")
	@Column(name="ORINAME",columnDefinition="VARCHAR(300)")
	private String oriName;

	public String getOriName() {
		return oriName;
	}

	public void setOriName(String oriName){
		this.oriName = oriName;
	}

	/**
	 * 服务器文件名
	 */
	@PropertyDesc(name="服务器文件名")
	@ValidatePro(validate="{required:'true',maxlength:'1000'}")
	@Column(name="SERNAME",columnDefinition="VARCHAR(1000)")
	private String serName;

	public String getSerName() {
		return serName;
	}

	public void setSerName(String serName){
		this.serName = serName;
	}

	/**
	 * 保存路径
	 */
	@PropertyDesc(name="保存路径")
	@ValidatePro(validate="{required:'true',maxlength:'1000'}")
	@Column(name="SAVEPATH",columnDefinition="VARCHAR(1000)")
	private String savePath;

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath){
		this.savePath = savePath;
	}

	/**
	 * 上传时间
	 */
	@PropertyDesc(name="上传时间")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="UPLOADTIME",columnDefinition="TIMESTAMP")
	private java.util.Date uploadTime;

	public java.util.Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(java.util.Date uploadTime){
		this.uploadTime = uploadTime;
	}

	/**
	 * 上传人员
	 */
	@PropertyDesc(name="上传人员")
	@ValidatePro(validate="{required:'true',maxlength:'100'}")
	@Column(name="UPLOADUSERID",columnDefinition="VARCHAR(100)")
	private String uploadUserId;

	public String getUploadUserId() {
		return uploadUserId;
	}

	public void setUploadUserId(String uploadUserId){
		this.uploadUserId = uploadUserId;
	}

}
