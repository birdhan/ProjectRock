package com.word.service;

import java.util.List;

import com.word.pojo.Wordcolumn;
import com.word.pojo.Wordtype;

public interface WordcolumnService {

	public List<Wordcolumn> findAll();
	
	public void addcolumn(Wordcolumn column);
	
	public void deletecolumn(String id);
	
	public List<Wordcolumn> findbyall(String type,String uid,String cgrade);
	
	public void updatebyid(String id,String name);
	
	public void updatebyfathid(String faid,String faname);
	
	public String findbyid(String id);
	
	public List<Wordtype> findbyuid(String uid);
	
	public List<Wordtype> findbyfaid(String fid);
	
}
