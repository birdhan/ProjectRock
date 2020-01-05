package com.cityinspector.excelfile.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cityinspector.excelfile.model.ExcelFile;

@Repository
public class ExcelFileDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public ExcelFile getExcelFileById(String id) {
		return (ExcelFile)getDataObject(ExcelFile.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public ExcelFile saveExcelFile(ExcelFile excelFile) {
		if(excelFile.getId() == null || excelFile.getId().equals("")) {
			saveData(excelFile);
		} else {
			saveOrUpdate(excelFile);
		}
		return excelFile;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<ExcelFile> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				ExcelFile excelFile = list.get(i);
				session.save(excelFile);
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
	public Map searchExcelFile(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM ExcelFile excelFile";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public ExcelFile delExcelFile(ExcelFile excelFile) {
		return (ExcelFile)delData(excelFile);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delExcelFileBatch(List<String> list) {
		String delHql = "DELETE ExcelFile ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM ExcelFile excelFile where 1=1 " + where;
		return getDataList(hql);
	}

}
