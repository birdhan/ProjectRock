package com.cloud.unit.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.unit.dao.IunitDaoMybatis;
import com.cloud.unit.dao.unitDaoHibernate;
import com.cloud.unit.dao.unitDaoJDBC;
import com.cloud.unit.model.unit;

@Service
public class unitServiceImpl implements IunitService {

	@Resource
	private IunitDaoMybatis unitDaoMybatis;

	@Resource
	private unitDaoHibernate unitDaoHibernate;

	@Resource
	private unitDaoJDBC unitDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public unit getunitById(String id) {
		return unitDaoHibernate.getunitById(id);
	}

	/**
	 * 保存
	 */
	public unit saveunit(unit unit) {
		return unitDaoHibernate.saveunit(unit);
	}

	/**
	 * 列表查询
	 */
	public Map searchunit(Long curPage, Long pageSize,String whereStr) {
		return unitDaoHibernate.searchunit(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public unit delunit(unit unit) {
		return unitDaoHibernate.delunit(unit);
	}

	/**
	 * 批量删除
	 */
	public void delunitBatch(List<String> list) {
		unitDaoHibernate.delunitBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return unitDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<unit> list) {
		return unitDaoHibernate.saveDataBatch(list);
	}

}
