package com.cloud.department.service;

import java.util.List;
import java.util.Map;

import com.cloud.department .model.Department;

public interface IDepartmentService {

	/**
	 * 通过id得到某一对象
	 */
	public Department getDepartmentById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public Department saveDepartment(Department department);

	/**
	 * 分页查询
	 */
	public Map searchDepartment(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public Department delDepartment(Department department);

	/**
	 * 批量删除
	 */
	public void delDepartmentBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<Department> list);

	/**
	 * 得到所有部门树串
	 * @return
	 */
	public String getAllDeptTree(String initDeptNos);
	
	/**
	 * 将部门编号转成部门名称
	 * @param deptNo
	 * @return
	 */
	public String deptNo2deptName(String deptNo);
	
	/**
	 * 部门树形视图
	 * @return
	 */
	public String treeView();
}
