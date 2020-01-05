package com.cityinspector.excelfile.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.excelfile .model.ExcelFile;

public interface IExcelFileService {

	/**
	 * 通过id得到某一对象
	 */
	public ExcelFile getExcelFileById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public ExcelFile saveExcelFile(ExcelFile excelFile);

	/**
	 * 分页查询
	 */
	public Map searchExcelFile(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public ExcelFile delExcelFile(ExcelFile excelFile);

	/**
	 * 批量删除
	 */
	public void delExcelFileBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<ExcelFile> list);

}
