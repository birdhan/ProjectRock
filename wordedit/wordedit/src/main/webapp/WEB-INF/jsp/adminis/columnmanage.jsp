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
	    url: "/wordedit/back/updatecolumn" ,
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
function submit1(){
	var arage=document.getElementById('grage').value;
	var cname=document.getElementById('cname').value;
	if(cname==""){
		alert("栏目不能为空");
	}else {
		var datas={
				name:cname,
				grade:arage
		}
		$.ajax({
		    type: "POST",
		    dataType: "json",
		    url: "/wordedit/back/addcolumn" ,
		    data: datas,
		    success: function (result) {
		    	alert(result["result"]);
		    },
		    error:function(){
		   	 alert("error");
		    }
		})
	}
}
function shanchusss(id){
	if(confirm("请谨慎操作！")){
	window.location.href="${pageContext.request.contextPath}/back/deletecol?id="+id;
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
                  <a href="#demo3" class="list-group-item " data-toggle="collapse">创建栏目</a>
                  <div class="collapse" id="demo3" style="padding-left: 15px;">
                  <br>
                    <label>栏目名称</label>
                    <input type="text" id="cname"><br><br>
                    <label>栏目级别</label>
                    <select style="width: 126px;height: 23.15px;" id="grage">
                    <option value="first">一级栏目</option>
                    <c:forEach items="${findbyfirst }" var="findbyfirst">
                    <option value="${findbyfirst.id }">${findbyfirst.cname }</option>
                    </c:forEach>
                    </select><br><br>
                    <button class="btn btn-link" onclick="submit1()">创建</button>
                  </div>
                    </li>
				</ul>
			</div>
			<div class="col-xs-12 col-sm-9 content">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a class="panel-title" href="#" style="margin-left: 50px;">栏目管理（注：本页栏目为系统栏目将会下放给所有用户）</a>
					</div>
					<div class="panel-body">
						<div class="content-row">
							<table class="table table-bordered">
								<tr>
									<th>栏目名称（注：一级栏目）</th>
									<th>修改</th>
									<th>删除</th>
								</tr>
								<c:forEach items="${findbyfirst }" var="findbyfirst1">
								<tr>
									<td style="width: 50%;"><input type="text" value="${findbyfirst1.cname }" disabled="disabled" id="${findbyfirst1.id }"><button onclick="queren1('${findbyfirst1.id }')" class="${findbyfirst1.id }" style="display: none;">确认</button></td>
									<td><button onclick="xiugai('${findbyfirst1.id }')">修改</button></td>
									<td><button onclick="shanchusss('${findbyfirst1.id }')">删除</button></td>
								</tr>
								</c:forEach>
							</table>
							<table class="table table-bordered">
							<tr>
									<th>栏目名称（注：二级栏目）</th>
									<th>父级栏目</th>
									<th>修改</th>
									<th>删除</th>
									
								</tr>
								<c:forEach items="${findbysecond }" var="findbysecond">
								<tr>
									<td style="width: 40%;"><input value="${findbysecond.cname }" type="text" id="${findbysecond.id }" disabled="disabled"><button onclick="queren1('${findbysecond.id }')" class="${findbysecond.id }" style="display: none;">确认</button></td>
									<td>${findbysecond.fathername }</td>
									<td><button onclick="xiugai('${findbysecond.id }')">修改</button></td>
									<td><button onclick="shanchusss('${findbysecond.id }')">删除</button></td>
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
