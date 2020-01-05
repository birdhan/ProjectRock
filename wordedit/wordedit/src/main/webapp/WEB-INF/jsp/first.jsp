<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>word</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/font-awesome.min.css">
<link href="${pageContext.request.contextPath }/css/googleapis.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/scribbler-global.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/scribbler-doc.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/zidingyi.css">
<link rel="author" href="humans.txt">

<link rel="shortcut icon" type="image/x-icon"
	href="${pageContext.request.contextPath }/images/hljgsxy.jpg">
</head>
<body>
<script type="text/javascript">
function fenye02(value){
	if(value=="end"){
		if("${pagenumber }"=="0"){
		}else{
		window.location.href="${pageContext.request.contextPath}/word/firstpaging?page=${pagenumber }";
		}
	}else if(value=="down"){
		if("${nowpage}"=="${pagenumber }" || "${pagenumber }"=="0"){
			alert("已是最后一页");
		}else{
		var page="${nowpage}"*1+1;
		window.location.href="${pageContext.request.contextPath}/word/firstpaging?page="+page;
		}
	}else if(value=="up"){
		if("${nowpage}"=="1"){
			alert("已是第一页");
		}else{
			var pagedown="${nowpage}"-1;
		window.location.href="${pageContext.request.contextPath}/word/firstpaging?page="+pagedown;
			
		}
	}else{
		window.location.href="${pageContext.request.contextPath}/word/firstpaging?page=1";
	}
	
}
function linkss(name){
	window.location.href="${pageContext.request.contextPath}/word/findtype?page=1&wordtype="+name;
}
</script>
	<div class="doc__bg"></div>
	<nav class="header">
	<h1 class="logo">Word管理系统</h1>
	<ul class="menu">
		<div class="menu__item toggle">
			<span></span>
		</div>
		<li class="menu__item"><a href="${pageContext.request.contextPath}/word/contrast" class="link link--dark">查重系统</a></li>
		<li class="menu__item"><a href="${pageContext.request.contextPath}/word/personal"
			class="link link--dark">个人中心</a></li>
	</ul>
	</nav>
	<div class="wrapper">
		<aside class="doc__nav">
		<ul>
			<li class="js-btn selected">热搜文档</li>
			<c:forEach items="${findAll }" var="findAll">
			<li class="js-btn" onclick="linkss('${findAll.name }')">${findAll.name }</li>
			</c:forEach>
		</ul>
		</aside>
		<article class="doc__content"> <section class="js-section">
		<h3 class="section__title">医药卫生</h3>
		
		<c:forEach items="${findalllimit }" var="findalllimit">
		<div class="contain">
			<img src="${findalllimit.serveraddress }${findalllimit.picurl}"> 
			<br> 
			<a href="${pageContext.request.contextPath}/word/details?id=${findalllimit.id }" title="${findalllimit.wname }">${findalllimit.wname }</a> 
			
			<small>${findalllimit.readnumber }人阅读</small>
		</div>
		</c:forEach>
		<div class="separate">
		<small>共有${findwordnumber }条数据，共分为${pagenumber }页，当前第${nowpage }页</small>
		<div class="button" onclick="fenye02('end')">尾页</div>
		<div class="button" onclick="fenye02('down')">下一页</div>
		<div class="button" onclick="fenye02('up')">上一页</div>
		<div class="button" onclick="fenye02('home')">首页</div>
		</div>

		</section> 
		</article>
	</div>

	<!-- <footer class="footer">Scribbler is a free HTML template
	created exclusively for <a href="https://tympanus.net/codrops/"
		target="_blank" class="link link--light">Codrops</a>.</footer> -->

	<script src="${pageContext.request.contextPath }/js/highlight.min.js"></script>
	<script>
		hljs.initHighlightingOnLoad();
	</script>
	<script src="${pageContext.request.contextPath }/js/scribbler.js"></script>
</body>
</html>