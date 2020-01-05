package cn.it.ssh.dao;

import java.util.List;

import cn.it.ssh.entity.movie;

public interface  MovieDao {
	
	public void save(movie mo);
	
	public List<movie> findmovie();
		
	

}
