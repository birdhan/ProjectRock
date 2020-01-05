<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/uncover.css" />
<!--stylesheets-->
<link href="${pageContext.request.contextPath }/css/style.css" rel='stylesheet' type='text/css' media="all">
<!--//stylesheets-->
<link href="${pageContext.request.contextPath }/css/gooleapis.css"
	rel="stylesheet">

</head>
<body>
<script type="text/javascript">
function tijiaoyaoxiang(){
	
	document.getElementById("aaaaa").submit();
}
</script>
	<!-- short -->
	<div class="using-border py-3">
		<div class="inner_breadcrumb  ml-4">
			<ul class="short_ls">
				<li><a href="${pageContext.request.contextPath }/consumer/first">第一网上药房</a></li>
				<li>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>

					<li id="deng">当前用户：</li>
				<li><a
					href="${pageContext.request.contextPath }/consumer/userhome?id=${consumer.id }">
						${consumer.uname } &nbsp;&nbsp;&nbsp;</a></li>
				<li><a
					href="${pageContext.request.contextPath }/consumer/zhuxiao">登录/注销
						&nbsp;&nbsp;&nbsp;</a></li>
				<li><a href="${pageContext.request.contextPath }/consumer/orderdrugs">我的药箱
						&nbsp;&nbsp;&nbsp;</a></li>
				<li><a
					href="${pageContext.request.contextPath }/consumer/upgroup">资质申请
				</a>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;</li>

				<!-- 当前系统时间 -->
				<li>
					<div id="show_time">
						<script>
							//这里代码多了几行，但是不会延迟显示，速度比较好，格式可以自定义，是理想的时间显示
							setInterval("fun(show_time)", 1);
							function fun(timeID) {
								var date = new Date(); //创建对象  
								var y = date.getFullYear(); //获取年份  
								var m = date.getMonth() + 1; //获取月份  返回0-11  
								var d = date.getDate(); // 获取日  
								var w = date.getDay(); //获取星期几  返回0-6   (0=星期天) 
								var ww = ' 星期'
										+ '日一二三四五六'.charAt(new Date().getDay());//星期几
								var h = date.getHours(); //时
								var minute = date.getMinutes() //分
								var s = date.getSeconds(); //秒
								var sss = date.getMilliseconds(); //毫秒
								if (m < 10) {
									m = "0" + m;
								}
								if (d < 10) {
									d = "0" + d;
								}
								if (h < 10) {
									h = "0" + h;
								}

								if (minute < 10) {
									minute = "0" + minute;
								}

								if (s < 10) {
									s = "0" + s;
								}

								if (sss < 10) {
									sss = "00" + sss;
								} else if (sss < 100) {
									sss = "0" + sss;
								}

								document.getElementById(timeID.id).innerHTML = y
										+ "-"
										+ m
										+ "-"
										+ d
										+ "   "
										+ h
										+ ":"
										+ minute + ":" + s
										/* + "."
										+ sss */
										+ "  " + ww;
								//document.write(y+"-"+m+"-"+d+"   "+h+":"+minute+":"+s);  
							}
						</script>
					</div>


				</li>
				<!-- 当前系统时间 -->

			</ul>


		</div>

	</div>
	<!-- //short-->

	<!--statement-->
	<section class="statement py-lg-4 py-md-3 py-sm-3 py-3" id="statement">
	<div class="container py-lg-5 py-md-5 py-sm-4 py-4">
		<div class="w3ls-statement-list text-center">
			<h4>
				早睡早起身体好，补充营养不可少<br> 常常运动最重要，健健康康活到老
			</h4>
		</div>
	</div>
	</section>
	<!--//statement-->
	<!-- events -->
	<section class="events py-lg-4 py-md-3 py-sm-3 py-3" id="events">
	<div class="container py-lg-5 py-md-5 py-sm-4 py-4">
		<h3 class="title text-center mb-lg-5 mb-md-4 mb-sm-4 mb-3">${setailsb.dname }</h3>
		<div class="row blog-top-grids">
			<div class="col-lg-7 left-side-agile p-lg-4 p-md-4 p-3">

				<h4>所属药房：${setailsb.companyname }</h4>
				<p class="groom-right">${setailsb.detais }</p>
				<div class="row mt-2">
					<div class="col-md-2 col-sm-2 col-3 event-w3ls-date text-center">
						<h5>${setailsb.dprice }</h5>
						<span>价格</span>
					</div>
					<div class="col-md-8 col-sm-8 col-9 event-w3ls-sub-txt text-left">
						<h4 class="mb-2">${setailsb.type }</h4>
						<ul>
							<li><span class="fas fa-clock" aria-hidden="true"></span>${setailsb.usetype }</li>
							<li><span class="fas fa-map-marker-alt" aria-hidden="true"></span>${setailsb.companyadd }</li>
						</ul>
					</div>
					<div class="col-md-12 col-sm-12 col-9 event-w3ls-sub-txt text-right">
					
					<button class="btn btn-success" onclick="tijiaoyaoxiang()">添加到我的药箱</button>
					
					
					<form action="${pageContext.request.contextPath }/consumer/addorder" method="post" id="aaaaa">
					<input type="hidden" value="${setailsb.dname }" name="dname">
					<input type="hidden" value="${setailsb.type }" name="dtype">
					<input type="hidden" value="${setailsb.dprice }" name="dprice">
					<input type="hidden" value="${setailsb.id }" name="did">
					<input type="hidden" value="${setailsb.eid }" name="eid">
					</form>
					
					
					</div>
				</div>
			</div>
			<div class="col-lg-5 blog-w3l-right ">
				<img src="/picture/${setailsb.pictureurl }" class="img-fluid" alt="" style="max-height: 300px;">
			</div>
		</div>
	</div>
	</section>
	<!-- events -->
	<!-- //Footer -->
	<footer>
	<p>客户投诉电话:400-650-9988</p>
	</footer>
	<!-- //Footer -->

	<script src='${pageContext.request.contextPath }/js/jquery-2.2.3.min.js'></script>
	<!--//js working-->
	<!-- For-Banner -->
	<script src="${pageContext.request.contextPath }/js/imagesloaded.pkgd.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/anime.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/uncover.js"></script>
	<script src="${pageContext.request.contextPath }/js/demo1.js"></script>
	<!-- //For-Banner -->
	<!--About OnScroll-Number-Increase-JavaScript -->
	<script src="${pageContext.request.contextPath }/js/jquery.waypoints.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/jquery.countup.js"></script>
	<script>
		$('.counter').countUp();
	</script>
	<!-- //OnScroll-Number-Increase-JavaScript -->

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