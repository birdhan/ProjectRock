package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public abstract class DaoHibernateFile {

	public static void write(Map paramMap , String filePath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
        content.append("package " + coder.getPackageName() + ".dao;" + enter + enter);
        
        content.append("import java.util.List;"+enter);
        content.append("import java.util.Map;"+enter+enter);
        
        content.append("import org.hibernate.Transaction;"+enter);
        content.append("import org.springframework.stereotype.Repository;"+enter+enter);
        
        content.append("import com.cloud.base.jdbchibernate.JdbcHibernateUtil;"+enter);
        content.append("import "+coder.getPackageName()+".model."+coder.getModelName()+";"+enter+enter);
        
        content.append("@Repository"+enter);
        content.append("public class "+coder.getModelName()+"DaoHibernate extends JdbcHibernateUtil {"+enter+enter);
        
        content.append("	/**"+enter);
        content.append(" 	 * 通过id得到某个对象"+enter);
        content.append(" 	 * @param id"+enter);
        content.append(" 	 * @return"+enter);
        content.append(" 	 */"+enter);
        content.append("	public "+coder.getModelName()+" get"+coder.getModelName()+"ById(String id) {"+enter);
        content.append("		return ("+coder.getModelName()+")getDataObject("+coder.getModelName()+".class,id);"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 保存"+enter);
        content.append("	 * @param user"+enter);
        content.append("	 * @return"+enter);
        content.append("	 */"+enter);
        content.append("	public "+coder.getModelName()+" save"+coder.getModelName()+"("+coder.getModelName()+" "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+") {"+enter);
        content.append("		if("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+".getId() == null || "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+".getId().equals(\"\")) {"+enter);
        content.append("			saveData("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+");"+enter);
        content.append("		} else {"+enter);
        content.append("			saveOrUpdate("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+");"+enter);
        content.append("		}"+enter);
        content.append("		return "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+";"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 批量保存数据"+enter);
        content.append("	 * @param list"+enter);
        content.append("	 * @return"+enter);
        content.append("	 */"+enter);
        content.append("	public synchronized boolean saveDataBatch(List<"+coder.getModelName()+"> list) {"+enter);
        content.append("		boolean flag = true;"+enter);
        content.append("		Transaction tx = beginTransaction();"+enter);
        content.append("		try {"+enter);
        content.append("			for(int i=0;i<list.size();i++) {"+enter);
        content.append("				"+coder.getModelName()+" "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+" = list.get(i);"+enter);
        content.append("				session.save("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+");"+enter);
        content.append("				if (i == list.size()-1) {"+enter);
        content.append("					session.flush();"+enter);
        content.append("					session.clear();"+enter);
        content.append("				}"+enter);
        content.append("			}"+enter);
        content.append("			tx.commit();"+enter);
        content.append("			closeSession();"+enter);
        content.append("		} catch(Exception e) {"+enter);
        content.append("			e.printStackTrace();"+enter);
        content.append("			tx.rollback();"+enter);
        content.append("			flag = false;"+enter);
        content.append("		} finally {"+enter);
        content.append("			return flag;"+enter);
        content.append("		}"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 列表查询"+enter);
        content.append("	 * @param curPage"+enter);
        content.append("	 * @param pageSize"+enter);
        content.append("	 * @param whereStr"+enter);
        content.append("	 * @return"+enter);
        content.append("	 */"+enter);
        content.append("	public Map search"+coder.getModelName()+"(Long curPage, Long pageSize,String whereStr) {"+enter);
        content.append("		String hql = \"FROM "+coder.getModelName()+" "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+"\";"+enter);
        content.append("		return queryData2MapByPage(curPage, pageSize, hql, whereStr);"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 删除数据"+enter);
        content.append("	 * @param user"+enter);
        content.append("	 * @return"+enter);
        content.append("	 */"+enter);
        content.append("	public "+coder.getModelName()+" del"+coder.getModelName()+"("+coder.getModelName()+" "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+") {"+enter);
        content.append("		return ("+coder.getModelName()+")delData("+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+");"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 批量删除"+enter);
        content.append("	 * @param user"+enter);
        content.append("	 * @return"+enter);
        content.append("	 */"+enter);
        content.append("	public void del"+coder.getModelName()+"Batch(List<String> list) {"+enter);
        content.append("		String delHql = \"DELETE "+coder.getModelName()+" \";"+enter);
        content.append("		delDataBatch(delHql,list);"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append("	 * 通过条件查询数据(非分页)"+enter);
        content.append("	 */"+enter);
        content.append("	public List getAllDataByWhere(String where) {"+enter);
        content.append("		String hql = \"FROM "+coder.getModelName()+" "+StringUtil.replaceFirstStr2LowerCase(coder.getModelName())+" where 1=1 \" + where;"+enter);
        content.append("		return getDataList(hql);"+enter);
        content.append("	}"+enter+enter);
        
        content.append("}"+enter);
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
