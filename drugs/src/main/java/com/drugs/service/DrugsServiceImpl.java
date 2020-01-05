package com.drugs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drugs.mapper.DrugsMapper;
import com.drugs.pojo.Drugs;

@Service
public class DrugsServiceImpl implements DrugsService {

	
	@Autowired
	private DrugsMapper drugsmapper;
	@Override
	public List<Drugs> selectall() {
		
		return drugsmapper.selectall();
	}
	@Override
	public void insertdrugs(Drugs drugs) {
		drugsmapper.insertdrugs(drugs);
		
	}
	@Override
	public List<Drugs> selectByeid(int id) {
		return drugsmapper.selectByeid(id);
	}
	@Override
	public void updatepriceById(int id, String dprice) {
		drugsmapper.updatepriceById(id, dprice);
		
	}
	@Override
	public void updatestockById(int id, String stock) {
		drugsmapper.updatestockById(id, stock);
		
	}
	@Override
	public void delete(int id) {
		drugsmapper.delete(id);
		
	}
	@Override
	public List<Drugs> selectlimit() {
		// TODO Auto-generated method stub
		return drugsmapper.selectlimit();
	}
	@Override
	public Drugs selectdrugsByid(int id) {
		// TODO Auto-generated method stub
		return drugsmapper.selectdrugsByid(id);
	}
	@Override
	public List<Drugs> selectajax(int number) {
		// TODO Auto-generated method stub
		return drugsmapper.selectajax(number);
	}
	@Override
	public List<Drugs> selectsearch(String dname) {
		// TODO Auto-generated method stub
		return drugsmapper.selectsearch(dname);
	}
	@Override
	public void deletedrugs(int id) {
		drugsmapper.deletedrugs(id);
		
	}
	@Override
	public void updatestatus(int eid, String status) {
		drugsmapper.updatestatus(eid, status);
		
	}

}
