package com.cloud.systemconfig.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloud.systemconfig.model.SystemConfig;

public interface ISystemConfigService {

	/**
	 * 通过id得到某一对象
	 */
	public SystemConfig getSystemConfigById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public SystemConfig saveSystemConfig(SystemConfig systemConfig);

	/**
	 * 分页查询
	 */
	public Map searchSystemConfig(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public SystemConfig delSystemConfig(SystemConfig systemConfig);

	/**
	 * 批量删除
	 */
	public void delSystemConfigBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<SystemConfig> list);

	/**
	 * 恢复数据库文件
	 * @param recoverFile
	 */
	public void recoverDmp(HttpServletRequest request , String recoverFile);
}
