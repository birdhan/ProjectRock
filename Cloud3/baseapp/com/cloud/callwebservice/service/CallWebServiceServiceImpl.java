package com.cloud.callwebservice.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.callwebservice.dao.ICallWebServiceDaoMybatis;
import com.cloud.callwebservice.dao.CallWebServiceDaoHibernate;
import com.cloud.callwebservice.dao.CallWebServiceDaoJDBC;
import com.cloud.callwebservice.model.CallWebService;

@Service
public class CallWebServiceServiceImpl implements ICallWebServiceService {

	@Resource
	private ICallWebServiceDaoMybatis callWebServiceDaoMybatis;

	@Resource
	private CallWebServiceDaoHibernate callWebServiceDaoHibernate;

	@Resource
	private CallWebServiceDaoJDBC callWebServiceDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public CallWebService getCallWebServiceById(String id) {
		return callWebServiceDaoHibernate.getCallWebServiceById(id);
	}

	/**
	 * 保存
	 */
	public CallWebService saveCallWebService(CallWebService callWebService) {
		return callWebServiceDaoHibernate.saveCallWebService(callWebService);
	}

	/**
	 * 列表查询
	 */
	public Map searchCallWebService(Long curPage, Long pageSize,String whereStr) {
		return callWebServiceDaoHibernate.searchCallWebService(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public CallWebService delCallWebService(CallWebService callWebService) {
		return callWebServiceDaoHibernate.delCallWebService(callWebService);
	}

	/**
	 * 批量删除
	 */
	public void delCallWebServiceBatch(List<String> list) {
		callWebServiceDaoHibernate.delCallWebServiceBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return callWebServiceDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<CallWebService> list) {
		return callWebServiceDaoHibernate.saveDataBatch(list);
	}

}
