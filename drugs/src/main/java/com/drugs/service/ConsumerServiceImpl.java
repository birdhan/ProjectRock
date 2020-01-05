package com.drugs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugs.mapper.ConsumerMapper;
import com.drugs.pojo.Consumer;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	
	@Autowired
	private ConsumerMapper consumermapper;
	@Override
	public List<Consumer> selectall() {
		
		return consumermapper.selectall();
	}
	@Override
	public void sign(Consumer consumer) {
		consumermapper.sign(consumer);
		
	}
	@Override
	public Consumer login(String uname, String pwd) {
		// TODO Auto-generated method stub
		return consumermapper.login(uname, pwd);
	}
	@Override
	public Consumer selectbyid(int id) {
		// TODO Auto-generated method stub
		return consumermapper.selectbyid(id);
	}
	@Override
	public void updatebyuname(int id, String uname) {
		consumermapper.updatebyuname(id, uname);
		
	}
	@Override
	public void updatebytel(int id, String tel) {
		consumermapper.updatebytel(id, tel);
		
	}
	@Override
	public void updatebyaddress(int id, String address) {
		consumermapper.updatebyaddress(id, address);
		
	}
	@Override
	public void updatebypwd(int id, String pwd) {
		consumermapper.updatebypwd(id, pwd);
		
	}
	@Override
	public void uputype(int id, String utype) {
		consumermapper.uputype(id, utype);
		
		
	}
	@Override
	public void upubalance(int id, String balance) {
		consumermapper.upubalance(id, balance);
		
	}
	@Override
	public void deleteconsumer(int id) {
		consumermapper.deleteconsumer(id);
		
	}

}
