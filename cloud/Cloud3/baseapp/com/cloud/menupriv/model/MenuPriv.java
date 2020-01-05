package com.cloud.menupriv.model;

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
 * 代码创建时间：2013-12-06 09:08:49
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
@Table(name="SYSTEM_MENU_PRIV")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SYSTEM_MENU_PRIV",			//数据表名
	indexes={
		@Index(name="SYSTEM_MENU_PRIV_PRIVNAME",columnNames={"PRIVNAME"}),
		@Index(name="SYSTEM_MENU_PRIV_LINKMENUID",columnNames={"LINKMENUID"})
	}
)
public class MenuPriv {

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
	 *权限编号
	 */
	@PropertyDesc(name="权限编号")
	@ValidatePro(validate="{required:'true',maxlength:'10'}")
	@Column(name="PRIVNO",columnDefinition="VARCHAR(50)")
	private String privNo;

	public String getPrivNo() {
		return privNo;
	}

	public void setPrivNo(String privNo){
		this.privNo = privNo;
	}

	/**
	 *权限名称
	 */
	@PropertyDesc(name="权限名称")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="PRIVNAME",columnDefinition="VARCHAR(50)")
	private String privName;

	public String getPrivName() {
		return privName;
	}

	public void setPrivName(String privName){
		this.privName = privName;
	}

	/**
	 *关联菜单
	 */
	@PropertyDesc(name="关联菜单")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="LINKMENUID",columnDefinition="VARCHAR(50)")
	private String linkMenuId;

	public String getLinkMenuId() {
		return linkMenuId;
	}

	public void setLinkMenuId(String linkMenuId){
		this.linkMenuId = linkMenuId;
	}
	
	/**
	 *URL
	 */
	@PropertyDesc(name="URL")
	@ValidatePro(validate="{required:'true',maxlength:'1000'}")
	@Column(name="URL",columnDefinition="VARCHAR(1000)")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
