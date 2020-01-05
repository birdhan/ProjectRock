package com.cloud.systemuser.model;

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
 * 代码创建时间：2013-11-19 13:23:53
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
@Table(name="SYSTEM_USER")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SYSTEM_USER",			//数据表名
	indexes={
		@Index(name="SYSTEM_USER_USERID",columnNames={"USERID"}),
		@Index(name="SYSTEM_USER_USERNAME",columnNames={"USERNAME"}),
		@Index(name="SYSTEM_USER_PASSWORD",columnNames={"PASSWORD"}),
		@Index(name="SYSTEM_USER_DEPARTNO",columnNames={"DEPARTNO"})
	}
)
public class SystemUser {

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
	 *帐号
	 */
	@PropertyDesc(name="帐号")
	@ValidatePro(validate="{required:'true',maxlength:'10'}")
	@Column(name="USERID",columnDefinition="VARCHAR(10)")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	/**
	 *密码
	 */
	@PropertyDesc(name="密码")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="PASSWORD",columnDefinition="VARCHAR(50)")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	/**
	 *名字
	 */
	@PropertyDesc(name="名字")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="USERNAME",columnDefinition="VARCHAR(50)")
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	/**
	 *部门编号
	 */
	@PropertyDesc(name="部门编号")
	@ValidatePro(validate="{required:'true',maxlength:'20'}")
	@Column(name="DEPARTNO",columnDefinition="VARCHAR(20)")
	private String departNo;

	public String getDepartNo() {
		return departNo;
	}

	public void setDepartNo(String departNo){
		this.departNo = departNo;
	}
}
