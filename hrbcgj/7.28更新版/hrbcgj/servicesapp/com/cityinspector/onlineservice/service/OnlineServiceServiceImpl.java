package com.cityinspector.onlineservice.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.onlineservice.dao.IOnlineServiceDaoMybatis;
import com.cityinspector.onlineservice.dao.OnlineServiceDaoHibernate;
import com.cityinspector.onlineservice.dao.OnlineServiceDaoJDBC;
import com.cityinspector.onlineservice.model.OnlineService;

@Service
public class OnlineServiceServiceImpl implements IOnlineServiceService {

	@Resource
	private IOnlineServiceDaoMybatis onlineServiceDaoMybatis;

	@Resource
	private OnlineServiceDaoHibernate onlineServiceDaoHibernate;

	@Resource
	private OnlineServiceDaoJDBC onlineServiceDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public OnlineService getOnlineServiceById(String id) {
		return onlineServiceDaoHibernate.getOnlineServiceById(id);
	}

	/**
	 * 保存
	 */
	public OnlineService saveOnlineService(OnlineService onlineService) {
		return onlineServiceDaoHibernate.saveOnlineService(onlineService);
	}

	/**
	 * 列表查询
	 */
	public Map searchOnlineService(Long curPage, Long pageSize,String whereStr) {
		return onlineServiceDaoHibernate.searchOnlineService(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public OnlineService delOnlineService(OnlineService onlineService) {
		return onlineServiceDaoHibernate.delOnlineService(onlineService);
	}

	/**
	 * 批量删除
	 */
	public void delOnlineServiceBatch(List<String> list) {
		onlineServiceDaoHibernate.delOnlineServiceBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return onlineServiceDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<OnlineService> list) {
		return onlineServiceDaoHibernate.saveDataBatch(list);
	}

}
