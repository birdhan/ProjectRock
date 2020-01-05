package com.cloud.demo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.demo.dao.DemoDaoHibernate;
import com.cloud.demo.dao.DemoDaoJDBC;
import com.cloud.demo.dao.IDemoDaoMybatis;
import com.cloud.demo.model.Demo;

@Service
public class DemoServiceImpl implements IDemoService {

	@Resource
	private IDemoDaoMybatis demoDaoMybatis;
	
	@Resource
	private DemoDaoHibernate demoDaoHibernate;
	
	@Resource
	private DemoDaoJDBC demoDaoJDBC;
	
	/**
	 * 通过id得到对象
	 */
	public Demo getDemoById(String id) {
		return demoDaoHibernate.getDemoById(id);
	}

	/**
	 * 保存
	 */
	public Demo saveDemo(Demo demo) {
		return demoDaoHibernate.saveDemo(demo);
	}
	
	/**
	 * 列表查询
	 */
	public Map searchDemo(Long curPage, Long pageSize,String whereStr) {
//		demoDaoMybatis.selectAllDemo();
		return demoDaoHibernate.searchDemo(curPage, pageSize, whereStr);
	}
	
	/**
	 * 删除
	 */
	public Demo delDemo(Demo demo) {
		return demoDaoHibernate.delDemo(demo);
	}
	
	/**
	 * 批量删除
	 */
	public void delDemoBatch(List<String> list) {
		demoDaoHibernate.delDemoBatch(list);
	}
	
	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return demoDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<Demo> list) {
		return demoDaoHibernate.saveDataBatch(list);
	}
}
