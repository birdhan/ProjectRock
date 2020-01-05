package com.cityinspector.excelfile.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.excelfile.dao.IExcelFileDaoMybatis;
import com.cityinspector.excelfile.dao.ExcelFileDaoHibernate;
import com.cityinspector.excelfile.dao.ExcelFileDaoJDBC;
import com.cityinspector.excelfile.model.ExcelFile;

@Service
public class ExcelFileServiceImpl implements IExcelFileService {

	@Resource
	private IExcelFileDaoMybatis excelFileDaoMybatis;

	@Resource
	private ExcelFileDaoHibernate excelFileDaoHibernate;

	@Resource
	private ExcelFileDaoJDBC excelFileDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public ExcelFile getExcelFileById(String id) {
		return excelFileDaoHibernate.getExcelFileById(id);
	}

	/**
	 * 保存
	 */
	public ExcelFile saveExcelFile(ExcelFile excelFile) {
		return excelFileDaoHibernate.saveExcelFile(excelFile);
	}

	/**
	 * 列表查询
	 */
	public Map searchExcelFile(Long curPage, Long pageSize,String whereStr) {
		return excelFileDaoHibernate.searchExcelFile(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public ExcelFile delExcelFile(ExcelFile excelFile) {
		return excelFileDaoHibernate.delExcelFile(excelFile);
	}

	/**
	 * 批量删除
	 */
	public void delExcelFileBatch(List<String> list) {
		excelFileDaoHibernate.delExcelFileBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return excelFileDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<ExcelFile> list) {
		return excelFileDaoHibernate.saveDataBatch(list);
	}

}
