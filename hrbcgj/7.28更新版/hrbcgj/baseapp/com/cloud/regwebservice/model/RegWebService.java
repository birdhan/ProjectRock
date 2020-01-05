package com.cloud.regwebservice.model;

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
 * 代码创建时间：2014-02-08 14:36:01
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
@Table(name="SYSTEM_REGWEBSERVICE")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SYSTEM_REGWEBSERVICE",			//数据表名
	indexes={
		@Index(name="SYSTEM_REGWEBSERVICE_INTERFACENO",columnNames={"INTERFACENO"}),
		@Index(name="SYSTEM_REGWEBSERVICE_CLASSNAME",columnNames={"CLASSNAME"}),
		@Index(name="SYSTEM_REGWEBSERVICE_STATUS",columnNames={"STATUS"})
	}
)
public class RegWebService {

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
	 * 接口编号
	 */
	@PropertyDesc(name="接口编号")
	@ValidatePro(validate="{required:'true',normalText:'true',maxlength:'20'}")
	@Column(name="INTERFACENO",columnDefinition="VARCHAR(20)")
	private String interfaceNo;

	public String getInterfaceNo() {
		return interfaceNo;
	}

	public void setInterfaceNo(String interfaceNo){
		this.interfaceNo = interfaceNo;
	}

	/**
	 * 类名
	 */
	@PropertyDesc(name="类名")
	@ValidatePro(validate="{required:'true',maxlength:'200'}")
	@Column(name="CLASSNAME",columnDefinition="VARCHAR(200)")
	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className){
		this.className = className;
	}

	/**
	 * 状态
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
	 * 备注
	 */
	@PropertyDesc(name="备注")
	@ValidatePro(validate="{required:'true',maxlength:'200'}")
	@Column(name="REMARK",columnDefinition="VARCHAR(200)")
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

}
