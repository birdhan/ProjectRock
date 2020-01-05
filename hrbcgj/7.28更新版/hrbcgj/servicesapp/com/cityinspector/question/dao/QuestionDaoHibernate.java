package com.cityinspector.question.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cityinspector.question.model.Question;

@Repository
public class QuestionDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public Question getQuestionById(String id) {
		return (Question)getDataObject(Question.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public Question saveQuestion(Question question) {
		if(question.getId() == null || question.getId().equals("")) {
			saveData(question);
		} else {
			saveOrUpdate(question);
		}
		return question;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<Question> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				Question question = list.get(i);
				session.save(question);
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
	public Map searchQuestion(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM Question question";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public Question delQuestion(Question question) {
		return (Question)delData(question);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delQuestionBatch(List<String> list) {
		String delHql = "DELETE Question ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM Question question where 1=1 " + where;
		return getDataList(hql);
	}

}
