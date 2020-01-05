package com.cityinspector.interaction.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.interaction.dao.IInteractionDaoMybatis;
import com.cityinspector.interaction.dao.InteractionDaoHibernate;
import com.cityinspector.interaction.dao.InteractionDaoJDBC;
import com.cityinspector.interaction.model.Interaction;

@Service
public class InteractionServiceImpl implements IInteractionService {

	@Resource
	private IInteractionDaoMybatis interactionDaoMybatis;

	@Resource
	private InteractionDaoHibernate interactionDaoHibernate;

	@Resource
	private InteractionDaoJDBC interactionDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public Interaction getInteractionById(String id) {
		return interactionDaoHibernate.getInteractionById(id);
	}

	/**
	 * 保存
	 */
	public Interaction saveInteraction(Interaction interaction) {
		return interactionDaoHibernate.saveInteraction(interaction);
	}

	/**
	 * 列表查询
	 */
	public Map searchInteraction(Long curPage, Long pageSize,String whereStr) {
		return interactionDaoHibernate.searchInteraction(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public Interaction delInteraction(Interaction interaction) {
		return interactionDaoHibernate.delInteraction(interaction);
	}

	/**
	 * 批量删除
	 */
	public void delInteractionBatch(List<String> list) {
		interactionDaoHibernate.delInteractionBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return interactionDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<Interaction> list) {
		return interactionDaoHibernate.saveDataBatch(list);
	}

}
