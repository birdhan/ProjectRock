<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="head.jsp" %>
<style>
.services1{
color: #ECB731;
	border: 2px solid #ECB630;
}
</style>
<body>
<%@include file="header.jsp" %>

<!--services start here-->
<div class="main-grid-one3" id="three">

	<div class="services">
	    <div class="temp-heading">
		    <h3 class="main-head">分类点餐</h3>
		</div>
		<div class="services-1">
		   <div class="container"> 
		   	 <div class="services-1-main">
		   	 	<div class="col-md-4 service-grid wow bounceInDown" data-wow-delay="0.3s">
		   	 		<a href="/vegetables/FindByTypeServlet?typeone=meiwei" class="hi-icon hi-icon-archive"><span class="ser1"> </span></a>
		   	 		<h3>美味佳肴</h3>
		   	 		<p>为了你的胃,我们煞费苦心.</p>
		   	 	</div>
		   	 	<div class="col-md-4 service-grid wow bounceInLeft" data-wow-delay="0.3s">
		   	 		<a href="/vegetables/FindByTypeServlet?typeone=cantian" class="hi-icon hi-icon-archive"><span class="ser2"> </span></a>
		   	 		<h3>餐后饮品</h3>
		   	 		<p>甜品,果汁,碳酸饮品,水,新鲜水果汁.</p>
		   	 	</div>
		   	 	<div class="col-md-4 service-grid wow bounceInUp" data-wow-delay="0.3s">
		   	 		<a href="/vegetables/FindByTypeServlet?typeone=zhushi" class="hi-icon hi-icon-archive"><span class="ser3"> </span></a>
		   	 		<h3>叫点主食</h3>
		   	 		<p>特色面食,地方特色糕点,哈尔滨烤冷面</p>
		   	 	</div>
		   	 <!--  <div class="clearfix"> </div> -->
		   	  
		   	   
		   	 </div>
		  
		 
		   </div>	
		 
		</div>
<!-- 	<div class="portfolio"> -->
			 <div class="temp-heading">
				<h3 class="main-head">${retype }</h3>
			</div>
			<div class="menu-port">
				<div class="container">
					<div class="menu-grid-main">
						
						
						<c:forEach items="${tlist}" var="veg2">
						<div class="col-md-4 menu-grid wow ${veg2.animation}"
							data-wow-delay="0.3s" style="height: 400px;">
						<img onclick="details('${veg2.did}','${veg2.dishname }','${veg2.title }','${veg2.price }','${veg2.details }','${veg2.picture }')" src="http://www.hanchunyang.com/vagetables/${veg2.picture}" alt=""
								class="img-responsive" style="height: 250px; ">
							<h4><b>菜品名称:</b>${veg2.dishname}</h4>
							<h4><b>售价:</b>￥${veg2.price}</h4>
							<h4><b>简要介绍:</b>${veg2.details}</h4>
						</div>
						</c:forEach>

						
					
						<div class="clearfix"></div>
					</div>
				</div>
			</div>

		<!-- </div> -->


	    </div>



</div>

<%@include file="details.jsp"%>


<%@include file="footer.jsp" %>
</body>
</html>