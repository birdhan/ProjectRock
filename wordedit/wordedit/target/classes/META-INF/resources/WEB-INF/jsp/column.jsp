<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>栏目整理</title>
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
<body>
<script type="text/javascript">
function xiugai(id){
	document.getElementById(id).disabled=false;
	var x=document.getElementsByClassName(id);
	x[0].style.display=""; 
}
function submit2(id){
	var values=document.getElementById(id).value;
	var datas={
			id:id,
			name:values
	}
	$.ajax({
	    type: "POST",
	    dataType: "json",
	    url: "/wordedit/word/updatecolumn" ,
	    data: datas,
	    success: function (result) {
	    	document.getElementById(id).disabled=true;
	    	var x=document.getElementsByClassName(id);
	    	x[0].style.display="none";
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
		    url: "/wordedit/word/addcolumn" ,
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
	window.location.href="${pageContext.request.contextPath}/word/deletecolumn?id="+id;
	}
}
</script>
	<!--nav-->
	<nav role="navigation" class="navbar navbar-custom">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button data-target="#bs-content-row-navbar-collapse-5"
				data-toggle="collapse" class="navbar-toggle" type="button">
				<span class="sr-only">HCY</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a href="#" class="navbar-brand">当前用户：${user.name}</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div id="bs-content-row-navbar-collapse-5"
			class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${pageContext.request.contextPath }/word/first">前往首页</a></li>
				<li class="active"><a href="${pageContext.request.contextPath }/word/contrast">查重系统</a></li>
				<li class="active"><a href="${pageContext.request.contextPath }/word/personal">个人中心</a></li>
			</ul>
		</div>
	</div>
</nav>
	<div class="container-fluid">
		<div class="row row-offcanvas row-offcanvas-left">
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation" >
				<ul class="list-group panel">
					<li class="list-group-item"><img
						src="${pageContext.request.contextPath }/images/word1.jpg"
						width="18px" height="18px"> <a href="${pageContext.request.contextPath }/word/personal">返回个人中心</a></li>
					<li>
                  <a href="#demo3" class="list-group-item " data-toggle="collapse">创建栏目</a>
                  <div class="collapse" id="demo3" style="padding-left: 15px;">
                  <br>
                  <form action="" id="form3">
                    <label>栏目名称</label>
                    <input type="text" id="cname"><br><br>
                    <label>栏目级别</label>
                    <select style="width: 126px;height: 23.15px;" id="grage">
                    <option value="first">一级标题</option>
                    <c:forEach items="${findfirst }" var="findfirst">
                    <option value="${findfirst.id }">${findfirst.cname }</option>
                    </c:forEach>
                    </select><br><br>
                    </form>
                    <button class="btn btn-link" onclick="submit1()">创建</button>
                  </div>
                </li>
				</ul>
			</div>
			<div class="col-xs-12 col-sm-9 content">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a class="panel-title" href="#" style="margin-left: 50px;">栏目管理</a>

					</div>
					<div class="panel-body">
						<div class="content-row">
							<table class="table table-bordered">
								<tr>
									<th style="width: 400px;">标题名称</th>
									<th>标题级别</th>
									<th>所属标题</th>
									<th>修改名称</th>
									<th>删除标题</th>
								</tr>
								<c:forEach items="${findfirst }" var="findfir">
								<tr>
									<td style="width: 400px;"><input type="text" id="${findfir.id }" value="${findfir.cname }" disabled="disabled"><button class="${findfir.id }" style="display: none;" onclick="submit2('${findfir.id }')">确定</button></td>
									<td>${findfir.cgrade }</td>
									<td>${findfir.fathername }</td>
									<td><button onclick="xiugai('${findfir.id }')">修改</button></td>
									<td><button  onclick="shanchusss('${findfir.id }')">删除</button></td>
								</tr>
								</c:forEach>
								<c:forEach items="${findsecond }" var="findsecond">
								<tr>
									<td style="width: 400px;"><input type="text" value="${findsecond.cname }" id="${findsecond.id }" disabled="disabled"><button class="${findsecond.id }" style="display: none;" onclick="submit2('${findsecond.id }')">确定</button></td>
									<td>${findsecond.cgrade }</td>
									<td>${findsecond.fathername }</td>
									<td><button onclick="xiugai('${findsecond.id }')">修改</button></td>
									<td><button onclick="shanchusss('${findsecond.id }')">删除</button></td>
								</tr>
								</c:forEach>
							</table>

						</div>


					</div>
					<!-- panel body -->
				</div>
			</div>
			<!-- content -->
		</div>
	</div>
</body>
</html>
