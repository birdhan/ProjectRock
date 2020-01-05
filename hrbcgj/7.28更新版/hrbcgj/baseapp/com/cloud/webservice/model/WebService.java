package com.cloud.webservice.model;

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
 * 代码创建时间：2013-11-16 14:33:59
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
@Table(name="SYSTEM_WEBSERVICE")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SYSTEM_WEBSERVICE",			//数据表名
	indexes={
		@Index(name="WEBSERVICE_CLASSNAME",columnNames={"CLASSNAME"}),
		@Index(name="WEBSERVICE_SERVICENAME",columnNames={"SERVICENAME"})
	}
)
public class WebService {

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
	 *类名
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
	 *服务名
	 */
	@PropertyDesc(name="服务名")
	@ValidatePro(validate="{required:'true',maxlength:'100'}")
	@Column(name="SERVICENAME",columnDefinition="VARCHAR(100)")
	private String serviceName;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName){
		this.serviceName = serviceName;
	}

	/**
	 *状态
	 */
	@PropertyDesc(name="状态")
	@ValidatePro(validate="{required:'true',maxlength:'2'}")
	@Column(name="STATUS",columnDefinition="VARCHAR(2)")
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
	@Column(name="REMARK",columnDefinition="VARCHAR(50)")
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}
	
	/**
	 * wsdl地址
	 */
	@PropertyDesc(name="wsdl地址")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="WSDLURL",columnDefinition="VARCHAR(1000)") 
	private String wsdlUrl;

	public String getWsdlUrl() {
		return wsdlUrl;
	}

	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}
}
