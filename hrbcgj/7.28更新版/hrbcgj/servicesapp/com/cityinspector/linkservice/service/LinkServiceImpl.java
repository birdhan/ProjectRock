package com.cityinspector.linkservice.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.linkservice.dao.ILinkDaoMybatis;
import com.cityinspector.linkservice.dao.LinkDaoHibernate;
import com.cityinspector.linkservice.dao.LinkDaoJDBC;
import com.cityinspector.linkservice.model.Link;

@Service
public class LinkServiceImpl implements ILinkService {

	@Resource
	private ILinkDaoMybatis linkDaoMybatis;

	@Resource
	private LinkDaoHibernate linkDaoHibernate;

	@Resource
	private LinkDaoJDBC linkDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public Link getLinkById(String id) {
		return linkDaoHibernate.getLinkById(id);
	}

	/**
	 * 保存
	 */
	public Link saveLink(Link link) {
		return linkDaoHibernate.saveLink(link);
	}

	/**
	 * 列表查询
	 */
	public Map searchLink(Long curPage, Long pageSize,String whereStr) {
		return linkDaoHibernate.searchLink(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public Link delLink(Link link) {
		return linkDaoHibernate.delLink(link);
	}

	/**
	 * 批量删除
	 */
	public void delLinkBatch(List<String> list) {
		linkDaoHibernate.delLinkBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return linkDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<Link> list) {
		return linkDaoHibernate.saveDataBatch(list);
	}

}
