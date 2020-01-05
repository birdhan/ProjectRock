package com.cloud.base.test.other;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlToken;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;

public class WordPic {
	
	
	public void replaceTextToImage(Map<String, String> mapImage, int width,int height) {
		String templatePath = "c:/123.docx";
		XWPFDocument document = null;
		try {
			document = new XWPFDocument(POIXMLDocument.openPackage(templatePath));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<XWPFParagraph> listParagraphs = document.getParagraphs();
		System.out.println("listParagraphs:"+listParagraphs.size());
		for (int i = 0; i < listParagraphs.size(); i++) {
			for (Entry<String, String> entry : mapImage.entrySet()) {
				System.out.println("内容："+listParagraphs.get(i).getText().trim());
				System.out.println("entry.getKey():"+entry.getKey());
				if (listParagraphs.get(i).getText().trim().indexOf(entry.getKey()) != -1) {
					CTInline inline = listParagraphs.get(i).createRun().getCTR().addNewDrawing().addNewInline();
					try {
						insertPicture(document,entry.getValue(), inline, width, height);
					} catch (InvalidFormatException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void insertPicture(XWPFDocument document,String filePath, CTInline inline, int width,int height) throws InvalidFormatException, FileNotFoundException {
		try {
			String ind = document.addPictureData(new FileInputStream(filePath), 5);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			System.out.println("exception");
		}
		int id = document.getAllPictures().size() - 1;
		System.out.println("id:"+id);
		final int EMU = 9525;
		width *= EMU;
		height *= EMU;
		String blipId = document.getAllPictures().get(id)
				.getPackageRelationship().getId();
		System.out.println("blipId:"+blipId);
		String picXml = ""
				+ "<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">"
				+ "   <a:graphicData  uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">"
				+ "      <pic:pic  xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">"
				+ "         <pic:nvPicPr>" + "            <pic:cNvPr id=\""
				+ id
				+ "\" name=\"Generated\"/>"
				+ "            <pic:cNvPicPr/>"
				+ "         </pic:nvPicPr>"
				+ "         <pic:blipFill>"
				+ "            <a:blip r:embed=\""
				+ blipId
				+ "\"  xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>"
				+ "            <a:stretch>"
				+ "               <a:fillRect/>"
				+ "            </a:stretch>"
				+ "         </pic:blipFill>"
				+ "         <pic:spPr>"
				+ "            <a:xfrm>"
				+ "               <a:off x=\"0\" y=\"0\"/>"
				+ "               <a:ext cx=\""
				+ width
				+ "\" cy=\""
				+ height
				+ "\"/>"
				+ "            </a:xfrm>"
				+ "            <a:prstGeom prst=\"rect\">"
				+ "               <a:avLst/>"
				+ "            </a:prstGeom>"
				+ "         </pic:spPr>"
				+ "      </pic:pic>"
				+ "   </a:graphicData>" + "</a:graphic>";
		inline.addNewGraphic().addNewGraphicData();
		XmlToken xmlToken = null;
		try {
			xmlToken = XmlToken.Factory.parse(picXml);
		} catch (XmlException xe) {
			xe.printStackTrace();
		}
		inline.set(xmlToken);
		inline.setDistT(0);
		inline.setDistB(0);
		inline.setDistL(0);
		inline.setDistR(0);
		CTPositiveSize2D extent = inline.addNewExtent();
		extent.setCx(width);
		extent.setCy(height);
		CTNonVisualDrawingProps docPr = inline.addNewDocPr();
		docPr.setId(id);
		docPr.setName("IMG_" + id);
		docPr.setDescr("IMG_" + id);
	}
	
	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("${picpath}", "c:/123.jpg");
		
		WordPic wp = new WordPic();
		wp.replaceTextToImage(map, 100, 100);
	}

}
