package com.cloud.attachment.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.attachment.model.Attachment;

@Repository
public class AttachmentDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public Attachment getAttachmentById(String id) {
		return (Attachment)getDataObject(Attachment.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public Attachment saveAttachment(Attachment attachment) {
		if(attachment.getId() == null || attachment.getId().equals("")) {
			saveData(attachment);
		} else {
			saveOrUpdate(attachment);
		}
		return attachment;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<Attachment> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				Attachment attachment = list.get(i);
				session.save(attachment);
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
	public Map searchAttachment(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM Attachment attachment";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public Attachment delAttachment(Attachment attachment) {
		return (Attachment)delData(attachment);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delAttachmentBatch(List<String> list) {
		String delHql = "DELETE Attachment ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM Attachment attachment where 1=1 " + where;
		return getDataList(hql);
	}

}
