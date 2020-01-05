<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>管理员</title>
<link rel="shortcut icon" type="image/x-icon"
	href="${pageContext.request.contextPath }/images/hljgsxy.jpg">
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/zidingyi.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/site.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/site.min.js"></script>
</head>
<body style="background-color: black;">
<script type="text/javascript">
function frozen(id,action){
	if(action=="frozen"){
		window.location.href="${pageContext.request.contextPath}/back/modifystatus?id="+id+"&status=冻结";
	}else{
		window.location.href="${pageContext.request.contextPath}/back/modifystatus?id="+id+"&status=活跃";
	}
}
function fenye02(value){
	if(value=="end"){
		if("${pagenumber }"=="0"){
		}else{
		window.location.href="${pageContext.request.contextPath}/back/persionmanage?page=${pagenumber }";
		}
	}else if(value=="down"){
		if("${nownumber}"=="${pagenumber }" || "${pagenumber }"=="0"){
			alert("已是最后一页");
		}else{
		var page="${nownumber}"*1+1;
		window.location.href="${pageContext.request.contextPath}/back/persionmanage?page="+page;
		}
	}else if(value=="up"){
		if("${nownumber}"=="1"){
			alert("已是第一页");
		}else{
			var pagedown="${nownumber}"-1;
		window.location.href="${pageContext.request.contextPath}/back/persionmanage?page="+pagedown;
			
		}
	}else{
		window.location.href="${pageContext.request.contextPath}/back/persionmanage?page=1";
	}
	
}

</script>
	<nav role="navigation" class="navbar navbar-custom">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">后台管理系统</a>
		</div>
	</div>
	</nav>
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-left">
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
				<ul class="list-group panel">
					<li class="list-group-item"><img
						src="${pageContext.request.contextPath }/images/word1.jpg"
						width="18px" height="18px"> <a href="#">管理选项</a></li>
					<li><a class="list-group-item " href="${pageContext.request.contextPath }/back/persionmanage?page=1">用户管理</a></li>
					<li><a class="list-group-item " href="${pageContext.request.contextPath }/back/articlemanage?page=1">文章管理</a></li>
					<li><a class="list-group-item " href="${pageContext.request.contextPath }/back/columnmanage">栏目管理</a></li>
					<li><a class="list-group-item " href="${pageContext.request.contextPath }/back/typemanage">类型管理</a></li>
				</ul>
			</div>
			<div class="col-xs-12 col-sm-9 content">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a class="panel-title" href="#" style="margin-left: 50px;">用户管理</a>
					</div>
					<div class="panel-body">
						<div class="content-row">
							<table class="table table-bordered">
								<tr>
									<th>用户名</th>
									<th style="width: 300px;">密码</th>
									<th>状态</th>
									<th>冻结</th>
									<th>解冻</th>
								</tr>
								<c:forEach items="${findAll }" var="findAll">
								<tr>
									<td>${findAll.name }</td>
									<td style="width: 300px;"><input type="password"
										value="${findAll.pwd}" disabled="disabled"></td>
									<td>${findAll.pstatus }</td>
									<td><button onclick="frozen('${findAll.id }','frozen')">冻结</button></td>
									<td><button onclick="frozen('${findAll.id }','thaw')">解冻</button></td>
								</tr>
								</c:forEach>
							</table>
							<div class="separate">
		                    <small>共有${findpersonalnumber }条数据，共分为${pagenumber }页，当前第${nownumber }页</small>
		                    <div class="fanye" onclick="fenye02('end')">尾页</div>
		                    <div class="fanye" onclick="fenye02('down')">下一页</div>
		                    <div class="fanye" onclick="fenye02('up')">上一页</div>
		                    <div class="fanye" onclick="fenye02('home')">首页</div>
		                    </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
