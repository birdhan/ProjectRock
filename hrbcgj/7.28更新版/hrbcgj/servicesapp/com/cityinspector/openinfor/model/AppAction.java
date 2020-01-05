package com.cityinspector.openinfor.model;

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
 * @author 韩春阳
 * 代码创建时间：2018-07-21 15:22:33
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
@Table(name="APPACTION")
public class AppAction {

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
	@ValidatePro(validate="{required:'false'}")
	@Column(name="TITLE",columnDefinition="VARCHAR(50)")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * 内容
	 */
	@PropertyDesc(name="内容")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="ACTICE",columnDefinition="VARCHAR(500)")
	private String actice;

	public String getActice() {
		return actice;
	}

	public void setActice(String actice){
		this.actice = actice;
	}

	/**
	 * 申请时间
	 */
	@PropertyDesc(name="申请时间")
	@ValidatePro(validate="{date:'true',maxlength:'50'}")
	@Column(name="CREATETIME",columnDefinition="timestamp")
	private Date createtime;

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime){
		this.createtime = createtime;
	}

	/**
	 * 状态
	 */
	@PropertyDesc(name="状态")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="STATE",columnDefinition="VARCHAR(50)")
	private String state;

	
	public String getState() {
		return state;
	}

	public void setState(String state){
		this.state = state;
	}

	/**
	 * 批文
	 */
	@PropertyDesc(name="批文")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="REPLY",columnDefinition="VARCHAR(200)")
	private String reply;

	public String getReply() {
		return reply;
	}

	public void setReply(String reply){
		this.reply = reply;
	}

	/**
	 * 用户id
	 */
	@PropertyDesc(name="用户id")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="UID",columnDefinition="VARCHAR(50)")
	private String uid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid){
		this.uid = uid;
	}
	
	@Override
	public String toString() {
		return "AppAction [id=" + id + ", title=" + title + ", actice="
				+ actice + ", createtime=" + createtime + ", state=" + state
				+ ", reply=" + reply + ", uid=" + uid + "]";
	}


}
