package com.cloud.base.tag.id2name;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.SpringContextHolder;
import com.cloud.department.dao.DepartmentDaoHibernate;
import com.cloud.department.model.Department;

public class DepartNo2Name extends TagSupport {

	private String departNo;
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			String result = departNo;
			
			if(SysCache.getInstance().getSystemConfig().getRootDepartNo().equals(departNo)) {
				result = SysCache.getInstance().getSystemConfig().getRootDepartName();
			} else {							
				DepartmentDaoHibernate ddh = (DepartmentDaoHibernate)SpringContextHolder.getApplicationContext().getBean("departmentDaoHibernate");
				List<Department> list = ddh.getAllDataByWhere(" and department.departNo='"+departNo+"'");
				if(list != null) {
					if(list.size() != 0) {
						result = list.get(0).getDepartName();
					}
				}
			}
			out.print(result);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getDepartNo() {
		return departNo;
	}

	public void setDepartNo(String departNo) {
		this.departNo = departNo;
	}
	
}
