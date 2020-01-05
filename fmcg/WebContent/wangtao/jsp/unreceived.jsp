<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/index.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	type="text/css">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>快销品</title>
</head>
<body>
<script type="text/javascript"> 

function qie(local){
	window.location.href="${pageContext.request.contextPath}/"+local;
}
function received(id){
	window.location.href="${pageContext.request.contextPath}/received?id="+id;
}
</script>
<div style="position: fixed;top: 15px;right: 40px;">
<p>用户:${user.name }</p>
<p>余额:${user.balance }</p>
</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12 logo1">

				<a href="${pageContext.request.contextPath }/index"><img
					src="${pageContext.request.contextPath }/images/logo.png"> </a>
					

			</div>
			<div class="row">
				<div class="col-md-12 ulcen"
					style="height: 50px; background: #4CCFC1; border-radius: 5px;">
					<ul class="list-unstyled list-inline ullist">
						<li onclick="qie('shopping')">购物车</li>
						<li onclick="qie('unshipped')">待发货</li>
						<li onclick="qie('unreceived')">待收货</li>
						<li onclick="qie('finishorder')">已完成</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="container" style="height: 500px; overflow: scroll;">
		<div class="row">
		
		<c:forEach items="${shopping }" var="listshop">
			<div class="col-md-8 col-md-offset-2"
				style="height: 115px; margin-top: 10px; border-radius: 5px; border: 1px solid #B4EEB4;">
				<div class="row">
					<div class="col-md-2 col-md-offset-1">
						<img alt=""
							src="http://localhost:80/pic/${listshop.kpicture }"
							height="111px" width="111px;">
					</div>
					<div class="col-md-4" style="padding-top: 10px;">
						<p>商品名称：${listshop.kname }</p>
						<p>商品类型：${listshop.ktype }</p>
						<p>商品单价：${listshop.kpic }</p>
					</div>
					<div class="col-md-4" style="padding-top: 5px;">
						<p>
							购买数量：<span>${listshop.snumber }</span>
						</p>
						<p><span>订单总价：</span>$<span>${listshop.spay }</span></p>
						<p style="float: right;">
							<button class="btn btn-success" onclick="received('${listshop.id }')">确认收货</button>
						</p>
					</div>
				</div>
			</div>
			
			</c:forEach>
			
		</div>
	</div>


</body>
</html>