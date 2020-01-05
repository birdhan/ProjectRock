package com.cityinspector.openinfor.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.openinfor.dao.IAppActionDaoMybatis;
import com.cityinspector.openinfor.dao.AppActionDaoHibernate;
import com.cityinspector.openinfor.dao.AppActionDaoJDBC;
import com.cityinspector.openinfor.model.AppAction;

@Service
public class AppActionServiceImpl implements IAppActionService {

	@Resource
	private IAppActionDaoMybatis appActionDaoMybatis;

	@Resource
	private AppActionDaoHibernate appActionDaoHibernate;

	@Resource
	private AppActionDaoJDBC appActionDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public AppAction getAppActionById(String id) {
		return appActionDaoHibernate.getAppActionById(id);
	}

	/**
	 * 保存
	 */
	public AppAction saveAppAction(AppAction appAction) {
		return appActionDaoHibernate.saveAppAction(appAction);
	}

	/**
	 * 列表查询
	 */
	public Map searchAppAction(Long curPage, Long pageSize,String whereStr) {
		return appActionDaoHibernate.searchAppAction(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public AppAction delAppAction(AppAction appAction) {
		return appActionDaoHibernate.delAppAction(appAction);
	}

	/**
	 * 批量删除
	 */
	public void delAppActionBatch(List<String> list) {
		appActionDaoHibernate.delAppActionBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return appActionDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<AppAction> list) {
		return appActionDaoHibernate.saveDataBatch(list);
	}

}
