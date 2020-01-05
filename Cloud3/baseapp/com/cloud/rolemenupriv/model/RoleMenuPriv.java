package com.cloud.rolemenupriv.model;

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
 * 代码创建时间：2013-12-12 10:11:07
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
@Table(name="SYSTEM_ROLEMENUPRIV")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SYSTEM_ROLEMENUPRIV",			//数据表名
	indexes={
		@Index(name="SYSTEM_ROLEMENUPRIV_LINKROLEID",columnNames={"LINKROLEID"}),
		@Index(name="SYSTEM_ROLEMENUPRIV_LINKMENUID",columnNames={"LINKMENUID"}),
		@Index(name="SYSTEM_ROLEMENUPRIV_PRIVNO",columnNames={"PRIVNO"})
	}
)
public class RoleMenuPriv {

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
	 *关联角色ID
	 */
	@PropertyDesc(name="关联角色ID")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="LINKROLEID",columnDefinition="VARCHAR(36)")
	private String linkRoleId;

	public String getLinkRoleId() {
		return linkRoleId;
	}

	public void setLinkRoleId(String linkRoleId){
		this.linkRoleId = linkRoleId;
	}

	/**
	 *关联菜单ID
	 */
	@PropertyDesc(name="关联菜单ID")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="LINKMENUID",columnDefinition="VARCHAR(36)")
	private String linkMenuId;

	public String getLinkMenuId() {
		return linkMenuId;
	}

	public void setLinkMenuId(String linkMenuId){
		this.linkMenuId = linkMenuId;
	}

	/**
	 *对应权限编号
	 */
	@PropertyDesc(name="对应权限编号")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="PRIVNO",columnDefinition="VARCHAR(50)")
	private String privNo;

	public String getPrivNo() {
		return privNo;
	}

	public void setPrivNo(String privNo){
		this.privNo = privNo;
	}

}
