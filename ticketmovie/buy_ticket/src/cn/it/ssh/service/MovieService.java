package cn.it.ssh.service;

import java.util.List;

import cn.it.ssh.entity.movie;

public interface MovieService {

	public void save(movie move);
	
	public List<movie> selectAll();
}
