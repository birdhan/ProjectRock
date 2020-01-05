package cn.it.ssh.action;

import java.io.File;

import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.it.ssh.entity.movie;
import cn.it.ssh.service.MovieService;

public class HomeAction extends ActionSupport {

	// 封装上传文件域的属性

	private File imageFile;
	// 封装上传文件类型的属性
	private String imageFileFileName;
	// 封装上传文件名的属性
	private String imageFileContentType;

	/*
	 * public MultipartFile getImageFile() { return imageFile; } public void
	 * setImageFile(MultipartFile imageFile) { this.imageFile = imageFile;
	 */
	/* } */

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageFileFileName() {
		return imageFileFileName;
	}

	public void setImageFileFileName(String imageFileFileName) {
		this.imageFileFileName = imageFileFileName;
	}

	public String getImageFileContentType() {
		return imageFileContentType;
	}

	public void setImageFileContentType(String imageFileContentType) {
		this.imageFileContentType = imageFileContentType;
	}

	private movie move;

	public movie getMove() {
		return move;
	}

	public void setMove(movie move) {
		this.move = move;
	}

	public MovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}

	private MovieService movieService;

	public String execute() throws Exception {
		/*
		 * File destFile = null; //获得图片保存的路径 String root =
		 * ServletActionContext.getServletContext().getRealPath("/upload");
		 * 
		 * try {
		 * 
		 * InputStream is = new FileInputStream(file); destFile = new
		 * File(root,this.getFileFileName());
		 * 
		 * FileUtils.copyFile(file, destFile);
		 * 
		 * //把图片写入到上面设置的路径里 OutputStream os = new FileOutputStream(destFile); byte[]
		 * buffer = new byte[400]; int length = 0 ; while((length = is.read(buffer))>0){
		 * os.write(buffer, 0, length); } FileImg fileImg=new
		 * FileImg(name,this.getFileFileName()); //把图片添加到数据库 imgServlet.AddImg(fileImg);
		 * is.close(); os.close(); } catch (Exception e) { e.printStackTrace(); } return
		 * "scce"; }
		 */
		// 将提交的数据添加到数据库中.
		/* product.setPdate(new Date()); */
		// product.setImage(image);

		// 将商品图片上传到服务器上.
		// 获得上传图片的服务器端路径.

		if (imageFile != null) {

			/*
			 * String path=ServletActionContext.getServletContext().getRealPath("/upload");
			 */
			String path = "D://upload";
			/*String path1 = " .//upload1";*/
			String name = UUID.randomUUID().toString().replace("-", "").toLowerCase();
			/* System.out.println(this.getImageFileFileName()); */

			String fileName = name + ".jpg";
			/* System.out.println(dilename); */
			System.out.println(imageFileFileName);
			/*
			 * String fileName=name.substring(this.getImageFileFileName().lastIndexOf("."));
			 */

			/* System.out.println(this.getImageFileFileName().lastIndexOf(".")); */
			/* System.out.println(fileName); */
			File file = new File(path, fileName);
			/*File file2 = new File(path1, fileName);*/
			/* File file=new File(fileName); */

			System.out.println(file.getAbsoluteFile());

			System.out.println(file.getPath());

			/*System.out.println(file2.getAbsoluteFile());

			System.out.println(file2.getPath());*/

			FileUtils.copyFile(imageFile, file);
			/*FileUtils.copyFile(imageFile, file2);*/

			move.setMovie_img(fileName);

		}

		/*
		 * // 创建文件类型对象: File diskFile = new File(path + "//" + uploadFileName); // 文件上传:
		 * FileUtils.copyFile(upload, diskFile);
		 * 
		 * move.setMovie_img("products/" + uploadFileName);
		 */

		this.movieService.save(move);
		return SUCCESS;
	}

}
