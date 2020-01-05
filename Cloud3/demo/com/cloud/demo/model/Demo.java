package com.cloud.demo.model;

import java.util.Date;

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
 * @author cloud7
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
@Table(name="DEMO")
//创建索引
@org.hibernate.annotations.Table(
		appliesTo="DEMO",
		indexes={
				@Index(name="DEMO_NAME",columnNames={"NAME"}),
				@Index(name="DEMO_CREATETIME",columnNames={"CREATETIME"}),
				@Index(name="DEMO_AGE",columnNames={"AGE"})
		}
)
public class Demo {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@PropertyDesc(name="主键ID")
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 姓名
	 */
	@PropertyDesc(name="姓名")								
	@ValidatePro(validate="{required:'true',maxlength:'10'}")
	@Column(name="NAME",columnDefinition="varchar(100) default ''")
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 创建时间
	 */
	@PropertyDesc(name="创建时间")
	@ValidatePro(validate="{required:'true',date:'true'}")
	@Column(name="CREATETIME",columnDefinition="date")
	private Date createTime;
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 年龄
	 */
	@PropertyDesc(name="年龄")
	@ValidatePro(validate="{required:'true',isInt:'true'}")
	@Column(name="AGE",columnDefinition="INT")
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * 内容
	 */
	@PropertyDesc(name="内容")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="CONTENTVAL",columnDefinition ="TEXT")
	private String contentVal;

	public String getContentVal() {
		return contentVal;
	}

	public void setContentVal(String contentVal) {
		this.contentVal = contentVal;
	}
	
	/**
	 * 爱好
	 */
	@PropertyDesc(name="爱好")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="HOBBY",columnDefinition="varchar(50) default ''")
	private String hobby;

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	/**
	 * 性别
	 */
	@PropertyDesc(name="性别")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="SEX",columnDefinition="varchar(1) default ''")
	private String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/**
	 * 部门
	 */
	@PropertyDesc(name="部门")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="DEPART",columnDefinition="varchar(60) default ''")
	private String depart;

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	/**
	 * 方向
	 */
	@PropertyDesc(name="方向")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="DIRECTION",columnDefinition="varchar(100) default ''")
	private String direction;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	/**
	 * 透明测试
	 */
	@Transient
	@PropertyDesc(name="透明测试")	
	private String transientCol;
	
	public String getTransientCol() {
		return transientCol;
	}

	public void setTransientCol(String transientCol) {
		this.transientCol = transientCol;
	}
}
