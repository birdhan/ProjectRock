package com.cityinspector.excelfile.model;

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
 * @author cuiyp
 * 代码创建时间：2018-07-16 10:18:39
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
@Table(name="SERVICE_EXCELFILE")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SERVICE_EXCELFILE",			//数据表名
	indexes={
		@Index(name="EXCELFILE_NAME",columnNames={"NAME"})
	}
)
public class ExcelFile {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@PropertyDesc(name="主键")
	@Column(name="ID",columnDefinition="VARCHAR(36)")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	/**
	 * 文件名称
	 */
	@PropertyDesc(name="文件名称")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="NAME",columnDefinition="VARCHAR(50)")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	/**
	 * 上传时间
	 */
	@PropertyDesc(name="上传时间")
	@ValidatePro(validate="{date:'true',maxlength:'50'}")
	@Column(name="UPLOADTIME",columnDefinition="TIMESTAMP")
	private java.util.Date uploadtime;

	public java.util.Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(java.util.Date uploadtime){
		this.uploadtime = uploadtime;
	}

	/**
	 * 附件
	 */
	@PropertyDesc(name="附件")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="ATTACHMENTFILEID",columnDefinition="VARCHAR(1000)")
	private String attachmentfileid;

	public String getAttachmentfileid() {
		return attachmentfileid;
	}

	public void setAttachmentfileid(String attachmentfileid){
		this.attachmentfileid = attachmentfileid;
	}

}
