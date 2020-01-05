package com.cityinspector.onlineservice.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.onlineservice .model.OnlineService;

public interface IOnlineServiceService {

	/**
	 * 通过id得到某一对象
	 */
	public OnlineService getOnlineServiceById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public OnlineService saveOnlineService(OnlineService onlineService);

	/**
	 * 分页查询
	 */
	public Map searchOnlineService(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public OnlineService delOnlineService(OnlineService onlineService);

	/**
	 * 批量删除
	 */
	public void delOnlineServiceBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<OnlineService> list);

}
