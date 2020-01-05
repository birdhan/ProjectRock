package com.cloud.webservice.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.webservice.dao.IWebServiceDaoMybatis;
import com.cloud.webservice.dao.WebServiceDaoHibernate;
import com.cloud.webservice.dao.WebServiceDaoJDBC;
import com.cloud.webservice.model.WebService;

@Service
public class WebServiceServiceImpl implements IWebServiceService {

	@Resource
	private IWebServiceDaoMybatis webServiceDaoMybatis;

	@Resource
	private WebServiceDaoHibernate webServiceDaoHibernate;

	@Resource
	private WebServiceDaoJDBC webServiceDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public WebService getWebServiceById(String id) {
		return webServiceDaoHibernate.getWebServiceById(id);
	}

	/**
	 * 保存
	 */
	public WebService saveWebService(WebService webService) {
		return webServiceDaoHibernate.saveWebService(webService);
	}

	/**
	 * 列表查询
	 */
	public Map searchWebService(Long curPage, Long pageSize,String whereStr) {
		return webServiceDaoHibernate.searchWebService(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public WebService delWebService(WebService webService) {
		return webServiceDaoHibernate.delWebService(webService);
	}

	/**
	 * 批量删除
	 */
	public void delWebServiceBatch(List<String> list) {
		webServiceDaoHibernate.delWebServiceBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return webServiceDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<WebService> list) {
		return webServiceDaoHibernate.saveDataBatch(list);
	}

}
