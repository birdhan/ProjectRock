package com.cloud.unit.service;

import java.util.List;
import java.util.Map;

import com.cloud.unit .model.unit;

public interface IunitService {

	/**
	 * 通过id得到某一对象
	 */
	public unit getunitById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public unit saveunit(unit unit);

	/**
	 * 分页查询
	 */
	public Map searchunit(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public unit delunit(unit unit);

	/**
	 * 批量删除
	 */
	public void delunitBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<unit> list);

}
