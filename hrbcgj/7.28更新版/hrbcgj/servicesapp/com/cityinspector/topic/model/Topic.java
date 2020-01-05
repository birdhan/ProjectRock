package com.cityinspector.topic.model;

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
 * @author 王富生
 * 代码创建时间：2018-07-22 13:03:23
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
@Table(name="SERVICE_TOPIC")
public class Topic {

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
	 * 话题标题
	 */
	@PropertyDesc(name="话题标题")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="TITLE",columnDefinition="VARCHAR(50)")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * 话题内容
	 */
	@PropertyDesc(name="话题内容")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="CONTENTVALUE",columnDefinition="TEXT")
	private String contentvalue;

	public String getContentvalue() {
		return contentvalue;
	}

	public void setContentvalue(String contentvalue){
		this.contentvalue = contentvalue;
	}

	/**
	 * 创建时间
	 */
	@PropertyDesc(name="创建时间")
	@ValidatePro(validate="{required:'true',date:'true',maxlength:'50'}")
	@Column(name="CREATETIME",columnDefinition="TIMESTAMP")
	private java.util.Date createtime;

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime){
		this.createtime = createtime;
	}

	/**
	 * 是否公开
	 */
	@PropertyDesc(name="是否公开")
	@ValidatePro(validate="{required:'true',normalText:'true',maxlength:'50'}")
	@Column(name="ISSHOW",columnDefinition="VARCHAR(50)")
	private String isshow;

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow){
		this.isshow = isshow;
	}

}
