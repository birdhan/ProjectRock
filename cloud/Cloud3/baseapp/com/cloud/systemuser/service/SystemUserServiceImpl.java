package com.cloud.systemuser.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.MD5Util;
import com.cloud.department.dao.DepartmentDaoHibernate;
import com.cloud.department.model.Department;
import com.cloud.systemuser.dao.ISystemUserDaoMybatis;
import com.cloud.systemuser.dao.SystemUserDaoHibernate;
import com.cloud.systemuser.dao.SystemUserDaoJDBC;
import com.cloud.systemuser.model.SystemUser;

@Service
public class SystemUserServiceImpl implements ISystemUserService {

	@Resource
	private ISystemUserDaoMybatis systemUserDaoMybatis;

	@Resource
	private SystemUserDaoHibernate systemUserDaoHibernate;

	@Resource
	private SystemUserDaoJDBC systemUserDaoJDBC;
	
	@Resource
	private DepartmentDaoHibernate departmentDaoHibernate;

	/**
	 * 通过id得到对象
	 */
	public SystemUser getSystemUserById(String id) {
		return systemUserDaoHibernate.getSystemUserById(id);
	}

	/**
	 * 保存
	 */
	public SystemUser saveSystemUser(SystemUser systemUser) {
		if(systemUser.getId() == null || systemUser.getId().equals("")) {
			String pwd = systemUser.getPassword();
			pwd = MD5Util.string2MD5(MD5Util.string2MD5(pwd));			//进行密码加密
			systemUser.setPassword(pwd);
		} else {														//表示修改数据
			SystemUser su = getSystemUserById(systemUser.getId());
			if(!su.getPassword().equals(systemUser.getPassword())) {	//如果两者ID不一致
				String pwd = systemUser.getPassword();
				pwd = MD5Util.string2MD5(MD5Util.string2MD5(pwd));		//进行密码加密
				systemUser.setPassword(pwd);
			}
		}		
		return systemUserDaoHibernate.saveSystemUser(systemUser);
	}

	/**
	 * 列表查询
	 */
	public Map searchSystemUser(Long curPage, Long pageSize,String whereStr) {
		return systemUserDaoHibernate.searchSystemUser(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public SystemUser delSystemUser(SystemUser systemUser) {
		return systemUserDaoHibernate.delSystemUser(systemUser);
	}

	/**
	 * 批量删除
	 */
	public void delSystemUserBatch(List<String> list) {
		systemUserDaoHibernate.delSystemUserBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return systemUserDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<SystemUser> list) {
		return systemUserDaoHibernate.saveDataBatch(list);
	}

	/**
	 * 将userId转成userName
	 * @param userId
	 * @return
	 */
	public String userId2UserName(String userId) {
		String userName = "";
		for(String uid : userId.split(",")) {
			userName += systemUserDaoHibernate.getUserNameByUserId(uid)+",";
		}
		if(!userName.equals("")) {
			userName = userName.substring(0, userName.length()-1);
		}
		return userName;
	}
	
	/**
	 * 得到所有人员部门树串
	 * @return
	 */
	public String getAllUserTree(String initUserIds) {
		JSONArray array = new JSONArray();
		JSONArray resultArray = new JSONArray();
		String rootDeptNo = SysCache.getInstance().getSystemConfig().getRootDepartNo();		//得到根部门编号
		String rootDeptName = SysCache.getInstance().getSystemConfig().getRootDepartName();
		
		List<Department> rootList = departmentDaoHibernate.getAllDataByWhere(" and department.parentNo='"+rootDeptNo+"' order by department.departSort");	//先得到根部门下的所有部门
		if(rootList != null) {
			if(rootList.size() != 0) {
				array = getChildrenDept(rootList,array,initUserIds);
			}
		}
		JSONObject object = new JSONObject();
		object.put("text", rootDeptName);
		object.put("deptNo", rootDeptNo);		
		
		List<SystemUser> userList =  systemUserDaoHibernate.getAllDataByWhere(" and systemUser.departNo='"+rootDeptNo+"' order by systemUser.userName");
		for(SystemUser su : userList) {
			JSONObject userObject = new JSONObject();
			userObject.put("text", su.getUserName());										//人员名称
			userObject.put("deptNo", su.getUserId());										//人员编号
			userObject.put("icon", SysCache.getInstance().getContextPath()+"/images/user.gif");
			for(String uid : initUserIds.split(",")) {									//初始化人员，为其自动勾选
				if(uid.equals(su.getUserId())) {
					userObject.put("ischecked", true);
					break;
				}
			}	
			array.add(userObject);
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
	public JSONArray getChildrenDept(List<Department> childrenList , JSONArray array,String initUserIds) {
		JSONArray returnArray = new JSONArray();											//最终返回的jsonarray对象
		for(Department dept : childrenList) {												//遍历子部门集合
			JSONObject object = new JSONObject();											//将子部门集合封装成jsonobject对象
			object.put("text", dept.getDepartName());										//部门名称
			object.put("deptNo", dept.getDepartNo());										//部门编号
			
			JSONArray userArray = new JSONArray();
			List<SystemUser> userList =  systemUserDaoHibernate.getAllDataByWhere(" and systemUser.departNo='"+dept.getDepartNo()+"' order by systemUser.userName");
			for(SystemUser su : userList) {
				JSONObject userObject = new JSONObject();
				userObject.put("text", su.getUserName());										//人员名称
				userObject.put("deptNo", su.getUserId());										//人员编号
				userObject.put("icon", SysCache.getInstance().getContextPath()+"/images/user.gif");
				for(String uid : initUserIds.split(",")) {									//初始化人员，为其自动勾选
					if(uid.equals(su.getUserId())) {
						userObject.put("ischecked", true);
						break;
					}
				}	
				userArray.add(userObject);
			}
			
			if(userArray.size() == 0) {
				object.put("children", "{}");
			} else {
				object.put("children", userArray);
			}
			
			List<Department> subList = departmentDaoHibernate.getAllDataByWhere(" and department.parentNo='"+dept.getDepartNo()+"' order by department.departSort");	//获取子部门集合
			if(subList != null) {
				if(subList.size() != 0) {					
					array = getChildrenDept(subList,array,initUserIds);
					object.put("children", array);
				}
			} 
			returnArray.add(object);	
		}
		return returnArray;
	}
}
