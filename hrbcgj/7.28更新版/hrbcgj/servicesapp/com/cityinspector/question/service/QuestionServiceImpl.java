package com.cityinspector.question.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.question.dao.IQuestionDaoMybatis;
import com.cityinspector.question.dao.QuestionDaoHibernate;
import com.cityinspector.question.dao.QuestionDaoJDBC;
import com.cityinspector.question.model.Question;

@Service
public class QuestionServiceImpl implements IQuestionService {

	@Resource
	private IQuestionDaoMybatis questionDaoMybatis;

	@Resource
	private QuestionDaoHibernate questionDaoHibernate;

	@Resource
	private QuestionDaoJDBC questionDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public Question getQuestionById(String id) {
		return questionDaoHibernate.getQuestionById(id);
	}

	/**
	 * 保存
	 */
	public Question saveQuestion(Question question) {
		return questionDaoHibernate.saveQuestion(question);
	}

	/**
	 * 列表查询
	 */
	public Map searchQuestion(Long curPage, Long pageSize,String whereStr) {
		return questionDaoHibernate.searchQuestion(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public Question delQuestion(Question question) {
		return questionDaoHibernate.delQuestion(question);
	}

	/**
	 * 批量删除
	 */
	public void delQuestionBatch(List<String> list) {
		questionDaoHibernate.delQuestionBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return questionDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<Question> list) {
		return questionDaoHibernate.saveDataBatch(list);
	}

}
