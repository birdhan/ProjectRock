package com.cloud.requestrecord.service;

import java.util.List;
import java.util.Map;

import com.cloud.requestrecord .model.RequestRecord;

public interface IRequestRecordService {

	/**
	 * 通过id得到某一对象
	 */
	public RequestRecord getRequestRecordById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public RequestRecord saveRequestRecord(RequestRecord requestRecord);

	/**
	 * 分页查询
	 */
	public Map searchRequestRecord(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public RequestRecord delRequestRecord(RequestRecord requestRecord);

	/**
	 * 批量删除
	 */
	public void delRequestRecordBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<RequestRecord> list);

}
