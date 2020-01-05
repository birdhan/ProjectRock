package com.cloud.datadict.model;

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
 * 代码创建时间：2013-11-15 11:37:15
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
@Table(name="SYSTEM_DATADICT")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SYSTEM_DATADICT",			//数据表名
	indexes={
		@Index(name="SYSTEM_DATADICT_DICTVALUE",columnNames={"DICTVALUE"}),
		@Index(name="SYSTEM_DATADICT_DICTLABEL",columnNames={"DICTLABEL"}),
		@Index(name="SYSTEM_DATADICT_DICTTYPE",columnNames={"DICTTYPE"}),
		@Index(name="SYSTEM_DATADICT_MODULENAME",columnNames={"MODULENAME"}),
		@Index(name="SYSTEM_DATADICT_PROPERTY",columnNames={"PROPERTY"})
	}
)
public class DataDict {

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
	 *字典值
	 */
	@PropertyDesc(name="字典值")
	@ValidatePro(validate="{required:'true',maxlength:'100'}")
	@Column(name="DICTVALUE",columnDefinition="VARCHAR(100)")
	private String dictValue;

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue){
		this.dictValue = dictValue;
	}

	/**
	 *字典显示值
	 */
	@PropertyDesc(name="字典显示值")
	@ValidatePro(validate="{required:'true',maxlength:'200'}")
	@Column(name="DICTLABEL",columnDefinition="VARCHAR(200)")
	private String dictLabel;

	public String getDictLabel() {
		return dictLabel;
	}

	public void setDictLabel(String dictLabel){
		this.dictLabel = dictLabel;
	}

	/**
	 *字典控件类型
	 */
	@PropertyDesc(name="字典控件类型")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="DICTTYPE",columnDefinition="VARCHAR(50)")
	private String dictType;

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType){
		this.dictType = dictType;
	}

	/**
	 *模块名称
	 */
	@PropertyDesc(name="模块名称")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="MODULENAME",columnDefinition="VARCHAR(50)")
	private String moduleName;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName){
		this.moduleName = moduleName;
	}

	/**
	 *控件属性
	 */
	@PropertyDesc(name="控件属性")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="PROPERTY",columnDefinition="VARCHAR(50)")
	private String property;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property){
		this.property = property;
	}

}
