package com.cloud.special.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.special.dao.IspecialDaoMybatis;
import com.cloud.special.dao.specialDaoHibernate;
import com.cloud.special.dao.specialDaoJDBC;
import com.cloud.special.model.special;

@Service
public class specialServiceImpl implements IspecialService {

	@Resource
	private IspecialDaoMybatis specialDaoMybatis;

	@Resource
	private specialDaoHibernate specialDaoHibernate;

	@Resource
	private specialDaoJDBC specialDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public special getspecialById(String id) {
		return specialDaoHibernate.getspecialById(id);
	}

	/**
	 * 保存
	 */
	public special savespecial(special special) {
		return specialDaoHibernate.savespecial(special);
	}

	/**
	 * 列表查询
	 */
	public Map searchspecial(Long curPage, Long pageSize,String whereStr) {
		return specialDaoHibernate.searchspecial(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public special delspecial(special special) {
		return specialDaoHibernate.delspecial(special);
	}

	/**
	 * 批量删除
	 */
	public void delspecialBatch(List<String> list) {
		specialDaoHibernate.delspecialBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return specialDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<special> list) {
		return specialDaoHibernate.saveDataBatch(list);
	}

}
