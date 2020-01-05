package com.cityinspector.problem.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cityinspector.problem.model.problem;
import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.base.util.DateHelper;

@Repository
public class problemDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public problem getproblemById(String id) {
		return (problem)getDataObject(problem.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public problem saveproblem(problem problem) {
		if(problem.getId() == null || problem.getId().equals("")) {
			saveData(problem);
		} else {
			String curDate = DateHelper.yyyy_MM_dd.format(new Date());
			problem.setResponseTime(curDate);
			problem.setStatus("2");
			saveOrUpdate(problem);
		}
		return problem;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<problem> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				problem problem = list.get(i);
				session.save(problem);
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
	public Map searchproblem(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM problem problem";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public problem delproblem(problem problem) {
		return (problem)delData(problem);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delproblemBatch(List<String> list) {
		String delHql = "DELETE problem ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM problem problem where 1=1 " + where;
		return getDataList(hql);
	}

}
