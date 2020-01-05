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
function tixian(id){
	if(confirm("你确定要全部提现吗？")){
		window.location.href="${pageContext.request.contextPath}/backmanage/enterprisetixian?id="+id;
		
	}else{
		
	}
	
} 
function dongjie(id){
	if(confirm("你确定要将此账号冻结？")){
		window.location.href="${pageContext.request.contextPath}/backmanage/enterprisedongjie?id="+id;
		
		
	}else{
		
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
							<li><a href="${pageContext.request.contextPath }/backmanage/usermanage" >用户管理</a></li>
							<li><a href="${pageContext.request.contextPath }/backmanage/enterprisemanage" class="active">企业管理</a></li>
							<li><a href="${pageContext.request.contextPath }/backmanage/drugsmanage">药品监督</a></li>
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
									<td>企业负责人</td>
									<td>公司名称</td>
									<td>证件号码</td>
									<td>账号余额</td>
									<td>提现</td>
									<td>操作</td>
									
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${enterall }" var="enterall">
								<tr>
									<td>${enterall.personcharge }</td>
									<td>${enterall.companyname }</td>
									<td>${enterall.personnumber }</td>
									<td>${enterall.ebalance }</td>
									<td><a class="templatemo-edit-btn" onclick="tixian('${enterall.id }')">提现</a></td>
									<td><a class="templatemo-edit-btn" onclick="dongjie('${enterall.id }')">冻结</a></td>
								</tr>
								</c:forEach>
								
							</tbody>
						</table>
					</div>
				</div>
				<div class="templatemo-flex-row flex-content-row"
					style="position: fixed; right: 10%; top: 30%; width: 300px;">
					<div class="col-1">
						<div class="templatemo-content-widget pink-bg shanhu"
							style="display: none;">
							<i class="fa fa-times"></i>
							<h2 class="text-uppercase margin-bottom-10">企业端手动提现</h2>
							<p class="margin-bottom-0">当前账户余额：100</p>
							
							<button class="btn btn-success">全部提现</button>
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