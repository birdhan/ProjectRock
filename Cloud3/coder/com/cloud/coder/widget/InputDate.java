package com.cloud.coder.widget;

import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class InputDate {

	public static String get(Coder coder , ProMappingCol pmc) {
		return "<cloud:inputDate497 value=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}\" property=\""+pmc.getPro()+"\" format=\""+pmc.getJspInput()+"\"/>";
	}
}
