package com.cloud.systemconfig.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.cache.SysCache;
import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.systemconfig.model.SystemConfig;

@Repository
public class SystemConfigDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public SystemConfig getSystemConfigById(String id) {
		return (SystemConfig)getDataObject(SystemConfig.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public SystemConfig saveSystemConfig(SystemConfig systemConfig) {
		if(systemConfig.getId() == null || systemConfig.getId().equals("")) {
			saveData(systemConfig);
		} else {
			saveOrUpdate(systemConfig);
		}
		SysCache.getInstance().setSystemConfig(systemConfig);
		return systemConfig;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<SystemConfig> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				SystemConfig systemConfig = list.get(i);
				session.save(systemConfig);
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
	public Map searchSystemConfig(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM SystemConfig systemConfig";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public SystemConfig delSystemConfig(SystemConfig systemConfig) {
		return (SystemConfig)delData(systemConfig);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delSystemConfigBatch(List<String> list) {
		String delHql = "DELETE SystemConfig ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM SystemConfig systemConfig where 1=1 " + where;
		return getDataList(hql);
	}

}
