package com.cloud.systemuser.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.cache.SysCache;
import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.base.util.MD5Util;
import com.cloud.systemconfig.model.SystemConfig;
import com.cloud.systemuser.model.SystemUser;

@Repository
public class SystemUserDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public SystemUser getSystemUserById(String id) {
		return (SystemUser)getDataObject(SystemUser.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public SystemUser saveSystemUser(SystemUser systemUser) {
		if(systemUser.getId() == null || systemUser.getId().equals("")) {
			saveData(systemUser);
		} else {
			saveOrUpdate(systemUser);
		}
		return systemUser;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<SystemUser> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				SystemUser systemUser = list.get(i);
				if(systemUser.getId() == null || systemUser.getId().equals("")) {
					String pwd = systemUser.getPassword();
					pwd = MD5Util.string2MD5(MD5Util.string2MD5(pwd));			//进行密码加密
					systemUser.setPassword(pwd);
				} else {														//表示修改数据
					SystemUser su = getSystemUserById(systemUser.getId());
					if(!su.getPassword().equals(systemUser.getPassword())) {	//如果两者ID不一致
						String pwd = systemUser.getPassword();
						pwd = MD5Util.string2MD5(MD5Util.string2MD5(pwd));		//进行密码加密
						systemUser.setPassword(pwd);
					}
				}	
				session.save(systemUser);
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
	public Map searchSystemUser(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM SystemUser systemUser";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public SystemUser delSystemUser(SystemUser systemUser) {
		return (SystemUser)delData(systemUser);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delSystemUserBatch(List<String> list) {
		String delHql = "DELETE SystemUser ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM SystemUser systemUser where 1=1 " + where;
		return getDataList(hql);
	}
	
	/**
	 * 通过用户帐号id得到其名字
	 * @param userId
	 * @return
	 */
	public String getUserNameByUserId(String userId) {
		String userName = "";
		String sql = "select USERNAME from SYSTEM_USER where USERID='"+userId+"'";
		Map map = jdbcTemplate.queryForMap(sql);
		if(map != null) {
			userName = (String)map.get("USERNAME");
		}
		return userName;
	}

}
