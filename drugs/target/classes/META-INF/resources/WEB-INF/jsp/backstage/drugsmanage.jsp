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
function shanchuuser(id){
	if(confirm("确定删除违禁药品？")){
		window.location.href="${pageContext.request.contextPath}/backmanage/drugsdelete?id="+id;
		
	}
	
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
							<li><a href="${pageContext.request.contextPath }/backmanage/usermanage">用户管理</a></li>
							<li><a href="${pageContext.request.contextPath }/backmanage/enterprisemanage">企业管理</a></li>
							<li><a href="${pageContext.request.contextPath }/backmanage/drugsmanage" class="active">药品监督</a></li>
							<li><a href="${pageContext.request.contextPath }/backmanage/capitalmanage">资金管理</a></li>
							<li><a href="${pageContext.request.contextPath }/backmanage/examinemanage">账号审核</a></li>
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

									<td>药品名称</td>
									<td>药企名称</td>
									<td>产地</td>
									<td>库存</td>
									<td>售价</td>
									<td>充值</td>
									<td>违禁清除</td>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${drugsadmin }" var="drugsadmin">
								<tr>
									<td>${drugsadmin.dname }</td>
									<td>${drugsadmin.companyname }</td>
									<td>${drugsadmin.companyadd }</td>
									<td>${drugsadmin.stock }</td>
									<td>${drugsadmin.dprice }</td>
									<td><a class="templatemo-edit-btn dianji" onclick="xiangqiang('${drugsadmin.dname }','${drugsadmin.companyname }','${drugsadmin.type }','${drugsadmin.companyadd }','${drugsadmin.dprice }','${drugsadmin.usetype }','${drugsadmin.stock }','${drugsadmin.jianshu }','${drugsadmin.detais }')">详情</a></td>
									<td><a class="templatemo-edit-btn" onclick="shanchuuser('${drugsadmin.id }')">删除</a></td>
								</tr>
								</c:forEach>
								
							</tbody>
						</table>
					</div>
				</div>
<script type="text/javascript">
function xiangqiang(dname,companyname,type,companyadd,dprice,usetype,stock,jianshu,detais){
	document.getElementById("100").innerHTML=dname;
	document.getElementById("200").innerHTML=companyname;
	document.getElementById("300").innerHTML=type;
	document.getElementById("400").innerHTML=companyadd;
	document.getElementById("500").innerHTML=dprice;
	document.getElementById("600").innerHTML=usetype;
	document.getElementById("700").innerHTML=stock;
	document.getElementById("800").innerHTML=jianshu;
	document.getElementById("900").innerHTML=detais;
	 
}
</script>

				<div class="templatemo-flex-row flex-content-row"
					style="position: fixed; right: 10%; top: 30%; width: 500px;">
					<div class="col-1">
						<div class="templatemo-content-widget blue-bg shanhu"
							style="display: none;">
							<i class="fa fa-times"></i>
							<h2 class="text-uppercase margin-bottom-10">详细信息查询</h2>
							<p class="margin-bottom-0">药品名称：<span id="100"></span></p>
							<p class="margin-bottom-0">销售企业：<span id="200">同仁堂</span></p>
							<p class="margin-bottom-0">药物类型：<span id="300">非处方</span></p>
							<p class="margin-bottom-0">药品产地：<span id="400">北京</span></p>
							<p class="margin-bottom-0">在售价格：<span id="500">345</span></p>
							<p class="margin-bottom-0">使用方式：<span id="600">一日三次</span></p>
							<p class="margin-bottom-0">当前库存：<span id="700">3456</span></p>
							<p class="margin-bottom-0">医疗作用：<span id="800">云南白药创可贴，止血，镇痛，消炎，愈创</span></p>
							<p class="margin-bottom-0">药物说明：<span id="900">云南白药创可贴，止血，镇痛，消炎，愈创云南白药创可贴，止血，镇痛，消炎，愈创云南白药创可贴，止血，镇痛，消炎，愈创</span></p>
							<br>
							
							
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