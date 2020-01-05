package com.cloud.index.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.index.dao.IndexDao;

@Service
public class IndexService {

	@Resource
	private IndexDao indexDao;
	
	/**
	 * 获取一级菜单
	 * @return
	 */
	public List<Map<String,Object>> getserviceSection(){
		return indexDao.getserviceSection();
	}
	
	public Map<String,Object> getserviceSectionById(String sectionId){
		return indexDao.getserviceSectionById(sectionId);
	}
	
	
	
	/**
	 * 获取二级菜单
	 * @return
	 */
	public List<Map<String,Object>> getSecondSection(String sectionId){
		return indexDao.getSecondSection(sectionId);
	}
	
	public Map<String,Object> getSecondSectionById(String secondSectionId){
		return indexDao.getSecondSectionById(secondSectionId);
	}
	
	/**
	 * 获取新闻信息
	 */
	public Map<String,Object> getArticleBySectionId(Long curPage, Long pageSize, String whereStr){
		return indexDao.getArticleBySectionId(curPage, pageSize, whereStr);
	}
	
	/**
	 * 获取新闻详情
	 * @return
	 */
	public Map<String,Object> getArticleDetailById(String articleId){
		return indexDao.getArticleDetailById(articleId);
	}
	
	/**
	 * 获取视频新闻详情
	 * @return
	 */
	public Map<String,Object> getArticleVideoDetailById(String articleId){
		return indexDao.getArticleVideoDetailById(articleId);
	}
	
	/**
	 * 获取我的信箱
	 */
	public Map<String,Object> getMyEmail(Long curPage, Long pageSize, String whereStr){
		return indexDao.getMyEmail(curPage, pageSize, whereStr);
	}
	
	
	/**
	 *获取问题
	 * @param curPage
	 * @param pageSize
	 * @param whereStr
	 * @return
	 */
	
	public Map<String,Object> getProblem(Long curPage, Long pageSize, String whereStr){
		return indexDao.getProblem(curPage, pageSize, whereStr);
	}

	/**
	 * 获取互动交流
	 */
	public Map<String,Object> getInteraction(Long curPage, Long pageSize, String whereStr){
		return indexDao.getInteraction(curPage, pageSize, whereStr);
	}
	
	/**
	 * 获取城管问计发布话题
	 */
	public Map<String,Object> getTopic(Long curPage, Long pageSize, String whereStr){
		return indexDao.getTopic(curPage, pageSize, whereStr);
	}
	
	/**
	 * 获取城管问计留言的内容
	 */
	public Map<String,Object> getViewmessage(Long curPage, Long pageSize, String whereStr){
		return indexDao.getViewmessage(curPage, pageSize, whereStr);
	}
	
	
}
