package com.cloud.menumanager.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.ApplicationContextHolder;
import com.cloud.base.util.DBFM;
import com.cloud.base.util.StringUtil;
import com.cloud.menumanager.dao.IMenuDaoMybatis;
import com.cloud.menumanager.dao.MenuDaoHibernate;
import com.cloud.menumanager.dao.MenuDaoJDBC;
import com.cloud.menumanager.model.Menu;

@Service
public class MenuServiceImpl implements IMenuService {

	@Resource
	private IMenuDaoMybatis menuDaoMybatis;

	@Resource
	private MenuDaoHibernate menuDaoHibernate;

	@Resource
	private MenuDaoJDBC menuDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public Menu getMenuById(String id) {
		return menuDaoHibernate.getMenuById(id);
	}

	/**
	 * 保存
	 */
	public Menu saveMenu(Menu menu) {
		return menuDaoHibernate.saveMenu(menu);
	}

	/**
	 * 列表查询
	 */
	public Map searchMenu(Long curPage, Long pageSize,String whereStr) {
		return menuDaoHibernate.searchMenu(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public Menu delMenu(Menu menu) {
		return menuDaoHibernate.delMenu(menu);
	}

	/**
	 * 批量删除
	 */
	public void delMenuBatch(List<String> list) {
		menuDaoHibernate.delMenuBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return menuDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<Menu> list) {
		return menuDaoHibernate.saveDataBatch(list);
	}

	/**
	 * 预览菜单树
	 * @return
	 */
	public String viewMenuTree() {
		StringBuffer menuTree = new StringBuffer("");
		List<Menu> moduleList =  menuDaoHibernate.getAllDataByWhere(" and menu.parentId='root'order by menu.menuType,menu.parentId,menu.menuSort");		//得到所有模块菜单
		for(Menu m : moduleList) {
			menuTree.append("<div title=\""+m.getMenuName()+"\" style=\"padding:1px\">");
			menuTree.append("<ul>");
			List<Menu> subMenu =  menuDaoHibernate.getAllDataByWhere(" and menu.parentId='"+m.getId()+"'order by menu.menuType,menu.parentId,menu.menuSort");		//得到当前模块下的菜单
			for(Menu sub : subMenu) {
				menuTree.append("<li>"+sub.getMenuName()+"</li>");
			}
			menuTree.append("</ul>");
			menuTree.append("</div>");
		}	
		return menuTree.toString();
	}
	
	/**
	 * 得到所有菜单结构
	 * @return
	 */
	public String getAllMenuTree() {
		String ctx = SysCache.getInstance().getContextPath();
		StringBuffer menuTree = new StringBuffer("");
		List<Menu> moduleList =  menuDaoHibernate.getAllDataByWhere(" and menu.parentId='root'order by menu.menuType,menu.parentId,menu.menuSort");		//得到所有模块菜单
		for(Menu m : moduleList) {
			menuTree.append("<h3 onclick=\"openMenu(this)\">");
			menuTree.append("<a href=\"#\">"+m.getMenuName()+"</a>");
			menuTree.append("</h3>");
			menuTree.append("<ul id=\"child\">");
			List<Menu> subMenu =  menuDaoHibernate.getAllDataByWhere(" and menu.parentId='"+m.getId()+"'order by menu.menuType,menu.parentId,menu.menuSort");		//得到当前模块下的菜单
			for(Menu sub : subMenu) {
				menuTree.append("<li><a href=\""+ctx+""+sub.getMenuUrl()+"\" target=\"rightIframe\">"+sub.getMenuName()+"</a></li>");
			}
			menuTree.append("</ul>");
		}	
		return menuTree.toString();
	}
	
	/**
	 * 得到所有菜单结构
	 * @return
	 */
	public String getAllMenuTreeByUserId(String userId) {
		
		JdbcTemplate jdbc = (JdbcTemplate)ApplicationContextHolder.getInstance().getBean("jdbcTemplate");
		String queryMenuId = "select replace("+DBFM.WM_CONCAT()+"(t.linkmenuid),',',',') as linkmenuid from(select linkmenuid as linkmenuid from SYSTEM_ROLEMENUPRIV where LINKROLEID in (select LINKROLEID from SYSTEM_ROLEUSER where userid='"+userId+"') group by linkmenuid) t";
		Map map = jdbc.queryForMap(queryMenuId);
		String menuIds = "";
		if(map != null) {
			menuIds = StringUtil.null2String((String)map.get("linkmenuid"));
		}
		
		String ctx = SysCache.getInstance().getContextPath();
		StringBuffer menuTree = new StringBuffer("");
		List<Menu> moduleList =  menuDaoHibernate.getAllDataByWhere(" and menu.parentId='root'order by menu.menuType,menu.parentId,menu.menuSort");		//得到所有模块菜单
		for(Menu m : moduleList) {
			int flag = 0;
			StringBuffer curTree = new StringBuffer("");
			curTree.append("<h3 onclick=\"openMenu(this)\">");
			curTree.append("<a href=\"#\">"+m.getMenuName()+"</a>");
			curTree.append("</h3>");
			curTree.append("<ul id=\"child\">");
			List<Menu> subMenu =  menuDaoHibernate.getAllDataByWhere(" and menu.parentId='"+m.getId()+"'order by menu.menuType,menu.parentId,menu.menuSort");		//得到当前模块下的菜单
			for(Menu sub : subMenu) {
				for(String menuId : menuIds.split(",")) {
					if(menuId.equals(sub.getId())) {
						curTree.append("<li><a href=\""+ctx+""+sub.getMenuUrl()+"\" target=\"rightIframe\">"+sub.getMenuName()+"</a></li>");
						flag++;
					}
				}				
			}
			curTree.append("</ul>");
			if(flag != 0) {
				menuTree.append(curTree.toString());
			}			
		}	
		return menuTree.toString();
	}
	
	/**
	 * 将菜单id转成name值
	 * @param id
	 * @return
	 */
	public String id2Name(String id) {
		return menuDaoHibernate.id2Name(id);
	}
}
