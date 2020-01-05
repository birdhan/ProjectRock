package com.cloud.callwebservice.service;

import java.util.List;
import java.util.Map;

import com.cloud.callwebservice .model.CallWebService;

public interface ICallWebServiceService {

	/**
	 * 通过id得到某一对象
	 */
	public CallWebService getCallWebServiceById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public CallWebService saveCallWebService(CallWebService callWebService);

	/**
	 * 分页查询
	 */
	public Map searchCallWebService(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public CallWebService delCallWebService(CallWebService callWebService);

	/**
	 * 批量删除
	 */
	public void delCallWebServiceBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<CallWebService> list);

}
