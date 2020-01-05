package com.cloud.coder.widget;

import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class InputSelect24DataDict {

	public static String get(Coder coder , ProMappingCol pmc) {
		return "<datadict:select2 moduleName=\""+coder.getNameSpace()+"\" inputType=\"select2\" value=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}\" property=\""+pmc.getPro()+"\"/>";
	}
}
