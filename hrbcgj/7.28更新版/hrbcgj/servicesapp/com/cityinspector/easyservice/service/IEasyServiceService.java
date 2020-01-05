package com.cityinspector.easyservice.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.easyservice .model.EasyService;

public interface IEasyServiceService {

	/**
	 * 通过id得到某一对象
	 */
	public EasyService getEasyServiceById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public EasyService saveEasyService(EasyService easyService);

	/**
	 * 分页查询
	 */
	public Map searchEasyService(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public EasyService delEasyService(EasyService easyService);

	/**
	 * 批量删除
	 */
	public void delEasyServiceBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<EasyService> list);

}
