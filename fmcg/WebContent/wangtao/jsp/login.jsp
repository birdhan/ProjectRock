<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html class="no-js">

    <head>

        <meta charset="utf-8">
        <title>用户登录</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="assets/css/reset.css">
        <link rel="stylesheet" href="assets/css/supersized.css">
        <link rel="stylesheet" href="assets/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>

        <div class="page-container">
            <h1>登录</h1>
            <form action="${pageContext.request.contextPath }/login" method="post" id="form002">
                <input type="text" name="name" class="username" placeholder="Username">
                <input type="password" name="pwd" class="password" placeholder="Password">
                <button type="submit" onclick="logins()">快速登录</button>
                
            </form>
             <div class="connect">
                <%-- <p><a href="${pageContext.request.contextPath }/register">点击注册账号</a></p> --%>
                <p style="cursor: pointer;" onclick="dainji()">点击注册账号</p>
            </div>
            
        </div>

        <!-- Javascript -->
        <script src="assets/js/jquery-1.8.2.min.js"></script>
        <script src="assets/js/supersized.3.2.7.min.js"></script>
        <script src="assets/js/supersized-init.js"></script>
        <script src="assets/js/scripts.js"></script>
<script type="text/javascript">
function logins(){
	$("#form002").submit();
}
function dainji(){
	window.location.href="${pageContext.request.contextPath }/register";
}
</script>
    </body>

</html>

