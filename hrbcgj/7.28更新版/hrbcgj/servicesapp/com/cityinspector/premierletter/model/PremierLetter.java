package com.cityinspector.premierletter.model;

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
 * 代码创建时间：2018-07-20 11:14:47
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
@Table(name="SERVICE_PREMIERLETTER")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SERVICE_PREMIERLETTER",			//数据表名
	indexes={
		@Index(name="SERVICE_PREMIERLETTER_TITLE",columnNames={"TITLE"}),
		@Index(name="SERVICE_PREMIERLETTER_REQREGISTERUSER",columnNames={"REQREGISTERUSER"})
	}
)
public class PremierLetter {

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
	 * 标题
	 */
	@PropertyDesc(name="标题")
	@ValidatePro(validate="{required:'true',maxlength:'300'}")
	@Column(name="TITLE",columnDefinition="VARCHAR(300)")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * 邮件内容
	 */
	@PropertyDesc(name="邮件内容")
	@ValidatePro(validate="{required:'true',maxlength:'1000'}")
	@Column(name="REQCONTENT",columnDefinition="VARCHAR(1000)")
	private String reqcontent;

	public String getReqcontent() {
		return reqcontent;
	}

	public void setReqcontent(String reqcontent){
		this.reqcontent = reqcontent;
	}

	/**
	 * 申请时间
	 */
	@PropertyDesc(name="申请时间")
	@ValidatePro(validate="{date:'true',maxlength:'50'}")
	@Column(name="REQTIME",columnDefinition="TIMESTAMP")
	private java.util.Date reqtime;

	public java.util.Date getReqtime() {
		return reqtime;
	}

	public void setReqtime(java.util.Date reqtime){
		this.reqtime = reqtime;
	}

	/**
	 * 申请人
	 */
	@PropertyDesc(name="申请人")
	@ValidatePro(validate="{required:'true',maxlength:'200'}")
	@Column(name="REQREGISTERUSER",columnDefinition="VARCHAR(200)")
	private String reqregisteruser;

	public String getReqregisteruser() {
		return reqregisteruser;
	}

	public void setReqregisteruser(String reqregisteruser){
		this.reqregisteruser = reqregisteruser;
	}

	/**
	 * 状态
	 */
	@PropertyDesc(name="状态")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="REPSTATUS",columnDefinition="VARCHAR(50)")
	private String repstatus;

	public String getRepstatus() {
		return repstatus;
	}

	public void setRepstatus(String repstatus){
		this.repstatus = repstatus;
	}

	/**
	 * 回复内容
	 */
	@PropertyDesc(name="回复内容")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="REPCONTENT",columnDefinition="VARCHAR(1000)")
	private String repcontent;

	public String getRepcontent() {
		return repcontent;
	}

	public void setRepcontent(String repcontent){
		this.repcontent = repcontent;
	}

	/**
	 * 回复时间
	 */
	@PropertyDesc(name="回复时间")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="REPTIME",columnDefinition="VARCHAR(100)")
	private String reptime;

	public String getReptime() {
		return reptime;
	}

	public void setReptime(String reptime){
		this.reptime = reptime;
	}

}
