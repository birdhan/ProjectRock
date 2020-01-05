package cn.it.ssh.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.it.ssh.dao.RelatedDao;
import cn.it.ssh.entity.Relate;

public class RelatedDaoImpl extends HibernateDaoSupport implements RelatedDao {

	@Override
	public void saveMo(Relate re) {
		this.getHibernateTemplate().save(re);

	}

}
