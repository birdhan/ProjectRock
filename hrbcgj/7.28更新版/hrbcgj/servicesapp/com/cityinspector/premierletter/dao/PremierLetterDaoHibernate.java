package com.cityinspector.premierletter.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cityinspector.premierletter.model.PremierLetter;
import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.base.util.DateHelper;

@Repository
public class PremierLetterDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public PremierLetter getPremierLetterById(String id) {
		return (PremierLetter)getDataObject(PremierLetter.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public PremierLetter savePremierLetter(PremierLetter premierLetter) {
		if(premierLetter.getId() == null || premierLetter.getId().equals("")) {
			saveData(premierLetter);
		} else {
			String curDate = DateHelper.yyyy_MM_dd.format(new Date());
			premierLetter.setReptime(curDate);
			premierLetter.setRepstatus("1");
			saveOrUpdate(premierLetter);
		}
		return premierLetter;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<PremierLetter> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				PremierLetter premierLetter = list.get(i);
				session.save(premierLetter);
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
	public Map searchPremierLetter(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM PremierLetter premierLetter";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public PremierLetter delPremierLetter(PremierLetter premierLetter) {
		return (PremierLetter)delData(premierLetter);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delPremierLetterBatch(List<String> list) {
		String delHql = "DELETE PremierLetter ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM PremierLetter premierLetter where 1=1 " + where;
		return getDataList(hql);
	}

}
