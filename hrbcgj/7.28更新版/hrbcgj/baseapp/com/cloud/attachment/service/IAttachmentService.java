package com.cloud.attachment.service;

import java.util.List;
import java.util.Map;

import com.cloud.attachment .model.Attachment;

public interface IAttachmentService {

	/**
	 * 通过id得到某一对象
	 */
	public Attachment getAttachmentById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public Attachment saveAttachment(Attachment attachment);

	/**
	 * 分页查询
	 */
	public Map searchAttachment(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public Attachment delAttachment(Attachment attachment);

	/**
	 * 批量删除
	 */
	public void delAttachmentBatch(List<String> list,String [] filePaths);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<Attachment> list);

}
