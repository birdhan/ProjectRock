<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<c:set var="ctx" value="${pageContext.request.contextPath}"/><!-- 设置根路径 -->
	<title>${sc.sysDescName}</title>
	<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css"/><!-- 公共样式 -->
	<link href="${ctx}/css/index.css" rel="stylesheet" type="text/css"/><!-- 首页样式 -->
	<link href="${ctx}/css/left-menu.css" rel="stylesheet" type="text/css" /><!-- 左侧菜单样式 -->
	<link href="${ctx}/css/head_tool.css" rel="stylesheet" type="text/css" /><!-- 头部工具栏样式 -->
	<!-- 皮肤(浅蓝) -->
	<link href="${ctx}/javascript/jquery/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	
	<script src="${ctx}/javascript/common/common_function.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/javascript/jquery/jquery-1.9.0.js"></script>
	<script type="text/javascript" src="${ctx}/javascript/layout.js"></script>
	<script type="text/javascript" src="${ctx}/javascript/menu.js"></script>
	<script type="text/javascript" src="${ctx}/javascript/tool.js"></script>
	
	<!-- jqueryui核心环境 -->
	<script src="${ctx}/javascript/jquery/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${ctx}/javascript/jquery/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
	
	<script language="javascript">
		function logout(url) {
			$.ligerDialog.confirm("您确定要退出登录吗？", function (yes) {
				if(yes) {
					window.location.href = url;
				}
			});			
		}
	</script>
</head>

<body onresize="claLayoutWidthHeight()">
	<div class="tire">
		<div class="header">
			<div class="header_left" >
				<div class="logo"><img src="${ctx}/images/logo.jpg" width="430" height="88" /></div>
				<%--<div class="tool">
					<ul>
						<li class="li_1"><a href="#"></a></li>
						<li class="li_2"><a href="#"></a></li>
						<li class="li_3"><a href="#"></a></li>
						<li class="li_4"><a href="#"></a></li>
						<li class="li_5"><a href="#"></a></li>
						<li class="li_6"><a href="#"></a></li>
					</ul>
				</div><!--tool_end-->--%>
			</div>

			<div class="header_right">
				<div class="help">
					<ul>
						<li><a href="javascript:aboutLinkWin()">帮助</a></li>
						<li><a href="javascript:aboutLinkWin()">关于</a></li>
						<li><a href="javascript:logout('${ctx}/login/logout.do')">退出</a></li>
					</ul>
				</div>
				<p>用户名:${sessionScope.user.userId} </p>
			</div><!--header_right_end-->
		</div><!--header_end-->
		
		<!-- 左侧菜单 -->
		<%@ include file="../commonjsp/left-menu.jsp"%>

		<div class="right" id="right">
 			<iframe src="${ctx}/desc.jsp" frameborder="0" width="100%" height="auto" scrolling="auto" name="rightIframe" id="right_iframe"/>
		</div><!--right_end-->
	</div>
</body>
</html>