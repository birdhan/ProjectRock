package com.cityinspector.problem.model;

import java.util.Date;

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
 * @author wst
 * 代码创建时间：2018-07-22 14:23:21
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
@Table(name="service_problem")
public class problem {

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
	 * 用户名
	 */
	@PropertyDesc(name="用户名")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="USERNAME",columnDefinition="VARCHAR(200)")
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	/**
	 * 联系电话
	 */
	@PropertyDesc(name="联系电话")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="PHONE",columnDefinition="VARCHAR(50)")
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	/**
	 * 标题
	 */
	@PropertyDesc(name="标题")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="TITLE",columnDefinition="VARCHAR(50)")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * 申请内容
	 */
	@PropertyDesc(name="申请内容")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="CONTENT",columnDefinition="VARCHAR(50)")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}

	/**
	 * 类别
	 */
	@PropertyDesc(name="类别")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="CATEGORY",columnDefinition="VARCHAR(50)")
	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category){
		this.category = category;
	}

	/**
	 * 申请时间
	 */
	@PropertyDesc(name="申请时间")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="TIME",columnDefinition="VARCHAR(50)")
	private java.util.Date time;

	public java.util.Date getTime() {
		return time;
	}

	public void setTime(java.util.Date date){
		this.time = date;
	}

	/**
	 * 状态
	 */
	@PropertyDesc(name="状态")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="STATUS",columnDefinition="VARCHAR(50)")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	/**
	 * 回复内容
	 */
	@PropertyDesc(name="回复内容")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="REPLY",columnDefinition="VARCHAR(50)")
	private String reply;

	public String getReply() {
		return reply;
	}

	public void setReply(String reply){
		this.reply = reply;
	}

	/**
	 * 回复时间
	 */
	@PropertyDesc(name="回复时间")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="RESPONSETIME",columnDefinition="VARCHAR(50)")
	private String responseTime;

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime){
		this.responseTime = responseTime;
	}

}
