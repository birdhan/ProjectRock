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
</head>
<style>
/**/
input{
border: 1px  solid red;
}
#login_button{
}
/*选项卡的样式*/
.demo {
	padding: 1em 0;
}

a:hover,a:focus {
	outline: none;
	text-decoration: none;
}

.tab .nav-tabs {
	margin-left: -240px;
}

.tab .nav-tabs li {
	margin: 0;
	width: 240px;
	height: 60px;
}

.tab .nav-tabs li a {
	font-size: 18px;
	color: #999898;
	margin: 0;
	padding: 20px 25px;
	border-radius: 0;
	border: none;
	text-transform: uppercase;
	position: relative;
}

.tab .nav-tabs li a:hover {
	border-top: none;
	border-bottom: none;
	border-right-color: #ddd;
}

.tab .nav-tabs li.active a,.tab .nav-tabs li.active a:hover {
	color: #fff;
	border: none;
	background: #1fc1dd;
	border-right: 1px solid #ddd;
}

.tab .nav-tabs li.active a:before {
	content: "";
	width: 58%;
	height: 0px;
	background: #fff;
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	margin: 0 auto;
}

.tab .nav-tabs li.active a:after {
	content: "";
	border-top: 10px solid #1fc1dd;
	border-left: 10px solid transparent;
	border-right: 10px solid transparent;
	position: absolute;
	bottom: -10px;
	left: 43%;
}

.tab .tab-content {
	width: 800px;
	margin-left: -240px;
	font-size: 13px;
	color: #999898;
	line-height: 25px;
	background: #fff;
	padding: 20px;
	border-top: none;
}

.tab .tab-content h3 {
	font-size: 24px;
	color: #999898;
	margin-top: 0;
}

@media only screen and (max-width: 800px) {
	.tab .nav-tabs li {
		width: 100%;
		text-align: center;
	}
	.tab .nav-tabs li.active a,.tab .nav-tabs li.active a:after,.tab .nav-tabs li.active a:hover
		{
		border: none;
	}
}
</style>

<body>
	<%@ include file="/index/indexTop.jsp"%>
	<%@ include file="/index/indexBanner.jsp"%>
	<%@ include file="/index/indexMenu.jsp"%>
	<%@ include file="/index/indexSearch.jsp"%>
	<!--内容-->
	<div id="cgdt_sy">
		<div class="cgxw_xqy_title">
			<span>您当前位置：</span> <a href="index.html">首页</a> > 民意征集
		</div>
		<div class="demo">
			<div class="container">
				<div class="row">
					<div class="col-lg-offset-3 col-lg-6">
						<div class="tab" role="tabpanel">
							<!-- Nav tabs -->
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation" class="active"
									style="background-color: #c3f6ff;"><a href="#Section1"
									aria-controls="home" role="tab" data-toggle="tab"
									style=" text-align: center;">已完成的投票</a>
								</li>
								<li role="presentation" style="background-color: #c3f6ff;">
									<a href="#Section2" aria-controls="profile" role="tab"
									data-toggle="tab" style="text-align: center;">进行中的投票</a>
								</li>
							</ul>
							<!-- 内容 -->
							<div class="tab-content tabs">
								<div role="tabpanel" class="tab-pane fade in active"
									id="Section1">
									<li style="list-style: none;margin-top: 20px;"><span
										style="font:18px '微软雅黑';color: #000;padding-left: 30px;">标题</span><span
										style="float: right;font:18px '微软雅黑';color: #000;">
											截止时间</span></li>
									<c:forEach var="it" items="${TVoteList}">
										<li><a href="javascript:void(0);"
											onclick="TmyDetail('${it.id}')"
											style="font: 14px '微软雅黑';color: #000;line-height: 30px;">
												<c:choose>
													<c:when test="${fn:length(it.title)>25}">${fn:substring(it.title, 0, 25)  }...</c:when>
													<c:otherwise>${it.title}</c:otherwise>
												</c:choose> </a> <span style="float: right;">${fn:substring(it.endTime,0,10)}</span>
										</li>
									</c:forEach>
								</div>
								<div role="tabpanel" class="tab-pane fade" id="Section2">
									<div style="margin-top: 20px;margin-bottom:20px ;">
										<li style="list-style: none;margin-top: 20px;"><span
											style="font:18px '微软雅黑';color: #000;padding-left: 30px;">标题</span><span
											style="float: right;font:18px '微软雅黑';color: #000;">
												截止时间</span></li>
									</div>
									<c:forEach var="it" items="${FVoteList}">
										<li><a href="javascript:void(0);"
											onclick="myDetail('${it.id}')"
											style="font: 14px '微软雅黑';color: #000;line-height: 30px;">
												<c:choose>
													<c:when test="${fn:length(it.title)>25}">${fn:substring(it.title, 0, 25)  }...</c:when>
													<c:otherwise>${it.title}</c:otherwise>
												</c:choose> </a><span style="float: right;">${fn:substring(it.endTime,0,10)}</span>
										</li>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/index/indexButtom.jsp"%>
</body>
<script>
	function myDetail(letterId) {
		var usercuid="${sessionScope.webSiteLoginUser.id}";
		window.open("${ctx}/myDetail?sectionId=index&secondSectionId=default&letterId="
						+ letterId + "&usercuid=" + usercuid);
	}

	function TmyDetail(letterId) {		
		var usercuid = "${usercuid}";
		window.open("${ctx}/TmyDetail?sectionId=index&secondSectionId=default&letterId="
						+ letterId + "&usercuid=" + usercuid);
	}
</script>
</html>
