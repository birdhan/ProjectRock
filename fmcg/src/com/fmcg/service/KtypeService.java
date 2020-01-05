package com.fmcg.service;

import java.util.List;

import com.fmcg.pojo.Ktype;

public interface KtypeService {
	public void addtype(Ktype ktype);
	
	public List<Ktype> selectktype();
	
	public void deletektypeById(String id);
}
