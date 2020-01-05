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
* {
	margin: 0px;
	padding: 0px;
}

#Section1 li,#Section2 li {
	margin-left: 50px;
	list-style: disc;
}

#Section1 li a,#Section2 li a {
	color: black;
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

table tr {
	border: 1px solid red;
}

table tr td {
	border: 1px solid red;
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
			<span>您当前位置：</span> <a href="index.html">首页</a>投票
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
									style=" text-align: center;">投票</a></li>
								
							</ul>
							<!-- 内容 -->
							<div class="tab-content tabs">
								<div role="tabpanel" class="tab-pane fade in active"
									id="Section1">
									
								 <div class="cgxw_xqy_nr">
              <h1>${vote.title }</h1>
              <h6><span>发布时间：${vote.startTime }</span></h6>
              <p>${vote.content }</p>
              <div style="margin-top: 100px;text-align: center;">
              <form id="form" action="${ctx}/tp" method="post">
                <c:forEach var="it" items="${xuanList}">
                  <input type="radio" name="r" id="r" value="${it.id}" checked="checked"/><label for="xx1" style="display: inline;">${it.name}</label>
                </c:forEach>
                <br/>
              	<input type="button" value="投票" style="background-color: #a2ccff;margin: 0 auto;" onclick="TP()"/>
              	<input type="hidden" name="sectionId" value="index"/>
              	<input type="hidden" name="secondSectionId" value="default"/>
              	<input type="hidden" name="letterId" value="${vote.id}"/>
                <input type="hidden" name="userid" value="${sessionScope.webSiteLoginUser.id}"/>          
              	</form>
              </div>
            </div>
								</div>
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
    function TP() {
		var sessionUserId = "${sessionScope.webSiteLoginUser.account}";
		var usercuid="${sessionScope.webSiteLoginUser.id}";
		if (sessionUserId == "" || sessionUserId == null) {
			$('#login_background').css('display', 'block');
			$('#login_alert').css('display', 'block');
			return;
		}    
          document.getElementById('form').submit();	
		
	}
  </script>
</html>
