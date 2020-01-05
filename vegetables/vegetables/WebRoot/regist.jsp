<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>注册页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords"
	content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="css/style1.css" rel='stylesheet' type='text/css' />
<!--webfonts-->
<link
	href='http://fonts.useso.com/css?family=PT+Sans:400,700,400italic,700italic|Oswald:400,300,700'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Exo+2' rel='stylesheet'
	type='text/css'>
<!--//webfonts-->
<script
	src="http://ajax.useso.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
	</head>
	<body>
	<script>
	$(document).ready(function(c) {
		$('.close').on('click', function(c) {
			$('.login-form').fadeOut('slow', function(c) {
				$('.login-form').remove();
			});
		});
	});
	
	
	$(function() {
		$("#uname").blur(function() {

			if ($("#uname").val() == "") {

				$("#msg1").html("请输入用户名");

			} else {
				//如果内容不为空,进行ajax异步校验
				$.ajax({
					url : "AjaxServlet",
					type : "post",
					//这是需要验证的用户输入的用户名
					data : {
						uname : $("#uname").val()
					},

					dataType : "json",
					//接收到后台发送来的消息
					success : function(msg) {
						if (msg) {
							$("#msg1").html("用户已存在");
						} else {
							$("#msg1").html("");
						}

					},
					error : function() {
						alert("yanzheng");
					}
				});
			}

		});

	});
</script>
<!--SIGN UP-->
<h1>注册页面</h1>
<div class="login-form">
	<div class="close"></div>
	<div class="head-info">
		<label class="lbl-1"> </label> <label class="lbl-2"> </label> <label
			class="lbl-3"> </label>
	</div>
	<div class="clear"></div>
	<div class="avtar">
		<img src="images/avtar.png" />
	</div>
	<form action="RegistServlet" method="post" name="myForm" id="form01">
		<input type="text" class="text" name="uname" id="uname"
			value="Username" onfocus="this.value = '';"
			onblur="if (this.value == '') {this.value = 'Username';}">
		<div id="msg1"></div>

		<div class="key">
			<input type="password" value="Password" name="upassword"
				id="upassword" onfocus="this.value = '';"
				onblur="if (this.value == '') {this.value = 'Password';}"> <span
				id="msg2"></span>
		</div>
		<div class="signin">
			
			<input type="submit" value="Regist">
		</div>

	</form>

</div>
</body>
</html>
