package com.fmcg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmcg.dao.AdminisMapper;
import com.fmcg.pojo.Adminis;

@Service
public class AdminisServiceImpl implements AdminisService {
@Autowired
private AdminisMapper adminisMapper;
	@Override
	public List<Adminis> adminloginByback(Adminis Adminis) {
		// TODO Auto-generated method stub
		List<com.fmcg.pojo.Adminis> adminloginByback = adminisMapper.adminloginByback(Adminis);
		return adminloginByback;
	}

}
