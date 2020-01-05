package com.cloud.requestrecord.model;

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
 * 代码创建时间：2018-07-22 15:39:42
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
@Table(name="SYSTEM_REQUESTRECORD")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SYSTEM_REQUESTRECORD",			//数据表名
	indexes={
		@Index(name="SYSTEM_REQUESTRECORD_USERACCOUNT",columnNames={"USERACCOUNT"}),
		@Index(name="SYSTEM_REQUESTRECORD_FORMIP",columnNames={"FORMIP"})
	}
)
public class RequestRecord {

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
	 * 操作帐号
	 */
	@PropertyDesc(name="操作帐号")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="USERACCOUNT",columnDefinition="VARCHAR(50)")
	private String useraccount;

	public String getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(String useraccount){
		this.useraccount = useraccount;
	}

	/**
	 * 用户类型
	 */
	@PropertyDesc(name="用户类型")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="USERTYPE",columnDefinition="VARCHAR(50)")
	private String usertype;

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype){
		this.usertype = usertype;
	}

	/**
	 * 访问IP
	 */
	@PropertyDesc(name="访问IP")
	@ValidatePro(validate="{required:'true',maxlength:'100'}")
	@Column(name="FORMIP",columnDefinition="VARCHAR(100)")
	private String formip;

	public String getFormip() {
		return formip;
	}

	public void setFormip(String formip){
		this.formip = formip;
	}

	/**
	 * 请求地址
	 */
	@PropertyDesc(name="请求地址")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="REQUESTURI",columnDefinition="VARCHAR(1000)")
	private String requesturi;

	public String getRequesturi() {
		return requesturi;
	}

	public void setRequesturi(String requesturi){
		this.requesturi = requesturi;
	}

	/**
	 * 请求时间
	 */
	@PropertyDesc(name="请求时间")
	@ValidatePro(validate="{date:'true',maxlength:'50'}")
	@Column(name="REQTIME",columnDefinition="TIMESTAMP")
	private java.util.Date reqtime;

	public java.util.Date getReqtime() {
		return reqtime;
	}

	public void setReqtime(java.util.Date reqtime){
		this.reqtime = reqtime;
	}

}
