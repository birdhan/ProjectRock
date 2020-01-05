package com.cityinspector.linkservice.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.linkservice .model.Link;

public interface ILinkService {

	/**
	 * 通过id得到某一对象
	 */
	public Link getLinkById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public Link saveLink(Link link);

	/**
	 * 分页查询
	 */
	public Map searchLink(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public Link delLink(Link link);

	/**
	 * 批量删除
	 */
	public void delLinkBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<Link> list);

}
