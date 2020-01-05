package com.drugs.mapper;

import java.util.List;

import com.drugs.pojo.Consumer;

public interface ConsumerMapper {

	public List<Consumer> selectall();
	
	public void sign(Consumer consumer);
	
	public Consumer login(String uname,String pwd);
	
	public Consumer selectbyid(int id);
	
	public void updatebyuname(int id,String uname);
	
	public void updatebytel(int id,String tel);
	
	public void updatebyaddress(int id,String address);
	
	public void updatebypwd(int id,String pwd);
	
	public void uputype(int id,String utype);
	
	public void upubalance(int id,String balance);
	
	public void deleteconsumer(int id);
	
}
