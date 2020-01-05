<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<!-- Meta tags -->
	<title>管理员登录</title>
	<meta name="keywords" content="Winter Login Form Responsive widget, Flat Web Templates, Android Compatible web template, 
	Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- stylesheets -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/font-awesome.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style1.css">
</head>
<body>
	<div class="agile-login">
		<h1>欢迎来到黑龙江工商学院</h1>
		<div class="wrapper">
			<h2>管理员登录</h2>
			<div class="w3ls-form">
				<form action="${pageContext.request.contextPath }/back/adminlogin" method="post">
					<label>Username</label>
					<input type="text" name="name" placeholder="Username" required/>
					<label>Password</label>
					<input type="text" name="pwd" placeholder="Password" required />
		<br>
					<input type="submit" value="提交" />
				</form>
				
			</div>
		</div>
		<br>
	</div>
</body>
</html>