package cn.it.ssh.dao;

import java.util.List;

import cn.it.ssh.entity.UservoMovie;
import cn.it.ssh.entity.movie;

public interface BuyMovieDao {

	
	public List<movie> findmovie(int u_id);
	
	
}
