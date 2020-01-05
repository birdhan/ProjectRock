package com.word.mapper;

import java.util.List;

import com.word.pojo.Wordfile;

public interface WordfileMapper {

	public List<Wordfile> findAll();
	
	public void addwordfile(Wordfile wprdfile);
	
	public List<Wordfile> findbyfacoid(String id);
	
	public void deleteword(String id);
	
	public void upnamebyid(String id,String name);
	
	public void upannotationbyid(String id,String annotation);
	
	public void upreadnumberbyid(String id);
	
	public List<Wordfile> findalllimit(int start);
	
	public List<Wordfile> findalltypelimit(String type,int start);
	
	public int findwordnumber();
	
	public int findwordnumberbytype(String type);
	
	public Wordfile findbyid(String id);
	
}
