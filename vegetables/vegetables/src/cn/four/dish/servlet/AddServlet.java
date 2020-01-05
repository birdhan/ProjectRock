package cn.four.dish.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import cn.four.dish.domain.Dish;
import cn.four.dish.service.DishService;
import cn.four.tools.util.CommonUtils;
import cn.four.tools.util.Upload;

public class AddServlet extends HttpServlet {

	private File fileimg;

	public File getFileimg() {
		return fileimg;
	}

	public void setFileimg(File fileimg) {
		this.fileimg = fileimg;
	}

	private static final long serialVersionUID = 1L;
	DishService dishservice = new DishService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 流上传图片

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 2、创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);

		List<String> pList = new ArrayList<String>();

		List<FileItem> list = null;
		String filename = null;
		try {
			list = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (FileItem item : list) {
			// 如果fileitem中封装的是普通输入项的数据
			if (item.isFormField()) {
				String name = item.getFieldName();
				String value = item.getString("UTF-8");
				pList.add(value);
				// 将非文件的其他参数放到一个list中，后面可以顺序的去取到
				System.out.println("name" + name + "value" + value);
				continue;
			} else {
				// 如果fileitem中封装的是上传文件
				InputStream stream = item.getInputStream();// 上传文件需要的文件流参数
				String filename1 = item.getName(); // 上传文件需要的参数
				String dilename=UUID.randomUUID().toString();
				
				filename=filename1+dilename+".jpg";

				// String
				// savepath=getServletContext().getRealPath("/WEB-INF/upload");

				String savepath = "D://img"; 
				
				//String savepath=getServletContext().getRealPath("/WEB-INF/upload");
				//String uu = UUID.randomUUID().toString();
				//String picname = uu + ".jpg";

				File path = new File(savepath); // 这个要自己写具体的路径，是需要上传文件需要的参数

				Upload.uploadFile(stream, path, filename); // 调用工具类方法

				// FileUtils.copyFile(item, path);

				if (filename == null || filename.trim().equals("")) { // 判空处理
				}
				continue;
			}
		}
		// 开始顺序取非文件参数
		/*String project_id = pList.get(0);
		String contract_name = pList.get(1);
		String customer_company_name = pList.get(2);
		String technical_director = pList.get(3);
		String date = pList.get(4);
		String money = pList.get(5);*/
		
		
		Dish dish = new Dish();
		String did=UUID.randomUUID().toString();
		dish.setDid(did);
		
		dish.setDishname(pList.get(0));
		
		dish.setTitle(pList.get(1));
		
		String string = pList.get(2);
		Double valueOf = Double.valueOf(string);
		dish.setPrice(valueOf);
		
		dish.setType(pList.get(3));
		
		String string2 = pList.get(5);
		Integer valueOf2 = Integer.valueOf(string2);
		dish.setHeat(valueOf2);
		
		dish.setDetails(pList.get(6));
		String contract_appendices = filename;
		dish.setPicture(contract_appendices);
		
		dish.setAnimation(pList.get(4));
		
		
		
		

		dishservice.addDish(dish);
		request.getRequestDispatcher("han/index.jsp")
				.forward(request, response);

	}

}
