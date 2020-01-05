package com.cloud.requestrecord.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.requestrecord.model.RequestRecord;

@Repository
public class RequestRecordDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public RequestRecord getRequestRecordById(String id) {
		return (RequestRecord)getDataObject(RequestRecord.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public RequestRecord saveRequestRecord(RequestRecord requestRecord) {
		if(requestRecord.getId() == null || requestRecord.getId().equals("")) {
			saveData(requestRecord);
		} else {
			saveOrUpdate(requestRecord);
		}
		return requestRecord;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<RequestRecord> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				RequestRecord requestRecord = list.get(i);
				session.save(requestRecord);
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
	public Map searchRequestRecord(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM RequestRecord requestRecord";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public RequestRecord delRequestRecord(RequestRecord requestRecord) {
		return (RequestRecord)delData(requestRecord);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delRequestRecordBatch(List<String> list) {
		String delHql = "DELETE RequestRecord ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM RequestRecord requestRecord where 1=1 " + where;
		return getDataList(hql);
	}

}
