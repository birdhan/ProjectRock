<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>人民大药房</title>
<!--meta tags -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords"
	content="Trim-Fit Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
         Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script>
	addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
	}, false);

	function hideURLbar() {
		window.scrollTo(0, 1);
	}
</script>
<!--//meta tags ends here-->
<!--booststrap-->
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css"
	media="all">
<!--//booststrap end-->
<!-- font-awesome icons -->
<link href="${pageContext.request.contextPath }/css/fontawesome-all.min.css" rel="stylesheet"
	type="text/css" media="all">
<!-- //font-awesome icons -->
<link href="${pageContext.request.contextPath }/css/style.css" rel='stylesheet' type='text/css' media="all">
<!--//stylesheets-->
<link href="${pageContext.request.contextPath }/css/gooleapis.css"
	rel="stylesheet">
</head>
<body>

<script type="text/javascript">
window.onload=function (){
	
	var j = document.getElementsByClassName("交易中");
	for(a=0;a<j.length;a++){
	
		j[a].children[2].style.display="none";
	}
	
	var q = document.getElementsByClassName("确认收货");
	for(a=0;a<q.length;a++){
	
		q[a].children[0].style.display="none";
	}
	
	var y = document.getElementsByClassName("已完成");
	for(a=0;a<y.length;a++){
	
		y[a].innerHTML = "已完成订单"; 
	}
	
	var f = document.getElementsByClassName("付款");
	for(a=0;a<f.length;a++){
	
		f[a].innerHTML = "买家未付款";
	}
}

function shangchuang(id){
	document.getElementById(id).style.display="block";
	
}

function submit001(bg,id,iid){
	var num=document.getElementById(iid).value;
	window.location.href="${pageContext.request.contextPath}/company/updatenumber?id="+id+"&number="+num;
	/* bg.parentNode.style.display="none"; */
}

</script>
	<!--headder-->
	<div class="header-outs" id="home">
		<div class="header-w3layouts">
			<!--//navigation section -->
			<div class="headr-title">
				<div class="hedder-up">
					<h1>
						<a class="navbar-brand" href="index.html">${company.companyname }</a>
					</h1>
				</div>
				<div class="header-call">
					<!--  <span class="fas fa-phone-volume brand-icon"></span> -->
					<!--  <h4 style="color: #212529;">广东省汕头市</h4> -->
				</div>
				<div class="clearfix"></div>
			</div>
			<nav class="navbar navbar-expand-lg navbar-light">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse  nav-fill "
				id="navbarSupportedContent">
				<ul class="navbar-nav nav-pills nav-fill">
                     <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath }/company/enterprise">企业信息<span class="sr-only">(current)</span></a>
                     </li>
                     <li class="nav-item">
                        <a href="${pageContext.request.contextPath }/company/drugsadmin" class="nav-link ">药品管理</a>
                     </li>
                     <li class="nav-item active">
                        <a href="${pageContext.request.contextPath }/company/orderadmin" class="nav-link ">订单处理</a>
                     </li>
                     <li class="nav-item">
                        <a href="${pageContext.request.contextPath }/company/updrugs" class="nav-link ">添加良药</a>
                     </li>
                    
                  </ul>
			</div>
			</nav>
		</div>
	</div>
	<!-- //Navigation-->
	<!--//headder-->
	<div class="inner_page-banner"></div>

	<!--Typography-->
	<section class="about-inner py-lg-4 py-md-3 py-sm-3 py-3">
	<div class="container py-lg-5 py-md-4 py-sm-4 py-3">
		<!-- <h3 class="title text-center mb-lg-5 mb-md-4 mb-sm-4 mb-3">Typography</h3> -->
		<div class="typo-grids">
			<div class="typo-grids pt-md-5 pt-sm-4 pt-4">
				<div class="sub-title">
					<div class="sub-head mb-3 ">
						<h4>本店所有订单</h4>
					</div>
					<div class="bd-example mb-4">
						<table class="table">
							<thead class="thead-dark">
								<tr>
									<th scope="col">客户名称</th>
									<th scope="col">药品名称</th>
									<th scope="col">收货地址</th>
									<th scope="col">联系电话</th>
									<th scope="col">药品价格</th>
									<th scope="col">状态/单号</th>
								</tr>
							</thead>
							<tbody>
							
							<c:forEach items="${woshizhuzhuxia}" var="se">
								<tr>
									<td>${se.uname }</td>
									<td>${se.dname }</td>
									<td>${se.uaddress }</td>
									<td>${se.utel }</td>
									<td>${se.dprice }</td>
									<td class="${se.dstatus }">
									<button onclick="shangchuang('${se.id }')">上传单号</button>
									<div  style="display: none;" id="${se.id }"><input type="text" id="${se.id }01" style="width: 100px;"><button onclick="submit001(this,'${se.id }','${se.id }01')">确定</button></div>
									<div>${se.yunnumber }</div>
									</td>
								</tr>
								</c:forEach> 
								
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>
	</section>

	<footer>
	<p>客户投诉电话:400-650-9988</p>
	</footer>
	<!-- //Footer -->
	<!--js working-->
	<script src='${pageContext.request.contextPath }/js/jquery-2.2.3.min.js'></script>
	<!--//js working-->
	<!-- start-smoth-scrolling -->
	<script src="${pageContext.request.contextPath }/js/move-top.js"></script>
	<script src="${pageContext.request.contextPath }/js/easing.js"></script>
	<script>
		jQuery(document).ready(function($) {
			$(".scroll").click(function(event) {
				event.preventDefault();
				$('html,body').animate({
					scrollTop : $(this.hash).offset().top
				}, 900);
			});
		});
	</script>
	<!-- start-smoth-scrolling -->
	<!-- here stars scrolling icon -->
	<script>
		$(document).ready(function() {

			var defaults = {
				containerID : 'toTop', // fading element id
				containerHoverID : 'toTopHover', // fading element hover id
				scrollSpeed : 1200,
				easingType : 'linear'
			};

			$().UItoTop({
				easingType : 'easeOutQuart'
			});

		});
	</script>
	<!-- //here ends scrolling icon -->
	<!--bootstrap working-->
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<!-- //bootstrap working-->
</body>
</html>