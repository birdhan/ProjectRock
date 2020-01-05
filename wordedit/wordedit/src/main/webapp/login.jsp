<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>登录</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/fontawesome-all.css">
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/hljgsxy.jpg">
	<script src='${pageContext.request.contextPath }/js/jquery-2.2.3.min.js'></script>
</head>
<body>
<script type="text/javascript">
function zhucesss(){

	 document.getElementById('sdsdsd').style.display=""; 
}
function quxiaoaaa(){
	document.getElementById('sdsdsd').style.display="none";
}
function tiajiozhuce(){
	var pwd=document.getElementById('loginpwd').value;
	var name=document.getElementById('loginname').value;
	if(name=="" || pwd==""){
		alert("账号或密码不能为空");
	}else{
		var datas={
				name:name
		}
		$.ajax({
		    type: "POST",//方法类型
		    dataType: "json",//预期服务器返回的数据类型
		    url: "/wordedit/word/yanzhenglogin" ,//url
		    data: datas,
		    success: function (result) {
		    	var jieguo=result["result"];
		       if(jieguo=="注册成功"){
		    	   alert(jieguo);
		    	   document.getElementById('form2').submit();
		       }else{
		    	   alert(jieguo);
		       }
		    },
		    error:function(){
		   	 alert("error");
		    }
		})
	}
}

</script>
	<h1>文档解析与查重系统</h1>
	<div class="registerf" id="sdsdsd" style="display: none;">
	<form method="post" action="${pageContext.request.contextPath }/word/perregister" id="form2">
	<label>登录名</label><br>
	<input type="text" class="form-control" name="name" id="loginname"><br>
	<label>密码</label><br>
	<input type="text" class="form-control" name="pwd" id="loginpwd"><br><br>
	</form>
	<button onclick="tiajiozhuce()">注册</button>
	<button onclick="quxiaoaaa()">取消</button>
	</div>
	<div class="sub-main-w3">
		<form action="${pageContext.request.contextPath }/word/perlogin" method="post">
			<div class="form-style-agile">
				<label>
					用户名：
				</label>
				<input name="name" type="text">
			</div>
			<div class="form-style-agile">
				<label>
					密码：
				</label>
				<input name="pwd" type="password" >
			</div>
			<div class="wthree-text">
				<ul>
					<li>
					</li>
					<li>
						<a onclick="zhucesss()" style="cursor: pointer;">注册</a>
					</li>
				</ul>
			</div>
			<input type="submit" value="登录">
		</form>
		
		
	</div>
	<div class="clear"></div>
</body>

</html>