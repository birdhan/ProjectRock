package com.cityinspector.openinfor.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.openinfor .model.AppAction;

public interface IAppActionService {

	/**
	 * 通过id得到某一对象
	 */
	public AppAction getAppActionById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public AppAction saveAppAction(AppAction appAction);

	/**
	 * 分页查询
	 */
	public Map searchAppAction(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public AppAction delAppAction(AppAction appAction);

	/**
	 * 批量删除
	 */
	public void delAppActionBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<AppAction> list);

}
