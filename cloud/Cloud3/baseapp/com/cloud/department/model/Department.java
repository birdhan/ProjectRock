package com.cloud.department.model;

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
 * 代码创建时间：2013-11-22 09:19:27
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
@Table(name="SYSTEM_DEPARTMENT")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SYSTEM_DEPARTMENT",			//数据表名
	indexes={
		@Index(name="SYSTEM_DEPARTMENT_DEPARTNAME",columnNames={"DEPARTNAME"})
	}
)
public class Department {

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
	 *部门名称
	 */
	@PropertyDesc(name="部门名称")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="DEPARTNAME",columnDefinition="VARCHAR(50)")
	private String departName;

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName){
		this.departName = departName;
	}

	/**
	 *部门编号
	 */
	@PropertyDesc(name="部门编号")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="DEPARTNO",columnDefinition="VARCHAR(50)")
	private String departNo;

	public String getDepartNo() {
		return departNo;
	}

	public void setDepartNo(String departNo){
		this.departNo = departNo;
	}

	/**
	 *上级部门
	 */
	@PropertyDesc(name="上级部门")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="PARENTNO",columnDefinition="VARCHAR(50)")
	private String parentNo;

	public String getParentNo() {
		return parentNo;
	}

	public void setParentNo(String parentNo){
		this.parentNo = parentNo;
	}

	/**
	 *部门顺序
	 */
	@PropertyDesc(name="部门顺序")
	@ValidatePro(validate="{required:'true',number:'true',maxlength:'50'}")
	@Column(name="DEPARTSORT",columnDefinition="NUMBER")
	private int departSort;

	public int getDepartSort() {
		return departSort;
	}

	public void setDepartSort(int departSort){
		this.departSort = departSort;
	}

}
