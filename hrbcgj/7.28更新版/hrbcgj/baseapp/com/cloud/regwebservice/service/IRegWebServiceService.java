package com.cloud.regwebservice.service;

import java.util.List;
import java.util.Map;

import com.cloud.regwebservice .model.RegWebService;

public interface IRegWebServiceService {

	/**
	 * 通过id得到某一对象
	 */
	public RegWebService getRegWebServiceById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public RegWebService saveRegWebService(RegWebService regWebService);

	/**
	 * 分页查询
	 */
	public Map searchRegWebService(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public RegWebService delRegWebService(RegWebService regWebService);

	/**
	 * 批量删除
	 */
	public void delRegWebServiceBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<RegWebService> list);

}
