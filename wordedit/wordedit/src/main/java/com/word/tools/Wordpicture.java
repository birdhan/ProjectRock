package com.word.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.core.io.ClassPathResource;

import com.aspose.words.Document;
import com.aspose.words.ImageSaveOptions;
import com.aspose.words.SaveFormat;

import sun.misc.BASE64Encoder;

public class Wordpicture {
	

	public String wordpicture(String wordname,String pngname) {
		 File file1 = new File("D:\\wordfile\\word\\"+wordname);
		  FileInputStream inputStream1;
		  try {
				inputStream1 = new FileInputStream(file1);
				List<BufferedImage> wordToImg = wordToImg(inputStream1);
				BufferedImage mergeImage = mergeImage(false,wordToImg);
				File outputfile  = new File("D:\\wordfile\\png\\"+pngname);
				ImageIO.write(mergeImage,"png",outputfile);
				File outputfile1  = new File("D:\\wordfile\\img\\"+pngname);
				ImageIO.write(wordToImg.get(0),"png",outputfile1);
			} catch (Exception e) {
				e.printStackTrace();
			}

		 
		 return "success";
	}
	
	
	public String deletewordfile(String name) {
		File file1=new File("D:\\wordfile\\img\\"+name+".png");
		file1.delete();
		File file2=new File("D:\\wordfile\\png\\"+name+".png");
		file2.delete();
		File file3=new File("D:\\wordfile\\word\\"+name+".docx");
		file3.delete();
		return "success";
	}
	
	
    private static List<String> wordList = new ArrayList<>();
    static {
        wordList.add("TXT");
        wordList.add("DOC");
        wordList.add("DOCX");
        
    }
 
 
    public static String parseFileToBase64_PNG(InputStream inputStream, int pageNum, String ext) throws Exception {
        // String png_base64 = "";
 
        List<BufferedImage> bufferedImages = new ArrayList<>();
        BufferedImage image = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
        if (wordList.contains(ext.toUpperCase())) {
            bufferedImages = wordToImg(inputStream);
            image = mergeImage(false, bufferedImages);
            ImageIO.write(image, "png", baos);//写入流中
        } 
        byte[] bytes = baos.toByteArray();//转换成字节
        BASE64Encoder encoder = new BASE64Encoder();
        /*BASE64Decoder decoder = new BASE64Decoder();*/
        String png_base64 = encoder.encodeBuffer(bytes).trim();
        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
 
        return png_base64;
 
    }
 
 
    /**
     * @Description: 验证aspose.word组件是否授权：无授权的文件有水印标记
     */
    public static boolean isWordLicense() {
        boolean result = false;
        try {
 
            ClassPathResource resource = new ClassPathResource("license.xml");
            InputStream inputStream = resource.getInputStream();
 
            com.aspose.words.License license = new com.aspose.words.License();
            license.setLicense(inputStream);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
 
     
    /**
     * @Description: word和txt文件转换图片
     */
    private static List<BufferedImage> wordToImg(InputStream inputStream) throws Exception {
       /*if (!isWordLicense()) {
            return null;
        }*/
        try {
            /*long old = System.currentTimeMillis();*/
            Document doc = new Document(inputStream);
            ImageSaveOptions options = new ImageSaveOptions(SaveFormat.PNG);
            options.setPrettyFormat(true);
            options.setUseAntiAliasing(true);
            options.setUseHighQualityRendering(true);
            int pageCount = doc.getPageCount();
            /*if (pageCount > pageNum) {//生成前pageCount张
                pageCount = pageNum;
            }*/
            List<BufferedImage> imageList = new ArrayList<>();
            for (int i = 0; i < pageCount; i++) {
                OutputStream output = new ByteArrayOutputStream();
                options.setPageIndex(i);
 
                doc.save(output, options);
                ImageInputStream imageInputStream = javax.imageio.ImageIO.createImageInputStream(parse(output));
                imageList.add(javax.imageio.ImageIO.read(imageInputStream));
 
            }
            return imageList;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
 
    
 
 
    /**
     * 合并任数量的图片成一张图片
     *
     * @param isHorizontal true代表水平合并，fasle代表垂直合并
     * @param imgs         待合并的图片数组
     * @return
     * @throws IOException
     */
    public static BufferedImage mergeImage(boolean isHorizontal, List<BufferedImage> imgs) throws IOException {
        // 生成新图片
        BufferedImage destImage = null;
        // 计算新图片的长和高
        int allw = 0, allh = 0, allwMax = 0, allhMax = 0;
        // 获取总长、总宽、最长、最宽
        for (int i = 0; i < imgs.size(); i++) {
            BufferedImage img = imgs.get(i);
            allw += img.getWidth();
 
            if (imgs.size() != i + 1) {
                allh += img.getHeight() + 5;
            } else {
                allh += img.getHeight();
            }
 
 
            if (img.getWidth() > allwMax) {
                allwMax = img.getWidth();
            }
            if (img.getHeight() > allhMax) {
                allhMax = img.getHeight();
            }
        }
        // 创建新图片
        if (isHorizontal) {
            destImage = new BufferedImage(allw, allhMax, BufferedImage.TYPE_INT_RGB);
        } else {
            destImage = new BufferedImage(allwMax, allh, BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D g2 = (Graphics2D) destImage.getGraphics();
        g2.setBackground(Color.LIGHT_GRAY);
        g2.clearRect(0, 0, allw, allh);
        /*g2.setPaint(Color.RED);*/
 
        // 合并所有子图片到新图片
        int wx = 0, wy = 0;
        for (int i = 0; i < imgs.size(); i++) {
            BufferedImage img = imgs.get(i);
            int w1 = img.getWidth();
            int h1 = img.getHeight();
            // 从图片中读取RGB
            int[] ImageArrayOne = new int[w1 * h1];
            ImageArrayOne = img.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 逐行扫描图像中各个像素的RGB到数组中
            if (isHorizontal) { // 水平方向合并
                destImage.setRGB(wx, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            } else { // 垂直方向合并
                destImage.setRGB(0, wy, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            }
            wx += w1;
            wy += h1 + 5;
        }
 
 
        return destImage;
    }
 
 
    //outputStream转inputStream
    public static ByteArrayInputStream parse(OutputStream out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }
 
}


