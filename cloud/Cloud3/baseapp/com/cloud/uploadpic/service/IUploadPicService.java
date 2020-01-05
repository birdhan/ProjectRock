package com.cloud.uploadpic.service;

import java.util.List;
import java.util.Map;

import com.cloud.uploadpic .model.UploadPic;

public interface IUploadPicService {

	/**
	 * 通过id得到某一对象
	 */
	public UploadPic getUploadPicById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public UploadPic saveUploadPic(UploadPic uploadPic);

	/**
	 * 分页查询
	 */
	public Map searchUploadPic(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public UploadPic delUploadPic(UploadPic uploadPic);

	/**
	 * 批量删除
	 */
	public void delUploadPicBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<UploadPic> list);

}
