package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class ServiceImplFile {

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

        content.append("import javax.annotation.Resource;"+enter+enter);
        
        content.append("import org.springframework.stereotype.Service;"+enter+enter);
        
        content.append("import "+coder.getPackageName()+".dao.I"+coder.getModelName()+"DaoMybatis;"+enter);
        content.append("import "+coder.getPackageName()+".dao."+coder.getModelName()+"DaoHibernate;"+enter);
        content.append("import "+coder.getPackageName()+".dao."+coder.getModelName()+"DaoJDBC;"+enter);
        content.append("import "+coder.getPackageName()+".model."+coder.getModelName()+";"+enter+enter);
        
        content.append("@Service"+enter);
        content.append("public class "+coder.getModelName()+"ServiceImpl implements I"+coder.getModelName()+"Service {"+enter+enter);
        
        content.append("	@Resource"+enter);
        content.append("	private I"+coder.getModelName()+"DaoMybatis "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"DaoMybatis;"+enter+enter);
        
        content.append("	@Resource"+enter);
        content.append("	private "+coder.getModelName()+"DaoHibernate "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"DaoHibernate;"+enter+enter);
        
        content.append("	@Resource"+enter);
        content.append("	private "+coder.getModelName()+"DaoJDBC "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"DaoJDBC;"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 通过id得到对象"+enter);
        content.append("	 */"+enter);
        content.append("	public "+coder.getModelName()+" get"+coder.getModelName()+"ById(String id) {"+enter);
        content.append("		return "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"DaoHibernate.get"+coder.getModelName()+"ById(id);"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 保存"+enter);
        content.append("	 */"+enter);
        content.append("	public "+coder.getModelName()+" save"+coder.getModelName()+"("+coder.getModelName()+" "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+") {"+enter);
        content.append("		return "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"DaoHibernate.save"+coder.getModelName()+"("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+");"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 列表查询"+enter);
        content.append("	 */"+enter);
        content.append("	public Map search"+coder.getModelName()+"(Long curPage, Long pageSize,String whereStr) {"+enter);
        content.append("		return "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"DaoHibernate.search"+coder.getModelName()+"(curPage, pageSize, whereStr);"+enter);
        content.append("	}"+enter+enter);

        content.append("	/**"+enter);
        content.append("	 * 删除"+enter);
        content.append("	 */"+enter);
        content.append("	public "+coder.getModelName()+" del"+coder.getModelName()+"("+coder.getModelName()+" "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+") {"+enter);
        content.append("		return "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"DaoHibernate.del"+coder.getModelName()+"("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+");"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 批量删除"+enter);
        content.append("	 */"+enter);
        content.append("	public void del"+coder.getModelName()+"Batch(List<String> list) {"+enter);
        content.append("		"+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"DaoHibernate.del"+coder.getModelName()+"Batch(list);"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 通过条件查询数据(非分页)"+enter);
        content.append("	 */"+enter);
        content.append("	public List getAllDataByWhere(String where) {"+enter);
        content.append("		return "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"DaoHibernate.getAllDataByWhere(where);"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 批量保存"+enter);
        content.append("	 */"+enter);
        content.append("	public boolean saveDataBatch(List<"+coder.getModelName()+"> list) {"+enter);
        content.append("		return "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"DaoHibernate.saveDataBatch(list);"+enter);
        content.append("	}"+enter+enter);
        
        content.append("}"+enter);
    	
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
