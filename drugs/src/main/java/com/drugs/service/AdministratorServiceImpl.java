package com.drugs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugs.mapper.AdministratorMapper;
import com.drugs.pojo.Administrator;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	private AdministratorMapper administratormapper;
	@Override
	public List<Administrator> selectall() {
		// TODO Auto-generated method stub
		return administratormapper.selectall();
	}
	@Override
	public Administrator loginin(String name, String password) {
		// TODO Auto-generated method stub
		return administratormapper.loginin(name, password);
	}
	@Override
	public void updateabalance(int id, int abalance) {
		administratormapper.updateabalance(id, abalance);
		
	}
	@Override
	public void updateabalance1(int id, int abalance) {
		administratormapper.updateabalance1(id, abalance);
		
	}
	@Override
	public Administrator selectbyId(int id) {
		// TODO Auto-generated method stub
		return administratormapper.selectbyId(id);
	}

}
