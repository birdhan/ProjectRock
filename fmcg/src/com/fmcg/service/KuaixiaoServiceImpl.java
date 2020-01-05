package com.fmcg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fmcg.dao.KuaixiaoMapper;
import com.fmcg.pojo.Kuaixiao;

@Service
public class KuaixiaoServiceImpl implements KuaixiaoService {
@Autowired
private KuaixiaoMapper kuaixiaoMapper;
	@Override
	public void addkuaixiao(Kuaixiao kuaixiao) {
		// TODO Auto-generated method stub
		kuaixiaoMapper.addkuaixiao(kuaixiao);
	}
	@Override
	public List<Kuaixiao> findAll(Integer start) { 
		// TODO Auto-generated method stub
		return kuaixiaoMapper.findAll(start);
	}
	@Override
	public List<Kuaixiao> findBytype(Integer start, String types) {
		// TODO Auto-generated method stub
		return kuaixiaoMapper.findBytype(start, types);
	}
	@Override
	public Kuaixiao findById(String id) {
		// TODO Auto-generated method stub
		return kuaixiaoMapper.findById(id);
	}
	@Override
	public void updatekuai(String id) {
		kuaixiaoMapper.updatekuai(id);
		
	}
	@Override
	public List<Kuaixiao> findbackkuaixiaoBykcheck() {
		// TODO Auto-generated method stub
		List<Kuaixiao> bykcheck = kuaixiaoMapper.findbackkuaixiaoBykcheck();
		return bykcheck;
	}
	@Override
	public void deletebackkuaixiaoById(String id) {
		// TODO Auto-generated method stub
		kuaixiaoMapper.deletebackkuaixiaoById(id);
	}
	@Override
	public void updateKuaixiaoById(Kuaixiao kuaixiao) {
		// TODO Auto-generated method stub
		kuaixiaoMapper.updateKuaixiaoById(kuaixiao);
	}
	@Override
	public List<Kuaixiao> findkuaixiaopinById(String id) {
		// TODO Auto-generated method stub
		 List<Kuaixiao> findkuaixiaopinById = kuaixiaoMapper.findkuaixiaopinById(id);
		return findkuaixiaopinById;
	}
	@Override
	public List<Kuaixiao> findBysousuo(String guanjianzi) {
		// TODO Auto-generated method stub
		return kuaixiaoMapper.findBysousuo(guanjianzi);
	}

}
