package com.word.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.word.mapper.ContrastlibraryMapper;
import com.word.pojo.Contrastlibrary;

@Service
public class ContrastServiceImpl implements ContrastService{

	@Autowired
	private ContrastlibraryMapper contrastdao;
	@Override
	public List<Contrastlibrary> findAll() {
		// TODO Auto-generated method stub
		return contrastdao.findAll();
	}
	@Override
	public void addcontrast(Contrastlibrary contrast) {
		contrastdao.addcontrast(contrast);
		
	}
	@Override
	public List<Contrastlibrary> findbyuid(String uid) {
		// TODO Auto-generated method stub
		return contrastdao.findbyuid(uid);
	}
	@Override
	public void deletecontrast(String uid) {
		contrastdao.deletecontrast(uid);
		
	}

}
