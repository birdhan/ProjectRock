package com.cloud.base.tag.id2name;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cloud.base.util.ApplicationContextHolder;
import com.cloud.base.util.DBFM;

/**
 * 通用的ID转名称或其它待显示的值标签类
 * @author cuiyp
 * @date 2016年9月16日 下午2:01:25
 * 忠告：写代码就是耍流氓，请诸位认真并且尽情地耍流氓！！！
 */
public class CommonId2Name extends TagSupport {

	private String selCol;						// 待查询的列
	private String tableName;					// 待查询的数据表名
	private String whereCol;					// where条件列
	private String whereColValue;				// where条件列的内容值
	
	private String resultValue;					// 最终返回结果
	
	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			out.print(resultValue);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doStartTag() throws JspException {
		resultValue = "";
		String[] colValueArr = whereColValue.split(",");
		String finalWhereColValue = "";
		for(int i=0;i<colValueArr.length;i++) {
			if(i != colValueArr.length-1) 
				finalWhereColValue += "'" + colValueArr[i] + "',";
			else
				finalWhereColValue += "'" + colValueArr[i] + "'";
		}
		String sql = "select " + DBFM.WM_CONCAT() + "(" + selCol + ") as name from " + tableName + " where "+whereCol+" in (" + finalWhereColValue + ")";
		JdbcTemplate jdbc = (JdbcTemplate)ApplicationContextHolder.getInstance().getBean("jdbcTemplate");
		Map rs = jdbc.queryForMap(sql);
		if(rs == null || rs.get("name") == null) resultValue = "";
		else resultValue = (String)rs.get("name");
		return EVAL_BODY_INCLUDE;
	}

	public String getSelCol() {
		return selCol;
	}

	public void setSelCol(String selCol) {
		this.selCol = selCol;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getWhereCol() {
		return whereCol;
	}

	public void setWhereCol(String whereCol) {
		this.whereCol = whereCol;
	}

	public String getWhereColValue() {
		return whereColValue;
	}

	public void setWhereColValue(String whereColValue) {
		this.whereColValue = whereColValue;
	}
}
