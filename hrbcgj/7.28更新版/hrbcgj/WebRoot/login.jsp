<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="./commonjsp/common-top.jsp" %>
<head>
<title>登录</title>
<link href="${ctx}/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/javascript/login.js"></script>

<script type="text/javascript">
	function validateUser() {
		var userId = getEleById("userId").value;
		var password = getEleById("password").value;
		if(userId.Trim() != "" && password.Trim() != "") {
			getEleById("loginDiv").innerHTML = "";
			$.ajax({   
				type:"post",     
				url:getRootPath()+"/systemuser/validateUser.action",           
				dataType:"text",             
				data:"userId="+userId+"&password="+password+"&randomParam="+new Date(),        
				success:function(data){
					if(data != "0") {																					
						getEleById("loginForm").submit();
					} else {													
						getEleById("loginDiv").innerHTML = "<font color='red'>用户名和密码不正确</font>";
					}
				}
			});	
		}
	}
	
	function enterClick() {
		var event=arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异  
		if(event.keyCode == 13) {  
			validateUser();  
		}  
	}
</script>
</head>
<body style="background-color:#156594;" onresize="common()">
	<div class="login" id="login">
		<div class="l_header">
			<span class="log">欢迎光临，${sc.sysDescName} ！</span>
			<ul>
				<li style="background-image:url(${ctx}/images/home_logo.jpg); background-repeat: no-repeat;">
					<a href="#">返回首页</a>
				</li>
				<li style="background-image:url(${ctx}/images/exit_log.jpg); background-repeat: no-repeat;">
					<a href="javascript:window.close()">退出系统</a>
				</li>
			</ul>
		</div>
		<!--l_header_end-->

		<div class="l_middle" id="l_middle">
			<div id="blank"></div>
			<div class="l_middle_content">
				<div class="content_login">
					<div class="logo">
						<img src="${ctx}/images/logo.png" width="280" height="60" />
					</div>
					<div class="content">
						<form action="${ctx}/login/login.action" id="loginForm" method="post">
							<div class="login_form">
								<p>
									<input id="userId" name="userId" type="text" value="" style="border:none; width:293px; height:30px; background-image:url(${ctx}/images/txt_1.jpg); padding-left:50px; padding-top:18px;" autocomplete="off"/>
								</p>
								<p>
									<input id="password" name="password" type="password" value="" style="border:none; width:293px; height:30px; background-image:url(${ctx}/images/txt_2.jpg); padding-left:50px; padding-top:18px;" autocomplete="off" onkeyup="enterClick()"/>
								</p>
								<ul>
									<li>
										<a href="javascript:validateUser();">
											<img src="${ctx}/images/btn_login.jpg" width="111" height="35" />
										</a>
									</li>									
									<li>
										<a href="#">忘记密码?</a>
										<div id="loginDiv" style="display: inline;"></div>										
									</li>
								</ul>
							</div>							
						</form>
					</div>
					<!--content_end-->
				</div>
			</div>
			<div id="blank"></div>
		</div>
		<!--l_middle_end-->
		<div class="l_footer"><p>${sc.sysDescName}</p></div>
		<!--l_footer_end-->
		
		
	</div>
	<script type="text/javascript">
		getEleById("userId").focus();
	</script>
</body>
<%@ include file="./commonjsp/common-bottom.jsp" %>