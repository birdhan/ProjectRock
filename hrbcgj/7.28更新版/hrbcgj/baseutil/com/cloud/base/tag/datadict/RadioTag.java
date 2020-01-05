package com.cloud.base.tag.datadict;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cloud.base.util.SpringContextHolder;
import com.cloud.base.util.StringUtil;
import com.cloud.datadict.dao.DataDictDaoHibernate;
import com.cloud.datadict.model.DataDict;

public class RadioTag extends TagSupport {

	private String property;					//属性
	private String inputType;					//控件类型(字典类型)
	private String moduleName;					//模块名
	private String style;						//样式
	private String value;						//真实选择的值以英文逗号分隔
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			DataDictDaoHibernate ddh = (DataDictDaoHibernate)SpringContextHolder.getApplicationContext().getBean("dataDictDaoHibernate");
			DataDict dd = new DataDict();
			dd.setModuleName(moduleName);
			dd.setDictType(inputType);
			dd.setProperty(property);
			Map map = ddh.getDictInitValue(dd);
			if(map.size() != 0 && map.get("dictvalue") != null && map.get("dictlabel") != null) {
				String[] tv_arr = String.valueOf(map.get("dictvalue")).trim().split(",");
				String[] tl_arr = String.valueOf(map.get("dictlabel")).trim().split(",");
				String[] v_arr = null;
				if(!value.trim().equals("")) {
					v_arr = value.split(",");
				}			
				
				for(int i=0;i<tv_arr.length;i++) {
					String checked = "";
					if(v_arr != null) {
						for(int j=0;j<v_arr.length;j++) {
							if(tv_arr[i].trim().equals(v_arr[j].trim())) {		//如果有相等的值，说明要被选中
								checked = "checked='checked'";
								break;
							}
						}
					} else {
						if(i == 0) {
							checked = "checked='checked'";
						}
					}
					sb.append("<span class='span_1'><input type=\"radio\" name=\"" + property + "\" value=\""+tv_arr[i]+"\" " + checked + " " + StringUtil.null2String(style) + "/></span><span class='span_2'>"+tl_arr[i]+"</span>");					
				}
				out.print(sb.toString());
			} else {
				out.print("<font color='red'>字典类型为：" + inputType + "，属性为："+property+"，模块名称为："+moduleName+"没有在数据字典中配置</font>");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
