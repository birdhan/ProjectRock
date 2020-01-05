package cn.it.ssh.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.it.ssh.entity.movie;
import cn.it.ssh.service.MovieService;

public class SelectAll extends ActionSupport{

	private movie move;
	
	private MovieService ms;

	public movie getMove() {
		return move;
	}

	public void setMove(movie move) {
		this.move = move;
	}

	public MovieService getMs() {
		return ms;
	}

	public void setMs(MovieService ms) {
		this.ms = ms;
	}
	
	public String execute() throws Exception{
		
		
		
		List<movie> mo=this.ms.selectAll();
		
		 ActionContext.getContext().put("list", mo);
		
		 
		return SUCCESS;
	}
}
