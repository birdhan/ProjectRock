<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录与注册</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Simple Login and Signup Form web template Responsive, Login form web template,Flat Pricing tables,Flat Drop downs  Sign up Web Templates, Flat Web Templates, Login sign up Responsive web template, SmartPhone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<!-- //custom-theme -->
<link href="${pageContext.request.contextPath }/css/style001.css"
	rel="stylesheet" type="text/css" media="all" />
<!-- js -->
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.min.js"></script>
<!--// js -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/easy-responsive-tabs.css " />
<!-- <link href="//fonts.googleapis.com/css?family=Questrial"
	rel="stylesheet"> -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/gooleapis.css " />
</head>
<body class="bg agileinfo">
<script>

var aaa = "${fail}"; 
if(aaa=='账号或者密码错误'){
	alert(aaa);	
}


</script>

	<h1 class="agile_head text-center">欢迎您登录到网上购药系统</h1>
	<div class="w3layouts_main wrap">
		<!--Horizontal Tab-->
		<div id="parentHorizontalTab_agile">
			<ul class="resp-tabs-list hor_1">
				<li>登录</li>
				<li>注册</li>
			</ul>
			<div class="resp-tabs-container hor_1">
				<div class="w3_agile_login">
					<form action="${pageContext.request.contextPath }/consumer/logincon" method="post" class="agile_form">
						<p>登录名</p>
						<input type="text" name="uname" required="required" />
						<p>用户密码</p>
						<input type="password" name="pwd" required="required"
							class="password" /> <br> <br> <input type="submit"
							value="LogIn" class="agileinfo" />
					</form>
				</div>
				<div class="agile_its_registration">
					<form action="${pageContext.request.contextPath }/consumer/signuser" method="post" class="agile_form">
						<p>用户名</p>
						<input type="text" name="uname" required="required" />
						<p>密码</p>
						<input type="password" name="pwd" required="required" />
						<p>电话</p>
						<input type="text" name="tel" required="required">
						<p>地址</p>
						<input type="text" name="address" required="required">
						<div class="check w3_agileits"></div>
						<input type="submit" value="注册" /> <input type="reset" value="重置" />
					</form>
				</div>
			</div>
		</div>
		<!-- //Horizontal Tab -->
	</div>
	<!-- <div class="agileits_w3layouts_copyright text-center">
			<p>© 2017 Simple Login and Signup Form. All rights reserved | Design by <a href="//w3layouts.com/">W3layouts</a></p>
	</div> -->
	<!--tabs-->
	<script
		src="${pageContext.request.contextPath }/js/easyResponsiveTabs.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//Horizontal Tab
			$('#parentHorizontalTab_agile').easyResponsiveTabs({
				type : 'default', //Types: default, vertical, accordion
				width : 'auto', //auto or any width like 600px
				fit : true, // 100% fit in a container
				tabidentify : 'hor_1', // The tab groups identifier
				activate : function(event) { // Callback function if tab is switched
					var $tab = $(this);
					var $info = $('#nested-tabInfo');
					var $name = $('span', $info);
					$name.text($tab.text());
					$info.show();
				}
			});
		});
	</script>
	<script type="text/javascript">
		window.onload = function() {
			document.getElementById("password1").onchange = validatePassword;
			document.getElementById("password2").onchange = validatePassword;
		}
		function validatePassword() {
			var pass2 = document.getElementById("password2").value;
			var pass1 = document.getElementById("password1").value;
			if (pass1 != pass2)
				document.getElementById("password2").setCustomValidity(
						"Passwords Don't Match");
			else
				document.getElementById("password2").setCustomValidity('');
			//empty string means no validation error
		}
	</script>
	<!--//tabs-->
</body>
</html>

