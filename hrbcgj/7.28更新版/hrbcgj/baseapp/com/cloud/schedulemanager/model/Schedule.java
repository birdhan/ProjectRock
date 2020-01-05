package com.cloud.schedulemanager.model;

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
 * @author cloudwork
 * 代码创建时间：2013-11-15 13:52:33
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
@Table(name="SYSTEM_SCHEDULE")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SYSTEM_SCHEDULE",			//数据表名
	indexes={
		@Index(name="SCHEDULE_CLASSTYPE",columnNames={"CLASSTYPE"}),
		@Index(name="SCHEDULE_STATUS",columnNames={"STATUS"})
	}
)
public class Schedule {

	/**
	 *主键
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
	 *执行的类
	 */
	@PropertyDesc(name="执行的类")
	@ValidatePro(validate="{required:'true',maxlength:'200'}")
	@Column(name="CLASSTYPE",columnDefinition="VARCHAR(200)")
	private String classType;

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType){
		this.classType = classType;
	}

	/**
	 *执行频率
	 */
	@PropertyDesc(name="执行频率")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="FREQUENCY",columnDefinition="VARCHAR(50)")
	private String frequency;

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency){
		this.frequency = frequency;
	}

	/**
	 *状态
	 */
	@PropertyDesc(name="状态")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="STATUS",columnDefinition="VARCHAR(50)")
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	/**
	 *备注
	 */
	@PropertyDesc(name="备注")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="REMARK",columnDefinition="VARCHAR(250)")
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

}
