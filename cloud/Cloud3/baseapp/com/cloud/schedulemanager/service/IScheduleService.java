package com.cloud.schedulemanager.service;

import java.util.List;
import java.util.Map;

import com.cloud.schedulemanager .model.Schedule;

public interface IScheduleService {

	/**
	 * 通过id得到某一对象
	 */
	public Schedule getScheduleById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public Schedule saveSchedule(Schedule schedule);

	/**
	 * 分页查询
	 */
	public Map searchSchedule(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public Schedule delSchedule(Schedule schedule);

	/**
	 * 批量删除
	 */
	public void delScheduleBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<Schedule> list);

}
