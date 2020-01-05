package com.fmcg.dao;

import java.util.List;

import com.fmcg.pojo.Kuaixiao;

public interface KuaixiaoMapper {
	
	public void addkuaixiao(Kuaixiao kuaixiao);
	
	public List<Kuaixiao> findAll(Integer start);
	
	public List<Kuaixiao> findBytype(Integer start,String types);
	
	public Kuaixiao findById(String id);
	
	public void updatekuai(String id);
	
	public List<Kuaixiao> findbackkuaixiaoBykcheck();
	
	public void deletebackkuaixiaoById(String id);
	
	public void updateKuaixiaoById(Kuaixiao kuaixiao);
	
	public List<Kuaixiao> findkuaixiaopinById(String id);
	
	public List<Kuaixiao> findBysousuo(String guanjianzi);

}
