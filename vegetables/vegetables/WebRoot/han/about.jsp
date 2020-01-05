<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="head.jsp"%>
<style>
.about1 {
	color: #ECB731;
	border: 2px solid #ECB630;
}

.buhuan {
	color: #ECB731;
}
</style>
<body>
	<%@include file="header.jsp"%>

	<!-- about start here-->
	<div class="main-grid-one2" id="two">
		<div class="about">
			<div class="temp-heading">
				<h3 class="main-head">我的午餐</h3>
			</div>
			<div class="about-1">
				<div class="container">
					<!--enjoy-->
					<div class="about-bottom">
						<div class="enjoy-food">
							<div class="container">
								<h3>Enjoy Your Lunch</h3>
							</div>
						</div>
					</div>


					<div class="team">



						<!--portfolio-->
						<div class="portfolio-1">
							<div class="container">
								<div class="portfolio-1-main">




									<c:forEach items="${olist }" var="olist">

										<div class="col-md-4 port-right wow ${olist.animation }"
											data-wow-delay="0.3s">
											<ul class="portfolio-grid">
												<li>
													<h5>${olist.dishname }</h5>
													<p class="numberprice">售价：${olist.price }</p>
													<p>title：${olist.title }</p>
													<p>details：${olist.details }</p></li>
												<li><img class="top-grid"
													src="http://www.hanchunyang.com/vagetables/${olist.picture }"
													alt="" style="width: 189px;height: 189px;" />
												</li>
											</ul>
										</div>

									</c:forEach>

									<script type="text/javascript">
										
									</script>



								</div>
							</div>
						</div>
						<div class="container" style="margin-left: 40px;">
							<div class="contact-1-main">
								<div class="contact-right wow bounceInUp" data-wow-delay="0.3s">


									<b class="buhuan">商品总价${prices }元</b>

									<button onclick="shouhui()" class="btn btn-success">我要买单</button>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>






		<div class="details" onclick="shouhui()">

			<div class="container datai">
				<div class="row">
					<div class="col-md-12">

						<img id="picimg"
							src="http://www.hanchunyang.com/vagetables/zhifubao.jpg"
							alt="" style="width: 240px; height: 380px;">
<br><br>
					</div>


					<div class="col-md-offset-4 col-md-4">
                     

									<b class="buhuan">商品总价${prices }元</b>

									<button class="btn btn-success">我已支付</button> 

						</div>
						
					</div>

				</div>
			</div>

		</div>

		<%@include file="footer.jsp"%>
</body>
</html>