package com.cloud.webservice.service;

import java.util.List;
import java.util.Map;

import com.cloud.webservice .model.WebService;

public interface IWebServiceService {

	/**
	 * 通过id得到某一对象
	 */
	public WebService getWebServiceById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public WebService saveWebService(WebService webService);

	/**
	 * 分页查询
	 */
	public Map searchWebService(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public WebService delWebService(WebService webService);

	/**
	 * 批量删除
	 */
	public void delWebServiceBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<WebService> list);

}
