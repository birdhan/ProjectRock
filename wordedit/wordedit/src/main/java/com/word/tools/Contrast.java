package com.word.tools;

import java.io.File;
import java.io.FileInputStream;

import com.ljm.utils.DocumentUtil;
import com.ljm.utils.SimilarityUtil;

public class Contrast {
/*
	public static void main(String[] args) throws Exception {
		File file1 = new File("D:\\test1.docx");
		FileInputStream inputStream1 = new FileInputStream(file1);
		File file2 = new File("D:\\test2.docx");
		FileInputStream inputStream2 = new FileInputStream(file2);
		// DocumentUtil读取word或者pdf的工具类 通过流和文件名字获取字符串
		String text1 = DocumentUtil.readWordFile(inputStream1, file1.getName());
		String text2 = DocumentUtil.readWordFile(inputStream2, file2.getName());
		// SimilarityUtil统计工具类 里有不同的方法 计算相似度 详情看方法注释
		System.out.println(String.format("%.4f", SimilarityUtil.simpleTextSimilarity(text1, text2) * 100) + "%");
		// 统计方法的返回类型都是double 工具包在com.ljm.utils下
	}*/


	public String startcontrast(String wordurel1, String wordurel2) {

		File file1 = new File(wordurel1);
		File file2 = new File(wordurel2);
		FileInputStream inputStream1;
		FileInputStream inputStream2;
		String text1;
		String text2;
		String result;
		try {
			inputStream1 = new FileInputStream(file1);
			inputStream2 = new FileInputStream(file2);
			text1 = DocumentUtil.readWordFile(inputStream1, file1.getName());
			text2 = DocumentUtil.readWordFile(inputStream2, file2.getName());
			result=String.format("%.4f",SimilarityUtil.simpleTextSimilarity(text1,text2)*100)+"%";
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "error1";
		} 
		
	}
}
