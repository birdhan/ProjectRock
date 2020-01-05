package com.word.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.word.mapper.WordcolumnMapper;
import com.word.pojo.Wordcolumn;
import com.word.pojo.Wordtype;

@Service
public class WordcolumnServiceImpl implements WordcolumnService{

	@Autowired
	private WordcolumnMapper wordcolumndao;
	
	@Override
	public List<Wordcolumn> findAll() {
		// TODO Auto-generated method stub
		return wordcolumndao.findAll();
	}

	@Override
	public void addcolumn(Wordcolumn column) {
		wordcolumndao.addcolumn(column);
		
	}

	@Override
	public void deletecolumn(String id) {
		wordcolumndao.deletecolumn(id);
		
	}

	@Override
	public List<Wordcolumn> findbyall(String type, String uid, String cgrade) {
		// TODO Auto-generated method stub
		return wordcolumndao.findbyall(type, uid, cgrade);
	}

	@Override
	public void updatebyid(String id, String name) {
		wordcolumndao.updatebyid(id, name);
		
	}

	@Override
	public void updatebyfathid(String faid, String faname) {
		wordcolumndao.updatebyfathid(faid, faname);
		
	}

	@Override
	public String findbyid(String id) {
		// TODO Auto-generated method stub
		return wordcolumndao.findbyid(id);
	}

	@Override
	public List<Wordtype> findbyuid(String uid) {
		// TODO Auto-generated method stub
		return wordcolumndao.findbyuid(uid);
	}

	@Override
	public List<Wordtype> findbyfaid(String fid) {
		// TODO Auto-generated method stub
		return wordcolumndao.findbyfaid(fid);
	}

	
}
