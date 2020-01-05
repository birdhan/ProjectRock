package com.cloud.menumanager.service;

import java.util.List;
import java.util.Map;

import com.cloud.menumanager .model.Menu;

public interface IMenuService {

	/**
	 * 通过id得到某一对象
	 */
	public Menu getMenuById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public Menu saveMenu(Menu menu);

	/**
	 * 分页查询
	 */
	public Map searchMenu(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public Menu delMenu(Menu menu);

	/**
	 * 批量删除
	 */
	public void delMenuBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<Menu> list);

	/**
	 * 预览菜单树
	 * @return
	 */
	public String viewMenuTree();
	
	/**
	 * 得到所有菜单结构
	 * @return
	 */
	public String getAllMenuTree();
	
	/**
	 * 将菜单id转成name值
	 * @param id
	 * @return
	 */
	public String id2Name(String id);
	
	/**
	 * 得到所有菜单结构
	 * @return
	 */
	public String getAllMenuTreeByUserId(String userId);
}
