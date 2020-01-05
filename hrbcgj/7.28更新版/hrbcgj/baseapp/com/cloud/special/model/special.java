package com.cloud.special.model;

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
 * @author 王子健
 * 代码创建时间：2018-07-22 16:32:13
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
@Table(name="SPECIAL")
public class special {

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
	 * 图片链接
	 */
	@PropertyDesc(name="图片链接")
	@ValidatePro(validate="{required:'false',url:'true',maxlength:'1000'}")
	@Column(name="WEBURL",columnDefinition="VARCHAR(50)")
	private String weburl;

	public String getWeburl() {
		return weburl;
	}
	public void setWeburl(String weburl){
		this.weburl = weburl;
	}

	/**
	 * logo图片
	 */
	@PropertyDesc(name="logo图片")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="PICURL",columnDefinition="VARCHAR(50)")
	private String picurl;

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl){
		this.picurl = picurl;
	}

}
