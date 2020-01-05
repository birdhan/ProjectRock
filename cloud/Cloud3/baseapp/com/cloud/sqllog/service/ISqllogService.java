package com.cloud.sqllog.service;

import java.util.List;
import java.util.Map;

import com.cloud.sqllog .model.Sqllog;

public interface ISqllogService {

	/**
	 * 通过id得到某一对象
	 */
	public Sqllog getSqllogById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public Sqllog saveSqllog(Sqllog sqllog);

	/**
	 * 分页查询
	 */
	public Map searchSqllog(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public Sqllog delSqllog(Sqllog sqllog);

	/**
	 * 批量删除
	 */
	public void delSqllogBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<Sqllog> list);

}
