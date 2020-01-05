package com.cloud.department.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.StringUtil;
import com.cloud.department.dao.IDepartmentDaoMybatis;
import com.cloud.department.dao.DepartmentDaoHibernate;
import com.cloud.department.dao.DepartmentDaoJDBC;
import com.cloud.department.model.Department;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Resource
	private IDepartmentDaoMybatis departmentDaoMybatis;

	@Resource
	private DepartmentDaoHibernate departmentDaoHibernate;

	@Resource
	private DepartmentDaoJDBC departmentDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public Department getDepartmentById(String id) {
		return departmentDaoHibernate.getDepartmentById(id);
	}

	/**
	 * 保存
	 */
	public Department saveDepartment(Department department) {
		return departmentDaoHibernate.saveDepartment(department);
	}

	/**
	 * 列表查询
	 */
	public Map searchDepartment(Long curPage, Long pageSize,String whereStr) {
		return departmentDaoHibernate.searchDepartment(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public Department delDepartment(Department department) {
		return departmentDaoHibernate.delDepartment(department);
	}

	/**
	 * 批量删除
	 */
	public void delDepartmentBatch(List<String> list) {
		departmentDaoHibernate.delDepartmentBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return departmentDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<Department> list) {
		return departmentDaoHibernate.saveDataBatch(list);
	}

	/**
	 * 得到所有部门树串
	 * @return
	 */
	public String getAllDeptTree(String initDeptNos) {
		JSONArray array = new JSONArray();
		JSONArray resultArray = new JSONArray();
		String rootDeptNo = SysCache.getInstance().getSystemConfig().getRootDepartNo();		//得到根部门编号
		String rootDeptName = SysCache.getInstance().getSystemConfig().getRootDepartName();
		
		List<Department> rootList = getAllDataByWhere(" and department.parentNo='"+rootDeptNo+"' order by department.departSort");	//先得到根部门下的所有部门
		if(rootList != null) {
			if(rootList.size() != 0) {
				array = getChildrenDept(rootList,array,initDeptNos);
			}
		}
		JSONObject object = new JSONObject();
		object.put("text", rootDeptName);
		object.put("deptNo", rootDeptNo);		
		for(String initDeptNo : initDeptNos.split(",")) {									//初始化根部门，为其自动勾选
			if(initDeptNo.equals(rootDeptNo)) {
				object.put("ischecked", true);
				break;
			}
		}		
		object.put("children", array.toString());
		
		resultArray.add(object);
		return resultArray.toString();
	}
	
	/**
	 * 递归查子部门结点
	 * @param childrenList
	 * @return
	 */
	public JSONArray getChildrenDept(List<Department> childrenList , JSONArray array,String initDeptNos) {
		JSONArray returnArray = new JSONArray();											//最终返回的jsonarray对象
		for(Department dept : childrenList) {												//遍历子部门集合
			JSONObject object = new JSONObject();											//将子部门集合封装成jsonobject对象
			object.put("text", dept.getDepartName());										//部门名称
			object.put("deptNo", dept.getDepartNo());										//部门编号			
			
			for(String initDeptNo : initDeptNos.split(",")) {								//初始化部门，为其自动勾选
				if(initDeptNo.equals(dept.getDepartNo())) {									//如果部门相等那么自动勾选
					object.put("ischecked", true);
					break;
				}
			}
			
			List<Department> subList = getAllDataByWhere(" and department.parentNo='"+dept.getDepartNo()+"' order by department.departSort");	//获取子部门集合
			if(subList != null) {
				if(subList.size() != 0) {
					array = getChildrenDept(subList,array,initDeptNos);
					object.put("children", array);
				} 
			}
			returnArray.add(object);	
		}
		return returnArray;
	}
	
	/**
	 * 将部门编号转成部门名称
	 * @param deptNo
	 * @return
	 */
	public String deptNo2deptName(String deptNo) {
		String deptName = "";
		for(String no : deptNo.split(",")) {
			deptName += departmentDaoHibernate.deptNo2deptName(no)+",";
		}
		if(!deptName.equals("")) {
			deptName = deptName.substring(0, deptName.length()-1);
		}
		return deptName;
	}
	
	/**
	 * 部门树形视图
	 * @return
	 */
	public String treeView() {
		List<Department> rootList = departmentDaoHibernate.getAllDataByWhere(" and department.parentNo='root' order by department.departSort");		//得到根部门下的所有子部门
		String treeData = getChildrenDeptWithHTML(rootList,new StringBuffer(""),"&nbsp;&nbsp;&nbsp;&nbsp;").toString();
		return treeData;
	}
	
	/**
	 * 递归获取树串
	 * @param childrenList
	 * @param curHTML
	 * @return
	 */
	public StringBuffer getChildrenDeptWithHTML(List<Department> childrenList , StringBuffer curHTML ,String spaceNBSP) {
		StringBuffer resultHTML = new StringBuffer("");
		String ctx = SysCache.getInstance().getContextPath();
		
		if(childrenList != null && childrenList.size() != 0) {
			for(Department dept : childrenList) {
				curHTML.append("<tr>");
				curHTML.append("	<td>");
				curHTML.append("		<input id=\"ids\" name\"ids\" type=\"checkbox\" value=\""+dept.getId()+"\"/>");
				curHTML.append("	</td>");
				curHTML.append("	<td>");
				curHTML.append("		"+spaceNBSP+"<img src=\""+ctx+"/images/dept.png\" style=\"vertical-align: middle; margin-right:2px;\"/>"+dept.getDepartName());
				curHTML.append("	</td>");
				curHTML.append("	<td style=\"text-align: center;width:auto;\">");
				curHTML.append("		"+dept.getDepartNo());
				curHTML.append("	</td>");
				curHTML.append("	<td style=\"text-align: center;width:auto;\">");
				curHTML.append("		"+dept.getParentNo());
				curHTML.append("	</td>");
				curHTML.append("	<td style=\"text-align: center;\">");
				curHTML.append("		<img src=\""+ctx+"/images/cog_edit.png\" title=\"编辑\" style=\"cursor: pointer;\"");
				curHTML.append("			onclick=\"goToUrl('${ctx}/department/editDepartment.do?id="+dept.getId()+"&viewType=tree')\"/>");
				curHTML.append("	</td>");
				curHTML.append("	<td style=\"text-align: center;\">");
				curHTML.append("		<img src=\""+ctx+"/images/delete.png\" title=\"删除\" style=\"cursor: pointer;\"");
				curHTML.append("			onclick=\"delDepartmentByIdAjax('"+dept.getId()+"')\"/>");
				curHTML.append("	</td>");
				curHTML.append("	<td style=\"text-align: center;\">");
				curHTML.append("		<img src=\""+ctx+"/images/detail.png\" title=\"详细\" style=\"cursor: pointer;\"");
				curHTML.append("			onclick=\"goToUrl('${ctx}/department/detailDepartment.do?id="+dept.getId()+"&viewType=tree')\"/>");
				curHTML.append("	</td>");
				curHTML.append("	<td style=\"text-align: center;\">");
				curHTML.append("		<img src=\""+ctx+"/images/dept.png\" title=\"添加下属部门\" style=\"cursor: pointer;\"");
				curHTML.append("			onclick=\"goToUrl('${ctx}/department/addDepartment.do?parentDeptNo="+dept.getDepartNo()+"&viewType=tree')\"/>");
				curHTML.append("	</td>");
				curHTML.append("</tr>");
				
				List<Department> list = departmentDaoHibernate.getAllDataByWhere(" and department.parentNo='"+dept.getDepartNo()+"' order by department.departSort");
				if(list != null && list.size() != 0) {
					curHTML = getChildrenDeptWithHTML(list, curHTML,spaceNBSP+"&nbsp;&nbsp;&nbsp;&nbsp;");
				}
			}
		}
		
		resultHTML = curHTML;
		return resultHTML;
	}
}

