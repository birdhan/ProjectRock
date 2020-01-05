package com.cloud.base.tag.other;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cloud.base.util.ApplicationContextHolder;

public class GetBusInfoByCol extends TagSupport {

	private String busNo;
	private String col;
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			String result = "<a title='表示没有在客车信息表中添加该车号的信息或信息填写不完整' href='javascript:void(0)'>未知</a>";
			JdbcTemplate jdbc = (JdbcTemplate)ApplicationContextHolder.getInstance().getBean("jdbcTemplate");
			List list = jdbc.queryForList("select * from service_businfo where busno='"+busNo+"'");
			if(list.size() != 0) {
				Map m = (Map)list.get(0);
				if(col.equals("xl")) {
					String xl1 = (String)m.get("xl1");
					String xl2 = (String)m.get("xl2");
					result = xl1 + "&nbsp;——&nbsp;" + xl2;
				} else {
					result = (String)m.get(col);
				}
			}
			
			out.print(result);			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getBusNo() {
		return busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}
	
}
