package com.cloud.sqllog.model;

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
 * @author cloudwork
 * 代码创建时间：2014-01-06 12:56:59
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
@Table(name="T_LOG_SQLLOG")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="T_LOG_SQLLOG",			//数据表名
	indexes={
		@Index(name="T_LOG_SQLLOG_FBEGINTIME",columnNames={"FBEGINTIME"}),
		@Index(name="T_LOG_SQLLOG_FENDTIME",columnNames={"FENDTIME"}),
		@Index(name="T_LOG_SQLLOG_FSQLTYPE",columnNames={"FSQLTYPE"})
	}
)
public class Sqllog {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@PropertyDesc(name="主键")
	@Column(name="FID")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	/**
	 * 开始时间
	 */
	@PropertyDesc(name="开始时间")
	@ValidatePro(validate="{date:'true',maxlength:'50'}")
	@Column(name="FBEGINTIME",columnDefinition="TIMESTAMP")
	private java.util.Date fbegintime;

	public java.util.Date getFbegintime() {
		return fbegintime;
	}

	public void setFbegintime(java.util.Date fbegintime){
		this.fbegintime = fbegintime;
	}

	/**
	 * 结束时间
	 */
	@PropertyDesc(name="结束时间")
	@ValidatePro(validate="{date:'true',maxlength:'50'}")
	@Column(name="FENDTIME",columnDefinition="TIMESTAMP")
	private java.util.Date fendtime;

	public java.util.Date getFendtime() {
		return fendtime;
	}

	public void setFendtime(java.util.Date fendtime){
		this.fendtime = fendtime;
	}

	/**
	 * SQL
	 */
	@PropertyDesc(name="SQL")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="FSQL",columnDefinition="VARCHAR(2000)")
	private String fsql;

	public String getFsql() {
		return fsql;
	}

	public void setFsql(String fsql){
		this.fsql = fsql;
	}

	/**
	 * SQL类型
	 */
	@PropertyDesc(name="SQL类型")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="FSQLTYPE",columnDefinition="NUMBER")
	private int fsqltype;

	public int getFsqltype() {
		return fsqltype;
	}

	public void setFsqltype(int fsqltype){
		this.fsqltype = fsqltype;
	}

	/**
	 * 参数
	 */
	@PropertyDesc(name="参数")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="FPARAMETERS",columnDefinition="CLOB")
	private String fparameters;

	public String getFparameters() {
		return fparameters;
	}

	public void setFparameters(String fparameters){
		this.fparameters = fparameters;
	}
	
	@Transient
	private Date fbegintimeSearch;

	public Date getFbegintimeSearch() {
		return fbegintimeSearch;
	}

	public void setFbegintimeSearch(Date fbegintimeSearch) {
		this.fbegintimeSearch = fbegintimeSearch;
	}
	
	@Transient
	private Date fendtimeSearch;

	public Date getFendtimeSearch() {
		return fendtimeSearch;
	}

	public void setFendtimeSearch(Date fendtimeSearch) {
		this.fendtimeSearch = fendtimeSearch;
	}

}
