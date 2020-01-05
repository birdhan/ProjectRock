package com.cloud.base.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * <p>
 * 用于导入导出Excel
 * 
 * @author JiahaoWang
 * 
 */
public class ExcelUtil {

	public static void WriteExcel(String templatePath, int startRow,
			List dataList, OutputStream ops) throws BiffException, IOException,
			RowsExceededException, WriteException {


		Workbook wb = Workbook.getWorkbook(new File(templatePath));
		ByteArrayOutputStream targetFile = new ByteArrayOutputStream();
		WritableWorkbook wwb = Workbook.createWorkbook(targetFile, wb);
		
		WritableSheet wws = wwb.getSheet(0);
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10,WritableFont.NO_BOLD);
		WritableCellFormat cellFormat = new WritableCellFormat(font,NumberFormats.TEXT);
		cellFormat.setWrap(true);
		cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
		cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);		
		
		int taskStartRow = startRow;
		for (int i = 0; i < dataList.size(); i++) {
			Object[] objs = (Object[]) dataList.get(i);
			for (int j = 0; j < objs.length; j++) {
				wws.addCell(new Label(j, taskStartRow, objs[j].toString(), cellFormat));
			}
			taskStartRow++;
		}
		wwb.write();
		wwb.close();
		wb.close();
		targetFile.writeTo(ops);
		targetFile.close();
	}

	/**
	 * 根据传输的Excel内容取得第一个sheet中的内容
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static Map ReadExcelToMap(final InputStream is) throws Exception {
		return ReadExcelToMap(is, 0);
	}

	/**
	 * 根据传输的Excel内容取得第sheetNum个sheet中的内容
	 * 
	 * @param is
	 * @param sheetNum
	 * @return Map
	 */
	public static Map ReadExcelToMap(final InputStream is, final int sheetNum)
			throws Exception {
		Map returnMap = new HashMap();
		Map rowMap = new TreeMap();
		// 工作表行�?
		int rowNum;
		// 工作表列�?
		int colNum;
		try {
			// 根据输入流创建Workbook对象
			Workbook wb = Workbook.getWorkbook(is);
			// 获得第sheetNum个工作表（第0个工作表呗）
			Sheet sheet = wb.getSheet(sheetNum);

			// sheet.getName();
			// 获得工作表行�?
			rowNum = sheet.getRows();
			// 获得工作表列�?
			colNum = sheet.getColumns();
			// 根据行数循环
			for (int i = 0; i < rowNum; i++) {
				rowMap = new HashMap();
				// 根据列数循环
				for (int j = 0; j < colNum; j++) {
					// System.out.println("i=" + i + "j=" + j);
					// 放入rowMap对象
					rowMap.put(String.valueOf(j), sheet.getCell(j, i)
							.getContents());// 把当前行每一列的内容放入rowMap�?
				}
				// 将rowMap对象放入returnMap对象
				returnMap.put(String.valueOf(i), rowMap);// �?��把所有的rowMan放入整个的excel的map�?
			}

		}
		// 捕捉异常
		catch (BiffException biffe) {
			biffe.printStackTrace();
			throw new RuntimeException(biffe.toString());
		}
		return returnMap;
	}

	/**
	 * @param dirPath
	 *            :文件要保存的目录
	 * @param fileName
	 *            :文件要保存的文件名
	 * @param sheetTitle
	 *            :报表名称
	 * @param title
	 *            :报表的题目
	 * @param columnTitle
	 *            :列名集合（类型为List）
	 * @param text
	 *            :正文数据（类型为两级嵌套List） return boolean
	 */
	public static boolean writeExcelTemplate(String dirPath, String fileName,String sheetTitle, List columnTitle) {
		WritableWorkbook workBook = null;
		WritableSheet sheet = null;
		if (dirPath == null || "".equals(dirPath) || fileName == null
				|| "".equals(fileName)) {
			System.out.println("建立excel文件失败：路径或文件名为空");
			return false;
		}
		File filePath = new File(dirPath);
		{
			if (!filePath.exists()) {
				// 如果文件要保存的目录不存在则产生该目录
				if (!filePath.mkdir()) {
					System.out.println("建立excel文件失败：无法建立该目录");
					return false;
				}
			}
		}
		// 在该目录下产生要保存的文件名
		String excelPath = dirPath + "/" + fileName + ".xls";
		File excelFile = new File(excelPath);
		// 以下开始输出到EXCEL
		try {
			if (!excelFile.exists()) {
				if (!excelFile.createNewFile()) {
					System.out.println("建立excel文件失败：建立excel文件发生异常");
					return false;
				}
			}
			/** **********创建工作簿************ */
			workBook = Workbook.createWorkbook(excelFile);
			/** **********创建工作表************ */
			if ("".equals(sheetTitle) || sheetTitle == null) {
				sheet = workBook.createSheet("Sheet1", 0);
			} else {
				sheet = workBook.createSheet(sheetTitle, 0);
			}
			sheet.setRowView(0,1000);
			// SheetSettings sheetSet = sheet.getSettings();
			// sheetSet.setProtected(false);
			/** ************设置单元格字体************** */
			WritableFont headFont = new WritableFont(WritableFont.createFont("宋体"), 15,WritableFont.NO_BOLD);
			/** ************以下设置几种格式的单元格************ */
			// 用于表头
			WritableCellFormat wcf_head = new WritableCellFormat(headFont,NumberFormats.TEXT);
			wcf_head.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
			wcf_head.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
			wcf_head.setAlignment(Alignment.CENTRE); // 文字水平对齐
			wcf_head.setWrap(false); // 文字是否换行
			wcf_head.setBackground(Colour.PALE_BLUE);

			// 用于正文居中
//			WritableCellFormat wcf_center = new WritableCellFormat(normalFont);
//			wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
//			wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
//			wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
//			wcf_center.setWrap(false); // 文字是否换行

			/** ************单元格格式设置完成****************** */

			/** ***************以下是报表的内容********************* */
			// 合并单元格设置excel的题目
			int x = 0;
//			if ("".equals(title) || title == null) {
//				title = "";
//				x = 0;
//			} else {
//				if (columnTitle != null && columnTitle.size() > 0) {
//					sheet.mergeCells(0, 0, columnTitle.size() - 1, 0);
//					sheet.addCell(new Label(0, 0, title, wcf_head));
//					x = 1;
//				} else {
//					sheet.addCell(new Label(0, 0, title, wcf_head));
//					x = 1;
//				}
//			}
			// 设置列名
			if (columnTitle != null && columnTitle.size() > 0) {
				for (int i = 0; i < columnTitle.size(); i++) {
					sheet.addCell(new Label(i, x, String.valueOf(columnTitle.get(i)), wcf_head));
				}
				x = x + 1;
			}
			/** **********以上所写的内容都是写在缓存中的，下一句将缓存的内容写到文件中******** */
			workBook.write();
		} catch (Exception e) {
			System.out.println("建立excel文件失败：" + e.getMessage());
			return false;
		} finally {
			/** *********关闭文件************* */
			try {
				if (workBook != null) {
					workBook.close();
				}
			} catch (Exception ex) {
				System.out.println("关闭文件流失败：" + ex.getMessage());
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {

		List columnList = new ArrayList();
		for (int i = 0; i < 5; i++) {
			columnList.add("列"+i);
		}
		writeExcelTemplate("d:/","1","模板", columnList);
	}

}
