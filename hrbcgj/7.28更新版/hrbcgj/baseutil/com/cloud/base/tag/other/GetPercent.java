package com.cloud.base.tag.other;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cloud.base.util.ApplicationContextHolder;

public class GetPercent extends TagSupport {

	private String id;
	private String share;
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}
	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			String result = "";
			if(!share.equals("")) {
				DecimalFormat df = new DecimalFormat("#.00");
				JdbcTemplate jdbc = (JdbcTemplate)ApplicationContextHolder.getInstance().getBean("jdbcTemplate");
				if(!id.equals("")) {															//如果id不为空，表示是要修改数据
					String sql = "select sum(PARSHARE) as sumparshare from service_partner where id!='"+id+"'";
					Map map = jdbc.queryForMap(sql);
					if(map.get("sumparshare") != null) {
						BigDecimal sumparshare = (BigDecimal)map.get("sumparshare");
						long sum = Integer.parseInt(share) + sumparshare.longValue();
						result = df.format((Long.valueOf(share)*10000 / sum) * 0.0001 * 100);
					} else {
						result = df.format((Long.valueOf(share)*10000 / (Long.valueOf(share))) * 0.0001 * 100);
					}
				} else {																		//如果id为空
					String sql = "select sum(PARSHARE) as sumparshare from service_partner";
					Map map = jdbc.queryForMap(sql);
					if(map.get("sumparshare") != null) {
						BigDecimal sumparshare = (BigDecimal)map.get("sumparshare") == null ? new BigDecimal(0) : (BigDecimal)map.get("sumparshare");
						long sum = Integer.parseInt(share) + sumparshare.longValue();
						result = df.format((Long.valueOf(share)*10000 / sum) * 0.0001 * 100);
					} else {
						result = df.format((Long.valueOf(share)*10000 / (Long.valueOf(share))) * 0.0001 * 100);
					}
				}
				
				if(!result.equals("") && result.indexOf(".") == 0) {
					result = "0" + result;
				}
			}
			result = result.equals("") ? "" : result+"%";
			out.print(result);			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShare() {
		return share;
	}
	public void setShare(String share) {
		this.share = share;
	}
}
