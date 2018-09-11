<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
<link rel="stylesheet" type="text/css" href="css/style100.css" />
<link rel="stylesheet" type="text/css" href="css/body.css"/> 
</head>
<body>
<div class="container">
	<section id="content">
		<form action="/Customer/register" method="post">
			<h1>会员登录</h1>
			<div>
				<input type="text" placeholder="账号" required="" id="username" name="user"/>
			</div>
			<div>
				<input type="password" placeholder="密码" required="" id="password" name="pwd"/>
			</div>
			 <div class="">
				<span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>			</div> 
			<div>
				<!-- <input type="submit" value="Log in" /> -->
				<input type="submit" value="登录" class="btn btn-primary" id="js-btn-login"/>
				<a href="#">重置密码</a>
				<!-- <a href="#">Register</a> -->
			</div>
		</form><!-- form -->
		 <div class="button">
			
			<a href="#">用户管理</a>			
		</div> <!-- button -->
	</section><!-- content -->
</div>
<!-- container -->



</body>
</html>