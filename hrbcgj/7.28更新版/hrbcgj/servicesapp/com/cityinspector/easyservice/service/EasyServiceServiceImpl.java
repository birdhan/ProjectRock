package com.cityinspector.easyservice.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.easyservice.dao.IEasyServiceDaoMybatis;
import com.cityinspector.easyservice.dao.EasyServiceDaoHibernate;
import com.cityinspector.easyservice.dao.EasyServiceDaoJDBC;
import com.cityinspector.easyservice.model.EasyService;

@Service
public class EasyServiceServiceImpl implements IEasyServiceService {

	@Resource
	private IEasyServiceDaoMybatis easyServiceDaoMybatis;

	@Resource
	private EasyServiceDaoHibernate easyServiceDaoHibernate;

	@Resource
	private EasyServiceDaoJDBC easyServiceDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public EasyService getEasyServiceById(String id) {
		return easyServiceDaoHibernate.getEasyServiceById(id);
	}

	/**
	 * 保存
	 */
	public EasyService saveEasyService(EasyService easyService) {
		return easyServiceDaoHibernate.saveEasyService(easyService);
	}

	/**
	 * 列表查询
	 */
	public Map searchEasyService(Long curPage, Long pageSize,String whereStr) {
		return easyServiceDaoHibernate.searchEasyService(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public EasyService delEasyService(EasyService easyService) {
		return easyServiceDaoHibernate.delEasyService(easyService);
	}

	/**
	 * 批量删除
	 */
	public void delEasyServiceBatch(List<String> list) {
		easyServiceDaoHibernate.delEasyServiceBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return easyServiceDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<EasyService> list) {
		return easyServiceDaoHibernate.saveDataBatch(list);
	}

}
