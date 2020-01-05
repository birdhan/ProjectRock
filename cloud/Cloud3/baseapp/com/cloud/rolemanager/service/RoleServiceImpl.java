package com.cloud.rolemanager.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cloud.base.util.ApplicationContextHolder;
import com.cloud.base.util.DBFM;
import com.cloud.menumanager.dao.MenuDaoHibernate;
import com.cloud.menupriv.dao.MenuPrivDaoHibernate;
import com.cloud.rolemanager.dao.IRoleDaoMybatis;
import com.cloud.rolemanager.dao.RoleDaoHibernate;
import com.cloud.rolemanager.dao.RoleDaoJDBC;
import com.cloud.rolemanager.model.Role;
import com.cloud.roleuser.dao.RoleUserDaoHibernate;

@Service
public class RoleServiceImpl implements IRoleService {

	@Resource
	private IRoleDaoMybatis roleDaoMybatis;

	@Resource
	private RoleDaoHibernate roleDaoHibernate;

	@Resource
	private RoleDaoJDBC roleDaoJDBC;
	
	@Resource
	private RoleUserDaoHibernate roleUserDaoHibernate;
	
	@Resource
	private MenuPrivDaoHibernate menuPrivDaoHibernate;

	@Resource
	private MenuDaoHibernate menuDaoHibernate;

	/**
	 * 通过id得到对象
	 */
	public Role getRoleById(String id) {
		return roleDaoHibernate.getRoleById(id);
	}

	/**
	 * 保存
	 */
	public Role saveRole(Role role) {
		return roleDaoHibernate.saveRole(role);
	}

	/**
	 * 列表查询
	 */
	public Map searchRole(Long curPage, Long pageSize,String whereStr) {
		return roleDaoHibernate.searchRole(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public Role delRole(Role role) {
		return roleDaoHibernate.delRole(role);
	}

	/**
	 * 批量删除
	 */
	public void delRoleBatch(List<String> list) {
		roleDaoHibernate.delRoleBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return roleDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<Role> list) {
		return roleDaoHibernate.saveDataBatch(list);
	}

	/**
	 * 得到角色菜单关系的初始数据html格式串
	 * @return
	 */
	public String getInitMenuPrivHTML(String roleId) {
		String innerHTML = "<font color='red' style='margin-top:20px;margin-bottom:20px;'>暂时没有分配权限</font>";
		JdbcTemplate jdbc = (JdbcTemplate)ApplicationContextHolder.getInstance().getBean("jdbcTemplate");
		List rmpList = jdbc.queryForList("select linkmenuid,replace("+DBFM.WM_CONCAT()+"(privno),',',',') as privno from SYSTEM_ROLEMENUPRIV where linkroleid='"+roleId+"' group by linkmenuid");

		if(rmpList != null && rmpList.size() != 0) {
			String table_start = "<table width='100%'>";
			String trs = "<tr><td width='30' style='text-align:center;'><strong>序号</strong></td><td width='120'><strong>名称</strong></td><td><strong>权限</strong></td></tr>";
			for(int i=0;i<rmpList.size();i++) {
				Map rpm = (Map)rmpList.get(i);
				String privName = menuPrivDaoHibernate.privNo2Name((String)rpm.get("privno"),(String)rpm.get("linkmenuid"));
				trs += "<tr style='color:#999999;'><td style='text-align:center;'>"+(i+1)+"</td><td>"+menuDaoHibernate.id2Name((String)rpm.get("linkmenuid")) + "</td><td>"+privName+"</td></tr>";
			}
			String table_end = "</table>";
			innerHTML = table_start + trs + table_end;
		}		
		return innerHTML;
	}
}
