package com.cityinspector.section.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.section .model.Section;

public interface ISectionService {

	/**
	 * 通过id得到某一对象
	 */
	public Section getSectionById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public Section saveSection(Section section);

	/**
	 * 分页查询
	 */
	public Map searchSection(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public Section delSection(Section section);

	/**
	 * 批量删除
	 */
	public void delSectionBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<Section> list);

}
