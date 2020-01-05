package com.drugs.service;

import java.util.List;

import com.drugs.pojo.Enterprise;

public interface EnterpriseService {

	public List<Enterprise> selectall();

	public void sign(Enterprise enterprise);

	public Enterprise login2(String uname, String pwd);

	public Enterprise login(String uname, String pwd);

	public void upuname(int uid, String uname);

	public void uppwd(int uid, String pwd);

	public void updatedtatus(int id, String status);

	public void updateablance(int id, String ablance);
	
	public List<Enterprise> selectenter();
	
	public void updateblan(int id, int ablance);
}
