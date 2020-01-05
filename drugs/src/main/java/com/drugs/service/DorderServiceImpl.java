package com.drugs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugs.mapper.DorderMapper;
import com.drugs.pojo.Dorder;

@Service
public class DorderServiceImpl implements DorderService {

	@Autowired
	private DorderMapper dordermapper;
	@Override
	public List<Dorder> selectall() {
		return dordermapper.selectall();
	}
	@Override
	public void insertintoo(Dorder order) {
		dordermapper.insertintoo(order);
		
	}
	@Override
	public List<Dorder> selectbyuid(int id) {
		// TODO Auto-generated method stub
		return dordermapper.selectbyuid(id);
	}
	@Override
	public List<Dorder> selectbyeid(int id) {
		// TODO Auto-generated method stub
		return dordermapper.selectbyeid(id);
	}
	@Override
	public void updatestatus(int id, String status) {
		dordermapper.updatestatus(id, status);
		
	}
	@Override
	public void updateyunnamber(int id, String number) {
		dordermapper.updateyunnamber(id, number);
		
	}
	@Override
	public List<Dorder> selectodate(int uid, String odate) {
		// TODO Auto-generated method stub
		return dordermapper.selectodate(uid, odate);
	}
	@Override
	public List<Dorder> selectloan() {
		// TODO Auto-generated method stub
		return dordermapper.selectloan();
	}
	@Override
	public void updateloan(int id, String loan) {
		dordermapper.updateloan(id, loan);
		
	}

}
