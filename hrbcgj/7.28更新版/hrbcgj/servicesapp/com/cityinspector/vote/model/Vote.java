package com.cityinspector.vote.model;

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
 * @author ljm
 * 代码创建时间：2018-07-23 02:37:11
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
@Table(name="VOTE")
public class Vote {

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
	 * 内容
	 */
	@PropertyDesc(name="内容")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="CONTENT",columnDefinition="VARCHAR(500)")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}

	/**
	 * 开始时间
	 */
	@PropertyDesc(name="开始时间")
	@ValidatePro(validate="{date:'true',maxlength:'50'}")
	@Column(name="STARTTIME",columnDefinition="VARCHAR(50)")
	private String startTime;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime){
		this.startTime = startTime;
	}
	/**
	 * 结束时间
	 */
	@PropertyDesc(name="结束时间")
	@ValidatePro(validate="{date:'true',maxlength:'50'}")
	@Column(name="ENDTIME",columnDefinition="VARCHAR(50)")
	private String endTime;

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime){
		this.endTime = endTime;
	}

	/**
	 * 是否完成
	 */
	@PropertyDesc(name="是否完成")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="IF_FINISH",columnDefinition="VARCHAR(50)")
	private String if_Finish;

	public String getIf_Finish() {
		return if_Finish;
	}

	public void setIf_Finish(String if_Finish){
		this.if_Finish = if_Finish;
	}

	/**
	 * 类型
	 */
	@PropertyDesc(name="类型")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="TYPE",columnDefinition="VARCHAR(50)")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

}
