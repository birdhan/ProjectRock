<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/style5.css" />
	<link rel="stylesheet" type="text/css" href="css/body.css"/> 
  </head>
  
  <body>
    <div class="container">
	<section id="content">
		<form action="LoginServlet" method="post">
			<h1>用户登录</h1>
			<div>
				<input type="text" placeholder="用户名" required="" id="uname"  name="uname" />
			</div>
			<div>
				<input type="password" placeholder="密码" required="" id="upassword"  name="upassword" />
			</div>
			 <div class="">
				<span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>			</div> 
			<div>
				<!-- <input type="submit" value="Log in" /> -->
				<input type="submit" value="Login" class="btn btn-primary" id="js-btn-login"/>
			</div>
		</form><!-- form -->
		 <div class="button">
			<span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>
		</div> <!-- button -->
	</section><!-- content -->
</div>
<!-- container -->


<br><br><br><br>
<div style="text-align:center;">
</div>
  </body>
</html>
