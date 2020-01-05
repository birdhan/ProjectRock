<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>文档查重</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/font-awesome.min.css">
<link href="${pageContext.request.contextPath }/css/googleapis.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/scribbler-global.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/scribbler-landing.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/zidingyi.css">
<link rel="author" href="humans.txt">

<link rel="shortcut icon" type="image/x-icon"
	href="${pageContext.request.contextPath }/images/hljgsxy.jpg">

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/railscasts.min.css">

</head>
<body>
	<nav>
	<div class="logo"></div>
	<ul class="menu">
		<div class="menu__item toggle">
			<span></span>
		</div>
		<li class="menu__item"><a href="${pageContext.request.contextPath }/word/first" class="link link--dark">返回首页</a></li>
		<li class="menu__item"><a href="${pageContext.request.contextPath }/word/personal" class="link link--dark">个人中心</a></li>
	</ul>
	</nav>
	<div class="hero">
		<h1 class="hero__title">文档查重系统</h1>
		<p class="hero__description">支持在线doc，docx格式的文档查重</p>
	</div>
	<div class="hero__terminal">
		<pre>
        <!-- Place your demo code here -->
        <code class="shell-session demo">
        <b>请选择两篇文档进行文档查重</b>
		</code>
      </pre>
	</div>
	<div class="starts" onclick="starts()" id="startcont" style="display: ">开始查重</div>
	<div class="starts1" onclick="clean1()">清空输出</div>
	<div class="starts2" onclick="clean2()">清空缓存</div>
	<div class="wrapper">
		<div class="installation">
			<h3 class="section__title">查重文档库</h3>
			<div class="tab__container">
				<div class="kuang">
					<c:forEach items="${findbyuid }" var="findbyuid">
						<div class="tiao">
							<div class="imgaa">
								<img src="${pageContext.request.contextPath }/images/word1.jpg"
									width="20px" height="20px">
							</div>
							<div class="imga">
								<a calss="biaoqiana">${findbyuid.name }</a>
							</div>
							<div class="imgb"
								onclick="addcon('${findbyuid.wfid }','A','${findbyuid.name }')">文档A</div>
						</div>
					</c:forEach>
				</div>

				<div class="kuang">
					<c:forEach items="${findbyuid }" var="findbyuid1">
						<div class="tiao">
							<div class="imgaa">
								<img src="${pageContext.request.contextPath }/images/word1.jpg"
									width="20px" height="20px">
							</div>
							<div class="imga">
								<a calss="biaoqiana">${findbyuid1.name }</a>
							</div>
							<div class="imgb"
								onclick="addcon('${findbyuid1.wfid }','B','${findbyuid1.name }')">文档B</div>
						</div>
					</c:forEach>
				</div>

			</div>
		</div>
	</div>
	<div class="space"></div>
	<script src="${pageContext.request.contextPath }/js/highlight.min.js"></script>
	<script>
		hljs.initHighlightingOnLoad();
	</script>
	<script src="${pageContext.request.contextPath }/js/scribbler.js"></script>
	<script src="${pageContext.request.contextPath }/js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript">
	function starts(){
		
		var Asessionname=sessionStorage.getItem('Acontrastname');
		var Bsessionname=sessionStorage.getItem('Bcontrastname');
		var Asession=sessionStorage.getItem('Acontrast');
		var Bsession=sessionStorage.getItem('Bcontrast');
		if(Asession==null || Bsession==null){
			alert("请正确选择两篇文档");
		}else{
			document.getElementById("startcont").style.display="none";
			document.getElementsByClassName('demo')[0].innerHTML+="<br>正在对文档A&nbsp;"+Asessionname+"&nbsp;文档B&nbsp;"+Bsessionname+"&nbsp做重复率计算<br>";
			document.getElementsByClassName('demo')[0].innerHTML+="系统正在计算，请稍后！<br>";
			
			var datas={ 
					word1:Asession,
					word2:Bsession
			}
			$.ajax({
			    type: "POST",
			    dataType: "json",
			    url: "/wordedit/word/startcontrast" ,
			    data: datas,
			    success: function (result) {
			    	var sgss=result["result"];
			    	document.getElementsByClassName('demo')[0].innerHTML+="文档相似度为：&nbsp;"+sgss+"<br>";
			    	document.getElementById("startcont").style.display="";
			    },
			    error:function(){
			   	 alert("error3");
			    }
			})
		}
	}
	
	function clean1(){
		var Asession=sessionStorage.getItem('Acontrastname');
		var Bsession=sessionStorage.getItem('Bcontrastname');
		
		document.getElementsByClassName('demo')[0].innerHTML="<b>请选择两篇文档进行文档查重</b><br>";
		
		if(Asession!=null){
			document.getElementsByClassName('demo')[0].innerHTML+="当前选中文档A:"+Asession+"<br>";
		}
		if(Bsession!=null){
			document.getElementsByClassName('demo')[0].innerHTML+="当前选中文档B:"+Bsession+"<br>";
		}
		
	}
	function addcon(id,choise,name){
		 if(choise=="A"){
			sessionStorage.setItem('Acontrast',id);
			sessionStorage.setItem('Acontrastname',name);
			document.getElementsByClassName('demo')[0].innerHTML +="选择文档A："+name+"<br>";
		}else{
			sessionStorage.setItem('Bcontrast',id);
			sessionStorage.setItem('Bcontrastname',name);
			document.getElementsByClassName('demo')[0].innerHTML +="选择文档B："+name+"<br>";
		}
		
	}
	function clean2(){
		sessionStorage.removeItem('Acontrast');
		sessionStorage.removeItem('Acontrastname');
		sessionStorage.removeItem('Bcontrast');
		sessionStorage.removeItem('Bcontrastname');
		window.location.href="${pageContext.request.contextPath}/word/clearcontrast";
	}
	</script>
</body>
</html>