package com.cloud.regwebservice.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.regwebservice.dao.IRegWebServiceDaoMybatis;
import com.cloud.regwebservice.dao.RegWebServiceDaoHibernate;
import com.cloud.regwebservice.dao.RegWebServiceDaoJDBC;
import com.cloud.regwebservice.model.RegWebService;

@Service
public class RegWebServiceServiceImpl implements IRegWebServiceService {

	@Resource
	private IRegWebServiceDaoMybatis regWebServiceDaoMybatis;

	@Resource
	private RegWebServiceDaoHibernate regWebServiceDaoHibernate;

	@Resource
	private RegWebServiceDaoJDBC regWebServiceDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public RegWebService getRegWebServiceById(String id) {
		return regWebServiceDaoHibernate.getRegWebServiceById(id);
	}

	/**
	 * 保存
	 */
	public RegWebService saveRegWebService(RegWebService regWebService) {
		return regWebServiceDaoHibernate.saveRegWebService(regWebService);
	}

	/**
	 * 列表查询
	 */
	public Map searchRegWebService(Long curPage, Long pageSize,String whereStr) {
		return regWebServiceDaoHibernate.searchRegWebService(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public RegWebService delRegWebService(RegWebService regWebService) {
		return regWebServiceDaoHibernate.delRegWebService(regWebService);
	}

	/**
	 * 批量删除
	 */
	public void delRegWebServiceBatch(List<String> list) {
		regWebServiceDaoHibernate.delRegWebServiceBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return regWebServiceDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<RegWebService> list) {
		return regWebServiceDaoHibernate.saveDataBatch(list);
	}

}
