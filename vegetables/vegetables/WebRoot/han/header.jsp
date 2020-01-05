<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--horizan start here-->
<div class="top-navg ">
	<div class="container">
		<div class=" logo wow tada" data-wow-delay="0.3s">
			<a href="${pageContext.request.contextPath }/login.jsp"><img src="images/hanLogo.png" alt=""></a>
		</div>
		<div class="navigation">	
			<span class="menu-ic"><img src="images/menu2.png" alt=""></span>
			<div class="menu menu-li">
					<ul class="menu-list">
					<li class="menu-item "><a href="/vegetables/FindByHeatServlet" class="menu-link home1">首页</a></li>
					<li class="menu-item"><a href="/vegetables/FindServlet" class="menu-link index1">菜单</a></li>
					
					<li class="menu-item"><a href="/vegetables/FindByTypeServlet?typeone=meiwei" class="menu-link services1">类别</a></li>
					<li class="menu-item"><a href="/vegetables/FindOrderServlet" class="menu-link about1">订单</a></li>
					<li class="menu-item"><a href="${pageContext.request.contextPath }/han/addvagetabes.jsp" class="menu-link addvage">添加菜品</a></li>
				</ul>
						</div>

			 <script>
				   $( "span.menu-ic" ).click(function() {
					 $( ".navigation ul" ).slideToggle( 300, function() {
					 // Animation complete.
					  });
					 });
				</script>
	</div>
	<ul class=" head-icons wow bounceInRight" data-wow-delay="0.3s">
				 		<li><a href="#"><b style="color: #ECB731;">user</b></a></li>
				 		
				 		<li><a href="#"><h2 style="color: #ECB731; font-family: DFKai-SB ;" class="main-head">${u}</h2></a></li>
				 	</ul>
	<div class="clearfix"></div>
</div>
</div>