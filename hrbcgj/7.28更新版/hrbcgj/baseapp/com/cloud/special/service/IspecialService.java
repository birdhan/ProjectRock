package com.cloud.special.service;

import java.util.List;
import java.util.Map;

import com.cloud.special .model.special;

public interface IspecialService {

	/**
	 * 通过id得到某一对象
	 */
	public special getspecialById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public special savespecial(special special);

	/**
	 * 分页查询
	 */
	public Map searchspecial(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public special delspecial(special special);

	/**
	 * 批量删除
	 */
	public void delspecialBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<special> list);

}
