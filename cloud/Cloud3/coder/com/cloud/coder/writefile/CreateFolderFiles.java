package com.cloud.coder.writefile;

import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;
import com.cloud.coder.writecontent.ActionFile;
import com.cloud.coder.writecontent.ClickExportJsFile;
import com.cloud.coder.writecontent.ClickFormJsFile;
import com.cloud.coder.writecontent.ClickImportJsFile;
import com.cloud.coder.writecontent.ClickListJsFile;
import com.cloud.coder.writecontent.DaoHibernateFile;
import com.cloud.coder.writecontent.DaoJDBCFile;
import com.cloud.coder.writecontent.DaoMybatisFile;
import com.cloud.coder.writecontent.DepolyFile;
import com.cloud.coder.writecontent.ExcelFile;
import com.cloud.coder.writecontent.IWSFile;
import com.cloud.coder.writecontent.JobFile;
import com.cloud.coder.writecontent.JspDetailFile;
import com.cloud.coder.writecontent.JspExportFile;
import com.cloud.coder.writecontent.JspFormFile;
import com.cloud.coder.writecontent.JspImportFile;
import com.cloud.coder.writecontent.JspListFile;
import com.cloud.coder.writecontent.ModelFile;
import com.cloud.coder.writecontent.ServiceFile;
import com.cloud.coder.writecontent.ServiceImplFile;
import com.cloud.coder.writecontent.SqlFile;
import com.cloud.coder.writecontent.SqlMapFile;
import com.cloud.coder.writecontent.StrtusFile;
import com.cloud.coder.writecontent.WSImplFile;

/**
 * 创建各层的文件夹以及各层的文件
 * @author cloud7
 *
 */
public class CreateFolderFiles {

