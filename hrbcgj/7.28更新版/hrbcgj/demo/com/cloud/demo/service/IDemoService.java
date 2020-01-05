package com.cloud.demo.service;

import java.util.List;
import java.util.Map;

import com.cloud.demo.model.Demo;

public interface IDemoService {

	/**
	 * 通过id得到某一对象
	 */
	public Demo getDemoById(String id);
	
	/**
	 * 保存或修改某一对象
	 */
	public Demo saveDemo(Demo demo);
	
	/**
	 * 分页查询
	 */
	public Map searchDemo(Long curPage, Long pageSize,String whereStr);
	
	/**
	 * 删除某一对象
	 */
	public Demo delDemo(Demo demo);
	
	/**
	 * 批量删除
	 */
	public void delDemoBatch(List<String> list);
	
	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);
	
	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<Demo> list);
}

