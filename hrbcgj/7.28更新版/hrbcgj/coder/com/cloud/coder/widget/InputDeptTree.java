package com.cloud.coder.widget;

import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

/**
 * 部门树
 * @author cyp
 *
 */
public class InputDeptTree {

	public static String get(Coder coder , ProMappingCol pmc) {
		return "<cloud:deptTree value=\"${"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"."+pmc.getPro()+"}\" property=\""+pmc.getPro()+"\" single=\"true\" autoCheckboxEven=\"true\" checkbox=\"true\"/>";
	}
}
