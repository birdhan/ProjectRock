package com.cloud.menupriv.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.base.cache.SysCache;
import com.cloud.menumanager.dao.MenuDaoHibernate;
import com.cloud.menumanager.model.Menu;
import com.cloud.menupriv.dao.IMenuPrivDaoMybatis;
import com.cloud.menupriv.dao.MenuPrivDaoHibernate;
import com.cloud.menupriv.dao.MenuPrivDaoJDBC;
import com.cloud.menupriv.model.MenuPriv;
import com.cloud.rolemenupriv.dao.RoleMenuPrivDaoHibernate;
import com.cloud.rolemenupriv.model.RoleMenuPriv;

@Service
public class MenuPrivServiceImpl implements IMenuPrivService {

	@Resource
	private IMenuPrivDaoMybatis menuPrivDaoMybatis;

	@Resource
	private MenuPrivDaoHibernate menuPrivDaoHibernate;

	@Resource
	private MenuPrivDaoJDBC menuPrivDaoJDBC;
	
	@Resource
	private MenuDaoHibernate menuDaoHibernate;
	
	@Resource
	private RoleMenuPrivDaoHibernate roleMenuPrivDaoHibernate;

	/**
	 * 通过id得到对象
	 */
	public MenuPriv getMenuPrivById(String id) {
		return menuPrivDaoHibernate.getMenuPrivById(id);
	}

	/**
	 * 保存
	 */
	public MenuPriv saveMenuPriv(MenuPriv menuPriv) {
		return menuPrivDaoHibernate.saveMenuPriv(menuPriv);
	}

