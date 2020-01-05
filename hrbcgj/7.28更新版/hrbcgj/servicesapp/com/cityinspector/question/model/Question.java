package com.cityinspector.question.model;

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
 * 代码创建时间：2018-07-16 13:53:15
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
@Table(name="SERVICE_QUESTION")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SERVICE_QUESTION",			//数据表名
	indexes={
		@Index(name="QUESTION_NAME",columnNames={"NAME"})
	}
)
public class Question {

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
	 * 问题名称
	 */
	@PropertyDesc(name="问题名称")
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
	 * 创建时间
	 */
	@PropertyDesc(name="创建时间")
	@ValidatePro(validate="{date:'true',maxlength:'50'}")
	@Column(name="CREATETIME",columnDefinition="TIMESTAMP")
	private java.util.Date createtime;

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime){
		this.createtime = createtime;
	}

	/**
	 * 解答内容
	 */
	@PropertyDesc(name="解答内容")
	@ValidatePro(validate="{required:'true',maxlength:'1000'}")
	@Column(name="ANSWERCONTENT",columnDefinition="VARCHAR(1000)")
	private String answercontent;

	public String getAnswercontent() {
		return answercontent;
	}

	public void setAnswercontent(String answercontent){
		this.answercontent = answercontent;
	}

}
