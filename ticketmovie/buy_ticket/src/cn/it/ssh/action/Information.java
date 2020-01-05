package cn.it.ssh.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.it.ssh.entity.UservoMovie;
import cn.it.ssh.entity.movie;
import cn.it.ssh.service.BuyMovieService;

public class Information extends ActionSupport{

	
	private BuyMovieService  buymovieservice;
	private UservoMovie  uservol;
	public UservoMovie getUservol() {
		return uservol;
	}
	public void setUservol(UservoMovie uservol) {
		this.uservol = uservol;
	}
	private movie movied;
	
	public BuyMovieService getBuymovieservice() {
		return buymovieservice;
	}
	public void setBuymovieservice(BuyMovieService buymovieservice) {
		this.buymovieservice = buymovieservice;
	}
	public movie getMovied() {
		return movied;
	}
	public void setMovied(movie movied) {
		this.movied = movied;
	}
	private String result="error";
	public String execute() throws Exception{
		
		HttpSession session=ServletActionContext.getRequest().getSession();
		
		
		if(session.getAttribute("user_id")!=null) {
			/*String name=(String) session.getAttribute("name");
			String sex=(String) session.getAttribute("sex");
			int age=(int) session.getAttribute("age");
			String tel=(String) session.getAttribute("phone");
			
			users user=new users();
			
			user.setUsername(name);
			user.setSex(sex);
			user.setAge(age);
			user.setTel(tel);*/
		    /*ActionContext.getContext().put("list", user);*/
			int id=(int) session.getAttribute("user_id");
			List<movie> movielist=this.buymovieservice.findByid(id);
			ActionContext.getContext().put("list", movielist);
			System.out.println(movielist);


			/*List<movie> list = new ArrayList<movie>();
			  
			  List<Object> movielist = list;
			  
			  System.out.println((List<movie>)ll);;*/
			
			/*System.out.println(movielist.get(0).getMovie_name());
			System.out.println(movielist.get(0).getMovie_type());
			System.out.println(movielist.get(1).getMovie_type());
			
			System.out.println(movielist.get(1).getMovie_name());
			*/
			 result="success";
		}
		
		
		return result;
	}
}
