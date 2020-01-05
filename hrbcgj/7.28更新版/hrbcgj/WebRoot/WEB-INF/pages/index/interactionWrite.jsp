<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>哈尔滨城市管理局官网</title>
<%@ include file="/commonjsp/indexCommon-css.jsp"%>
<!-- 样式环境 -->
<%@ include file="/commonjsp/indexCommon-js.jsp"%>
<!-- js环境 -->
<style>
		* {
			margin: 0px;
			padding: 0px;
		}
		
		#bianji input {
			text-align: center;
			width: 530px;
			height: 40px;
		}
		
		#bianji span {
			margin-left: 40px;
		}
		
		#fanhui {
			margin-left: 80px;
		}
		
		#fanhui,
		#fasong {
			width: 107px;
			height: 42px;
		}
	</style>
</head>

<body>

	<%@ include file="/index/indexTop.jsp"%>

	<%@ include file="/index/indexBanner.jsp"%>
	<%@ include file="/index/indexMenu.jsp"%>
	<%@ include file="/index/indexSearch.jsp"%>
	
	<div id="cgdt_sy">
		<div class="cgxw_xqy_title"><span>您当前位置：</span>
			<a href="${ctx }/index?sectionId=index">首页</a> >互动交流 > 我要交流 </div>
		<div id="bianji" style=" text-align: center;margin-top: 35px;"><span>标题：</span><input type="text" id="title">
			<br>
			<span style="float: left;margin-left: 220px;">内容：</span>
			<textarea style=" width: 530px; height: 300px; resize: none;margin-left: -180px;" id="content"> </textarea>
			<br>
			<div style="text-align: center; margin: 0 auto;">
				<button id="fanhui" onclick="interaction()" style="background-color: #4bc7ff;font-size: 18px;color: #FFFFFF;">上一步</button>
				<button id="fasong" onclick="saveInteraction()" style="margin-left: 75px; background-color: #4bc7ff; font-size: 18px;color: #FFFFFF;">发送</button>
			</div>
		</div>
	</div>

	<%@ include file="/index/indexButtom.jsp"%>
</body>
<script type="text/javascript">
	function saveInteraction(){
		var sessionUserId = "${sessionScope.webSiteLoginUser.account}";
		var loginUserCuid = "${sessionScope.webSiteLoginUser.id}";
		if(sessionUserId == "" || sessionUserId == null){
			$('#login_background').css('display', 'block');
			$('#login_alert').css('display', 'block');
			return;
		}
		
		var title = $("#title").val();
		var content = $("#content").val();
		if(title == ''){
			alert("请输入标题");
			return;
		}else if(content.trim().length == 0){
			alert("请输入内容");
			return;
		}else if(content.trim().length >= 1000){
			alert("内容长度不得超过500个字符");
			return;
		}
		var form = new Object();
		form.title = title;
		form.content = content;
		form.usercuid = loginUserCuid;
		$.ajax({    
	        type: "POST", // 用POST方式传输    
	        dataType: "text", // 数据格式:JSON    
	        url: "${ctx}/saveInteraction", // 目标地址    
	        data: form,    
	        success: function (data){     
	        	alert("发件成功");
	        	window.location.href = "${ctx}/interactionSecond?sectionId=index&secondSectionId=default&usercuid="+loginUserCuid;
	        },error: function (XMLHttpRequest, textStatus, errorThrown) {     
	                
	        },       
	    }); 
	}
	
	function interaction(){
		var sessionUserId = "${sessionScope.webSiteLoginUser.account}";
		var loginUserCuid = "${sessionScope.webSiteLoginUser.id}";
		if(sessionUserId == "" || sessionUserId == null){
			$('#login_background').css('display', 'block');
			$('#login_alert').css('display', 'block');
			return;
		}
		window.location.href = "${ctx}/interactionSecond?sectionId=index&secondSectionId=default&usercuid="+loginUserCuid;
	}
</script>
</html>
