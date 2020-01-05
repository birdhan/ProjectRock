package com.cloud.menupriv.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.menupriv.model.MenuPriv;

@Repository
public class MenuPrivDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public MenuPriv getMenuPrivById(String id) {
		return (MenuPriv)getDataObject(MenuPriv.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public MenuPriv saveMenuPriv(MenuPriv menuPriv) {
		if(menuPriv.getId() == null || menuPriv.getId().equals("")) {
			saveData(menuPriv);
		} else {
			saveOrUpdate(menuPriv);
		}
		return menuPriv;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<MenuPriv> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				MenuPriv menuPriv = list.get(i);
				session.save(menuPriv);
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
	public Map searchMenuPriv(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM MenuPriv menuPriv";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public MenuPriv delMenuPriv(MenuPriv menuPriv) {
		return (MenuPriv)delData(menuPriv);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delMenuPrivBatch(List<String> list) {
		String delHql = "DELETE MenuPriv ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM MenuPriv menuPriv where 1=1 " + where;
		return getDataList(hql);
	}
	
	/**
	 * 将权限编号转成名称
	 * @param privNo
	 * @return
	 */
	public String privNo2Name(String privNo , String menuId) {
		String name = "";
		for(String pn : privNo.split(",")) {
			String sql = "select PRIVNAME from SYSTEM_MENU_PRIV where PRIVNO='"+pn+"' and LINKMENUID='"+menuId+"'";
			Map map = jdbcTemplate.queryForMap(sql);
			if(map != null) {
				name += (String)map.get("PRIVNAME") + ",";
			}
		}
		if(!name.equals("")) {
			name = name.substring(0, name.length()-1);
		}
		
		return name;
	}

}
