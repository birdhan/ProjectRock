package com.cityinspector.viewmesasge.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.viewmesasge.dao.IViewMessageDaoMybatis;
import com.cityinspector.viewmesasge.dao.ViewMessageDaoHibernate;
import com.cityinspector.viewmesasge.dao.ViewMessageDaoJDBC;
import com.cityinspector.viewmesasge.model.ViewMessage;

@Service
public class ViewMessageServiceImpl implements IViewMessageService {

	@Resource
	private IViewMessageDaoMybatis viewMessageDaoMybatis;

	@Resource
	private ViewMessageDaoHibernate viewMessageDaoHibernate;

	@Resource
	private ViewMessageDaoJDBC viewMessageDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public ViewMessage getViewMessageById(String id) {
		return viewMessageDaoHibernate.getViewMessageById(id);
	}

	/**
	 * 保存
	 */
	public ViewMessage saveViewMessage(ViewMessage viewMessage) {
		return viewMessageDaoHibernate.saveViewMessage(viewMessage);
	}

	/**
	 * 列表查询
	 */
	public Map searchViewMessage(Long curPage, Long pageSize,String whereStr) {
		return viewMessageDaoHibernate.searchViewMessage(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public ViewMessage delViewMessage(ViewMessage viewMessage) {
		return viewMessageDaoHibernate.delViewMessage(viewMessage);
	}

	/**
	 * 批量删除
	 */
	public void delViewMessageBatch(List<String> list) {
		viewMessageDaoHibernate.delViewMessageBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return viewMessageDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<ViewMessage> list) {
		return viewMessageDaoHibernate.saveDataBatch(list);
	}

}
