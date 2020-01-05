package com.cityinspector.topic.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.topic.dao.ITopicDaoMybatis;
import com.cityinspector.topic.dao.TopicDaoHibernate;
import com.cityinspector.topic.dao.TopicDaoJDBC;
import com.cityinspector.topic.model.Topic;

@Service
public class TopicServiceImpl implements ITopicService {

	@Resource
	private ITopicDaoMybatis topicDaoMybatis;

	@Resource
	private TopicDaoHibernate topicDaoHibernate;

	@Resource
	private TopicDaoJDBC topicDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public Topic getTopicById(String id) {
		return topicDaoHibernate.getTopicById(id);
	}

	/**
	 * 保存
	 */
	public Topic saveTopic(Topic topic) {
		return topicDaoHibernate.saveTopic(topic);
	}

	/**
	 * 列表查询
	 */
	public Map searchTopic(Long curPage, Long pageSize,String whereStr) {
		return topicDaoHibernate.searchTopic(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public Topic delTopic(Topic topic) {
		return topicDaoHibernate.delTopic(topic);
	}

	/**
	 * 批量删除
	 */
	public void delTopicBatch(List<String> list) {
		topicDaoHibernate.delTopicBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return topicDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<Topic> list) {
		return topicDaoHibernate.saveDataBatch(list);
	}

}
