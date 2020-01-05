package cn.it.ssh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.it.ssh.dao.MovieDao;
import cn.it.ssh.entity.movie;
import cn.it.ssh.entity.users;

public class MovieDaoImpl extends HibernateDaoSupport implements MovieDao {

	@Override
	public void save(movie mo) {
		this.getHibernateTemplate().save(mo);

	}

	@Override
	public List<movie> findmovie() {
         String hql="from movie move order by move.id asc";
		 
          List<movie> listall=this.getHibernateTemplate().find(hql);
		 
          return  listall;
		 
	}

}
