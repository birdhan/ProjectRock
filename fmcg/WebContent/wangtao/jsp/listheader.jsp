<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="header_bg">
	<div class="wrap">
		<div class="header">
			<div class="logo">
				<img src="images/logo.png" alt="" />
			</div>
			<div class="h_icon">
				<ul class="icon1 sub-icon1">
					<li><a class="active-icon c1" href="#"></a>
						<ul class="sub-icon1 list">
							<li><h3>个人中心| ${user.name }</h3> <a href=""></a></li>
							<li><p>
									您的宝贝已等候您多时，进入<a
										href="${pageContext.request.contextPath }/shopping">个人中心 </a>
								</p></li>
						</ul></li>
				</ul>
			</div>
			<div class="h_search">
				<form action="${pageContext.request.contextPath }/sousuo">
					<input type="text" value="" name="guanjianzi"> <input type="submit" value="">
				</form>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</div>
<div class="header_btm">
	<div class="wrap">
		<div class="header_sub" style="height: 45px;">
			<div class="h_menu" >
				<ul>

					<li><a href="${pageContext.request.contextPath }/index">首页</a></li>

					<c:forEach items="${typelist }" var="list001">
						|<li><a style="" href="${pageContext.request.contextPath }/indextype?types=${list001.ktype }"> ${list001.ktype }</a></li>
					</c:forEach>



				</ul>
			</div>

			<div class="clear"></div>
		</div>
	</div>
</div>