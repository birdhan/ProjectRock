package com.word.service;

import java.util.List;

import com.word.pojo.Personal;

public interface PersonalService {

	public List<Personal> findAll(int number);
	
	public void register(Personal personal);
	
	public Personal login(String name,String pwd);
	
	public Personal loginyanzheng(String name);
	
	public void uppersonalstatus(String id,String status);
	
	public int findpersonalnumber();
	
	public Personal adminlogin(String name,String pwd);
}
