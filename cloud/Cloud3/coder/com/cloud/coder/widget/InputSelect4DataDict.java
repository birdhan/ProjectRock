package com.cloud.coder.widget;

import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class InputSelect4DataDict {

	public static String get(Coder coder , ProMappingCol pmc) {
		return "<datadict:select inputType=\"select\" value=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}\" property=\""+pmc.getPro()+"\" moduleName=\""+coder.getNameSpace()+"\"/><!--其它属性例  multiple=\"multiple\" style=\"height:50px;\" topValue=\"\" topLabel=\"--请选择--\" -->";
	}
}
