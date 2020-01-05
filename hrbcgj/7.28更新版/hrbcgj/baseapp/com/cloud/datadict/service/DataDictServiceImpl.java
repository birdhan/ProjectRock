package com.cloud.datadict.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.datadict.dao.IDataDictDaoMybatis;
import com.cloud.datadict.dao.DataDictDaoHibernate;
import com.cloud.datadict.dao.DataDictDaoJDBC;
import com.cloud.datadict.model.DataDict;

@Service
public class DataDictServiceImpl implements IDataDictService {

	@Resource
	private IDataDictDaoMybatis dataDictDaoMybatis;

	@Resource
	private DataDictDaoHibernate dataDictDaoHibernate;

	@Resource
	private DataDictDaoJDBC dataDictDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public DataDict getDataDictById(String id) {
		return dataDictDaoHibernate.getDataDictById(id);
	}

	/**
	 * 保存
	 */
	public DataDict saveDataDict(DataDict dataDict) {
		return dataDictDaoHibernate.saveDataDict(dataDict);
	}

	/**
	 * 列表查询
	 */
	public Map searchDataDict(Long curPage, Long pageSize,String whereStr) {
		return dataDictDaoHibernate.searchDataDict(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public DataDict delDataDict(DataDict dataDict) {
		return dataDictDaoHibernate.delDataDict(dataDict);
	}

	/**
	 * 批量删除
	 */
	public void delDataDictBatch(List<String> list) {
		dataDictDaoHibernate.delDataDictBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return dataDictDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<DataDict> list) {
		return dataDictDaoHibernate.saveDataBatch(list);
	}

	@Override
	public DataDict checkRepeatData(DataDict dataDict) {
		String where = " and dataDict.dictValue='" +dataDict.getDictValue()+ "' and dataDict.dictType='"+dataDict.getDictType()
					+"' and dataDict.moduleName='"+dataDict.getModuleName()+"' and dataDict.property='"+dataDict.getProperty()+"'";
		List list = dataDictDaoHibernate.getAllDataByWhere(where);
		if(list.size() != 0) {
			return (DataDict)list.get(0);
		} else {
			return new DataDict();
		}
	}
}
