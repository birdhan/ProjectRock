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
		
		body table {
			margin: 0 auto;
		}
		
		table tr {}
		
		table tr td {
			border: none;
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
			<a href="${ctx }/index?sectionId=index"">首页</a> >局长信箱 > 我要写信 </div>

		<div>
			<div style="font-size:30px;margin-left: 40px;margin-top: 20px;">发件内容</div>
			<table style="table-layout:fixed">
				<tr>
					<td style="width: 100px;font-size: 16px; font-family: '微软雅黑'; color: #000000;text-align: right;"> 标题：</td>
					<td style="font-size: 14px; color: #767676;word-wrap:break-word;word-break:break-all; "> ${email.title }</td>
				</tr>
				<tr>
					<td style="width: 100px; width: 100px;font-size: 16px; font-family: '微软雅黑'; color: #000000;text-align: right;">发件时间：</td>
					<td style="font-size: 14px; color: #767676; "><fmt:formatDate value="${email.reqtime }" pattern="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<td style="width: 120px;font-size: 16px; font-family: '微软雅黑'; color: #000000;text-align: right;"><span style="width:115px; margin-top: -100px;">邮件内容：</span></td>
					<td style="font-size: 14px; color: #767676; width: 390px;word-wrap:break-word;word-break:break-all;">
						${email.reqcontent }
					</td>
				</tr>

			</table>

		</div>
		<hr />
		<div style="font-size:30px;margin-top: -30px;margin-left: 40px;" <>局长回复</div>
		<p style="width: 520px;font: 12px '微软雅黑';color: #767676; margin: 0 auto;">${email.repcontent }
			<br><br><br><span style="float: right;">${email.reptime }</span>
		</p>
	</div>

	<%@ include file="/index/indexButtom.jsp"%>
</body>
<script type="text/javascript">
	function myEmail(){
		var sessionUserId = "${sessionScope.webSiteLoginUser.account}";
		var loginUserCuid = "${sessionScope.webSiteLoginUser.id}";
		if(sessionUserId == "" || sessionUserId == null){
			$('#login_background').css('display', 'block');
			$('#login_alert').css('display', 'block');
			return;
		}
		window.location.href = "${ctx}/myEmail?sectionId=index&secondSectionId=default&usercuid="+loginUserCuid;
	}
</script>
</html>
