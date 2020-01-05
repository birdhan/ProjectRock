package com.cloud.coder.widget;

import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class InputHidden {

	public static String get(Coder coder , ProMappingCol pmc) {
		return "<input type=\"hidden\" name=\""+pmc.getPro()+"\" id=\""+pmc.getPro()+"\" value=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}\"/>";
	}
}
