package com.cityinspector.vote.model;

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
 * @author ljm
 * 代码创建时间：2018-07-23 10:20:45
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
@Table(name="VOTEXUAN")
public class VoteXuan {

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
	 * 选项名称
	 */
	@PropertyDesc(name="选项名称")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="NAME",columnDefinition="VARCHAR(50)")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	/**
	 * 选项票数
	 */
	@PropertyDesc(name="选项票数")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="NUMBER",columnDefinition="INT")
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number){
		this.number = number;
	}

	/**
	 * 所属投票
	 */
	@PropertyDesc(name="所属投票")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="VID",columnDefinition="VARCHAR(36)")
	private String vid;

	public String getVid() {
		return vid;
	}

	public void setVid(String vid){
		this.vid = vid;
	}

}
