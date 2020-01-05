package com.cityinspector.article.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.article.dao.IArticleDaoMybatis;
import com.cityinspector.article.dao.ArticleDaoHibernate;
import com.cityinspector.article.dao.ArticleDaoJDBC;
import com.cityinspector.article.model.Article;

@Service
public class ArticleServiceImpl implements IArticleService {

	@Resource
	private IArticleDaoMybatis articleDaoMybatis;

	@Resource
	private ArticleDaoHibernate articleDaoHibernate;

	@Resource
	private ArticleDaoJDBC articleDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public Article getArticleById(String id) {
		return articleDaoHibernate.getArticleById(id);
	}

	/**
	 * 保存
	 */
	public Article saveArticle(Article article) {
		return articleDaoHibernate.saveArticle(article);
	}

	/**
	 * 列表查询
	 */
	public Map searchArticle(Long curPage, Long pageSize,String whereStr) {
		return articleDaoHibernate.searchArticle(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public Article delArticle(Article article) {
		return articleDaoHibernate.delArticle(article);
	}

	/**
	 * 批量删除
	 */
	public void delArticleBatch(List<String> list) {
		articleDaoHibernate.delArticleBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return articleDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<Article> list) {
		return articleDaoHibernate.saveDataBatch(list);
	}

}
