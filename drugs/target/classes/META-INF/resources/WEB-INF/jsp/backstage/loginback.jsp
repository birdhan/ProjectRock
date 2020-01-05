<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">  
	    <title>管理员登录</title>
        <meta name="description" content="">
        <meta name="author" content="templatemo">
        <!-- 
        Visual Admin Template
        http://www.templatemo.com/preview/templatemo_455_visual_admin
        -->
	    <link href="${pageContext.request.contextPath }/css/gooleapis.css" rel='stylesheet' type='text/css'>
	    <link href="${pageContext.request.contextPath }/css/font-awesome.min.css" rel="stylesheet">
	    <link href="${pageContext.request.contextPath }/css/bootstrap.min001.css" rel="stylesheet">
	    <link href="${pageContext.request.contextPath }/css/templatemo-style.css" rel="stylesheet">
	    
	    
	</head>
	<body class="light-gray-bg">
	<script type="text/javascript">
	var shun="${loginerror}";
	if(typeof shun == "undefined" || shun == null || shun == ""){
		
	}else{
		alert(shun);
	}
	</script>
		<div class="templatemo-content-widget templatemo-login-widget white-bg">
			<header class="text-center">
	          <div class="square"></div>
	          <h1>系统管理员登录入口</h1>
	        </header>
	        <form action="${pageContext.request.contextPath }/backmanage/yanzhenglogin" class="templatemo-login-form">
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-user fa-fw"></i></div>	        		
		              	<input type="text" name="name" class="form-control" placeholder="登录名">           
		          	</div>	
	        	</div>
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div>	        		
		              	<input type="password" name="password" class="form-control" placeholder="登录密码">           
		          	</div>	
	        	</div>	          	
	          	<div class="form-group">
				    			    
				</div>
				<div class="form-group">
					<button type="submit" class="templatemo-blue-button width-100">登录</button>
				</div>
	        </form>
		</div>
		<div class="templatemo-content-widget templatemo-login-widget templatemo-register-widget white-bg">
			<p> <strong>数据无价请您谨慎操作</strong></p>
		</div>
	</body>
</html>