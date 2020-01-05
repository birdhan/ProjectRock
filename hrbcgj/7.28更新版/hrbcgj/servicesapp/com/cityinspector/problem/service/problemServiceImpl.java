package com.cityinspector.problem.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.problem.dao.IproblemDaoMybatis;
import com.cityinspector.problem.dao.problemDaoHibernate;
import com.cityinspector.problem.dao.problemDaoJDBC;
import com.cityinspector.problem.model.problem;

@Service
public class problemServiceImpl implements IproblemService {

	@Resource
	private IproblemDaoMybatis problemDaoMybatis;

	@Resource
	private problemDaoHibernate problemDaoHibernate;

	@Resource
	private problemDaoJDBC problemDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public problem getproblemById(String id) {
		return problemDaoHibernate.getproblemById(id);
	}

	/**
	 * 保存
	 */
	public problem saveproblem(problem problem) {
		return problemDaoHibernate.saveproblem(problem);
	}

	/**
	 * 列表查询
	 */
	public Map searchproblem(Long curPage, Long pageSize,String whereStr) {
		return problemDaoHibernate.searchproblem(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public problem delproblem(problem problem) {
		return problemDaoHibernate.delproblem(problem);
	}

	/**
	 * 批量删除
	 */
	public void delproblemBatch(List<String> list) {
		problemDaoHibernate.delproblemBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return problemDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<problem> list) {
		return problemDaoHibernate.saveDataBatch(list);
	}

}
