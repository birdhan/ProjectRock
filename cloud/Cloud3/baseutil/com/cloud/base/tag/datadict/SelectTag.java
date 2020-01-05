package com.cloud.base.tag.datadict;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cloud.base.util.SpringContextHolder;
import com.cloud.base.util.StringUtil;
import com.cloud.datadict.dao.DataDictDaoHibernate;
import com.cloud.datadict.model.DataDict;

public class SelectTag extends TagSupport {
	private String property;					//属性
	private String inputType;					//控件类型(字典类型)
	private String moduleName;					//模块名
	private String style;						//样式
	private String value;						//真实选择的值以英文逗号分隔
	private String onChange;					//onChange事件
	private String multiple;					//是否多选属性
	private String topValue;					//最上面的值
	private String topLabel;					//最上面的label
	
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
				
				String onChangeEvent = "";
				if(!StringUtil.null2String(onChange).equals("")) {
					onChangeEvent = "onchange=\""+onChange+"\"";
				}
				
				if(!StringUtil.null2String(multiple).equals("")) {
					multiple = "multiple=\"multiple\"";
				}
				
				sb.append("<select name=\""+property+"\" id=\""+property+"\" " + onChangeEvent + " " + multiple + " style="+ StringUtil.null2String(style)+">");
				
				if(topValue != null && topLabel != null) {
					sb.append("<option id=\""+property+"00\" value=\""+StringUtil.null2String(topValue)+"\"><label for=\"" +property+ "00\">"+StringUtil.null2String(topLabel)+"</label></option>");
				}
				
				for(int i=0;i<tv_arr.length;i++) {
					String selected = "";
					if(v_arr != null) {
						for(int j=0;j<v_arr.length;j++) {
							if(tv_arr[i].trim().equals(v_arr[j].trim())) {		//如果有相等的值，说明要被选中
								selected = "selected='selected'";
								break;
							}
						}
					} 
					sb.append("<option id=\"" +property+i+ "\" value=\""+tv_arr[i]+"\" "+selected+" ><label for=\"" +property+i+ "\">"+tl_arr[i]+"</label></option>");
				}
				sb.append("</select>");
				out.print(sb.toString());
			} else {
				out.print("<font color='red'>字典类型为：" + inputType + "，属性为："+property+"，模块名称为："+moduleName+"没有在数据字典中配置</font>");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getTopValue() {
		return topValue;
	}

	public void setTopValue(String topValue) {
		this.topValue = topValue;
	}

	public String getTopLabel() {
		return topLabel;
	}

	public void setTopLabel(String topLabel) {
		this.topLabel = topLabel;
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

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}
}
