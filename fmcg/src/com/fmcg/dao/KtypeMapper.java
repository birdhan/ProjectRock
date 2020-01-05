package com.fmcg.dao;

import java.util.List;

import com.fmcg.pojo.Ktype;

public interface KtypeMapper {
	public void addtype(Ktype ktype);

	public List<Ktype> selectktype();
	
	public void deletektypeById(String id);
}
