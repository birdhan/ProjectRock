package com.cloud.coder.model;

public class Coder {

	private String id;				//主键
	private String index;			//索引
	private String author;			//作者
	private String pojo;			//pojo
	private String packageName;		//包名
	private String nameSpace;		//命名空间
	private String filePath;		//文件生成路径
	private String modelName;		//实体类
	private String modelDesc;		//描述
	private String tableName;		//数据表名
	private String isProperty;		//是否主键 yes表示是
	private String createWebService;//创建ws
	private String createJob;		//创建job

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPojo() {
		return pojo;
	}
	public void setPojo(String pojo) {
		this.pojo = pojo;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getNameSpace() {
		return nameSpace;
	}
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelDesc() {
		return modelDesc;
	}
	public void setModelDesc(String modelDesc) {
		this.modelDesc = modelDesc;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getIsProperty() {
		return isProperty;
	}
	public void setIsProperty(String isProperty) {
		this.isProperty = isProperty;
	}
	public String getCreateWebService() {
		return createWebService;
	}
	public void setCreateWebService(String createWebService) {
		this.createWebService = createWebService;
	}
	public String getCreateJob() {
		return createJob;
	}
	public void setCreateJob(String createJob) {
		this.createJob = createJob;
	}	
}
