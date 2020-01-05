package com.cityinspector.article.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cityinspector.article.model.Article;

@Repository
public class ArticleDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public Article getArticleById(String id) {
		return (Article)getDataObject(Article.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public Article saveArticle(Article article) {
		if(article.getId() == null || article.getId().equals("")) {
			saveData(article);
		} else {
			saveOrUpdate(article);
		}
		return article;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<Article> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				Article article = list.get(i);
				session.save(article);
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
	public Map searchArticle(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM Article article";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public Article delArticle(Article article) {
		return (Article)delData(article);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delArticleBatch(List<String> list) {
		String delHql = "DELETE Article ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM Article article where 1=1 " + where;
		return getDataList(hql);
	}

}