	/**
	 * 列表查询
	 */
	public Map searchMenuPriv(Long curPage, Long pageSize,String whereStr) {
		return menuPrivDaoHibernate.searchMenuPriv(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public MenuPriv delMenuPriv(MenuPriv menuPriv) {
		return menuPrivDaoHibernate.delMenuPriv(menuPriv);
	}

	/**
	 * 批量删除
	 */
	public void delMenuPrivBatch(List<String> list) {
		menuPrivDaoHibernate.delMenuPrivBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return menuPrivDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<MenuPriv> list) {
		return menuPrivDaoHibernate.saveDataBatch(list);
	}

	/**
	 * 得到菜单权限及编号树
	 * @return
	 */
	public String treeMenuPriv() {
		String ctx = SysCache.getInstance().getContextPath();
		StringBuffer tree = new StringBuffer("");
		List<Menu> rootList = menuDaoHibernate.getAllDataByWhere(" and menu.parentId='root' and menu.menuType='0' order by menuSort");	//root下的模块菜单
		for(Menu module : rootList) {
			tree.append("<tr>");
			tree.append("	<td>");
			tree.append("		&nbsp;&nbsp;&nbsp;&nbsp;<img src=\""+ctx+"/images/folder-open.gif\" style=\"vertical-align: middle; margin-right:2px;\"/>"+module.getMenuName());
			tree.append("	</td>");
			tree.append("	<td>");
			tree.append("		&nbsp;");
			tree.append("	</td>");
			tree.append("</tr>");
			
			List<Menu> menuList = menuDaoHibernate.getAllDataByWhere(" and menu.parentId='"+module.getId()+"' and menu.menuType='1' order by menuSort");
			for(Menu menu : menuList) {
				tree.append("<tr>");
				tree.append("	<td>");
				tree.append("		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:editMenuPriv('"+menu.getId()+"','"+menu.getMenuName()+"')\"><img src=\""+ctx+"/images/tree-leaf.gif\" style=\"vertical-align: middle; margin-right:2px;\">"+menu.getMenuName()+"</a>");
				tree.append("	</td>");
				tree.append("	<td>");
				
				List<MenuPriv> privList = menuPrivDaoHibernate.getAllDataByWhere(" and menuPriv.linkMenuId='"+menu.getId()+"' order by menuPriv.privNo");
				if(privList != null && privList.size() != 0) {
					for(MenuPriv priv : privList) {
						tree.append("		<span style=\"margin-right:3px;color:#999999\">"+priv.getPrivName()+"(编号"+priv.getPrivNo()+")</span>");
					}
				} else {
					tree.append("		&nbsp;");
				}				
				tree.append("	</td>");
				tree.append("</tr>");
			}			
		}
		return tree.toString();
	}
	
	/**
	  * 树型视图展现带checkbox
	  * @return
	  * @throws Exception
	  */
	 public String treeMenuPrivWithCheckbox(String roleId) {
		 String ctx = SysCache.getInstance().getContextPath();
			StringBuffer tree = new StringBuffer("");
			List<Menu> rootList = menuDaoHibernate.getAllDataByWhere(" and menu.parentId='root' and menu.menuType='0' order by menuSort");	//root下的模块菜单
			
			List<RoleMenuPriv> rmpList = roleMenuPrivDaoHibernate.getAllDataByWhere(" and roleMenuPriv.linkRoleId='"+roleId+"'");										//得到该角色下所有角色-菜单权限关系
			
			for(int m=0;m<rootList.size();m++) {																							//遍历root下的所有模块
				Menu module = rootList.get(m);
				tree.append("<tr>");
				tree.append("	<td>");
				tree.append("		&nbsp;&nbsp;&nbsp;&nbsp;<img src=\""+ctx+"/images/folder-open.gif\" style=\"vertical-align: middle; margin-right:2px;\"/>"+module.getMenuName());
				tree.append("	</td>");
				tree.append("	<td>");
				tree.append("		&nbsp;");
				tree.append("	</td>");
				tree.append("	<td>");
				tree.append("		&nbsp;");
				tree.append("	</td>");
				tree.append("</tr>");
				
				List<Menu> menuList = menuDaoHibernate.getAllDataByWhere(" and menu.parentId='"+module.getId()+"' and menu.menuType='1' order by menuSort");
				for(int i=0;i<menuList.size();i++) {
					Menu menu = menuList.get(i);
					tree.append("<tr>");
					tree.append("	<td>");
					tree.append("		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=\""+ctx+"/images/tree-leaf.gif\" style=\"vertical-align: middle; margin-right:2px;\">"+menu.getMenuName());
					tree.append("	</td>");
					tree.append("	<td>");
					
					String chekced4Menu = "";
					if(rmpList != null && rmpList.size() != 0) {
						for(RoleMenuPriv rmp : rmpList) {
							if(rmp.getLinkMenuId().equals(menu.getId())) {		
								chekced4Menu = "checked='checked'";
							}
						}
					}
					tree.append("		<input type='checkbox' value='"+menu.getId()+"' id='priv"+m+i+"' name='menu' "+chekced4Menu+" onclick=\"javascript:choosePrivNo(this,'"+m+i+"')\"/>");		//菜单IDcheckbox，choosePrivNo方法(this,'01')
					tree.append("	</td>");
					tree.append("	<td>");
					
					List<MenuPriv> privList = menuPrivDaoHibernate.getAllDataByWhere(" and menuPriv.linkMenuId='"+menu.getId()+"' order by menuPriv.privNo");
					if(privList != null && privList.size() != 0) {
						tree.append("		<table style='width:100%;height:auto;'><tr>");
						for(int j=0;j<privList.size();j++) {
							MenuPriv priv = privList.get(j);
							String chekced4Priv = "";
							if(rmpList != null && rmpList.size() != 0) {
								for(RoleMenuPriv rmp : rmpList) {
									if(rmp.getLinkMenuId().equals(menu.getId()) && rmp.getPrivNo().equals(priv.getPrivNo())) {		
										chekced4Priv = "checked='checked'";
									}
								}
							}
							if(j!= 0 && j % 4 == 0) {
								tree.append("		</tr>");
								tree.append("		<tr>");
							}
							tree.append("		<td style='width:25%;border:none;'>");
							tree.append("			<input type='checkbox' value='"+priv.getPrivNo()+"' "+chekced4Priv+" id='priv"+m+i+j+"' name='priv"+m+i+"' onclick=\"javascript:chooseParentChk(this.name)\"/>"+priv.getPrivName()+"(编号"+priv.getPrivNo()+")");
							tree.append("		</td>");
						}
						for(int p=0;p<(4-privList.size()%4);p++) {
							tree.append("<td style='width:25%;border:none;'>&nbsp;</td>");
						}
						tree.append("		</tr></table>");
					} else {
						tree.append("		&nbsp;");
					}		
					tree.append("	</td>");
					tree.append("</tr>");
				}			
			}
			return tree.toString();
	 }
	 
	 /**
	  * 将权限编号转成名称
	  * @param privNo
	  * @return
	  */
	 public String privNo2Name(String privNo , String menuId){
		return menuPrivDaoHibernate.privNo2Name(privNo , menuId);
	 }
}
