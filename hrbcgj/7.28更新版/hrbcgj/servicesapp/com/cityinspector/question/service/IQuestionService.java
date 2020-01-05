package com.cityinspector.question.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.question .model.Question;

public interface IQuestionService {

	/**
	 * 通过id得到某一对象
	 */
	public Question getQuestionById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public Question saveQuestion(Question question);

	/**
	 * 分页查询
	 */
	public Map searchQuestion(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public Question delQuestion(Question question);

	/**
	 * 批量删除
	 */
	public void delQuestionBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<Question> list);

}
