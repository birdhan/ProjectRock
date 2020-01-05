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
<body style="height: 100%;">

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
	<!--Typography-->
	<section class="about-inner py-lg-4 py-md-3 py-sm-3 py-3">
	<div class="container py-lg-5 py-md-4 py-sm-4 py-3">
		<!-- <h3 class="title text-center mb-lg-5 mb-md-4 mb-sm-4 mb-3">Typography</h3> -->
		<div class="typo-grids">
			<div class="typo-grids pt-md-5 pt-sm-4 pt-4">
				<div class="sub-title">
					<div class="sub-head mb-4">
						<h4>我的药箱</h4>
					</div>
					<div class="bd-example mb-4">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">药品名称</th>
									<th scope="col">药品分类</th>
									<th scope="col">药品价格</th>
									<th scope="col">下单时间</th>
									<th scope="col" style="text-align: center;">状态</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${selectbyuid }" var="selectbyuid">
								<tr>
									<td>${selectbyuid.dname }</td>
									<td>${selectbyuid.dtype }</td>
									<td>${selectbyuid.dprice }</td>
									<td>${selectbyuid.odate }</td>
									<td class="${selectbyuid.dstatus }" style="text-align: center;">
									<button onclick="fukuan(this,'${selectbyuid.id }','${selectbyuid.dprice }')">立即付款</button>
									<button onclick="querenshouhuo(this,'${selectbyuid.id }')">确认收货</button>
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
	<script type="text/javascript">
	window.onload=function (){
	
		
		var j = document.getElementsByClassName("交易中");
		for(a=0;a<j.length;a++){
			j[a].innerHTML = "交易中";
		}
		
		var w = document.getElementsByClassName("已完成");
		for(i=0;i<w.length;i++){
			w[i].innerHTML = "已完成"; 
		}

		var x = document.getElementsByClassName("付款");
		
		for(i=0;i<x.length;i++){
			
			x[i].children[1].style.display="none";
			
		} 
		
        var q = document.getElementsByClassName("确认收货");
		
		for(i=0;i<q.length;i++){
			
			/* q[i].innerHTML=" <button onclick=\"querenshouhuo(this)\">确认收货</button>";  */
			 q[i].children[0].style.display="none"; 
		} 
	}
	
	var consumer="<%=session.getAttribute("ubalance")%>";
	var sessii=consumer;
	/* 支付ajax */
	function fukuan(bq,id,price){
		var statu="交易中";
		
		if(confirm("确认付款？")){
			if(sessii*1>=price*1){
				sessii=sessii-price;
				var datas={
						id:id,
						status:statu
				}
				var datas1={
						
						balance:sessii,
						price:price
				}
				
				$.ajax({
					 type: "POST",//方法类型
			         dataType: "json",//预期服务器返回的数据类型
			         url: "/drugs/consumer/updatestatus" ,//url
			         data: datas,
			         success: function (data) {
			        	 },
			        error:function(){
			        	
			        }	 
			        	 
				});
				$.ajax({
					 type: "POST",//方法类型
			         dataType: "json",//预期服务器返回的数据类型
			         url: "/drugs/consumer/updateubalance" ,//url
			         data: datas1,
			         success: function (data) {
			        	
			        	 },
			        error:function(){
			        	alert("error")
			        }	 
			        	 
				});
				bq.parentNode.innerHTML="交易中";
			}else{
				alert("余额不足，请及时充值");
			}
		}
	}
	
	
	/* 确认收货ajax */
	function querenshouhuo(bq,id){
		if(confirm("确认收货？")){
			var statu="已完成";
			var datas={
					id:id,
					status:statu
			}
			$.ajax({
				 type: "POST",//方法类型
		         dataType: "json",//预期服务器返回的数据类型
		         url: "/drugs/consumer/updatestatus" ,//url
		         data: datas,
		         success: function (data) {
		        	 },
		        error:function(){
		        	
		        }	 
		        	 
			});
			bq.parentNode.innerHTML="已完成";
		}
		
	}
	</script>
	<!--//Typography-->
	<!-- //Footer -->
	<!-- <footer>
	<p>客户投诉电话:400-650-9988</p>
	</footer> -->
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