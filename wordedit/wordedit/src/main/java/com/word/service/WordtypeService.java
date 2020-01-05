package com.word.service;

import java.util.List;

import com.word.pojo.Wordtype;

public interface WordtypeService {

	public List<Wordtype> findAll();
	
	public void addtype(Wordtype wordtype);
	
	public void uptype(String id,String name);
	
	public void deletetype(String id);
}
