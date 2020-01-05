package com.cloud.menupriv.service;

import java.util.List;
import java.util.Map;

import com.cloud.menupriv .model.MenuPriv;

public interface IMenuPrivService {

	/**
	 * 通过id得到某一对象
	 */
	public MenuPriv getMenuPrivById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public MenuPriv saveMenuPriv(MenuPriv menuPriv);

	/**
	 * 分页查询
	 */
	public Map searchMenuPriv(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public MenuPriv delMenuPriv(MenuPriv menuPriv);

	/**
	 * 批量删除
	 */
	public void delMenuPrivBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<MenuPriv> list);
	
	/**
	 * 得到菜单权限及编号树
	 * @return
	 */
	public String treeMenuPriv();

	/**
	  * 树型视图展现带checkbox
	  * @return
	  * @throws Exception
	  */
	 public String treeMenuPrivWithCheckbox(String roleId);
	 
	 /**
	 * 将权限编号转成名称
	 * @param privNo
	 * @return
	 */
	public String privNo2Name(String privNo , String menuId);
}
