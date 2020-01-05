package com.cityinspector.premierletter.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.premierletter.dao.IPremierLetterDaoMybatis;
import com.cityinspector.premierletter.dao.PremierLetterDaoHibernate;
import com.cityinspector.premierletter.dao.PremierLetterDaoJDBC;
import com.cityinspector.premierletter.model.PremierLetter;

@Service
public class PremierLetterServiceImpl implements IPremierLetterService {

	@Resource
	private IPremierLetterDaoMybatis premierLetterDaoMybatis;

	@Resource
	private PremierLetterDaoHibernate premierLetterDaoHibernate;

	@Resource
	private PremierLetterDaoJDBC premierLetterDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public PremierLetter getPremierLetterById(String id) {
		return premierLetterDaoHibernate.getPremierLetterById(id);
	}

	/**
	 * 保存
	 */
	public PremierLetter savePremierLetter(PremierLetter premierLetter) {
		return premierLetterDaoHibernate.savePremierLetter(premierLetter);
	}

	/**
	 * 列表查询
	 */
	public Map searchPremierLetter(Long curPage, Long pageSize,String whereStr) {
		return premierLetterDaoHibernate.searchPremierLetter(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public PremierLetter delPremierLetter(PremierLetter premierLetter) {
		return premierLetterDaoHibernate.delPremierLetter(premierLetter);
	}

	/**
	 * 批量删除
	 */
	public void delPremierLetterBatch(List<String> list) {
		premierLetterDaoHibernate.delPremierLetterBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return premierLetterDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<PremierLetter> list) {
		return premierLetterDaoHibernate.saveDataBatch(list);
	}

}
