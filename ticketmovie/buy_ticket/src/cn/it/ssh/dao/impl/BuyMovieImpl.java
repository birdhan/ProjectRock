package cn.it.ssh.dao.impl;

import java.util.Arrays;
import java.util.List;



import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.it.ssh.dao.BuyMovieDao;
import cn.it.ssh.entity.UservoMovie;
import cn.it.ssh.entity.movie;

public class BuyMovieImpl extends HibernateDaoSupport implements BuyMovieDao {

	@Override
	public List<movie> findmovie(int u_id) {
		
		/*String sql="select new cn.it.ssh.entity.UservoMovie(m.id,m.movie_name,m.movie_type,m.movie_img,m.movie_price,m.movie_sketch) from movie m, Relate r where m.id=r.m_id and r.u_id='"+u_id+"'";*/
		/*HttpSession session=ServletActionContext.getRequest().getSession();*/
		/*SQLQuery query=session.createSQLQuery(sql) ;*/
		/*((Object) session).createSQLQuery(sql).addEntity(Classclass);*/
		/*query.addEntity("m", movie.class);*/
		/*Session   session = sessionFactory.openSession();*/
		/*SessionFactory sessions = cfg.buildSessionFactory();*/
		/*setResultTransformer(new AliasToBeanResultTransformer(movie.class));*/
		/*List cats= session.createSQLQuery(sql).addEntity( " m " , movie. class).list();*/
		/*List<movie> buymovie=this.getHibernateTemplate().find(sql);*/
	
		/*Session session = getHibernateTemplate().getSessionFactory()
				   .getCurrentSession();*/
		/*
		Session session=HibernateSessionFactory.getSession();
		
		Transaction tx=session.beginTransaction();*/
		
		
		
		
		
		/*List<movie> q = session.createQuery(sql).addEntity().list();*/
		
		
		/*List<UservoMovie> list= session.createSQLQuery(sql).addEntity("m",UservoMovie.class).list();*/
		
		 /*List list = q.list();*/
		 
		/* List stuList = scoreService.findAllScore(queryScore, null); // ���صĽ����
*/		 /*
		
		/*List cats= session.createSQLQuery("select {m.*} from movie m, Relate r where m.id=r.m_id and r.u_id='"+u_id+"'").addEntity("m",movie.class).list();*/
		
	/*	Query q = session.createQuery(sql);
		
		List list = q.list();*/
		
		/*List list = this.getList(sql,page.getPageSize(), page.getStartRow()); */
		/*new cn.it.ssh.entity.UservoMovie(m.id,m.movie_name,m.movie_type,m.movie_img,m.movie_price,m.movie_sketch)*/
		
		 String hql="from movie m, Relate r where m.id=r.m_id and r.u_id='"+u_id+"'";
		 
         List<Object[]> listall=this.getHibernateTemplate().find(hql);
		 for(Object o[]:listall)
		 {
			 System.out.println(Arrays.toString(o));
		 }
         return null;
		 
		
		
		/*select m.id,m.movie_name,m.movie_type,m.movie_img,m.movie_price,m.movie_sketch */
		
	}
/*
	private void setResultTransformer(AliasToBeanResultTransformer aliasToBeanResultTransformer) {
		// TODO Auto-generated method stub
		
	}
*/
}
