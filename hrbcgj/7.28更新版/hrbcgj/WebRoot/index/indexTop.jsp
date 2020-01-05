<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<style type="text/css">
#login_button {
	color: #FFFFFF;
	margin: 0 auto;
}
</style>
</head>
<script type="text/javascript"
	src="${ctx }/javascript/index/common/indexTop.js"></script>
<div id="login_background" style=""></div>
<div id="login_alert" style="text-align: center;">
	<img id="img_exit" src="${ctx }/images/index/cha.png"
		style="width: 20px; height: 20px; background-size: 100%;position: absolute; right: 8px;cursor: pointer;">
	<div id="login_alert_title">账号登录</div>
	<input type="text" value="" placeHolder="ID/手机号登录" id="userId">
	<input type="password" value="" placeHolder="密码" id="pwd">
	<div style="height: 30px; text-align: center;">
		<input type="text" value="" id="login_alert_yanzheng"
			style="width: 90px; height: 30px;margin-left: 100px;"> <img
			src="<%=request.getContextPath()%>/getLoginImg" alt="验证码"
			id="LoginImage" /> <a href="javascript:reload();"><label>换一张</label>
		</a>
		<br>
	<input type="button" onclick="Login()" value="登录"
			id="login_button"><br> <span>&nbsp;&nbsp;&nbsp;<a
			class="a_bottom" target="_blank" href="${ctx }/index/register.jsp">注册新账号</a>
		</span>
	</div>
</div>
<!--顶部-->
<div class="box_top">
	<div class="box_top_width">
		<span style="margin-left:40px;">哈尔滨城市管理局官网</span> <span
			style="margin-left:30px;"><a href="#" id="login">登录</a>
		</span> <a href="javascript:void(0)" onclick="userCenter()"
			style="display: none;" id="username"></a> <a
			href="javascript:void(0)" onclick="userLogOut()"
			style="display: none;" id="logout">退出</a> <input type="hidden"
			id="loginUserCuid" value="" /> <span style="margin-left:10px;"><a
			href="javascript:void(0)" onclick="myEmail()">局长信箱</a>
		</span> <span style="float:right;margin-right:20px;">今天：<font
			id="todayFont"></font>&nbsp;<font id="temperatureFont"></font>&nbsp;<font
			id="weatherFont"></font>&nbsp;<font id="windFont"></font>
		</span>
	</div>
</div>
<script>
	$
			.ajax({
				url : "http://api.map.baidu.com/telematics/v3/weather?location=哈尔滨&output=json&ak=H7W5CxI0BPzKtwGcBHmpGPAz50xP1Qjw",
				dataType : "jsonp",
				jsonpCallback : "admin_cross",
				success : function(data) {
					$("#todayFont").text(data.results[0].weather_data[0].date);
					$("#temperatureFont").text(
							data.results[0].weather_data[0].temperature);
					$("#weatherFont").text(
							data.results[0].weather_data[0].weather);
					$("#windFont").text(data.results[0].weather_data[0].wind);
				}
			})
	$(function() {
		$('#img_exit').click(function() {
			$('#login_background').css('display', 'none');
			$('#login_alert').css('display', 'none');
		});
		$('#login').click(function() {
			$('#login_background').css('display', 'block');
			$('#login_alert').css('display', 'block');
		});
		//刷新页面验证session客户信息
		var sessionUserId = "${sessionScope.webSiteLoginUser.account}";
		if (sessionUserId != "" && sessionUserId != null) {
			$("#login").css('display', 'none');
			$("#username").text("${sessionScope.webSiteLoginUser.account}");
			$("#loginUserCuid").val("${sessionScope.webSiteLoginUser.id}");
			$("#username").css('display', 'inline-block');
			$("#logout").css('display', 'inline-block');
		}
	});
	function reload() {
		document.getElementById("LoginImage").src = "${ctx}/getLoginImg?date="
				+ new Date().getTime();
		$("#login_alert_yanzheng").val(""); // 将验证码清空
	}
	function Login() {
		//获取用户输入的验证码，并后台验证是否输入正确
		var checkcode = $("#login_alert_yanzheng").val();
		if (checkcode == '' || checkcode == null) {
			alert("请输入验证码。");
			return;
		}

		$.ajax({
			type : "POST", // 用POST方式传输    
			dataType : "text", // 数据格式:JSON    
			url : "${ctx}/validateLoginImg", // 目标地址    
			data : "checkcode=" + checkcode,
			success : function(data) {
				data = JSON.parse(data);
				if (data.status == '1') {
					alert("验证码输入有误，请重新输入");
					$("#login_alert_yanzheng").focus();
				} else {
					//获取用户账号密码， 后台验证是否正确
					valLogin();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {

			},
		});

	}

	function valLogin() {
		var userId = $("#userId").val();
		var pwd = $("#pwd").val();

		if (userId == null || userId == "") {
			alert("请输入登录账号");
		}

		if (pwd == null || pwd == "") {
			alert("请输入密码");
		}

		$.ajax({
			type : "POST", // 用POST方式传输    
			dataType : "text", // 数据格式:JSON    
			url : "${ctx}/webSiteLoginVal", // 目标地址    
			data : "userId=" + userId + "&pwd=" + pwd,
			success : function(data) {
				data = JSON.parse(data);
				if (data.status == '1') {
					alert("账号或密码输入错误");
					$("#userId").focus();
				} else {
					$("#login").css('display', 'none');
					$("#login_background").css('display', 'none');
					$("#login_alert").css('display', 'none');

					$("#username").text(data.registerUser.account);
					$("#loginUserCuid").val(data.registerUser.id);

					$("#username").css('display', 'inline-block');
					$("#logout").css('display', 'inline-block');

					window.location.reload();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {

			},
		});
	}

	/**
	 * 个人中心
	 type=1个人中心
	 type=2账户安全
	 */
	function userCenter() {
		var form = $("<form></form>");
		form.attr("action", "${ctx}/userCenter");
		form.attr("method", "post");
		form.attr("target", "_self");
		var input1 = $("<input type='hidden' name='sectionId' value='index'/>");
		form.append(input1);
		var input2 = $("<input type='hidden' name='secondSectionId' value='default'/>");
		form.append(input2);
		var input3 = $("<input type='hidden' name='usercuid'/>");
		input3.attr("value", $("#loginUserCuid").val());
		form.append(input3);
		var input4 = $("<input type='hidden' name='type' value='1'/>");
		form.append(input4);
		form.appendTo("body");
		form.css("display", "none");
		form.submit();
	}

	function userLogOut() {
		var r = confirm("是否确认退出？");
		if (r == true) {
			$.ajax({
				type : "POST", // 用POST方式传输    
				dataType : "text", // 数据格式:JSON    
				url : "${ctx}/webSiteLoginOut", // 目标地址    
				success : function(data) {
					$("#username").css('display', 'none');
					$("#logout").css('display', 'none');
					$("#login").css('display', 'inline-block');

					//把登录框里的内容置空
					$("#login_alert_yanzheng").val("");
					$("#userId").val("");
					$("#pwd").val("");
					reload();
					window.location.reload();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {

				},
			});
		}
	}

	function myEmail() {
		var sessionUserId = "${sessionScope.webSiteLoginUser.account}";
		var loginUserCuid = "${sessionScope.webSiteLoginUser.id}";
		if (sessionUserId == "" || sessionUserId == null) {
			$('#login_background').css('display', 'block');
			$('#login_alert').css('display', 'block');
			return;
		}
		window.location.href = "${ctx}/myEmail?sectionId=index&secondSectionId=default&usercuid="
				+ loginUserCuid;
	}
</script>