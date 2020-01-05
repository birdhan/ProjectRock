package cn.it.ssh.service;

import java.util.List;

import cn.it.ssh.entity.UservoMovie;
import cn.it.ssh.entity.movie;

public interface BuyMovieService {

	public List<movie> findByid(int id);
}
