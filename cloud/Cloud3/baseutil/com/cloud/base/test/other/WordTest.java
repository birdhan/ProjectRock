package com.cloud.base.test.other;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;

public class WordTest {

	private static final String FILE_NAME = "e:/1.doc";

	/**
	 * @param args
	 */

	public static void main(String[] args) throws Exception {
		try {
			WordTest rtfMain = new WordTest();
			rtfMain.createRTFContext(FILE_NAME);

			// rtfMain.redRTFContext(FILE_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createRTFContext(String path) throws DocumentException,
			IOException {
		Document document = new Document(PageSize.A4);
		RtfWriter2.getInstance(document, new FileOutputStream(path));
		document.open();

		// 设置中文字体
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
				"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

		// 标题字体风格
		Font titleFont = new Font(bfChinese, 12, Font.BOLD);

		// 正文字体风格
		Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
		Paragraph title = new Paragraph("标题");

		// 设置标题格式对齐方式
		title.setAlignment(Element.ALIGN_CENTER);
		title.setFont(titleFont);
		document.add(title);
		String contextString = "中华人民共和sdfsadfsadfdfasafasff国中华人民共和sdfsadfsadfdfasafasff国中华人民共和sdfsadfsadfdfasafasff国中华人民共和sdfsadfsadfdfasafasff国中华人民共和sdfsadfsadfdfasafasff国中华人民共和sdfsadfsadfdfasafasff国中华人民共和sdfsadfsadfdfasafasff国";
		Paragraph context = new Paragraph(contextString);

		// 正文格式左对齐
		context.setAlignment(Element.ALIGN_LEFT);
		context.setFont(contextFont);

		// 离上一段落（标题）空的行数
		context.setSpacingBefore(20);

		// 设置第一行空的列数
		context.setFirstLineIndent(20);
		document.add(context);

		Table t = new Table(1);
		t.setBorderColor(new Color(220, 255, 100));
		t.setPadding(5);
		t.setSpacing(5);
		t.setBorderWidth(0);
		
		Cell c1 = new Cell("header1");
		c1.setBorderWidth(0);
		c1.setHeader(true);
		t.addCell(c1);
		c1 = new Cell("Header2");
		t.addCell(c1);

		t.endHeaders();
		
		t.addCell("1.1");
		t.addCell("1.2");
		t.addCell("1.3");
		t.addCell("1.4");
		document.add(t);

		// //在表格末尾添加图片
		// Image png = Image.getInstance("c:/search_btn.png");
		// document.add(png);
		document.close();
	}

	public void redRTFContext(String path) throws Exception {
		Document document = new Document(PageSize.A4);
		RtfWriter2.getInstance(document, new FileOutputStream(path));
		document.open();
		System.out.println(document.getHtmlStyleClass());
	}

}
