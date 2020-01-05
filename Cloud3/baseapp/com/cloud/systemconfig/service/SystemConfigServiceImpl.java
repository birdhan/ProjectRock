package com.cloud.systemconfig.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.cloud.bakedb.recover.CreateRecoverBatFile;
import com.cloud.bakedb.recover.CreateSQL;
import com.cloud.base.util.CallBatFile;
import com.cloud.base.util.FileUtil;
import com.cloud.systemconfig.dao.ISystemConfigDaoMybatis;
import com.cloud.systemconfig.dao.SystemConfigDaoHibernate;
import com.cloud.systemconfig.dao.SystemConfigDaoJDBC;
import com.cloud.systemconfig.model.SystemConfig;

@Service
public class SystemConfigServiceImpl implements ISystemConfigService {

	@Resource
	private ISystemConfigDaoMybatis systemConfigDaoMybatis;

	@Resource
	private SystemConfigDaoHibernate systemConfigDaoHibernate;

	@Resource
	private SystemConfigDaoJDBC systemConfigDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public SystemConfig getSystemConfigById(String id) {
		return systemConfigDaoHibernate.getSystemConfigById(id);
	}

	/**
	 * 保存
	 */
	public SystemConfig saveSystemConfig(SystemConfig systemConfig) {
		return systemConfigDaoHibernate.saveSystemConfig(systemConfig);
	}

	/**
	 * 列表查询
	 */
	public Map searchSystemConfig(Long curPage, Long pageSize,String whereStr) {
		return systemConfigDaoHibernate.searchSystemConfig(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public SystemConfig delSystemConfig(SystemConfig systemConfig) {
		return systemConfigDaoHibernate.delSystemConfig(systemConfig);
	}

	/**
	 * 批量删除
	 */
	public void delSystemConfigBatch(List<String> list) {
		systemConfigDaoHibernate.delSystemConfigBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return systemConfigDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<SystemConfig> list) {
		return systemConfigDaoHibernate.saveDataBatch(list);
	}
	
	/**
	 * 恢复数据库文件
	 * @param recoverFile例：XXXX.dmp
	 */
	public void recoverDmp(HttpServletRequest request , String recoverFile) {
		String[] sqlFileName = CreateSQL.create(request);
		String batFilePath = CreateRecoverBatFile.create(sqlFileName[0], recoverFile,sqlFileName[1]);
		CallBatFile.call(batFilePath);		
		FileUtil.delFolder(request.getSession().getServletContext().getRealPath("/").replace("/WEB-INF/classes/", "") + "/sql/recover/");
	}

}
