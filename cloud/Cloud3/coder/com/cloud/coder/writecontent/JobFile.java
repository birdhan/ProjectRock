package com.cloud.coder.writecontent;

import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;

public class JobFile {

	public static void write(Map paramMap , String filePath) {
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
        content.append("package " + coder.getPackageName() + ".job;" + enter + enter);
        
        content.append("import org.quartz.Job;"+enter);        
        content.append("import org.quartz.JobExecutionContext;"+enter);
        content.append("import org.quartz.JobExecutionException;"+enter+enter);
        content.append("import import com.cloud.base.job.IBaseJob"+enter+enter);
        
        content.append("/**"+enter);
        content.append(" * 定时任务类"+enter);
        content.append(" * @author "+coder.getAuthor()+enter);
        content.append(" *"+enter);
        content.append(" */"+enter);
        content.append("public class "+coder.getModelName()+"Job implements IBaseJob {"+enter+enter);
        content.append("	@Override"+enter);
        content.append("	public void execute(JobExecutionContext arg0) throws JobExecutionException {"+enter);
        content.append("		executeJob();"+enter);
        content.append("	}"+enter+enter);
        
        content.append("	/**"+enter);
        content.append(" 	 * 方便其它类中调用此方法"+enter);
        content.append(" 	 * @author "+coder.getAuthor()+enter);
        content.append(" 	 *"+enter);
        content.append(" 	 */"+enter);        
        content.append("	public void executeJob() {"+enter);
        content.append("		System.out.println(\"====正在执行"+coder.getModelName()+"Job.executeJob()====\");"+enter);
        content.append("	}"+enter+enter);
        
        content.append("}"+enter);
    	
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
