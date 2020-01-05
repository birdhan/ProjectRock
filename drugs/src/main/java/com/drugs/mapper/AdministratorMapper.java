package com.drugs.mapper;

import java.util.List;

import com.drugs.pojo.Administrator;

public interface AdministratorMapper {

	public List<Administrator> selectall();
	
	public Administrator loginin(String name,String password);
	
	public void updateabalance(int id,int abalance);
	
	public void updateabalance1(int id,int abalance);
	
	public Administrator selectbyId(int id);
	
}
