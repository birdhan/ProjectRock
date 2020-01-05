package com.cloud.coder.widget;

import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

/**
 * 文本控件
 * @author cyp
 *
 */
public class InputText {

	public static String get(Coder coder , ProMappingCol pmc) {
		return "<input name=\""+pmc.getPro()+"\" type=\"text\" id=\""+pmc.getPro()+"\" value=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}\" autocomplete=\"off\"/>";
	}
}
