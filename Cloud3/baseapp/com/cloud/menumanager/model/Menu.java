package com.cloud.menumanager.model;

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
 * 代码创建时间：2013-11-21 14:26:58
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
@Table(name="SYSTEM_MENU")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SYSTEM_MENU",			//数据表名
	indexes={
		@Index(name="SYSTEM_MENU_MENUNAME",columnNames={"MENUNAME"})
	}
)
public class Menu {

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
	 *菜单名称
	 */
	@PropertyDesc(name="菜单名称")
	@ValidatePro(validate="{required:'true',maxlength:'20'}")
	@Column(name="MENUNAME",columnDefinition="VARCHAR(20)")
	private String menuName;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName){
		this.menuName = menuName;
	}

	/**
	 *菜单地址
	 */
	@PropertyDesc(name="菜单地址")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="MENUURL",columnDefinition="VARCHAR(500)")
	private String menuUrl;

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl){
		this.menuUrl = menuUrl;
	}

	/**
	 *菜单类型
	 */
	@PropertyDesc(name="菜单类型")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="MENUTYPE",columnDefinition="VARCHAR(2)")
	private String menuType;

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType){
		this.menuType = menuType;
	}

	/**
	 *父菜单
	 */
	@PropertyDesc(name="父菜单")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="PARENTID",columnDefinition="VARCHAR(50)")
	private String parentId;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId){
		this.parentId = parentId;
	}

	/**
	 *菜单顺序
	 */
	@PropertyDesc(name="菜单顺序")
	@ValidatePro(validate="{required:'true',number:'true',maxlength:'50'}")
	@Column(name="MENUSOR",columnDefinition="NUMBER")
	private int menuSort;

	public int getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(int menuSort) {
		this.menuSort = menuSort;
	}

}
