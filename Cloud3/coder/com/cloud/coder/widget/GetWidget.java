package com.cloud.coder.widget;

import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class GetWidget {

	/**
	 * 通过jsp控件获取相应的控件
	 * @param pmc
	 * @return
	 */
	public static String get(Coder coder , ProMappingCol pmc) {
		String jspInput = pmc.getJspInput();
		String result = "";
		if(jspInput.equals("text")) {					//表示是文本
			result = InputText.get(coder,pmc);
		} else if(jspInput.equals("hidden")) {			//隐藏域
			result = InputHidden.get(coder, pmc);
		} else if(jspInput.equals("textarea")) {		//文本域
			result = InputTextarea.get(coder, pmc);
		} else if(jspInput.equals("datadict:inputRadio")) {		//数据字典中的radio
			result = InputRadio4DataDict.get(coder, pmc);
		} else if(jspInput.equals("datadict:inputCheckbox")) {	//数据字典中的checkbox
			result = InputCheckbox4DataDict.get(coder, pmc);
		} else if(jspInput.equals("datadict:select")) {			//数据字典中的select
			result = InputSelect4DataDict.get(coder, pmc);
		} else if(jspInput.equals("datadict:select2")) {		//数据字典中的双select
			result = InputSelect24DataDict.get(coder, pmc);
		} else if(jspInput.equals("htmleditor")) {				//htmleditor编辑器
			result = InputHTML.get(coder, pmc);
		} else if(jspInput.equals("yyyy-MM-dd HH:mm:ss") 
				|| jspInput.equals("yyyy-MM-dd HH:mm") 
				|| jspInput.equals("yyyy-MM-dd HH") 
				|| jspInput.equals("yyyy-MM-dd")) {				//日期控件		
			result = InputDate.get(coder, pmc);
		} else if(jspInput.equals("deptTree")) {						//部门树
			result = InputDeptTree.get(coder, pmc);
		}
		return result;
	}
}
