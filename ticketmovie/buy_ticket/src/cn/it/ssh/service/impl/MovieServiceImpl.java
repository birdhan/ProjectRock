package cn.it.ssh.service.impl;

import java.util.List;

import cn.it.ssh.dao.MovieDao;
import cn.it.ssh.entity.movie;
import cn.it.ssh.service.MovieService;

public class MovieServiceImpl implements MovieService {

	private MovieDao movieDao;
	public MovieDao getMovieDao() {
		return movieDao;
	}

	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	@Override
	public void save(movie move) {
		movieDao.save(move);

	}

	@Override
	public List<movie> selectAll() {
		return this.movieDao.findmovie();
		
	}

}
