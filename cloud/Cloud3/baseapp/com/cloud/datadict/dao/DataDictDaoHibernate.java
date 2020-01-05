package com.cloud.datadict.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.cache.SysCache;
import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.base.util.DBFM;
import com.cloud.datadict.model.DataDict;

@Repository
public class DataDictDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public DataDict getDataDictById(String id) {
		return (DataDict)getDataObject(DataDict.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public DataDict saveDataDict(DataDict dataDict) {
		String where = " and dataDict.dictValue='" +dataDict.getDictValue().trim()+ "' and dataDict.dictType='"+dataDict.getDictType().trim()+"' and dataDict.moduleName='"+dataDict.getModuleName().trim()+"' and dataDict.dictLabel='" +dataDict.getDictLabel().trim()+ "'";
		List reusltlist = getAllDataByWhere(where);
		if(reusltlist.size() == 0) {
			if(dataDict.getId() == null || dataDict.getId().equals("")) {
				saveData(dataDict);
				SysCache.getInstance().addDataDict(dataDict);
			} else {
				saveOrUpdate(dataDict);
				SysCache.getInstance().updateDataDict(dataDict);
			}	
		}	
		return dataDict;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<DataDict> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				DataDict dataDict = list.get(i);
				session.save(dataDict);
				if (i == list.size()-1) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			closeSession();
			SysCache.getInstance().initDictList();
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
	public Map searchDataDict(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM DataDict dataDict";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public DataDict delDataDict(DataDict dataDict) {
		dataDict = getDataDictById(dataDict.getId());
		SysCache.getInstance().deleteDataDice(dataDict);
		return (DataDict)delData(dataDict);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delDataDictBatch(List<String> list) {
		String delHql = "DELETE DataDict ";
		delDataBatch(delHql,list);
		SysCache.getInstance().initDictList();
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM DataDict dataDict where 1=1 " + where;
		return getDataList(hql);
	}

	public Map getDictInitValue(DataDict dataDict) {
		
		String sql = "select replace("+DBFM.WM_CONCAT()+"(sd.DICTVALUE),',',',') as dictvalue,replace("+DBFM.WM_CONCAT()+"(sd.DICTLABEL),',',',') as dictlabel " +
				"from (select * from system_datadict order by modulename,dicttype,property,dictvalue) sd " +
				"where MODULENAME='"+dataDict.getModuleName()+"' and DICTTYPE='"+dataDict.getDictType()+"' and PROPERTY='"+dataDict.getProperty()+"'";
		
		Map map = jdbcTemplate.queryForMap(sql);
		return map;
	}
}
