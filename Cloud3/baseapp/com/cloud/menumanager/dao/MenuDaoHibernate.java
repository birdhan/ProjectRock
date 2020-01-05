package com.cloud.menumanager.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.menumanager.model.Menu;

@Repository
public class MenuDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public Menu getMenuById(String id) {
		return (Menu)getDataObject(Menu.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public Menu saveMenu(Menu menu) {
		if(menu.getId() == null || menu.getId().equals("")) {
			saveData(menu);
		} else {
			saveOrUpdate(menu);
		}
		return menu;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<Menu> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				Menu menu = list.get(i);
				session.save(menu);
				if (i == list.size()-1) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			closeSession();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
			flag = false;
		} finally {
			return flag;
		}
	}

	/**
	 * 列表查询
	 * @param curPage
	 * @param pageSize
	 * @param whereStr
	 * @return
	 */
	public Map searchMenu(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM Menu menu";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public Menu delMenu(Menu menu) {
		String delSql = "delete from SYSTEM_ROLEMENUPRIV where LINKMENUID = '"+menu.getId()+"'";
		return (Menu)delData(menu);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delMenuBatch(List<String> list) {
		String delHql = "DELETE Menu ";
		for(String id : list) {
			String delSql = "delete from SYSTEM_ROLEMENUPRIV where LINKMENUID = '"+id+"'";
		}
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM Menu menu where 1=1 " + where;
		return getDataList(hql);
	}
	
	/**
	 * 将菜单id转成name值
	 * @param id
	 * @return
	 */
	public String id2Name(String id) {
		String name = "";
		String sql = "select MENUNAME from SYSTEM_MENU where id='"+id+"'";
		Map map = jdbcTemplate.queryForMap(sql);
		if(map != null) {
			name = (String)map.get("MENUNAME");
		}
		return name;
	}

}
