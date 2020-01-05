package com.cityinspector.guide.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.guide .model.Guide;

public interface IGuideService {

	/**
	 * 通过id得到某一对象
	 */
	public Guide getGuideById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public Guide saveGuide(Guide guide);

	/**
	 * 分页查询
	 */
	public Map searchGuide(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public Guide delGuide(Guide guide);

	/**
	 * 批量删除
	 */
	public void delGuideBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<Guide> list);

}
