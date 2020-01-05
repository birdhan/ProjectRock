package com.cloud.coder.widget;

import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class InputRadio4DataDict {

	public static String get(Coder coder , ProMappingCol pmc) {
		return "<datadict:inputRadio inputType=\"radio\" value=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}\" property=\""+pmc.getPro()+"\" moduleName=\""+coder.getNameSpace()+"\"/>";
	}
}
