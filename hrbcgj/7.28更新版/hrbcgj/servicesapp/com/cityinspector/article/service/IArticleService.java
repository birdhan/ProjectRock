package com.cityinspector.article.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.article .model.Article;

public interface IArticleService {

	/**
	 * 通过id得到某一对象
	 */
	public Article getArticleById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public Article saveArticle(Article article);

	/**
	 * 分页查询
	 */
	public Map searchArticle(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public Article delArticle(Article article);

	/**
	 * 批量删除
	 */
	public void delArticleBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<Article> list);

}
