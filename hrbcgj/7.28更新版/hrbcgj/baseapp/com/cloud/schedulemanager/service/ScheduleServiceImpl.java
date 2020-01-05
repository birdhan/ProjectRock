package com.cloud.schedulemanager.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.schedulemanager.dao.IScheduleDaoMybatis;
import com.cloud.schedulemanager.dao.ScheduleDaoHibernate;
import com.cloud.schedulemanager.dao.ScheduleDaoJDBC;
import com.cloud.schedulemanager.model.Schedule;

@Service
public class ScheduleServiceImpl implements IScheduleService {

	@Resource
	private IScheduleDaoMybatis scheduleDaoMybatis;

	@Resource
	private ScheduleDaoHibernate scheduleDaoHibernate;

	@Resource
	private ScheduleDaoJDBC scheduleDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public Schedule getScheduleById(String id) {
		return scheduleDaoHibernate.getScheduleById(id);
	}

	/**
	 * 保存
	 */
	public Schedule saveSchedule(Schedule schedule) {
		return scheduleDaoHibernate.saveSchedule(schedule);
	}

	/**
	 * 列表查询
	 */
	public Map searchSchedule(Long curPage, Long pageSize,String whereStr) {
		return scheduleDaoHibernate.searchSchedule(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public Schedule delSchedule(Schedule schedule) {
		return scheduleDaoHibernate.delSchedule(schedule);
	}

	/**
	 * 批量删除
	 */
	public void delScheduleBatch(List<String> list) {
		scheduleDaoHibernate.delScheduleBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return scheduleDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<Schedule> list) {
		return scheduleDaoHibernate.saveDataBatch(list);
	}

}
