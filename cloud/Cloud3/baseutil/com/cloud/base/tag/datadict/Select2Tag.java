package com.cloud.base.tag.datadict;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.SpringContextHolder;
import com.cloud.base.util.StringUtil;
import com.cloud.datadict.dao.DataDictDaoHibernate;
import com.cloud.datadict.model.DataDict;

public class Select2Tag extends TagSupport {

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
		JspWriter out = pageContext.getOut();
		try {
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
				
				sb.append("<script type=\"text/javascript\">");
				sb.append("	function func_insert"+property+"() {");
				sb.append("		var left = document.getElementById(\"left"+property+"\");");
				sb.append("		var right = document.getElementById(\"right"+property+"\");");
				sb.append("		for (i=0; i<left.options.length; i++) {");
				sb.append("			if(left.options[i].selected) {");
				sb.append("				option_text=left.options[i].text;");
				sb.append("				option_value=left.options[i].value;");
				sb.append("				var my_option = document.createElement(\"OPTION\");");
				sb.append("				my_option.text=option_text;");
				sb.append("				my_option.value=option_value;");
				sb.append("				pos=left.options.length;");
				sb.append("				right.options.add(my_option);");
				sb.append("				left.remove(i);");
				sb.append("				i--;");
				sb.append("			}");
				sb.append("		}");
				sb.append("		getValues"+property+"();");
				sb.append("	}");
				sb.append("");
				sb.append("	function func_delete"+property+"() {");
				sb.append("		var left = document.getElementById(\"left"+property+"\");");
				sb.append("		var right = document.getElementById(\"right"+property+"\");");
				sb.append("		for (i=0;i<right.options.length;i++) {");
				sb.append("			if(right.options[i].selected) {");
				sb.append("				option_text=right.options[i].text;");
				sb.append("				option_value=right.options[i].value;");
				sb.append("				var my_option = document.createElement(\"OPTION\");");
				sb.append("				my_option.text=option_text;");
				sb.append("				my_option.value=option_value;");
				sb.append("				pos=left.options.length;");
				sb.append("				left.options.add(my_option);");
				sb.append("				right.remove(i);");
				sb.append("				i--;");
				sb.append("			}");
				sb.append("		}");
				sb.append("		getValues"+property+"();");
				sb.append("	}");
				sb.append("");
				sb.append("	function getValues"+property+"() {");
				sb.append("		var values = \"\";");
				sb.append("		var right = document.getElementById(\"right"+property+"\");");
				sb.append("		for (i=0;i<right.options.length;i++) {");
				sb.append("			values += right.options[i].value + \",\";");
				sb.append("		}");
				sb.append("		if(values != \"\") {");
				sb.append("			values = values.substring(0,values.length-1);");
				sb.append("		}");
				sb.append("		getEleById(\""+property+"\").value = values;");
				sb.append("	}");
				sb.append("");
				sb.append("	function func_delete_left"+property+"() {");
				sb.append("		var left = document.getElementById(\"left"+property+"\");");
				sb.append("		var right = document.getElementById(\"right"+property+"\");");
				sb.append("		for (i=0; i<right.options.length; i++) {");
				sb.append("			for(var j=0;j<left.options.length;j++) {");
				sb.append("				if(left.options[j].value == right.options[i].value) {");
				sb.append("					left.remove(j);");
				sb.append("					j--;");
				sb.append("				}");
				sb.append("			}");
				sb.append("		}");
				sb.append("		getValues" +property+ "();");
				sb.append("	}");
				sb.append("</script>");
				sb.append("<table border='0' class='select2Table'>");
				sb.append("	<td style='border:none;padding:0px;'>");
				if(StringUtil.null2String(style).equals("")) {
					style = "style=\"width:120px;height:70px\"";
				}
				sb.append("		<select ondblclick=\"func_insert"+property+"();\" id=\"left"+property+"\" multiple=\"true\" "+style+">");
				for(int i=0;i<tv_arr.length;i++) {
					sb.append("		<option id=\"left" +property+i+ "\" value=\""+tv_arr[i]+"\" title='"+tl_arr[i]+"'><label for=\"left" +property+i+ "\">"+tl_arr[i]+"</label></option>");
				}
				sb.append("		</select>");
				sb.append("	</td>");
				sb.append("	<td style='border:none;'>");
				sb.append("		<img src=\""+SysCache.getInstance().getContextPath()+"/images/common/right.png\" onclick=\"func_insert"+property+"();\" title=\"添加\" style=\"cursor: pointer; margin-bottom: 3px;\"/><br/>");
				sb.append("		<img src=\""+SysCache.getInstance().getContextPath()+"/images/common/left.png\" onclick=\"func_delete"+property+"();\" title=\"移除\" style=\"cursor: pointer; margin-top: 3px;\"/>");
				sb.append("	</td>");
				sb.append("	<td style='border:none;'>");
				sb.append("		<select ondblclick=\"func_delete"+property+"();\" id=\"right"+property+"\" multiple=\"true\" "+style+">");
				if(v_arr != null) {
					for(int i=0;i<v_arr.length;i++) {
						for(int j=0;j<tv_arr.length;j++) {
							if(v_arr[i].equals(tv_arr[j])) {
								sb.append("		<option id=\"right" +property+i+ "\" value=\""+v_arr[i]+"\" title='"+tl_arr[j]+"'><label for=\"right" +property+i+ "\">"+tl_arr[j]+"</label></option>");
								break;
							}
						}					
					}
				}
				sb.append("		</select>");
				sb.append("		<input type=\"text\" name=\"" +property+ "\" id=\"" +property+ "\" value=\""+value+"\" style=\"visibility: hidden;width:1px;\"/>");
				sb.append("	</td>");
				sb.append("</table>");
				sb.append("<script type=\"text/javascript\">");
				sb.append("	getValues" +property+ "();");
				sb.append("	func_delete_left" +property+ "();");
				sb.append("</script>");
				out.print(sb.toString());
			} else {
				out.print("<font color='red'>字典类型为：" + inputType + "，属性为："+property+"，模块名称为："+moduleName+"没有在数据字典中配置</font>");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			try {
				out.print("<font color='red'>控件生成错误消息："+e.getMessage()+"</font>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
