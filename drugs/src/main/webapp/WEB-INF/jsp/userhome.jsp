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
	
	function qingdan(){
		window.location.href="${pageContext.request.contextPath}/consumer/consumelist";
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
<link href="//fonts.googleapis.com/css?family=Arimo:400,400i,700,700i"
	rel="stylesheet">
</head>
<body>
<script type="text/javascript">
function upuname(id,uname){
	var datas={
			id:id,
			uname:uname
	}
$.ajax({
		
        //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/drugs/consumer/upuname" ,//url
            data: datas,
            success: function (data) {
              alert("修改成功");
            },
            error : function() {
                alert("修改失败");
            }
        }); 
	
}

function uptel(id,tel){
	var datas={
			id:id,
			tel:tel
	}
	
	$.ajax({
		
		type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/drugs/consumer/uptel" ,//url
        data: datas,
        success: function (data) {
          alert("修改成功");
        },
        error : function() {
            alert("修改失败");
        }
		
	})
	
}

function upaddress(id,address){
	var datas={
			id:id,
			address:address
	}
	
	$.ajax({
		
		type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/drugs/consumer/upaddress" ,//url
        data: datas,
        success: function (data) {
          alert("修改成功");
        },
        error : function() {
            alert("修改失败");
        }
		
	})
	
}

function uppwd(id,pwd){
	var datas={
			id:id,
			pwd:pwd
	}
	
	$.ajax({
		
		type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/drugs/consumer/uppwd" ,//url
        data: datas,
        success: function (data) {
          alert("修改成功");
        },
        error : function() {
            alert("修改失败");
        }
		
	})
	
}
</script>
	<!-- short -->
	<div class="using-border py-3">
		<div class="inner_breadcrumb  ml-4">
			<ul class="short_ls">
				<li><a href="${pageContext.request.contextPath }/consumer/first">第一网上药房</a></li>
				<li>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>

				<li>你好,</li>
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
	<!--contact -->
	<section class="contact py-lg-4 py-md-3 py-sm-3 py-3">
	<div class="container py-lg-5 py-md-4 py-sm-4 py-3">
		<h3 class="title text-center mb-lg-5 mb-md-4 mb-sm-4 mb-3">您的个人信息</h3>
		<div class="agile-info-para">
			<div class="row contactright pb-lg-5 pb-md-4 pb-sm-3 pb-3">
				<div class="col-lg-4 col-md-4  text-center contact-address-grid">
					<div class="footer_grid_left">
						<div class="contact_footer_grid_left text-center mb-3">
							<h5>登录名</h5>
						</div>
						<p>
							<input type="text" value="${consbyid.uname }" onchange="upuname('${consbyid.id }',this.value)" style="height: 28px;text-align:center;background-color: #47c19b;">
						</p>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 text-center contact-address-grid">
					<div class="footer_grid_left">
						<div class="contact_footer_grid_left text-center mb-3">
							<h5>账户余额</h5>
						</div>
						<p>${ sessionScope.ubalance}</p>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 text-center contact-address-grid">
					<div class="footer_grid_left">
						<div class="contact_footer_grid_left text-center mb-3">
							<h5>联系电话</h5>
						</div>
						<p>
							<input type="text" value="${consbyid.tel }" onchange="uptel('${consbyid.id }',this.value)" style="height: 28px;text-align:center;background-color: #47c19b;">
						</p>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 text-center contact-address-grid"
					style="margin-top: 15px;">
					<div class="footer_grid_left">
						<div class="contact_footer_grid_left text-center mb-3">
							<h5>收货地址</h5>
						</div>
						<p>
							<input type="text" value="${consbyid.address }" onchange="upaddress('${consbyid.id }',this.value)" style="height: 28px;text-align:center;background-color: #47c19b;">
						</p>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 text-center contact-address-grid"
					style="margin-top: 15px;">
					<div class="footer_grid_left">
						<div class="contact_footer_grid_left text-center mb-3">
							<h5>修改密码</h5>
						</div>
						<p>
							<input type="password" value="${consbyid.pwd }" onchange="uppwd('${consbyid.id }',this.value)" style="height: 28px;text-align:center;background-color: #47c19b;">
						</p>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 text-center contact-address-grid"
					style="margin-top: 15px;">
					<div class="footer_grid_left">
						<div class="contact_footer_grid_left text-center mb-3">
							<h5>支付信息</h5>
						</div>
						<p><button class="btn btn-danger" style="height: 28px;line-height: 18px;" onclick="qingdan()">生成清单</button></p>
					</div>
				</div>
			</div>

		</div>
	</div>
	</section>
	<!--//contact  -->


	<!-- //Footer -->
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