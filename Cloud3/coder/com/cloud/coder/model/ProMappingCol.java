package com.cloud.coder.model;

/**
 * 页面中属性对应的字段
 * @author cloud7
 *
 */
public class ProMappingCol {

	private String pro;						//属性
	private String prodesc;					//属性描述
	private String proType;					//属性类型
	private String jspInput;				//jsp控件
	private String proValidation;			//属性验证串
	private String isQuery;					//是否参与查询
	private String isImport;				//参与数据导入
	private String listShow;				//是否参与列表显示
	
	private String col;						//字段
	private String coldesc;					//字段描述
	private String colType;					//字段类型
	private String colTypeLength;			//字段长度
	private String colValidation;			//字段验证串
	private String createIndex;				//创建索引
	private String isSort;					//参与排序
	private String maxLength;				//最大长度
	
	public String getPro() {
		return pro;
	}
	public void setPro(String pro) {
		this.pro = pro;
	}
	public String getProdesc() {
		return prodesc;
	}
	public void setProdesc(String prodesc) {
		this.prodesc = prodesc;
	}
	public String getProType() {
		return proType;
	}
	public void setProType(String proType) {
		this.proType = proType;
	}
	public String getJspInput() {
		return jspInput;
	}
	public void setJspInput(String jspInput) {
		this.jspInput = jspInput;
	}
	public String getProValidation() {
		return proValidation;
	}
	public void setProValidation(String proValidation) {
		this.proValidation = proValidation;
	}
	public String getIsQuery() {
		return isQuery;
	}
	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}
	public String getIsImport() {
		return isImport;
	}
	public void setIsImport(String isImport) {
		this.isImport = isImport;
	}
	public String getListShow() {
		return listShow;
	}
	public void setListShow(String listShow) {
		this.listShow = listShow;
	}
	public String getCol() {
		return col;
	}
	public void setCol(String col) {
		this.col = col;
	}
	public String getColdesc() {
		return coldesc;
	}
	public void setColdesc(String coldesc) {
		this.coldesc = coldesc;
	}
	public String getColType() {
		return colType;
	}
	public void setColType(String colType) {
		this.colType = colType;
	}
	public String getColTypeLength() {
		return colTypeLength;
	}
	public void setColTypeLength(String colTypeLength) {
		this.colTypeLength = colTypeLength;
	}
	public String getColValidation() {
		return colValidation;
	}
	public void setColValidation(String colValidation) {
		this.colValidation = colValidation;
	}
	public String getCreateIndex() {
		return createIndex;
	}
	public void setCreateIndex(String createIndex) {
		this.createIndex = createIndex;
	}
	public String getIsSort() {
		return isSort;
	}
	public void setIsSort(String isSort) {
		this.isSort = isSort;
	}
	public String getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
	
}
