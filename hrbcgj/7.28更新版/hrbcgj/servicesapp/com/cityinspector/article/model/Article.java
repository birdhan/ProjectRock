package com.cityinspector.article.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.cloud.base.annotation.propertydesc.PropertyDesc;
import com.cloud.base.annotation.validatepro.ValidatePro;

/**
 * 说明：model实体类，类的属性上都要写上两个注解。
 * @PropertyDesc：表示是属性的描述，这个注解是给excel导出使用的。
 * @ValidatePro：表示是该属性在excel导入时候的验证json串
 * @author cuiyp
 * 代码创建时间：2018-07-16 09:16:55
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
@Table(name="SERVICE_ARTICLE")
//创建索引
@org.hibernate.annotations.Table(
	appliesTo="SERVICE_ARTICLE",			//数据表名
	indexes={
		@Index(name="ARTICLE_NAME",columnNames={"NAME"})
	}
)
public class Article {

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
	 * 文章标题
	 */
	@PropertyDesc(name="文章标题")
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
	 * 作者
	 */
	@PropertyDesc(name="作者")
	@ValidatePro(validate="{required:'true',normalText:'true',maxlength:'50'}")
	@Column(name="AUTHOR",columnDefinition="VARCHAR(50)")
	private String author;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	/**
	 * 创建时间
	 */
	@PropertyDesc(name="创建时间")
	@ValidatePro(validate="{required:'true',date:'true',maxlength:'50'}")
	@Column(name="CREATETIME",columnDefinition="TIMESTAMP")
	private Date createtime;

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime){
		this.createtime = createtime;
	}

	/**
	 * 封面图片
	 */
	@PropertyDesc(name="封面图片")
	@ValidatePro(validate="{required:'false'}")
	@Column(name="COVER",columnDefinition="VARCHAR(1000)")
	private String cover;

	public String getCover() {
		return cover;
	}

	public void setCover(String cover){
		this.cover = cover;
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
	 * 内容
	 */
	@PropertyDesc(name="内容")
	@ValidatePro(validate="{required:'true',maxlength:'50'}")
	@Column(name="CONTENTVALUE",columnDefinition="TEXT")
	private String contentvalue;

	public String getContentvalue() {
		return contentvalue;
	}

	public void setContentvalue(String contentvalue){
		this.contentvalue = contentvalue;
	}
	
	/**
	 * 所属栏目
	 */
	@PropertyDesc(name="所属栏目")
	@ValidatePro(validate="{required:'true',normalText:'true',maxlength:'50'}")
	@Column(name="SECTIONID",columnDefinition="VARCHAR(50)")
	private String sectionid;

	public String getSectionid() {
		return sectionid;
	}

	public void setSectionid(String sectionid) {
		this.sectionid = sectionid;
	}
	
	/**
	 * 视频文件
	 */
	@PropertyDesc(name="视频文件")
	@ValidatePro(validate="{required:'true',normalText:'true',maxlength:'50'}")
	@Column(name="videoid",columnDefinition="VARCHAR(50)")
	private String videoid;

	public String getVideoid() {
		return videoid;
	}

	public void setVideoid(String videoid) {
		this.videoid = videoid;
	}
	
}
