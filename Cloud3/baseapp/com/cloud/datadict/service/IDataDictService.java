package com.cloud.datadict.service;

import java.util.List;
import java.util.Map;

import com.cloud.datadict .model.DataDict;

public interface IDataDictService {

	/**
	 * 通过id得到某一对象
	 */
	public DataDict getDataDictById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public DataDict saveDataDict(DataDict dataDict);

	/**
	 * 分页查询
	 */
	public Map searchDataDict(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public DataDict delDataDict(DataDict dataDict);

	/**
	 * 批量删除
	 */
	public void delDataDictBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<DataDict> list);

	public DataDict checkRepeatData(DataDict dataDict);
}
