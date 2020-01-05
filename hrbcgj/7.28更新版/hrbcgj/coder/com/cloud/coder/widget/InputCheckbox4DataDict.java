package com.cloud.coder.widget;

import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class InputCheckbox4DataDict {

	public static String get(Coder coder , ProMappingCol pmc) {
		return "<datadict:inputCheckbox inputType=\"checkbox\" value=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}\" property=\""+pmc.getPro()+"\" moduleName=\""+coder.getNameSpace()+"\"/>";
	}
}
