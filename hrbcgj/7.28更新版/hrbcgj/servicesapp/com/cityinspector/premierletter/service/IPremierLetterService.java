package com.cityinspector.premierletter.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.premierletter .model.PremierLetter;

public interface IPremierLetterService {

	/**
	 * 通过id得到某一对象
	 */
	public PremierLetter getPremierLetterById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public PremierLetter savePremierLetter(PremierLetter premierLetter);

	/**
	 * 分页查询
	 */
	public Map searchPremierLetter(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public PremierLetter delPremierLetter(PremierLetter premierLetter);

	/**
	 * 批量删除
	 */
	public void delPremierLetterBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<PremierLetter> list);

}
