package com.cloud.base.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.Range;

public class FileUtil {
	
	/**
	 * 按路径读文本文件,按行读取
	 * @param path:文件的路径
	 * @return Object[] 1:String类型的内容 2:字节数组 
	 */
	public static Object[] readFileByPath(String path) {
		Object[] obj = new Object[2];
		File textFile = new File(path);	//找到要读的文件
		try {
			FileInputStream fis = new FileInputStream(textFile);
			InputStreamReader isr = new InputStreamReader(fis,"GBK");
			BufferedReader br = new BufferedReader(isr);
			String len = null;
			
			StringBuffer content = new StringBuffer();
			while((len = br.readLine())!= null) {
				content.append(len+"\n");
			}
			
			obj[0] = content.toString();			//文件的内容
			obj[1] = content.toString().getBytes();	//内容的字节数组
			
			isr.close();
			br.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			LoggerUtil.error(FileUtil.class, "没有找到要读取的"+path+"文件。 异常信息："+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LoggerUtil.error(FileUtil.class, "IOException异常信息："+e.getMessage());
			e.printStackTrace();
		}
		
		return obj;
	}
	
	/**
	 * 读取URL上的文件，读取方式是httpclient
	 * @param fileUrl：文件的URL地址
	 * @return Object[] 1:String类型的内容 2:文件流 3:字节数组
	 */
	public static Object[] readFileByURL(String fileUrl) {
		Object[] obj = new Object[3];
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(fileUrl);
		try {
			client.getParams().setContentCharset("GBK");	//设置编码
			client.executeMethod(get);
			obj[0] = get.getResponseBodyAsString();		//文件内容
			obj[1] = get.getResponseBody();				//得到字节数组
			obj[2] = get.getResponseBodyAsStream();		//文件流			
		} catch (HttpException e) {
			LoggerUtil.error(FileUtil.class, "读取文件异常。 异常信息："+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LoggerUtil.error(FileUtil.class, "IOException异常信息："+e.getMessage());
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 写文件内容
	 * @param filePath：要写入的文件路径
	 * @param content：要写的内容
	 */
	public static void writeFileByContent(String filePath, String content) {
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			InputStream is = new ByteArrayInputStream(content.getBytes("UTF-8"));
			byte by[] = new byte[is.available()];
			is.read(by);			
			fos.write(by);
			is.close();
			fos.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过流来写文件
	 * @param filePath
	 * @param is
	 */
	public static void writeFileByInputStream(String filePath,InputStream is) {
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			byte by[] = new byte[is.available()];
			is.read(by);			
			fos.write(by);
			is.close();
			fos.close();			
			LoggerUtil.info(FileUtil.class, "文件写入成功");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 复制文件，默认不删除原文件
	 * @param src
	 * @param dest
	 */
	public static void copyFile(String src, String dest) {
		copyFile(src,dest,false);	//默认表示不删除原文件，即复制功能。若第三个参数为true,表示剪切功能
	}
	
	/**
	 * 复制文件
	 * @param src：源文件
	 * @param dest：目的文件
	 * @param copyType: 是否删除原文件
	 */
	public static void copyFile(String src, String dest, boolean copyType) {
		String src_extendName = src.substring(src.lastIndexOf(".")+1);
		String dest_extendName = dest.substring(dest.lastIndexOf(".")+1);
		
		if(src_extendName.toLowerCase().equals(dest_extendName.toLowerCase())) {
			try {
				File old_file = new File(src);
				FileInputStream fi = new FileInputStream(old_file);
				FileOutputStream fo = new FileOutputStream(dest);
				byte by[] = new byte[fi.available()];
				
				fi.read(by);			
				fo.write(by);
				fi.close();
				fo.close();
				
				if(copyType) {
					boolean delFlag = new File(src).delete();
					if(delFlag) {
						LoggerUtil.info(FileUtil.class, "删除文件"+src+"成功");
					} else {
						LoggerUtil.error(FileUtil.class, "删除文件"+src+"失败");
					}
				}
				
				LoggerUtil.info(FileUtil.class, "文件复制成功");
			} catch (FileNotFoundException e) {
				LoggerUtil.error(FileUtil.class, "没有找到文件");
			} catch (IOException ioe) {
				LoggerUtil.error(FileUtil.class, "IOException异常，信息"+ioe.getMessage());
			}
		} else {
			LoggerUtil.error(FileUtil.class, "文件格式不一致，无法复制");
		}		
	}
	
	/**
	 * 创建文件
	 * @param filePath
	 * @return
	 */
	public static boolean createFile(String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		if(!file.isFile()) {			//当前路径不是一个文件夹路径
			try {
				flag = file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				flag = false;
				LoggerUtil.info(FileUtil.class, filePath+"创建失败");
				return flag;
			}	
			LoggerUtil.info(FileUtil.class, filePath+"文件创建成功");
		} else {
			LoggerUtil.info(FileUtil.class, filePath+"不是一个文件路径或已经存在该路径");
		}
		return flag;
	} 
	
	/**
	 * 创建文件夹
	 * @param filePath
	 * @return
	 */
	public static boolean createFolder(String filePath) {
		boolean flag = false;
		File folder = new File(filePath);
		if(!folder.isDirectory()) {			//当前路径不是一个文件夹路径
			flag = folder.mkdirs();	
			LoggerUtil.info(FileUtil.class, filePath+"文件夹创建成功");
		} else {
			LoggerUtil.info(FileUtil.class, filePath+"不是一个文件夹路径或已经存在该路径");
		}
		return flag;
	}
	
	/**
	 * 删除文件夹
	 * @param folderPath
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
			LoggerUtil.info(FileUtil.class, "删除"+folderPath+"文件夹");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除文件夹下的所有文件
	 * @param path
	 * @return
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		LoggerUtil.info(FileUtil.class, "已清空"+path+"文件夹");
		return flag;
	}
	
	/**
	 * 创建bat文件
	 * @param path
	 * @param content
	 */
	public static void writeBatFile(String path , String content) {
		try {
			File FileName = new File(path);
			FileOutputStream fileOut = new FileOutputStream(FileName);
			DataOutputStream fou = new DataOutputStream(fileOut);
			fou.write(content.getBytes());
			fou.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("文件错误");
		}
	}
	
	/**
	 * 实现对word读取和修改操作
	 * 
	 * @param filePath
	 *            word模板路径和名称
	 * @param map
	 *            待填充的数据，从数据库读取
	 * @param outputFile
	 *            保存的路径和名称
	 */
	public static void readwriteWord(String filePath, Map<String, String> map,String outputFile) {
		try {
			// 读取word模板
			FileInputStream in = new FileInputStream(new File(filePath));
			HWPFDocument hdt = new HWPFDocument(in);
			// 读取word文本内容
			Range range = hdt.getRange();
			// 替换文本内容
			for (Map.Entry<String, String> entry : map.entrySet()) {
				range.replaceText("${" + entry.getKey() + "}", entry.getValue());
			}
			ByteArrayOutputStream ostream = new ByteArrayOutputStream();
			FileOutputStream out = new FileOutputStream(outputFile, true);
			hdt.write(ostream);
			out.write(ostream.toByteArray());
			out.flush();
			out.close();
			ostream.flush();
			ostream.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// ======================输出文件流下载方式：==========================　　
	/**
	 * 实现对word读取和修改操作
	 * 
	 * @param response
	 *            响应,设置生成的文件类型,文件头编码方式和文件名,以及输出
	 * @param filePath
	 *            word模板路径和名称
	 * @param map
	 *            待填充的数据，从数据库读取
	 */
	public static void readwriteWord(HttpServletResponse response,String filePath, Map<String, String> map) {
		// 读取word模板文件
		FileInputStream in;
		HWPFDocument hdt = null;
		try {
			in = new FileInputStream(new File(filePath));
			hdt = new HWPFDocument(in);			
			Range range = hdt.getRange();

			for (Map.Entry<String, String> entry : map.entrySet()) {
				range.replaceText("${" + entry.getKey() + "}", entry.getValue());
			}
			// 输出word内容文件流，提供下载
			response.reset();
			response.setContentType("application/x-msdownload");
			String fileName = "" + System.currentTimeMillis() + ".doc";
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			ByteArrayOutputStream ostream = new ByteArrayOutputStream();
			OutputStream os = null;
			os = response.getOutputStream();
			hdt.write(ostream);
			os.write(ostream.toByteArray());
			os.flush();
			os.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	/**
	 * 得到某文件的扩展名方法
	 * @param fileName
	 * @return
	 */
	public static String getExtendName(String fileName) {
		String extendName = "";
		if(fileName.lastIndexOf(".") != -1) {
			extendName = fileName.substring(fileName.lastIndexOf("."));
		} else {
			extendName = fileName;
		}
		return extendName;
	} 
}
