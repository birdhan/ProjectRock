package com.cloud.callwebservice.model;

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
 * 代码创建时间：2013-11-17 09:42:35
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
@Table(name="SYSTEM_CALLWEBSERVICE")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SYSTEM_CALLWEBSERVICE",			//数据表名
	indexes={
		@Index(name="CALLWEBSERVICE_WSDL",columnNames={"WSDL"}),
		@Index(name="CALLWEBSERVICE_CALLTYPE",columnNames={"CALLTYPE"})
	}
)
public class CallWebService {

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
	 *wsdl地址
	 */
	@PropertyDesc(name="wsdl地址")
	@ValidatePro(validate="{required:'true',maxlength:'1000'}")
	@Column(name="WSDL",columnDefinition="VARCHAR(1000)")
	private String wsdl;

	public String getWsdl() {
		return wsdl;
	}

	public void setWsdl(String wsdl){
		this.wsdl = wsdl;
	}

	/**
	 *调用类型
	 */
	@PropertyDesc(name="调用类型")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="CALLTYPE",columnDefinition="VARCHAR(50)")
	private String callType;

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType){
		this.callType = callType;
	}

	/**
	 *beanId
	 */
	@PropertyDesc(name="beanId")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="BEANID",columnDefinition="VARCHAR(50)")
	private String beanId;

	public String getBeanId() {
		return beanId;
	}

	public void setBeanId(String beanId){
		this.beanId = beanId;
	}

	/**
	 *备注
	 */
	@PropertyDesc(name="备注")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="REMARK",columnDefinition="VARCHAR(200)")
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

}
