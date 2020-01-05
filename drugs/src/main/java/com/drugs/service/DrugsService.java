package com.drugs.service;

import java.util.List;

import com.drugs.pojo.Drugs;

public interface DrugsService {

	public List<Drugs> selectall();

	public void insertdrugs(Drugs drugs);

	public List<Drugs> selectByeid(int id);

	public void updatepriceById(int id, String dprice);

	public void updatestockById(int id, String stock);
	
	public void delete(int id);
	
	public List<Drugs> selectlimit();
	
	public Drugs selectdrugsByid(int id);
	
	public List<Drugs> selectajax(int number);
	
	public List<Drugs> selectsearch(String dname);
	
	public void deletedrugs(int id);
	
	public void updatestatus(int eid,String status);
}
