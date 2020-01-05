package com.cityinspector.section.model;

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
 * 代码创建时间：2018-07-15 20:24:34
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
@Table(name="service_section")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="service_section",			//数据表名
	indexes={
		@Index(name="SECTION_NAME",columnNames={"NAME"}),
		@Index(name="SECTION_POSTION",columnNames={"POSTION"}),
		@Index(name="SECTION_ISSHOW",columnNames={"ISSHOW"})
	}
)
public class Section {

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
	 * 栏目名称
	 */
	@PropertyDesc(name="栏目名称")
	@ValidatePro(validate="{required:'true',normalText:'true',maxlength:'50'}")
	@Column(name="NAME",columnDefinition="VARCHAR(50)")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	/**
	 * 位置
	 */
	@PropertyDesc(name="位置")
	@ValidatePro(validate="{required:'true',normalText:'true',maxlength:'50'}")
	@Column(name="POSTION",columnDefinition="VARCHAR(50)")
	private String postion;

	public String getPostion() {
		return postion;
	}

	public void setPostion(String postion){
		this.postion = postion;
	}

	/**
	 * 是否显示
	 */
	@PropertyDesc(name="是否显示")
	@ValidatePro(validate="{required:'true',normalText:'true',maxlength:'50'}")
	@Column(name="ISSHOW",columnDefinition="VARCHAR(50)")
	private String isshow;

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow){
		this.isshow = isshow;
	}

	/**
	 * 排序顺序
	 */
	@PropertyDesc(name="排序顺序")
	@ValidatePro(validate="{required:'true',number:'true',maxlength:'50'}")
	@Column(name="SORTNUM",columnDefinition="INT")
	private int sortnum;

	public int getSortnum() {
		return sortnum;
	}

	public void setSortnum(int sortnum){
		this.sortnum = sortnum;
	}

	/**
	 * 父ID
	 */
	@PropertyDesc(name="父ID")
	@ValidatePro(validate="{required:'true',number:'true',maxlength:'50'}")
	@Column(name="PID",columnDefinition="VARCHAR(50)")
	private String pid;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
