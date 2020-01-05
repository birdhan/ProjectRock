package com.cloud.index.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cloud.base.util.ApplicationContextHolder;
import com.cloud.base.util.LoggerUtil;

@Repository
public class IndexDao {

	private JdbcTemplate getJdbcTemplate(){
		return (JdbcTemplate) ApplicationContextHolder.getInstance().getBean("jdbcTemplate");
	}
	
	public List<Map<String, Object>> getserviceSection(){
		String sql = "select * from service_section where isshow = '1' and postion = '1' order by sortnum";
		return getJdbcTemplate().queryForList(sql);
	}
	
	public Map<String, Object> getserviceSectionById(String sectionId){
		String sql = "select * from service_section where id = '"+sectionId+"' ";
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap = getJdbcTemplate().queryForMap(sql);
		}catch(Exception e){
			LoggerUtil.info(this.getClass(), "查询一级栏目结果为空");
		}
		return resultMap;
	}
	
	public List<Map<String, Object>> getSecondSection(String sectionId){
		String sql = "select * from service_section where isshow = '1' and pid = '"+sectionId+"' order by sortnum";
		return getJdbcTemplate().queryForList(sql);
	}
	
	public Map<String,Object> getSecondSectionById(String secondSectionId){
		String sql = "select * from service_section where id = '"+secondSectionId+"' ";
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap = getJdbcTemplate().queryForMap(sql);
		}catch(Exception e){
			LoggerUtil.info(this.getClass(), "查询二级栏目结果为空");
		}
		return resultMap;
	}
	
	public Map<String, Object> getArticleBySectionId(Long curPage, Long pageSize, String whereStr){
		String sql = "select * from SERVICE_ARTICLE ";
		return queryData2MapByPage(curPage, pageSize, sql, whereStr);
	}
	
	public Map<String, Object> queryData2MapByPage(Long curPage,Long pageSize,String sql, String whereStr) {
		int skip = pageSize.intValue()*(curPage.intValue()-1);
		int limit = pageSize.intValue();
		HashMap<String,Object> map = new HashMap<String,Object>();
		try {
	    	String queryStr = sql + " where 1=1";
	    	String queryStrWithWhere = queryStr;
	    	if (whereStr != null && whereStr.length() > 0) {
	    		queryStrWithWhere += whereStr;
	        }
	    	String queryCountStr = "select count(1) from ( " + queryStrWithWhere+" ) a ";
	    	int total =  getJdbcTemplate().queryForInt(queryCountStr);
	    	sql = "select  * from ( "+queryStrWithWhere+" ) a order by a.createtime desc limit "+skip+","+limit+"";
	    	/*sql = "SELECT * FROM ( "
	    			+ "SELECT ROWNUM rn,a.* FROM ( "+sql+" ) a WHERE ROWNUM <= "+limit+" ) t2"
	    		+ " WHERE t2.rn > "+skip+" ";*/
	    	List<Map<String,Object>> result = getJdbcTemplate().queryForList(sql);
	        
	        map.put("total", total);//集合总数
	        map.put("result", result);//查询结果集	        
		}catch (Exception e){
			e.printStackTrace();
		} finally {
		}
		return map;
	}
	
	public Map<String,Object> getArticleDetailById(String articleId){
		String sql = "select * from service_article where id = '"+articleId+"' ";
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap = getJdbcTemplate().queryForMap(sql);
		}catch(Exception e){
			LoggerUtil.info(this.getClass(), "查询文字详情结果为空");
		}
		return resultMap;
	}
	
	public Map<String,Object> getArticleVideoDetailById(String articleId){
		String sql = "select a.*,s.relativepath from SERVICE_ARTICLE a "
				+ "left join system_attachment s "
				+ "on s.id = a.videoid  where a.id='"+articleId+"' ";
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap = getJdbcTemplate().queryForMap(sql);
		}catch(Exception e){
			LoggerUtil.info(this.getClass(), "查询视频详情结果为空");
		}
		return resultMap;
	}
	
	public Map<String, Object> getMyEmail(Long curPage, Long pageSize, String whereStr){
		String sql = "select * from SERVICE_PREMIERLETTER ";
		return queryData2MapByPageMyEmail(curPage, pageSize, sql, whereStr);
	}
	
	public Map<String, Object> queryData2MapByPageMyEmail(Long curPage,Long pageSize,String sql, String whereStr) {
		int skip = pageSize.intValue()*(curPage.intValue()-1);
		int limit = pageSize.intValue();
		HashMap<String,Object> map = new HashMap<String,Object>();
		try {
	    	String queryStr = sql + " where 1=1";
	    	String queryStrWithWhere = queryStr;
	    	if (whereStr != null && whereStr.length() > 0) {
	    		queryStrWithWhere += whereStr;
	        }
	    	String queryCountStr = "select count(1) from ( " + queryStrWithWhere+" ) a ";
	    	int total =  getJdbcTemplate().queryForInt(queryCountStr);
	    	sql = "select  * from ( "+queryStrWithWhere+" ) a order by a.reqtime desc limit "+skip+","+limit+"";
	    	/*sql = "SELECT * FROM ( "
	    			+ "SELECT ROWNUM rn,a.* FROM ( "+sql+" ) a WHERE ROWNUM <= "+limit+" ) t2"
	    		+ " WHERE t2.rn > "+skip+" ";*/
	    	List<Map<String,Object>> result = getJdbcTemplate().queryForList(sql);
	        map.put("total", total);//集合总数
	        map.put("result", result);//查询结果集	        
		}catch (Exception e){
			e.printStackTrace();
		} finally {
		}
		return map;
	}
	
	public Map<String, Object> getProblem(Long curPage, Long pageSize, String whereStr){
		String sql = "select * from SERVICE_PROBLEM ";
		return queryData2MapByPageProblem(curPage, pageSize, sql, whereStr);
}
	
	public Map<String, Object> queryData2MapByPageProblem(Long curPage,Long pageSize,String sql, String whereStr) {
		int skip = pageSize.intValue()*(curPage.intValue()-1);
		int limit = pageSize.intValue();
		HashMap<String,Object> map = new HashMap<String,Object>();
		try {
	    	String queryStr = sql + " where 1=1";
	    	String queryStrWithWhere = queryStr;
	    	if (whereStr != null && whereStr.length() > 0) {
	    		queryStrWithWhere += whereStr;
	        }
	    	String queryCountStr = "select count(1) from ( "+queryStrWithWhere+" ) a ";
	    	int total =  getJdbcTemplate().queryForInt(queryCountStr);
	    	sql = "select  * from ( "+queryStrWithWhere+" ) a order by a.time desc limit "+skip+","+limit+"";
	    	/*sql = "SELECT * FROM ( "
	    			+ "SELECT ROWNUM rn,a.* FROM ( "+sql+" ) a WHERE ROWNUM <= "+limit+" ) t2"
	    		+ " WHERE t2.rn > "+skip+" ";*/
	    	List<Map<String,Object>> result = getJdbcTemplate().queryForList(sql);
	        map.put("total", total);//集合总数
	        map.put("result", result);//查询结果集	        
		}catch (Exception e){
			e.printStackTrace();
		} finally {
		}
		return map;
	}

	/**
	 * 互动交流
	 */
	public Map<String, Object> getInteraction(Long curPage, Long pageSize, String whereStr){
		String sql = "select * from SERVICE_INTERACTION ";
		return queryData2MapByPageInteraction(curPage, pageSize, sql, whereStr);
	}
	
	public Map<String, Object> queryData2MapByPageInteraction(Long curPage,Long pageSize,String sql, String whereStr) {
		int skip = pageSize.intValue()*(curPage.intValue()-1);
		int limit = pageSize.intValue();
		HashMap<String,Object> map = new HashMap<String,Object>();
		try {
	    	String queryStr = sql + " where 1=1";
	    	String queryStrWithWhere = queryStr;
	    	if (whereStr != null && whereStr.length() > 0) {
	    		queryStrWithWhere += whereStr;
	        }
	    	String queryCountStr = "select count(1) from ( " + queryStrWithWhere+" ) a ";
	    	int total =  getJdbcTemplate().queryForInt(queryCountStr);
	    	sql = "select  * from ( "+queryStrWithWhere+" ) a order by a.reqtime desc limit "+skip+","+limit+"";
	    	/*sql = "SELECT * FROM ( "
	    			+ "SELECT ROWNUM rn,a.* FROM ( "+sql+" ) a WHERE ROWNUM <= "+limit+" ) t2"
	    		+ " WHERE t2.rn > "+skip+" ";*/
	    	List<Map<String,Object>> result = getJdbcTemplate().queryForList(sql);
	        map.put("total", total);//集合总数
	        map.put("result", result);//查询结果集	        
		}catch (Exception e){
			e.printStackTrace();
		} finally {
		}
		return map;
	}
	
	/**
	 * 城管问计发布的话题
	 */
	public Map<String, Object> getTopic(Long curPage, Long pageSize, String whereStr){
		String sql = "select * from SERVICE_TOPIC ";
		return queryData2MapByPageTopic(curPage, pageSize, sql, whereStr);
	}
	
	public Map<String, Object> queryData2MapByPageTopic(Long curPage,Long pageSize,String sql, String whereStr) {
		int skip = pageSize.intValue()*(curPage.intValue()-1);
		int limit = pageSize.intValue();
		HashMap<String,Object> map = new HashMap<String,Object>();
		try {
	    	String queryStr = sql + " where 1=1";
	    	String queryStrWithWhere = queryStr;
	    	if (whereStr != null && whereStr.length() > 0) {
	    		queryStrWithWhere += whereStr;
	        }
	    	String queryCountStr = "select count(1) from ( " + queryStrWithWhere+" ) a ";
	    	int total =  getJdbcTemplate().queryForInt(queryCountStr);
	    	sql = "select  * from ( "+queryStrWithWhere+" ) a order by a.createtime desc limit "+skip+","+limit+"";
	    	/*sql = "SELECT * FROM ( "
	    			+ "SELECT ROWNUM rn,a.* FROM ( "+sql+" ) a WHERE ROWNUM <= "+limit+" ) t2"
	    		+ " WHERE t2.rn > "+skip+" ";*/
	    	List<Map<String,Object>> result = getJdbcTemplate().queryForList(sql);
	        map.put("total", total);//集合总数
	        map.put("result", result);//查询结果集	        
		}catch (Exception e){
			e.printStackTrace();
		} finally {
		}
		return map;
	}
	
	/**
	 * 城管问计留言的内容
	 */
	public Map<String, Object> getViewmessage(Long curPage, Long pageSize, String whereStr){
		String sql = "select * from SERVICE_VIEWMESASGE ";
		return queryData2MapByPagegetViewmessage(curPage, pageSize, sql, whereStr);
	}
	
	public Map<String, Object> queryData2MapByPagegetViewmessage(Long curPage,Long pageSize,String sql, String whereStr) {
		int skip = pageSize.intValue()*(curPage.intValue()-1);
		int limit = pageSize.intValue();
		HashMap<String,Object> map = new HashMap<String,Object>();
		try {
	    	String queryStr = sql + " where 1=1";
	    	String queryStrWithWhere = queryStr;
	    	if (whereStr != null && whereStr.length() > 0) {
	    		queryStrWithWhere += whereStr;
	        }
	    	String queryCountStr = "select count(1) from ( " + queryStrWithWhere+" ) a ";
	    	int total =  getJdbcTemplate().queryForInt(queryCountStr);
	    	sql = "select  * from ( "+queryStrWithWhere+" ) a order by a.reqtime desc limit "+skip+","+limit+"";
	    	/*sql = "SELECT * FROM ( "
	    			+ "SELECT ROWNUM rn,a.* FROM ( "+sql+" ) a WHERE ROWNUM <= "+limit+" ) t2"
	    		+ " WHERE t2.rn > "+skip+" ";*/
	    	List<Map<String,Object>> result = getJdbcTemplate().queryForList(sql);
	        map.put("total", total);//集合总数
	        map.put("result", result);//查询结果集	        
		}catch (Exception e){
			e.printStackTrace();
		} finally {
		}
		return map;
	}
}
