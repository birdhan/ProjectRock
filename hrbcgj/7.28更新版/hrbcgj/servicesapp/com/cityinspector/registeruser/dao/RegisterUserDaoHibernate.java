package com.cityinspector.registeruser.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cityinspector.registeruser.model.RegisterUser;

@Repository
public class RegisterUserDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public RegisterUser getRegisterUserById(String id) {
		return (RegisterUser)getDataObject(RegisterUser.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public RegisterUser saveRegisterUser(RegisterUser registerUser) {
		if(registerUser.getId() == null || registerUser.getId().equals("")) {
			saveData(registerUser);
		} else {
			saveOrUpdate(registerUser);
		}
		return registerUser;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<RegisterUser> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				RegisterUser registerUser = list.get(i);
				session.save(registerUser);
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
	public Map searchRegisterUser(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM RegisterUser registerUser";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public RegisterUser delRegisterUser(RegisterUser registerUser) {
		return (RegisterUser)delData(registerUser);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delRegisterUserBatch(List<String> list) {
		String delHql = "DELETE RegisterUser ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM RegisterUser registerUser where 1=1 " + where;
		return getDataList(hql);
	}
	/**
	 * 通过登录获得积分
	 */
	public void updatePoints(String userId) {
		String sql = "update service_registeruser set points=points+1 where ACCOUNT='" + userId + "'";
		jdbcTemplate.update(sql);
	}

}
