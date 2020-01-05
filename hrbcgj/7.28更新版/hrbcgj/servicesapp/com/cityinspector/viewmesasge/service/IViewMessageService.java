package com.cityinspector.viewmesasge.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.viewmesasge .model.ViewMessage;

public interface IViewMessageService {

	/**
	 * 通过id得到某一对象
	 */
	public ViewMessage getViewMessageById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public ViewMessage saveViewMessage(ViewMessage viewMessage);

	/**
	 * 分页查询
	 */
	public Map searchViewMessage(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public ViewMessage delViewMessage(ViewMessage viewMessage);

	/**
	 * 批量删除
	 */
	public void delViewMessageBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<ViewMessage> list);

}