	/**
	 * 第一步：创建各文件夹
	 * @param paramMap 封装model对象和pmcList对象
	 * @return
	 */
	public static String[] createFolder(Map paramMap) {
		String[] returnVal = new String[2];
		returnVal[0] = "0";
		try{
			String[] layer_arr = {"action","config","service","model","dao","job","webservice","sql","click","template","WEB-INF","deploy"};		//要创建各层的文件夹
			Coder coder = (Coder)paramMap.get("model");
			String filePath = coder.getFilePath();												//文件生成路径
			filePath = filePath + "/" + coder.getPackageName().replace(".", "/");
			for(String layer : layer_arr) {		
				if(layer.equals("click")) {														//click事件文件
					String folderPath = coder.getFilePath()+"/WebRoot/" + layer + "/" + coder.getNameSpace();
					FileUtil.createFolder(folderPath);
					writeFileByLayer(paramMap,layer,folderPath);
				} else if(layer.equals("template")) {											//excel模板文件
					String folderPath = coder.getFilePath()+"/WebRoot/" + layer + "/" + coder.getNameSpace();
					FileUtil.createFolder(folderPath);
					writeFileByLayer(paramMap,layer,folderPath);
				} else if(layer.equals("WEB-INF")) {											//jsp页面文件
					String folderPath = coder.getFilePath()+"/WebRoot/" + layer + "/pages/"+ coder.getNameSpace();
					FileUtil.createFolder(folderPath);
					writeFileByLayer(paramMap,layer,folderPath);
				} else if(layer.equals("deploy")) {												//部署文件
					FileUtil.createFolder(coder.getFilePath());
					writeFileByLayer(paramMap,layer,coder.getFilePath());
				} else {																		//java代码层
					if(layer.equals("job")) {
						if(coder.getCreateJob().equals("yes")) {
							FileUtil.createFolder(filePath+"/"+layer);							//创建文件夹
							writeFileByLayer(paramMap,layer,filePath);							//创建该层文件并向其写入代码内容
						}
					} else if(layer.equals("webservice")) {
						if(coder.getCreateWebService().equals("yes")) {
							FileUtil.createFolder(filePath+"/"+layer);							//创建文件夹
							writeFileByLayer(paramMap,layer,filePath);							//创建该层文件并向其写入代码内容
						}
					} else {
						FileUtil.createFolder(filePath+"/"+layer);								//创建文件夹
						writeFileByLayer(paramMap,layer,filePath);								//创建该层文件并向其写入代码内容
					}					
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			returnVal[0] = "1";
			returnVal[1] = "创建文件夹异常"+e.getMessage();
			return returnVal;
		}
		return returnVal; 
	}
	
	/**
	 * 第二步：写各层的文件
	 * @param paramMap
	 * @param layer
	 * @param filePath
	 * @return
	 */
	public static void writeFileByLayer(Map paramMap,String layer,String filePath) {
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");			
		if(layer.equals("action")) {														//表示是action层代码
			FileUtil.createFile(filePath+"/"+layer+"/"+coder.getPojo()+"Action.java");		//创建action文件，准备向其写入文件
			ActionFile.write(paramMap, filePath+"/"+layer+"/"+coder.getPojo()+"Action.java");
		} else if(layer.equals("config")) {
			FileUtil.createFile(filePath+"/"+layer+"/struts-"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo())+".xml");	//创建struts文件，准备向其写入文件
			StrtusFile.write(paramMap, filePath+"/"+layer+"/struts-"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo())+".xml");
			
			FileUtil.createFile(filePath+"/"+layer+"/"+coder.getPojo()+"_sqlmap.xml");
			SqlMapFile.write(paramMap, filePath+"/"+layer+"/"+coder.getPojo()+"_sqlmap.xml");
		} else if(layer.equals("service")) {
			FileUtil.createFile(filePath+"/"+layer+"/I"+coder.getPojo()+"Service.java");	//创建service接口文件，准备向其写入文件
			ServiceFile.write(paramMap, filePath+"/"+layer+"/I"+coder.getPojo()+"Service.java");
			
			FileUtil.createFile(filePath+"/"+layer+"/"+coder.getPojo()+"ServiceImpl.java");	//创建service实现类文件，准备向其写入文件
			ServiceImplFile.write(paramMap, filePath+"/"+layer+"/"+coder.getPojo()+"ServiceImpl.java");
			
		} else if(layer.equals("model")) {
			FileUtil.createFile(filePath+"/"+layer+"/"+coder.getPojo()+".java");			//创建model文件，准备向其写入文件
			ModelFile.write(paramMap, filePath+"/"+layer+"/"+coder.getPojo()+".java");		//向文件里写入内容
		} else if(layer.equals("dao")) {
			FileUtil.createFile(filePath+"/"+layer+"/I"+coder.getPojo()+"DaoMybatis.java");	//创建Mybatis接口文件，准备向其写入文件
			DaoMybatisFile.write(paramMap, filePath+"/"+layer+"/I"+coder.getPojo()+"DaoMybatis.java");
			
			FileUtil.createFile(filePath+"/"+layer+"/"+coder.getPojo()+"DaoHibernate.java");//创建Hibernate文件，准备向其写入文件
			DaoHibernateFile.write(paramMap, filePath+"/"+layer+"/"+coder.getPojo()+"DaoHibernate.java");
			
			FileUtil.createFile(filePath+"/"+layer+"/"+coder.getPojo()+"DaoJDBC.java");		//创建JDBC文件，准备向其写入文件
			DaoJDBCFile.write(paramMap, filePath+"/"+layer+"/"+coder.getPojo()+"DaoJDBC.java");
		} else if(layer.equals("job")) {													//创建定时任务文件及代码
			FileUtil.createFile(filePath+"/"+layer+"/"+coder.getPojo()+"Job.java");
			JobFile.write(paramMap, filePath+"/"+layer+"/"+coder.getPojo()+"Job.java");
		} else if(layer.equals("webservice")) {												//创建webservice文件及代码
			FileUtil.createFile(filePath+"/"+layer+"/I"+coder.getPojo()+"WS.java");
			IWSFile.write(paramMap, filePath+"/"+layer+"/I"+coder.getPojo()+"WS.java");
			
			FileUtil.createFile(filePath+"/"+layer+"/"+coder.getPojo()+"WSImpl.java");
			WSImplFile.write(paramMap, filePath+"/"+layer+"/"+coder.getPojo()+"WSImpl.java");
			
		} else if(layer.equals("deploy")) {													//部署说明文件
			FileUtil.createFile(filePath + "/部署文件.txt");
			DepolyFile.write(paramMap, filePath + "/部署文件.txt");
			
		} else if(layer.equals("sql")) {														
			FileUtil.createFile(filePath+"/"+layer+"/"+coder.getPojo()+".sql");				//创建sql文件
			SqlFile.write(paramMap, filePath+"/"+layer+"/"+coder.getPojo()+".sql");
		} else if(layer.equals("click")) {
			FileUtil.createFile(filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "ExportClick.js");
			ClickExportJsFile.write(paramMap, filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "ExportClick.js");
			
			FileUtil.createFile(filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "FormClick.js");
			ClickFormJsFile.write(paramMap, filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "FormClick.js");
			
			FileUtil.createFile(filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "ImportClick.js");
			ClickImportJsFile.write(paramMap, filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "ImportClick.js");
			
			FileUtil.createFile(filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "ListClick.js");
			ClickListJsFile.write(paramMap, filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "ListClick.js");
			
		} else if(layer.equals("template")) {
			FileUtil.createFile(filePath+"/"+coder.getNameSpace() + ".xls");
			ExcelFile.write(paramMap, filePath+"/", coder.getNameSpace());
			
		} else if(layer.equals("WEB-INF")) {
			FileUtil.createFile(filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "Form.jsp");
			JspFormFile.write(paramMap, filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "Form.jsp");
			
			FileUtil.createFile(filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "Detail.jsp");
			JspDetailFile.write(paramMap, filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "Detail.jsp");
			
			FileUtil.createFile(filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "List.jsp");
			JspListFile.write(paramMap, filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "List.jsp");
			
			FileUtil.createFile(filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "Import.jsp");
			JspImportFile.write(paramMap, filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "Import.jsp");
			
			FileUtil.createFile(filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "Export.jsp");
			JspExportFile.write(paramMap, filePath+"/"+StringUtil.replaceFirstStr2LowerCase(coder.getPojo()) + "Export.jsp");
			
		} 
	}
}