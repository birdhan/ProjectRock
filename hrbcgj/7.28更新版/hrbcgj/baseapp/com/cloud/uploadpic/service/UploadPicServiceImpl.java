package com.cloud.uploadpic.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.uploadpic.dao.IUploadPicDaoMybatis;
import com.cloud.uploadpic.dao.UploadPicDaoHibernate;
import com.cloud.uploadpic.dao.UploadPicDaoJDBC;
import com.cloud.uploadpic.model.UploadPic;

@Service
public class UploadPicServiceImpl implements IUploadPicService {

	@Resource
	private IUploadPicDaoMybatis uploadPicDaoMybatis;

	@Resource
	private UploadPicDaoHibernate uploadPicDaoHibernate;

	@Resource
	private UploadPicDaoJDBC uploadPicDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public UploadPic getUploadPicById(String id) {
		return uploadPicDaoHibernate.getUploadPicById(id);
	}

	/**
	 * 保存
	 */
	public UploadPic saveUploadPic(UploadPic uploadPic) {
		return uploadPicDaoHibernate.saveUploadPic(uploadPic);
	}

	/**
	 * 列表查询
	 */
	public Map searchUploadPic(Long curPage, Long pageSize,String whereStr) {
		return uploadPicDaoHibernate.searchUploadPic(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public UploadPic delUploadPic(UploadPic uploadPic) {
		return uploadPicDaoHibernate.delUploadPic(uploadPic);
	}

	/**
	 * 批量删除
	 */
	public void delUploadPicBatch(List<String> list) {
		uploadPicDaoHibernate.delUploadPicBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return uploadPicDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<UploadPic> list) {
		return uploadPicDaoHibernate.saveDataBatch(list);
	}

}
