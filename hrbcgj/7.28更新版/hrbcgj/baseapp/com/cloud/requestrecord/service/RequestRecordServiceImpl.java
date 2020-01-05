package com.cloud.requestrecord.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.requestrecord.dao.IRequestRecordDaoMybatis;
import com.cloud.requestrecord.dao.RequestRecordDaoHibernate;
import com.cloud.requestrecord.dao.RequestRecordDaoJDBC;
import com.cloud.requestrecord.model.RequestRecord;

@Service
public class RequestRecordServiceImpl implements IRequestRecordService {

	@Resource
	private IRequestRecordDaoMybatis requestRecordDaoMybatis;

	@Resource
	private RequestRecordDaoHibernate requestRecordDaoHibernate;

	@Resource
	private RequestRecordDaoJDBC requestRecordDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public RequestRecord getRequestRecordById(String id) {
		return requestRecordDaoHibernate.getRequestRecordById(id);
	}

	/**
	 * 保存
	 */
	public RequestRecord saveRequestRecord(RequestRecord requestRecord) {
		return requestRecordDaoHibernate.saveRequestRecord(requestRecord);
	}

	/**
	 * 列表查询
	 */
	public Map searchRequestRecord(Long curPage, Long pageSize,String whereStr) {
		return requestRecordDaoHibernate.searchRequestRecord(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public RequestRecord delRequestRecord(RequestRecord requestRecord) {
		return requestRecordDaoHibernate.delRequestRecord(requestRecord);
	}

	/**
	 * 批量删除
	 */
	public void delRequestRecordBatch(List<String> list) {
		requestRecordDaoHibernate.delRequestRecordBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return requestRecordDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<RequestRecord> list) {
		return requestRecordDaoHibernate.saveDataBatch(list);
	}

}
