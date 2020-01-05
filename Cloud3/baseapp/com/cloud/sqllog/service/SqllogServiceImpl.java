package com.cloud.sqllog.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.sqllog.dao.ISqllogDaoMybatis;
import com.cloud.sqllog.dao.SqllogDaoHibernate;
import com.cloud.sqllog.dao.SqllogDaoJDBC;
import com.cloud.sqllog.model.Sqllog;

@Service
public class SqllogServiceImpl implements ISqllogService {

	@Resource
	private ISqllogDaoMybatis sqllogDaoMybatis;

	@Resource
	private SqllogDaoHibernate sqllogDaoHibernate;

	@Resource
	private SqllogDaoJDBC sqllogDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public Sqllog getSqllogById(String id) {
		return sqllogDaoHibernate.getSqllogById(id);
	}

	/**
	 * 保存
	 */
	public Sqllog saveSqllog(Sqllog sqllog) {
		return sqllogDaoHibernate.saveSqllog(sqllog);
	}

	/**
	 * 列表查询
	 */
	public Map searchSqllog(Long curPage, Long pageSize,String whereStr) {
		return sqllogDaoHibernate.searchSqllog(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public Sqllog delSqllog(Sqllog sqllog) {
		return sqllogDaoHibernate.delSqllog(sqllog);
	}

	/**
	 * 批量删除
	 */
	public void delSqllogBatch(List<String> list) {
		sqllogDaoHibernate.delSqllogBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return sqllogDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<Sqllog> list) {
		return sqllogDaoHibernate.saveDataBatch(list);
	}

}
