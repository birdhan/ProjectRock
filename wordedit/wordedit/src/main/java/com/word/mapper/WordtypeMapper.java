package com.word.mapper;

import java.util.List;

import com.word.pojo.Wordtype;

public interface WordtypeMapper {

	public List<Wordtype> findAll();
	
	public void addtype(Wordtype wordtype);
	
	public void uptype(String id,String name);
	
	public void deletetype(String id);
}
