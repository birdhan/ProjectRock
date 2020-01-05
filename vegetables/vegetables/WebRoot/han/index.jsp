<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="head.jsp"%>
<style>
.index1 {
	color: #ECB731;
	border: 2px solid #ECB630;
}
</style>

<body>
	<!-- header -->
	<%@include file="header.jsp"%>

	<!--portfolio-->
	<div class="main-grid-one4" id="five">

		<div class="portfolio">
			<div class="temp-heading">
				<h3 class="main-head">Menu</h3>
			</div>
			<div class="menu-port">
				<div class="container">
					<div class="menu-grid-main">
						<c:forEach items="${list}" var="veg">
							<div
								class="col-md-4 menu-grid wow ${veg.animation }"
								data-wow-delay="0.3s"  style="height: 400px;">
								<img
									onclick="details('${veg.did}','${veg.dishname }','${veg.title }','${veg.price }','${veg.details }','${veg.picture }')"
									src="http://www.hanchunyang.com/vagetables/${veg.picture }" alt=""
									class="img-responsive" style="height: 250px;">
								<h4>
									<b>菜品名称:</b>${veg.dishname}
								</h4>
								<h4>
									<b>售价:</b>￥${veg.price}
								</h4>
								<h4>
									<b>简要介绍:</b>${veg.details}
								</h4>
							</div>
						</c:forEach>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>

		</div>


		<%@include file="details.jsp"%>


		<!-- footer -->
		<%@include file="footer.jsp"%>
</body>
</html>