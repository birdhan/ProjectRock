package com.cloud.systemconfig.model;

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
 * 代码创建时间：2013-11-22 11:43:47
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
@Table(name="SYSTEM_CONFIG")
public class SystemConfig {

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
	 *左侧菜单名称
	 */
	@PropertyDesc(name="左侧菜单名称")
	@ValidatePro(validate="{required:'true',maxlength:'5'}")
	@Column(name="LEFTNAME",columnDefinition="VARCHAR(5)")
	private String leftName;

	public String getLeftName() {
		return leftName;
	}

	public void setLeftName(String leftName){
		this.leftName = leftName;
	}

	/**
	 *根部门名称
	 */
	@PropertyDesc(name="根部门名称")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="ROOTDEPARTNAME",columnDefinition="VARCHAR(50)")
	private String rootDepartName;

	public String getRootDepartName() {
		return rootDepartName;
	}

	public void setRootDepartName(String rootDepartName){
		this.rootDepartName = rootDepartName;
	}

	/**
	 *根部门编号
	 */
	@PropertyDesc(name="根部门编号")
	@ValidatePro(validate="{required:'true',maxlength:'100'}")
	@Column(name="ROOTDEPARTNO",columnDefinition="VARCHAR(100)")
	private String rootDepartNo;

	public String getRootDepartNo() {
		return rootDepartNo;
	}

	public void setRootDepartNo(String rootDepartNo){
		this.rootDepartNo = rootDepartNo;
	}
	
	/**
	 * 日志删除天数
	 */
	@PropertyDesc(name="日志删除天数")
	@ValidatePro(validate="{required:'true',maxlength:'100'}")
	@Column(name="DELLOGDAY",columnDefinition="number default 1")
	private int delLogDay;

	public int getDelLogDay() {
		return delLogDay;
	}

	public void setDelLogDay(int delLogDay) {
		this.delLogDay = delLogDay;
	}
	
	/**
	 * 保留数据库备份文件天数
	 */
	@PropertyDesc(name="保留数据库备份文件天数")
	@ValidatePro(validate="{required:'true',maxlength:'100'}")
	@Column(name="SAVEDMPFILEDAY",columnDefinition="number default 5")
	private int saveDmpFileDay = 5;

	public int getSaveDmpFileDay() {
		return saveDmpFileDay;
	}

	public void setSaveDmpFileDay(int saveDmpFileDay) {
		this.saveDmpFileDay = saveDmpFileDay;
	}
	
	/**
	 * log日志文件保留天数
	 */
	@PropertyDesc(name="log日志文件保留天数")
	@ValidatePro(validate="{required:'true',maxlength:'100'}")
	@Column(name="SAVELOGFILEDAY",columnDefinition="number default 15")
	private int saveLogFileDay = 15;

	public int getSaveLogFileDay() {
		return saveLogFileDay;
	}

	public void setSaveLogFileDay(int saveLogFileDay) {
		this.saveLogFileDay = saveLogFileDay;
	}
	
	/**
	 * 图片保存路径
	 */
	@PropertyDesc(name="图片保存路径")
	@ValidatePro(validate="{required:'true',maxlength:'1000'}")
	@Column(name="PICSAVEPATH",columnDefinition="VARCHAR(1000)")
	private String picSavePath;

	public String getPicSavePath() {
		return picSavePath;
	}

	public void setPicSavePath(String picSavePath) {
		this.picSavePath = picSavePath;
	}
	
	/**
	 * 系统描述名称
	 */
	@PropertyDesc(name="系统描述名称")
	@ValidatePro(validate="{required:'true'}")
	@Column(name="SYSDESCNAME",columnDefinition="VARCHAR(1000) default '支撑平台'")
	private String sysDescName;

	public String getSysDescName() {
		return sysDescName;
	}

	public void setSysDescName(String sysDescName) {
		this.sysDescName = sysDescName;
	}
	
}
