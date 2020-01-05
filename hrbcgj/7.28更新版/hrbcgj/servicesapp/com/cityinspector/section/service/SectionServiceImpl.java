package com.cityinspector.section.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.section.dao.ISectionDaoMybatis;
import com.cityinspector.section.dao.SectionDaoHibernate;
import com.cityinspector.section.dao.SectionDaoJDBC;
import com.cityinspector.section.model.Section;

@Service
public class SectionServiceImpl implements ISectionService {

	@Resource
	private ISectionDaoMybatis sectionDaoMybatis;

	@Resource
	private SectionDaoHibernate sectionDaoHibernate;

	@Resource
	private SectionDaoJDBC sectionDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public Section getSectionById(String id) {
		return sectionDaoHibernate.getSectionById(id);
	}

	/**
	 * 保存
	 */
	public Section saveSection(Section section) {
		return sectionDaoHibernate.saveSection(section);
	}

	/**
	 * 列表查询
	 */
	public Map searchSection(Long curPage, Long pageSize,String whereStr) {
		return sectionDaoHibernate.searchSection(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public Section delSection(Section section) {
		return sectionDaoHibernate.delSection(section);
	}

	/**
	 * 批量删除
	 */
	public void delSectionBatch(List<String> list) {
		sectionDaoHibernate.delSectionBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return sectionDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<Section> list) {
		return sectionDaoHibernate.saveDataBatch(list);
	}

}
