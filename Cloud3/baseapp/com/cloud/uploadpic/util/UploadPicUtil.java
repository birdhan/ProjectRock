package com.cloud.uploadpic.util;

import java.io.File;

import com.cloud.base.util.SpringContextHolder;
import com.cloud.base.util.StringUtil;
import com.cloud.uploadpic.dao.UploadPicDaoHibernate;
import com.cloud.uploadpic.model.UploadPic;

public class UploadPicUtil {

	/**
	 * 通过数据唯一标识删除文件及数据
	 * @param id
	 */
	public static void delPicFile(String id) {
		if(!StringUtil.null2String(id).equals("")) {
			UploadPicDaoHibernate updh = (UploadPicDaoHibernate)SpringContextHolder.getApplicationContext().getBean("uploadPicDaoHibernate");
			UploadPic uploadPic = updh.getUploadPicById(id);
			if(uploadPic != null) {
				File delFile = new File(uploadPic.getSavePath() + "/" + uploadPic.getSerName());
				if(delFile.exists()) {
					delFile.delete();
				}
				updh.delUploadPic(uploadPic);
			}
		}
	}
}
