package com.cityinspector.onlineservice.model;

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
 * 代码创建时间：2018-07-16 14:36:44
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
@Table(name="SERVICE_ONLINESERVICE")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SERVICE_ONLINESERVICE",			//数据表名
	indexes={
		@Index(name="ONLINESERVICE_TITLE",columnNames={"TITLE"})
	}
)
public class OnlineService {

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
	 * 标题
	 */
	@PropertyDesc(name="标题")
	@ValidatePro(validate="{required:'true',normalText:'true',maxlength:'100'}")
	@Column(name="TITLE",columnDefinition="VARCHAR(100)")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * 链接地址
	 */
	@PropertyDesc(name="链接地址")
	@ValidatePro(validate="{required:'true',maxlength:'1000'}")
	@Column(name="LINKURL",columnDefinition="VARCHAR(1000)")
	private String linkurl;

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl){
		this.linkurl = linkurl;
	}

	/**
	 * 创建时间
	 */
	@PropertyDesc(name="创建时间")
	@ValidatePro(validate="{required:'true',date:'true',maxlength:'50'}")
	@Column(name="CREATETIME",columnDefinition="TIMESTAMP")
	private java.util.Date createtime;

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime){
		this.createtime = createtime;
	}

}
