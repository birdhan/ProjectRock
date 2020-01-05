package com.cityinspector.interaction.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.base.util.DateHelper;
import com.cityinspector.interaction.model.Interaction;

@Repository
public class InteractionDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public Interaction getInteractionById(String id) {
		return (Interaction)getDataObject(Interaction.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public Interaction saveInteraction(Interaction interaction) {
		if(interaction.getId() == null || interaction.getId().equals("")) {
			saveData(interaction);
		} else {
			String curDate = DateHelper.yyyy_MM_dd.format(new Date());
			interaction.setReptime(curDate);
			interaction.setRepstatus("1");
			saveOrUpdate(interaction);
		}
		return interaction;
		
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<Interaction> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				Interaction interaction = list.get(i);
				session.save(interaction);
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
	public Map searchInteraction(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM Interaction interaction";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public Interaction delInteraction(Interaction interaction) {
		return (Interaction)delData(interaction);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delInteractionBatch(List<String> list) {
		String delHql = "DELETE Interaction ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM Interaction interaction where 1=1 " + where;
		return getDataList(hql);
	}

}
