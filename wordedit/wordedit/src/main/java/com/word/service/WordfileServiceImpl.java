package com.word.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.word.mapper.WordfileMapper;
import com.word.pojo.Wordfile;

@Service
public class WordfileServiceImpl implements WordfileService{

	@Autowired
	private WordfileMapper wordfiledao;
	
	@Override
	public List<Wordfile> findAll() {
		// TODO Auto-generated method stub
		return wordfiledao.findAll();
	}

	@Override
	public void addwordfile(Wordfile wordfile) {
		wordfiledao.addwordfile(wordfile);
		
	}

	@Override
	public List<Wordfile> findbyfacoid(String id) {
		// TODO Auto-generated method stub
		return wordfiledao.findbyfacoid(id);
	}

	@Override
	public void deleteword(String id) {
		wordfiledao.deleteword(id);
		
	}

	@Override
	public void upnamebyid(String id, String name) {
		wordfiledao.upnamebyid(id, name);
		
	}

	@Override
	public void upannotationbyid(String id, String annotation) {
		wordfiledao.upannotationbyid(id, annotation);
		
	}

	@Override
	public void upreadnumberbyid(String id) {
		wordfiledao.upreadnumberbyid(id);
		
	}

	@Override
	public List<Wordfile> findalllimit(int start) {
		// TODO Auto-generated method stub
		return wordfiledao.findalllimit(start);
	}

	@Override
	public List<Wordfile> findalltypelimit(String type, int start) {
		// TODO Auto-generated method stub
		return wordfiledao.findalltypelimit(type, start);
	}

	@Override
	public Wordfile findbyid(String id) {
		// TODO Auto-generated method stub
		return wordfiledao.findbyid(id);
	}

	@Override
	public int findwordnumber() {
		// TODO Auto-generated method stub
		return wordfiledao.findwordnumber();
	}

	@Override
	public int findwordnumberbytype(String type) {
		// TODO Auto-generated method stub
		return wordfiledao.findwordnumberbytype(type);
	}

}
