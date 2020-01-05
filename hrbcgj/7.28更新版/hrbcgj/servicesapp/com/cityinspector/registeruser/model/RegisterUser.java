package com.cityinspector.registeruser.model;

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
 * 代码创建时间：2018-07-18 18:00:12
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
@Table(name="SERVICE_REGISTERUSER")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SERVICE_REGISTERUSER",			//数据表名
	indexes={
		@Index(name="REGISTERUSER_ACCOUNT",columnNames={"ACCOUNT"}),
		@Index(name="REGISTERUSER_USERNAME",columnNames={"USERNAME"})
	}
)
public class RegisterUser {

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
	 * 帐号
	 */
	@PropertyDesc(name="帐号")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="ACCOUNT",columnDefinition="VARCHAR(50)")
	private String account;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account){
		this.account = account;
	}

	/**
	 * 密码
	 */
	@PropertyDesc(name="密码")
	@ValidatePro(validate="{required:'true',maxlength:'100'}")
	@Column(name="PWD",columnDefinition="VARCHAR(100)")
	private String pwd;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd){
		this.pwd = pwd;
	}

	/**
	 * 姓名
	 */
	@PropertyDesc(name="姓名")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="USERNAME",columnDefinition="VARCHAR(50)")
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}
	
	/**
	 * 身份证号
	 */
	@PropertyDesc(name="身份证号")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="idnum",columnDefinition="VARCHAR(50)")
	private String idnum;
	
	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	/**
	 * 手机号
	 */
	@PropertyDesc(name="手机号")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="mobile",columnDefinition="VARCHAR(11)")
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 积分
	 */
	@PropertyDesc(name="积分")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="points",columnDefinition="INT")
	private int points;

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
}
