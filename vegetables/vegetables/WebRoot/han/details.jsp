<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="details" onclick="shouhui()">

	<div class="container datai">
		<div class="row">
			<div class="col-md-12">

				<img id="picimg"
					src="http://www.hanchunyang.com/vagetables/box_img2.jpg3f8a0621-d97f-42a2-883f-bf7c13278d3a.jpg"
					alt="" class="img-responsive imgpadding">

			</div>

			<div class="col-md-offset-3 col-md-3">
				<h4>
					<b style="color: white">菜品名称:</b><b id="dname"></b>
				</h4>
			</div>
			<div class="col-md-3">

				<h4>
					<b>售价:</b><b id="price"></b>
				</h4>
			</div>

			<div class="col-md-offset-3 col-md-3">

				<h4>
					<b>标题:</b><b id="title"></b>
				</h4>
			</div>

			<div class="col-md-3">

				<h4>
					<b>简要介绍:</b><b id="details"></b>
				</h4>
			</div>
			
			<div class="col-md-6 col-md-offset-3">
			
			
				<form action="AddOrderServlet" method="post" class="form-control">

					<input type="hidden" name="did" id="ddddid"> 
					
					<input type="submit" value="加入购物车" class="form-group btn-success" style="width: 100%;height: 100%;">
				</form>
			
			</div>

		</div>
	</div>

</div>