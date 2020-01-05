package com.cityinspector.guide.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.guide.dao.IGuideDaoMybatis;
import com.cityinspector.guide.dao.GuideDaoHibernate;
import com.cityinspector.guide.dao.GuideDaoJDBC;
import com.cityinspector.guide.model.Guide;

@Service
public class GuideServiceImpl implements IGuideService {

	@Resource
	private IGuideDaoMybatis guideDaoMybatis;

	@Resource
	private GuideDaoHibernate guideDaoHibernate;

	@Resource
	private GuideDaoJDBC guideDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public Guide getGuideById(String id) {
		return guideDaoHibernate.getGuideById(id);
	}

	/**
	 * 保存
	 */
	public Guide saveGuide(Guide guide) {
		return guideDaoHibernate.saveGuide(guide);
	}

	/**
	 * 列表查询
	 */
	public Map searchGuide(Long curPage, Long pageSize,String whereStr) {
		return guideDaoHibernate.searchGuide(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public Guide delGuide(Guide guide) {
		return guideDaoHibernate.delGuide(guide);
	}

	/**
	 * 批量删除
	 */
	public void delGuideBatch(List<String> list) {
		guideDaoHibernate.delGuideBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return guideDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<Guide> list) {
		return guideDaoHibernate.saveDataBatch(list);
	}

}
