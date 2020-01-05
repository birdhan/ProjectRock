package com.cityinspector.easyservice.model;

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
 * 代码创建时间：2018-07-16 17:52:26
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
@Table(name="SERVICE_EASYSERVICE")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SERVICE_EASYSERVICE",			//数据表名
	indexes={
		@Index(name="EASYSERVICE_NAME",columnNames={"NAME"})
	}
)
public class EasyService {

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
	 * 服务名称
	 */
	@PropertyDesc(name="服务名称")
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
	 * 图标
	 */
	@PropertyDesc(name="图标")
	@ValidatePro(validate="{required:'true',maxlength:'100'}")
	@Column(name="LOGOPIC",columnDefinition="VARCHAR(100)")
	private String logopic;

	public String getLogopic() {
		return logopic;
	}

	public void setLogopic(String logopic){
		this.logopic = logopic;
	}

	/**
	 * 外部链接
	 */
	@PropertyDesc(name="外部链接")
	@ValidatePro(validate="{required:'true',url:'true',maxlength:'1000'}")
	@Column(name="LINKURL",columnDefinition="VARCHAR(1000)")
	private String linkurl;

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl){
		this.linkurl = linkurl;
	}

}
