package cn.it.ssh.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.it.ssh.entity.Relate;
import cn.it.ssh.service.ReatedService;

public class Related extends ActionSupport {

	private Relate rel;

	public Relate getRel() {
		return rel;
	}

	public void setRel(Relate rel) {
		this.rel = rel;
	}

	public ReatedService getRea() {
		return rea;
	}

	public void setRea(ReatedService rea) {
		this.rea = rea;
	}

	private ReatedService rea;
	private String result = "error";

	public String execute() throws Exception {

		HttpSession session = ServletActionContext.getRequest().getSession();

		if (session.getAttribute("user_id") != null) {

			System.out.println(session.getAttribute("user_id"));
			System.out.println(rel.getM_id());
			result = "success";
			int uid = (int) session.getAttribute("user_id");

			rel.setU_id(uid);
			this.rea.save(rel);
		}

		return result;
	}

}
