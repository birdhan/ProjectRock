package com.cloud.coder.widget;

import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class InputTextarea {

	public static String get(Coder coder , ProMappingCol pmc) {
		return "<textarea rows=\"10\" cols=\"10\" id=\""+pmc.getPro()+"\" name=\""+pmc.getPro()+"\">${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}</textarea>";
	}
}
