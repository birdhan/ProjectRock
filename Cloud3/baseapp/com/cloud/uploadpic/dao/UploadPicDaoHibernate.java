package com.cloud.uploadpic.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.uploadpic.model.UploadPic;

@Repository
public class UploadPicDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public UploadPic getUploadPicById(String id) {
		return (UploadPic)getDataObject(UploadPic.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public UploadPic saveUploadPic(UploadPic uploadPic) {
		if(uploadPic.getId() == null || uploadPic.getId().equals("")) {
			saveData(uploadPic);
		} else {
			saveOrUpdate(uploadPic);
		}
		return uploadPic;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<UploadPic> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				UploadPic uploadPic = list.get(i);
				session.save(uploadPic);
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
	public Map searchUploadPic(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM UploadPic uploadPic";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public UploadPic delUploadPic(UploadPic uploadPic) {
		return (UploadPic)delData(uploadPic);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delUploadPicBatch(List<String> list) {
		String delHql = "DELETE UploadPic ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM UploadPic uploadPic where 1=1 " + where;
		return getDataList(hql);
	}

}
