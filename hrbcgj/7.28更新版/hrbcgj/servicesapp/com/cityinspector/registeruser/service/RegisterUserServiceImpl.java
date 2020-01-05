package com.cityinspector.registeruser.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.registeruser.dao.IRegisterUserDaoMybatis;
import com.cityinspector.registeruser.dao.RegisterUserDaoHibernate;
import com.cityinspector.registeruser.dao.RegisterUserDaoJDBC;
import com.cityinspector.registeruser.model.RegisterUser;

@Service
public class RegisterUserServiceImpl implements IRegisterUserService {

	@Resource
	private IRegisterUserDaoMybatis registerUserDaoMybatis;

	@Resource
	private RegisterUserDaoHibernate registerUserDaoHibernate;

	@Resource
	private RegisterUserDaoJDBC registerUserDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public RegisterUser getRegisterUserById(String id) {
		return registerUserDaoHibernate.getRegisterUserById(id);
	}

	/**
	 * 保存
	 */
	public RegisterUser saveRegisterUser(RegisterUser registerUser) {
		return registerUserDaoHibernate.saveRegisterUser(registerUser);
	}

	/**
	 * 列表查询
	 */
	public Map searchRegisterUser(Long curPage, Long pageSize,String whereStr) {
		return registerUserDaoHibernate.searchRegisterUser(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public RegisterUser delRegisterUser(RegisterUser registerUser) {
		return registerUserDaoHibernate.delRegisterUser(registerUser);
	}

	/**
	 * 批量删除
	 */
	public void delRegisterUserBatch(List<String> list) {
		registerUserDaoHibernate.delRegisterUserBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return registerUserDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<RegisterUser> list) {
		return registerUserDaoHibernate.saveDataBatch(list);
	}
	/**
	 * 通过登录获得积分
	 */
	public void updatePoints(String userId) {
		 registerUserDaoHibernate.updatePoints(userId);
		}

}
