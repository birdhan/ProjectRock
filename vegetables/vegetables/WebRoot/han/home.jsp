<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="head.jsp"%>
<style>
.home1 {
	color: #ECB731;
	border: 2px solid #ECB630;
}
</style>

<body>
	<%@include file="header.jsp"%>

	<!--mother grid start here-->

	<div class="main-grid-one1" id="one">
		<div class="home-banner">
			<div class="dream-home-bann-main wow bounceInDown"
				data-wow-delay="0.3s">
				<h1>College dining room</h1>
				<span class="span-dream-bar"> </span>
				<p>欢迎来到黑龙江工商学院5A级食堂在线点餐系统，优质的服务，美味的菜品，舒适的环境，我们一切的努力都是为了你.</p>
			</div>
		</div>
		<!--furniture-->
		<div class="furniture">
			<div class="container">

				<div class="furniture-main">
					<h2 align="center">本餐厅特别推荐菜品</h2><br>
					<c:forEach items="${hlist}" var="veg1">
					<div class="col-md-4 furniture-grid wow bounceInLeft ${veg1.animation }"
						data-wow-delay="0.3s">
						
						<figure> <img onclick="details('${veg1.did}','${veg1.dishname }','${veg1.title }','${veg1.price }','${veg1.details }','${veg1.picture }')" src="http://www.hanchunyang.com/vagetables/${veg1.picture }" alt=""
							class="img_responsive" > </figure>
							<h4><b>菜品名称:</b>${veg1.dishname}</h4>
							<h4><b>售价:</b>￥${veg1.price}</h4>
							<h4><b>简要介绍:</b>${veg1.details}</h4>

					</div>
					</c:forEach>
					
					
					<div class="clearfix"></div>
				</div>
			</div>
		</div>

		<!--promiss-->
		<div class="promiss">
			<div class="container">
				<div class="promiss-main wow bounceInDown" data-wow-delay="0.3s">
					<h3>我们的承诺和价值观</h3>
					<p>无论是在学院路还是在哈尔滨任何一个食堂，工商学院都都是一个将责任与安全作为核心价值观和可持续发展的优质学院。我们努力将学院的社会责任融入到每一位教师与同学中去、每一个部门以及每一个员工的日常工作，在满足利益相关方要求与期望的同时，实现食堂的发展目标。</p>
				</div>
			</div>
		</div>
		<!---welcome-->
		<div class="welcome">
			<div class="container">
				<div class="welcome-main">
					<div class="welcome-top">
						<h3>Welcome to join us</h3>
					</div>
					<div class="welcome-bottom">
						<div class="col-md-6 welcome-left wow bounceInUp"
							data-wow-delay="0.3s">
							<h4>美丽的学校,牛逼的团队,优雅的环境,热情的领导</h4>
							<p>对于美食我们很认真,我们急需大厨若干名,待遇没的说,条件好到不得了.</p>
						</div>

						<div class="col-md-3 wel-img1 wow bounceInLeft"
							data-wow-delay="0.3s">
							<ul class="welcome-grid gr-welcome ">
								<li>
									<h4 class="zero">招聘院长一名</h4>
									<p class="zero">有经验者优先.</p></li>
								<li><img class="top" src="images/r4.jpg" alt="" />
								</li>
							</ul>
						</div>
						<div class="col-md-3 wel-img2 wow bounceInRight"
							data-wow-delay="0.3s">
							<ul class="welcome-grid ">
								<li>
									<h4 class="zero">招聘寝室管理员</h4>
									<p class="zero">有经验者优先.</p></li>
								<li><img class="top" src="images/r5.jpg" alt="" />
								</li>
							</ul>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="details.jsp"%>
	
	
	<%@include file="footer.jsp"%>
</body>
</html>