package com.cloud.attachment.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.attachment.dao.AttachmentDaoHibernate;
import com.cloud.attachment.dao.AttachmentDaoJDBC;
import com.cloud.attachment.dao.IAttachmentDaoMybatis;
import com.cloud.attachment.model.Attachment;
import com.cloud.base.attachment.PropertiesUtil;

@Service
public class AttachmentServiceImpl implements IAttachmentService {

	@Resource
	private IAttachmentDaoMybatis attachmentDaoMybatis;

	@Resource
	private AttachmentDaoHibernate attachmentDaoHibernate;

	@Resource
	private AttachmentDaoJDBC attachmentDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public Attachment getAttachmentById(String id) {
		return attachmentDaoHibernate.getAttachmentById(id);
	}

	/**
	 * 保存
	 */
	public Attachment saveAttachment(Attachment attachment) {
		return attachmentDaoHibernate.saveAttachment(attachment);
	}

	/**
	 * 列表查询
	 */
	public Map searchAttachment(Long curPage, Long pageSize,String whereStr) {
		return attachmentDaoHibernate.searchAttachment(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public Attachment delAttachment(Attachment attachment) {
		return attachmentDaoHibernate.delAttachment(attachment);
	}

	/**
	 * 批量删除
	 */
	public void delAttachmentBatch(List<String> list,String [] filePaths) {
		//读取文件存储路径
		Properties p = new PropertiesUtil().loadProperty();
		String basePath= p.getProperty("basePath");
		
		for(int i=0;i<filePaths.length;i++){
			File file = new File(basePath+filePaths[i]);
			if(file.exists() && file.isFile()){
				file.delete();
			}else{
				System.out.println("要删除的文件不存在.");
			}
			file =null;
		}
		attachmentDaoHibernate.delAttachmentBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return attachmentDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<Attachment> list) {
		return attachmentDaoHibernate.saveDataBatch(list);
	}

}
