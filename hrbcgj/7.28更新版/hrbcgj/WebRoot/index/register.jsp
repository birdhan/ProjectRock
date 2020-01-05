<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>哈尔滨城市管理局官网</title>
		<%@ include file="/commonjsp/indexCommon-js.jsp" %>			<!-- js环境 -->
		<link rel="stylesheet" type="text/css" href="${ctx }/css/index/register.css">
	</head>
  
<body>
	<div>
		<h1 id="register_head">用户注册</h1>
		<table>
			<tr>
				<td style="text-align: right; padding-right: 20px;"><span>*&nbsp;</span>账号/用户名：</td>
				<td><input type="text" id="userId" class="err_1"></td>
				<td style="text-align: left; padding-left: 20px;"><span id="err_1"></span></td>
			</tr>
			<tr>
				<td style="text-align: right; padding-right: 20px;"><span>*&nbsp;</span>密码：</td>
				<td><input type="password" id="psw" class="err_2"></td>
				<td style="text-align: left; padding-left: 20px;"><span id="err_2"></span></td>
			</tr>
			<tr>
				<td style="text-align: right; padding-right: 20px;"><span>*&nbsp;</span>确认密码：</td>
				<td><input type="password" id="psw2" class="err_3"></td>
				<td style="text-align: left; padding-left: 20px;"><span id="err_3"></span></td>
			</tr>
			<tr>
				<td style="text-align: right; padding-right: 20px;"><span>*&nbsp;</span>手机</td>
				<td><input type="text" id="phone" class="err_4"></td>
				<td style="text-align: left; padding-left: 20px;"><span id="err_4"></span></td>
			</tr>
			<tr>
				<td style="text-align: right; padding-right: 20px;"><span>*&nbsp;</span>动态码</td>
				<td><input type="text" style="width: 150px;" id="phoneCode"><input type="button"  onclick="sendPhoneCode()" id="send_psw" value="发送验证码"></td>
				<td style="text-align: left; padding-left: 20px;"><span id="err_4_code"></span></td>
			</tr>
			<tr>
				<td style="text-align: right; padding-right: 20px;"><span>&nbsp;</span>姓名</td>
				<td><input type="text" id="userName" style="width: 150px;"></td>
				<td style="text-align: left; padding-left: 20px;"><span id="err_4_username"></span></td>
			</tr>
			<tr>
				<td style="text-align: right; padding-right: 20px;">身份证</td>
				<td><input type="text" id="idcard"></td>
				<td style="text-align: left; padding-left: 20px;"><span id="err_5"></span></td>
			</tr>
			<tr>
				<td style="text-align: right; padding-right: 20px;"><span>*&nbsp;</span>验证码</td>
				<td style="position: relative;">
					<input type="text" value="" id="login_alert_yanzheng" style="width: 90px; height: 30px;margin-left: 100px;">
					<img  src="<%=request.getContextPath() %>/getLoginImg" alt="验证码" id="LoginImage" />
					<a href="javascript:reload();"><label>换一张</label></a><br>
				</td>
				<td style="text-align: left; padding-left: 20px;"><span id="err_6"></span></td>
			</tr>
			<tr>
				<td style="text-align: right; padding-right: 20px;"></td>
				<td style="text-align: center;"><input type="button" value="立即注册" id="register_but"></td>
				<td style="text-align: left; padding-left: 20px;"></td>
			</tr>
		</table>
	</div>
	<script>
	var curCount = 60;
		$(function() {
			$('table input').click(function() {
				var id = this.className;
				$('#' + id).html('');
			})
			$('#register_but').click(function() {
				$('#err_1').html('');$('#err_2').html('');$('#err_3').html('');$('#err_4').html('');$('#err_4_code').html('');
				$('#err_5').html('');$('#err_6').html('');
				var index = true;
				if($('#userId').val() == '') {
					index = false;
					$('#err_1').html('账号不为空');
				}else if($('#err_1').html() !=""){
					index = false;
				}
				if($('#psw').val().length < 6 && $('#psw').val().length >= 0) {
					index = false;
					$('#err_2').html('密码长度小于6')
				}
				if($('#psw2').val() != $('#psw').val()) {
					index = false;
					$('#err_3').html('确认密码不一致！')
				}
				if($('#phone').val().length < 11) {
					index = false;
					$('#err_4').html('手机格式错误');
				}
				if($('#login_alert_yanzheng').val().length == 0) {
					index = false;
					$('#err_6').html('请输入验证码');
				}
				if(index){
					valUserId();
				}
				
			});
		})
		
		function register(){
			var form = new Object();
			form.account = $("#userId").val();
			form.pwd = $("#psw").val();
			form.username = $("#userName").val();
			form.idnum = $("#idcard").val();
			form.mobile = $("#phone").val();
			$.ajax({    
		        type: "POST", // 用POST方式传输    
		        dataType: "text", // 数据格式:JSON    
		        url: "${ctx}/register", // 目标地址    
		        data: form,    
		        success: function (data){     
		        	alert("注册成功");
		        	window.location.href = "${ctx}/index";
		        },error: function (XMLHttpRequest, textStatus, errorThrown) {     
		                
		        },       
		    }); 
		}
		
		function reload(){
			document.getElementById("LoginImage").src="${ctx}/getLoginImg?date="+new Date().getTime();
			$("#login_alert_yanzheng").val("");   // 将验证码清空
		}
		
		function valPhoneCode(){
			//获取用户输入的验证码，并后台验证是否输入正确
			var phoneCode = $("#phoneCode").val();
			if(phoneCode == '' || phoneCode == null){
				$("#err_4_code").html("请输入6位长度的手机动态码！");
				return;
			}
			
			$.ajax({    
		        type: "POST", // 用POST方式传输    
		        dataType: "text", // 数据格式:JSON    
		        url: "${ctx}/validatePhoneCode", // 目标地址    
		        data: "phoneCode=" + phoneCode,    
		        success: function (data){     
		        	data = JSON.parse(data);
		            if(data.status == '1'){
		            	$("#err_4_code").html("手机动态验证码输入有误，请重新输入");
		            	$("#phoneCode").focus();
		            }else{
		            	$("#err_4_code").html("");
		            	register();//提交注册
		            }
		        },error: function (XMLHttpRequest, textStatus, errorThrown) {     
		                
		        },       
		    }); 
		}
		
		function valImg(){
			//获取用户输入的验证码，并后台验证是否输入正确
			var checkcode = $("#login_alert_yanzheng").val();
			
			if(checkcode == '' || checkcode == null){
				$("#err_6").html("请输入验证码！");
				return;
			}
			
			
			$.ajax({    
		        type: "POST", // 用POST方式传输    
		        dataType: "text", // 数据格式:JSON    
		        url: "${ctx}/validateLoginImg", // 目标地址    
		        data: "checkcode=" + checkcode,    
		        success: function (data){     
		        	data = JSON.parse(data);
		            if(data.status == '1'){
		            	$("#err_6").html("验证码输入有误，请重新输入");
		            	$("#login_alert_yanzheng").focus();
		            }else{
		            	$("#err_6").html("");
		            	valPhoneCode();	//验证手机验证码
		            }
		        },error: function (XMLHttpRequest, textStatus, errorThrown) {     
		                
		        },       
		    }); 
			
		}
		
		function sendPhoneCode(){
			var phone = $("#phone").val();
			if(phone == null || phone == ""){
				$("#err_4").html("请输入手机号");
				return;
			}
			
			 $.ajax({    
		        type: "POST", // 用POST方式传输    
		        dataType: "text", // 数据格式:JSON    
		        url: "${ctx}/getPhoneValCode", // 目标地址    
		        data: "phone=" + phone,    
		        success: function (data){     
		        	data = JSON.parse(data);
		            if(data.status == '1'){
		            	$("#err_4_code").html("获取验证码次数过多，请在30分钟后在尝试。");
		            }else{
		            	$("#err_4_code").html("");
		                $("#send_psw").attr("disabled", "true");    
		                $("#send_psw").val("请在" + curCount + "秒内输入验证码");    
		                InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器，1秒执行一次
		            }
		        },error: function (XMLHttpRequest, textStatus, errorThrown) {     
		                
		        }       
		    }); 
		}
		
		//timer处理函数    
		function SetRemainTime() {    
		    if (curCount == 0) {                    
		        window.clearInterval(InterValObj);// 停止计时器    
		        $("#send_psw").removeAttr("disabled");// 启用按钮    
		        $("#send_psw").val("重新发送验证码");    
		        curCount = 60; // 清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
		    }else {    
		        curCount--;    
		        $("#send_psw").val("请在" + curCount + "秒内输入验证码");    
		    }    
		}
		
		function valUserId(){
			var userId = $("#userId").val();
			
			if(userId == null || userId == ""){
				$("#err_1").html("请输入账号");
				return ;
			}
			$.ajax({    
		        type: "POST", // 用POST方式传输    
		        dataType: "text", // 数据格式:JSON    
		        url: "${ctx}/valUserId", // 目标地址    
		        data: "userId=" + userId,    
		        success: function (data){     
		        	data = JSON.parse(data);
		            if(data.status == '1'){
		            	$("#err_1").html("账号重复，请重新输入");
		            	$("#userId").focus();
		            }else{
		            	$("#err_1").html("");
		            	valImg();
		            }
		        },error: function (XMLHttpRequest, textStatus, errorThrown) {     
		                
		        }       
		    }); 
		}
	</script>
</body>
</html>
