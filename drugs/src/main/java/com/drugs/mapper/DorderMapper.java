package com.drugs.mapper;

import java.util.List;

import com.drugs.pojo.Dorder;

public interface DorderMapper {

	public List<Dorder> selectall();
	
	public void insertintoo(Dorder order);
	
	public List<Dorder> selectbyuid(int id);
	
	public List<Dorder> selectbyeid(int id); 
	
	public void updatestatus(int id,String status);
	
	public void updateyunnamber(int id,String number);
	
	public List<Dorder> selectodate(int uid,String odate);
	
	public List<Dorder> selectloan();
	
	public void updateloan(int id,String loan);
	
	
	
}
