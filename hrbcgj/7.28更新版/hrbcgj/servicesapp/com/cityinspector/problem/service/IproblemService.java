package com.cityinspector.problem.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.problem .model.problem;

public interface IproblemService {

	/**
	 * 通过id得到某一对象
	 */
	public problem getproblemById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public problem saveproblem(problem problem);

	/**
	 * 分页查询
	 */
	public Map searchproblem(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public problem delproblem(problem problem);

	/**
	 * 批量删除
	 */
	public void delproblemBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<problem> list);

}
