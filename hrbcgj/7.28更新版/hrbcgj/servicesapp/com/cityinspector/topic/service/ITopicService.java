package com.cityinspector.topic.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.topic .model.Topic;

public interface ITopicService {

	/**
	 * 通过id得到某一对象
	 */
	public Topic getTopicById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public Topic saveTopic(Topic topic);

	/**
	 * 分页查询
	 */
	public Map searchTopic(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public Topic delTopic(Topic topic);

	/**
	 * 批量删除
	 */
	public void delTopicBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<Topic> list);

}
