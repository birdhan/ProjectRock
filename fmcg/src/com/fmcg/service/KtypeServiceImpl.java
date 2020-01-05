package com.fmcg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmcg.dao.KtypeMapper;
import com.fmcg.pojo.Ktype;

@Service
public class KtypeServiceImpl implements KtypeService {
@Autowired
private KtypeMapper ktypeMapper;
	@Override
	public void addtype(Ktype ktype) {
		// TODO Auto-generated method stub
		ktypeMapper.addtype(ktype);
	}
	@Override
	public List<Ktype> selectktype() {
		// TODO Auto-generated method stub
		List<Ktype> selectktype = ktypeMapper.selectktype();
		return selectktype;
	}
	@Override
	public void deletektypeById(String id) {
		// TODO Auto-generated method stub
		ktypeMapper.deletektypeById(id);
	}

}
