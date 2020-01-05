package cn.it.ssh.service.impl;

import java.util.List;

import cn.it.ssh.dao.BuyMovieDao;
import cn.it.ssh.entity.UservoMovie;
import cn.it.ssh.entity.movie;
import cn.it.ssh.service.BuyMovieService;

public class BuyMovieServiceImpl implements BuyMovieService {

	private BuyMovieDao buyMovieDao;
	
	public BuyMovieDao getBuyMovieDao() {
		return buyMovieDao;
	}

	public void setBuyMovieDao(BuyMovieDao buyMovieDao) {
		this.buyMovieDao = buyMovieDao;
	}

	@Override
	public List<movie> findByid(int id) {
		List<movie> listmovie=this.buyMovieDao.findmovie(id);
		return listmovie;
	}

}
