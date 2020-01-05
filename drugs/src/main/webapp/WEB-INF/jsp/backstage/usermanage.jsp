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
	if(confirm("你确定要将此用户清除？")){
		window.location.href="${pageContext.request.contextPath}/backmanage/deleteconsumer?id="+id;
		
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
			<header class="templatemo-site-header" style="text-align: center;color: white;">
				<!-- <div class="square"></div> -->
				<!-- <h1>管理员：rock</h1> -->
				
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
							<li><a href="${pageContext.request.contextPath }/backmanage/usermanage" class="active">用户管理</a></li>
							<li><a href="${pageContext.request.contextPath }/backmanage/enterprisemanage">企业管理</a></li>
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

									<td>用户姓名</td>
									<td>用户电话</td>
									<td style="width: 200px;">用户地址</td>
									<td>账号类型</td>
									<td>账号余额</td>
									<td>充值</td>
									<td>清除</td>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${useradmin }" var="useradmin">
								<tr>
									<td>${useradmin.uname }</td>
									<td>${useradmin.tel }</td>
									<td style="width: 200px;">${useradmin.address }</td>
									<td>${useradmin.utype }</td>
									<td>${useradmin.ubalance }</td>
									<td><a class="templatemo-edit-btn dianji" onclick="chuancan('${useradmin.id}','${useradmin.ubalance }')">充值</a></td>
									<td><a class="templatemo-edit-btn" onclick="shanchuuser('${useradmin.id}')">违规清理</a></td>
								</tr>
								</c:forEach>
								
							</tbody>
						</table>
					</div>
				</div>

<script type="text/javascript">
function chuancan(id,balance){
	
	document.getElementById("inputo").value=id;
	document.getElementById("inputo1").value=balance;
}

function chongzhi(){
	document.getElementById("form002").submit();
}
</script>
				<div class="templatemo-flex-row flex-content-row"
					style="position: fixed; right: 10%; top: 30%; width: 300px;">
					<div class="col-1">
						<div class="templatemo-content-widget pink-bg shanhu"
							style="display: none;">
							<i class="fa fa-times"></i>
							<h2 class="text-uppercase margin-bottom-10">用户账户充值</h2>
							<!-- <p class="margin-bottom-0">当前账户余额：100</p> -->
							<p class="margin-bottom-0">请选择充值金额</p>
							<br>
							<div class="row form-group">
								<div class="col-lg-12 form-group">
									<form action="${pageContext.request.contextPath }/backmanage/userrecharge" id="form002">
									<input type="hidden" name="id" id="inputo">
									<input type="hidden" name="balanceo" id="inputo1">
										<div class="margin-right-15 templatemo-inline-block">
											<input type="radio" name="balance" id="r4" value="100"> <label
												for="r4" class="font-weight-400"><span></span>100</label>
										</div>
										<div class="margin-right-15 templatemo-inline-block">
											<input type="radio" name="balance" id="r5" value="200" checked>
											<label for="r5" class="font-weight-400"><span></span>200</label>
										</div>
										<div class="margin-right-15 templatemo-inline-block">
											<input type="radio" name="balance" id="r6" value="300"> <label
												for="r6" class="font-weight-400"><span></span>300</label>
										</div>
									</form>
								</div>
							</div>
							<button class="btn btn-success" onclick="chongzhi()">充值</button>
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