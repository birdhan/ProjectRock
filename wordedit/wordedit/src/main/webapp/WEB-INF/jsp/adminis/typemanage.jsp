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
function xiugai(id){
	document.getElementById(id).disabled="";
	document.getElementsByClassName(id)[0].style.display="";
}
function queren1(id){
	
	var values=document.getElementById(id).value;
	var datas={
			id:id,
			name:values
	}
	$.ajax({
	    type: "POST",
	    dataType: "json",
	    url: "/wordedit/back/uptype" ,
	    data: datas,
	    success: function (result) {
	    	document.getElementById(id).disabled="disabled";
	    	document.getElementsByClassName(id)[0].style.display="none"; 
	    	alert(result["result"]);
	    },
	    error:function(){
	   	 alert("error");
	    }
	})
	
	
}
function chuanjian(){
	var id=document.getElementById('input10').value;
	if(id==""){
		alert("类型不能为空！");
	}else{
	window.location.href="${pageContext.request.contextPath}/back/addtype?name="+id;
	}
}
function shanchul(id){
	if(confirm("请谨慎操作！")){
		window.location.href="${pageContext.request.contextPath}/back/deletetype?id="+id;
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
					<li>
                  <a href="#demo3" class="list-group-item " data-toggle="collapse">创建类型</a>
                  <div class="collapse" id="demo3" style="padding-left: 15px;">
                  <br>
                    <label>类型名称</label>
                    <input type="text" id="input10"><br><br>
                    <button class="btn btn-link" onclick="chuanjian()">创建</button>
                  </div>
                    </li>
				</ul>
			</div>
			<div class="col-xs-12 col-sm-9 content">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a class="panel-title" href="#" style="margin-left: 50px;">类型管理</a>
					</div>
					<div class="panel-body">
						<div class="content-row">
							<table class="table table-bordered">
								<tr>
									<th>名称</th>
									<th>修改</th>
									<th>删除</th>
								</tr>
								<c:forEach items="${findAll }" var="findAll">
								<tr>
									<td style="width: 50%;"><input type="text" value="${findAll.name}" disabled="disabled" id="${findAll.id}"><button onclick="queren1('${findAll.id}')" class="${findAll.id}" style="display: none;">确认</button></td>
									<td><button onclick="xiugai('${findAll.id}')">修改</button></td>
									<td><button onclick="shanchul('${findAll.id}')">删除</button></td>
								</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
