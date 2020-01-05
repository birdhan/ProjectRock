package com.drugs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugs.mapper.EnterpriseMapper;
import com.drugs.pojo.Enterprise;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

	@Autowired
	private EnterpriseMapper enterprisemapper;
	@Override
	public List<Enterprise> selectall() {
		return enterprisemapper.selectall();
	}
	@Override
	public void sign(Enterprise enterprise) {
		enterprisemapper.sign(enterprise);
		
	}
	@Override
	public Enterprise login(String uname,String pwd) {
		// TODO Auto-generated method stub
		return enterprisemapper.login(uname, pwd);
	}
	@Override
	public void upuname(int uid, String uname) {
		enterprisemapper.upuname(uid, uname);
		
	}
	@Override
	public void uppwd(int uid, String pwd) {
		enterprisemapper.uppwd(uid, pwd);
		
	}
	@Override
	public Enterprise login2(String uname, String pwd) {
		return enterprisemapper.login2(uname, pwd);
	}
	@Override
	public void updatedtatus(int id, String status) {
		enterprisemapper.updatedtatus(id, status);
		
	}
	@Override
	public void updateablance(int id, String ablance) {
		enterprisemapper.updateablance(id, ablance);
		
	}
	@Override
	public List<Enterprise> selectenter() {
		// TODO Auto-generated method stub
		return enterprisemapper.selectenter();
	}
	@Override
	public void updateblan(int id, int ablance) {
		enterprisemapper.updateblan(id, ablance);
		
	}

}
