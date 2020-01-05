package com.word.html;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class Client {

    
    public String htmlword(String wordname,String htmlname) {
    	 File source = new File("D:\\wordfile\\word\\"+wordname);
         if (!source.exists() || !source.isFile()) {
             System.out.println("file not exist");
         }
         WordHtmlConverter wordHtmlConverter = null;
         if (source.getPath().endsWith("doc") || source.getPath().endsWith("DOC")) {
           /*  wordHtmlConverter = new Word03ToHtmlAchieve();*/
         } else if (source.getPath().endsWith("docx") || source.getPath().endsWith("DOCX")) {
             wordHtmlConverter = new Word07ToHtmlAchieve();
         } else {
             System.out.println("word file suffix not support");

         }
         ConverterContext context = new ConverterContext(wordHtmlConverter);
         try {
             context.convertToHtml(source, "D:\\wordfile\\html\\", htmlname);
         } catch (ParserConfigurationException e) {
             e.printStackTrace();
         } catch (TransformerException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
    	
    	return "success";
    }
    
    
}
