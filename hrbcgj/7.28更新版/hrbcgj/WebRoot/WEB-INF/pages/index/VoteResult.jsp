<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/taglibs/cloud.tld" prefix="cloud"%>
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
<script src="${ctx}/javascript/breakpage/listBreakPage.js"></script>
<title>哈尔滨城市管理局官网</title>
<link href="${ctx }/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx }/css/style.css">
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script src='${ctx }/js/jquery.min.js'></script>
</head>
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

#fanhui,#fasong {
	width: 107px;
	height: 42px;
}

body table {
	margin: 0 auto;
}

table tr {
	
}

table tr td {
	border: none;
}
</style>
<body>
	<%@ include file="/index/indexTop.jsp"%>
	<%@ include file="/index/indexBanner.jsp"%>
	<%@ include file="/index/indexMenu.jsp"%>
	<%@ include file="/index/indexSearch.jsp"%>
	<div id="cgdt_sy">
	<div style=" text-align: center;">
	<h1>${vote.title}</h1>
              <h6><span>发布时间：${vote.startTime }</span></h6>
              <p>
				${vote.content}
              </p>
	</div>
		<div style="height: 300px;border-top: 0px;">
			<div id="chart">
				<ul id="bars" style="border-bottom:1px solid red;  margin-top: 150px;">
					<li />
					<c:forEach var="it" items="${xuanList}">
						<input type="hidden" name="x" value="${it.name}" />
						<input type="hidden" name="num" value="${it.number}" />
					</c:forEach>
				</ul>
			</div>
	
			<script src='${ctx }/js/jquery.min.js'></script>
			<script src="${ctx }/js/index.js "></script>
		</div>
		<script src='${ctx }/js/jquery.min.js'></script>
		<script>
			$(function() {
				var x = document.getElementsByName("x");
				var num = document.getElementsByName("num");
				for ( var i = 0; i < x.length; i++) {
					create(num[i].value, x[i].value);
				}
				function create(num, name) {
					var al = $('#bars li').last();
					al.after('<li "><div data-percentage="'
									+ num
									+ '" class="bar" style="height: 100%; border-radius:5px;"><div style="position: absolute; top: 0px;">'
									+ num + '</div></div><span>' + name
									+ '</span></li>');
				}
			})
		</script>
		<script src="${ctx }/js/index.js "></script>
	</div>
	<%@ include file="/index/indexButtom.jsp"%>
</body>
<script src="http://www.jq22.com/jquery/1.9.1/jquery.min.js"></script>
</html>
