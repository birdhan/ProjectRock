package com.word.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.word.mapper.PersonalMapper;
import com.word.pojo.Personal;

@Service
public class PersonalServiceImpl implements PersonalService {

	@Autowired
	private PersonalMapper personaldao;
	@Override
	public List<Personal> findAll(int number) {
		// TODO Auto-generated method stub
		return personaldao.findAll(number);
	}
	@Override
	public void register(Personal personal) {
		personaldao.register(personal);
		
	}
	@Override
	public Personal login(String name, String pwd) {
		// TODO Auto-generated method stub
		return personaldao.login(name, pwd);
	}
	@Override
	public Personal loginyanzheng(String name) {
		// TODO Auto-generated method stub
		return personaldao.loginyanzheng(name);
	}
	@Override
	public void uppersonalstatus(String id, String status) {
		personaldao.uppersonalstatus(id, status);
		
	}
	@Override
	public int findpersonalnumber() {
		// TODO Auto-generated method stub
		return personaldao.findpersonalnumber();
	}
	@Override
	public Personal adminlogin(String name, String pwd) {
		// TODO Auto-generated method stub
		return personaldao.adminlogin(name, pwd);
	}

}
