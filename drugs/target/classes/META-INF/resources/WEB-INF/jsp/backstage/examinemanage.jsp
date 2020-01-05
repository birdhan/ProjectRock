<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>人民大药房</title>
<meta name="description" content="">
<meta name="author" content="templatemo">
<!-- 
    Visual Admin Template
    http://www.templatemo.com/preview/templatemo_455_visual_admin
    -->
<link href="${pageContext.request.contextPath }/css/woyebuzhdao.css"
	rel='stylesheet' type='text/css'>
<link
	href="${pageContext.request.contextPath }/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/css/bootstrap.min001.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/css/templatemo-style.css"
	rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<script type="text/javascript">
window.onload=function(){
	
	var w = document.getElementsByClassName("申请");
	for(i=0;i<w.length;i++){
		w[i].innerHTML = "通过"; 
	}
	
	var q = document.getElementsByClassName("冻结");
	for(i=0;i<q.length;i++){
		q[i].innerHTML = "解冻";
	}
	
	
}


function dongjie(id){
	if(confirm("确定进行账号状态修改？")){
		
		window.location.href="${pageContext.request.contextPath}/backmanage/enterprisestatus?id="+id;
	}else{
		
	}
	
} 


/* function message1(1,2,3,4,5,6,7,8){
	documnet.getElementById("1").innerHTML=1;
	documnet.getElementById("2").innerHTML=2;
	documnet.getElementById("3").innerHTML=3;
	documnet.getElementById("4").innerHTML=4;
	documnet.getElementById("5").innerHTML=5;
	documnet.getElementById("6").innerHTML=6;
	documnet.getElementById("7").innerHTML=7;
	documnet.getElementById("8").innerHTML=8;
} */

function message1(a,b,c,d,e,f,g,h){
	document.getElementById("10").innerHTML=a;
	document.getElementById("20").innerHTML=b;
	document.getElementById("30").innerHTML=c;
	document.getElementById("40").innerHTML=d;
	document.getElementById("50").innerHTML=e;
	document.getElementById("60").innerHTML=f;
	document.getElementById("70").innerHTML=g;
	document.getElementById("80").innerHTML=h;
	
}
</script>
	<!-- Left column -->
	<div class="templatemo-flex-row">
		<div class="templatemo-sidebar">
			<header class="templatemo-site-header">
				<div class="square"></div>
				<h1>医药系统后台管理</h1>
			</header>
			<div class="profile-photo-container">
				<img
					src="${pageContext.request.contextPath }/images/profile-photo.jpg"
					alt="Profile Photo" class="img-responsive">
				<div class="profile-photo-overlay"></div>
			</div>
			<!-- Search box -->
			<header class="templatemo-site-header">
				<div style="color: white;text-align: center;">
				<h2>当前系统截留资金</h2>
				<h2 style="width: 100px;height: auto; background: #39ADB4;border-radius: 5px;margin-left: 30%;">${backmanage.abalance }</h2>
				<p>为保障用户合法权益本系统采取截留交易资金的方式</p>
				</div> 
			</header>

			<div class="mobile-menu-icon">
				<i class="fa fa-bars"></i>
			</div>
		</div>
		<!-- Main content -->
		<div class="templatemo-content col-1 light-gray-bg">
			<div class="templatemo-top-nav-container">
				<div class="row">
					<nav class="templatemo-top-nav col-lg-12 col-md-12">
						<ul class="text-uppercase">
							<li><a href="${pageContext.request.contextPath }/backmanage/usermanage" >用户管理</a></li>
							<li><a href="${pageContext.request.contextPath }/backmanage/enterprisemanage">企业管理</a></li>
							<li><a href="${pageContext.request.contextPath }/backmanage/drugsmanage">药品监督</a></li>
							<li><a href="${pageContext.request.contextPath }/backmanage/capitalmanage">资金管理</a></li>
							<li><a href="${pageContext.request.contextPath }/backmanage/examinemanage" class="active">账号审核</a></li>
						</ul>
					</nav>
				</div>
			</div>
			<div class="templatemo-content-container"
				style="height: 550px; overflow: scroll;">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table
							class="table table-striped table-bordered templatemo-user-table">
							<thead>
								<tr>
									<td>企业负责人</td>
									<td>公司名称</td>
									<td>证件号码</td>
									<td>账号余额</td>
									<td>信息审核</td>
									<td>状态</td>
									<td>操作</td>
									
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${enteradmin }" var="enteradmin">
							
								<tr>
									<td>${enteradmin.personcharge }</td>
									<td>${enteradmin.companyname }</td>
									<td>${enteradmin.personnumber }</td>
									<td>${enteradmin.ebalance }</td>
									<td><a class="templatemo-edit-btn dianji" onclick="message1('${enteradmin.yingye }','${enteradmin.shuiwu }','${enteradmin.weisheng }','${enteradmin.jiankang }','${enteradmin.personcharge }','${enteradmin.personnumber }','${enteradmin.companyname }','${enteradmin.companyadd }')">详细信息</a></td>
									<td>${enteradmin.estatus }</td>
									<td><a class="templatemo-edit-btn ${enteradmin.estatus }" onclick="dongjie('${enteradmin.id }')" ></a></td>
								</tr>
								</c:forEach>
								
							</tbody>
						</table>
					</div>
				</div>
				<div class="templatemo-flex-row flex-content-row"
					style="position: fixed; right: 10%; top: 30%; width: 500px;">
					<div class="col-1">
						<div class="templatemo-content-widget blue-bg shanhu"
							style="display: none;">
							<i class="fa fa-times"></i>
							<h2 class="text-uppercase margin-bottom-10">详细信息审核</h2>
							<p class="margin-bottom-0">营业执照号：<span id="10"></span></p>
							<p class="margin-bottom-0">税务登记：<span id="20"></span></p>
							<p class="margin-bottom-0">卫生许可：<span id="30"></span></p>
							<p class="margin-bottom-0">健康证号：<span id="40"></span></p>
							<p class="margin-bottom-0">负责人：<span id="50"></span></p>
							<p class="margin-bottom-0">证件号：<span id="60"></span></p>
							<p class="margin-bottom-0">企业名称：<span id="70"></span></p>
							<p class="margin-bottom-0">企业地址：<span id="80"></span></p>
							
							<br>
							
							<!-- <button class="btn btn-success">充值</button> -->
						</div>
					</div>
				</div>
				<!-- Second row ends -->
			</div>
		</div>
	</div>

	<!-- JS -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/jquery-1.11.2.min.js"></script>
	<!-- jQuery -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/templatemo-script.js"></script>
	<!-- Templatemo Script -->
	<script>
		$(document).ready(
				function() {
					// Content widget with background image
					var imageUrl = $('img.content-bg-img').attr('src');
					$('.templatemo-content-img-bg').css('background-image',
							'url(' + imageUrl + ')');
					$('img.content-bg-img').hide();
				});
	</script>
</body>
</html>