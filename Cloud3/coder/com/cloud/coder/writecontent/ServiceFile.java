package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class ServiceFile {

	public static void write(Map paramMap , String filePath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
        content.append("package " + coder.getPackageName() + ".service;" + enter + enter);
        
        content.append("import java.util.List;"+enter);
        content.append("import java.util.Map;"+enter+enter);
        
        content.append("import "+coder.getPackageName()+" .model."+coder.getModelName()+";"+enter+enter);
        
        content.append("public interface I"+coder.getModelName()+"Service {"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 通过id得到某一对象"+enter);
        content.append("	 */"+enter);
        content.append("	public "+coder.getModelName()+" get"+coder.getModelName()+"ById(String id);"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 保存或修改某一对象"+enter);
        content.append("	 */"+enter);
        content.append("	public "+coder.getModelName()+" save"+coder.getModelName()+"("+coder.getModelName()+" "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+");"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 分页查询"+enter);
        content.append("	 */"+enter);
        content.append("	public Map search"+coder.getModelName()+"(Long curPage, Long pageSize,String whereStr);"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 删除某一对象"+enter);
        content.append("	 */"+enter);
        content.append("	public "+coder.getModelName()+" del"+coder.getModelName()+"("+coder.getModelName()+" "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+");"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 批量删除"+enter);
        content.append("	 */"+enter);
        content.append("	public void del"+coder.getModelName()+"Batch(List<String> list);"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 通过条件查询数据"+enter);
        content.append("	 */"+enter);
        content.append("	public List getAllDataByWhere(String where);"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 批量保存数据"+enter);
        content.append("	 */"+enter);
        content.append("	public boolean saveDataBatch(List<"+coder.getModelName()+"> list);"+enter+enter);

        content.append("}"+enter);
        
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
