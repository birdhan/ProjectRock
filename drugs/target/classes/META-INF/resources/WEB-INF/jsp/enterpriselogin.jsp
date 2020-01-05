<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>企业登录</title>
<link href="${pageContext.request.contextPath }/css/style10.css" rel="stylesheet" type="text/css" media="all"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Transparent Login Form Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements" />
<!--web-fonts-->
<link href='${pageContext.request.contextPath }/css/enterpriselogin.css' rel='stylesheet' type='text/css' />
<!--web-fonts-->
</head>

<body>
<script type="text/javascript">
var shun="${loginin}";
if(typeof shun == "undefined" || shun == null || shun == ""){
	
}else{
	alert(shun);
}
</script>


<!--header-->
	<div class="header-w3l">
		<h1> 医药公司企业端登录</h1>
	</div>
<!--//header-->

<!--main-->
<div class="main-content-agile">
	<div class="sub-main-w3">	
		<form action="${pageContext.request.contextPath }/company/loginyanzheng" method="post">
			<input placeholder="登录名" name="uname" class="user" type="text" required=""><br>
			<input  placeholder="密码" name="pwd" class="pass" type="password" required=""><br>
			<input type="submit" value="">
		</form>
	</div>
</div>
<!--//main-->


</body>
</html>