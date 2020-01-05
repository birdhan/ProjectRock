package com.cloud.base.tag.datadict;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.StringUtil;
import com.cloud.datadict.model.DataDict;

public class DictValue2DiceLabel extends TagSupport {

	private String moduleName;
	private String dictType;
	private String property;
	private String dictValue;
	@Override
	public int doEndTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doStartTag() throws JspException {
		String dictLabel = "";
		JspWriter out = pageContext.getOut();
		try {
			if(!StringUtil.null2String(dictValue).equals("")) {
				String[] dv_arr = dictValue.split(",");
				List<DataDict> ddList =  SysCache.getInstance().getDictList();
				for(String val : dv_arr) {
					for(DataDict dd : ddList) {
						if(dd.getModuleName().equals(moduleName) && dd.getDictType().equals(dictType) 
							&& dd.getProperty().equals(property) && dd.getDictValue().equals(val.trim())) {
							if(dictLabel.equals("")) {
								dictLabel += dd.getDictLabel();
							} else {
								dictLabel += ","+dd.getDictLabel();
							}
						}
					}
					if(dv_arr.length == dictLabel.split(",").length) {
						break;
					}
				}
				if(dictLabel.equals("")) {
					dictLabel = "<font color='red'>字典类型为：" + dictType + "，属性为："+property+"，模块名称为："+moduleName+"，值为：" +dictValue+ "没有在数据字典中配置</font>";
				}
			} else {
				dictLabel = "";
			}
			
			out.print(dictLabel);			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	
}
