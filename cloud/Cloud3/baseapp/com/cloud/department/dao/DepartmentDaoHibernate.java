package com.cloud.department.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.cache.SysCache;
import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.department.model.Department;
import com.cloud.systemconfig.model.SystemConfig;

@Repository
public class DepartmentDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public Department getDepartmentById(String id) {
		return (Department)getDataObject(Department.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public Department saveDepartment(Department department) {
		if(department.getId() == null || department.getId().equals("")) {
			saveData(department);
		} else {
			saveOrUpdate(department);
		}
		return department;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<Department> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				Department department = list.get(i);
				session.save(department);
				if (i == list.size()-1) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			closeSession();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
			flag = false;
		} finally {
			return flag;
		}
	}

	/**
	 * 列表查询
	 * @param curPage
	 * @param pageSize
	 * @param whereStr
	 * @return
	 */
	public Map searchDepartment(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM Department department";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public Department delDepartment(Department department) {
		return (Department)delData(department);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delDepartmentBatch(List<String> list) {
		String delHql = "DELETE Department ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM Department department where 1=1 " + where;
		return getDataList(hql);
	}

	/**
	 * 将部门编号转成部门名称
	 * @param deptNo
	 * @return
	 */
	public String deptNo2deptName(String deptNo) {
		String deptName = "";
		SystemConfig sc = SysCache.getInstance().getSystemConfig();
		if(deptNo.equals(sc.getRootDepartNo())) {
			deptName = sc.getRootDepartName();
		} else {
			String sql = "select DEPARTNAME from SYSTEM_DEPARTMENT where DEPARTNO='"+deptNo+"'";
			Map map = jdbcTemplate.queryForMap(sql);
			if(map != null) {
				deptName = (String)map.get("DEPARTNAME");
			}
		}
		return deptName;
	}
}
