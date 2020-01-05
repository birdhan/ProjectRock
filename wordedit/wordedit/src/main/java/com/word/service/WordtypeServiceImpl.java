package com.word.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.word.mapper.WordtypeMapper;
import com.word.pojo.Wordtype;

@Service
public class WordtypeServiceImpl implements WordtypeService{

	@Autowired
	private WordtypeMapper wordtypedao;
	
	@Override
	public List<Wordtype> findAll() {
		// TODO Auto-generated method stub
		return wordtypedao.findAll();
	}

	@Override
	public void addtype(Wordtype wordtype) {
		wordtypedao.addtype(wordtype);
		
	}

	@Override
	public void uptype(String id, String name) {
		wordtypedao.uptype(id, name);
		
	}

	@Override
	public void deletetype(String id) {
		wordtypedao.deletetype(id);
		
	}

}
