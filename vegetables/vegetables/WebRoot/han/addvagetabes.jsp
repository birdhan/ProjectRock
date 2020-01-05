<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="head.jsp"%>
<style>
.addvage {
	color: #ECB731;
	border: 2px solid #ECB630;
}
</style>
<body>
	<!-- header -->
	<%@include file="header.jsp"%>

	<!--short codes start here-->
	<div class="main-grid-one5" id="six">
		<div class="contact">
			<div class="temp-heading">
				<h3 class="main-head">添加菜品</h3>
			</div>
			<div class="contact-1">
				<div class="container">
					<div class="contact-1-main">
						<div class="contact-right wow bounceInUp" data-wow-delay="0.3s">
							<h3>美味信息</h3>
							<form action="${pageContext.request.contextPath }/AddServlet" method="post" enctype="multipart/form-data">
								<p>Name</p>
								<input type="text" placeholder="菜品名称" name="dishname"  required="" />
								<p>title</p>
								<input type="text" placeholder="标题" name="title"/>
								<p>price</p>
								<input type="text" placeholder="售价" name="price" required="" />
								<p>type</p>
								<select required="" style="width: 98%; padding: 6px;" name="type">
								<option value="null">-----</option>
								<option value="meiwei">美味佳肴</option>
								<option value="cantian">餐后甜品</option>
								<option value="zhushi">叫点主食</option>
								</select>
								
								<p>animation</p>
								<select required="" style="width: 98%; padding: 6px;" name="animation">
								<option value="null">-----</option>
								<option value="bounceInLeft">左侧插入</option>
								<option value="bounceInRight">右侧插入</option>
								<option value="bounceInUp">自上向下</option>
								<option value="bounceInDown">自下向上</option>
								</select>
								<p>heat</p>
								<input type="text" placeholder="热度排行" name="heat" required="" />
								<p>details</p>
								<textarea placeholder="简要介绍" name="details" required=""> </textarea>
								<p>photo</p>
								<input type="file" placeholder="图片" required="" id="imageFile"
									name="picture" accept="image/jpeg" />
									<br>
									<p>图片效果展示</p>
								<p id="previewImage" style="width: 400px; height: 300px;">
									<image id="imangid" class="img-thumbnail" src="images/a1.jpg" />
								</p>

								<input type="submit" value="提交">
<br><br><br>

							</form>

						</div>

					</div>

				</div>
			</div>
		</div>
	</div>


	<!-- footer -->
	<%@include file="footer.jsp"%>
</body>
</html>