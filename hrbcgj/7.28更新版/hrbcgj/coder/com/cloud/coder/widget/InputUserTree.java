package com.cloud.coder.widget;

import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

/**
 * 人员树
 * @author cyp
 *
 */
public class InputUserTree {

	public static String get(Coder coder , ProMappingCol pmc) {
		return "<cloud:userTree value=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}\" property=\""+pmc.getPro()+"\" single=\"true\" autoCheckboxEven=\"true\" checkbox=\"true\"/>";
	}
}
