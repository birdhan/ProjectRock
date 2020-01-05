package com.cityinspector.interaction.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.interaction .model.Interaction;

public interface IInteractionService {

	/**
	 * 通过id得到某一对象
	 */
	public Interaction getInteractionById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public Interaction saveInteraction(Interaction interaction);

	/**
	 * 分页查询
	 */
	public Map searchInteraction(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public Interaction delInteraction(Interaction interaction);

	/**
	 * 批量删除
	 */
	public void delInteractionBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<Interaction> list);

}
