
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--导航-->
<div class="menu_width" style="text-align: center;">
	<ul id="menu" style="width: 1020px;">
		<li
			<c:if test="${sectionId == '' || sectionId == 'index'}">
					 class="menuSelected"
				</c:if>>
			<a href="${ctx }/index?sectionId=index">首页</a></li>
		<c:forEach var="menu" items="${list }" varStatus="status">
			<li
				<c:if test="${sectionId == menu.ID}">
					 class="menuSelected"
				</c:if>>
				<a
				href="${ctx }/secondSection?sectionId=${menu.ID}&secondSectionId=default">${menu.NAME
					}</a></li>
		</c:forEach>

		<li
			<c:if test="${sectionId == null || sectionId == 'other'}">
					 class="menuSelected"
				</c:if>>
			<a href="javascript:void(0)" onclick="myProblem()">问题受理</a></li>

		<li
			<c:if test="${sectionId == null || sectionId == 'other'}">
					 class="menuSelected"
				</c:if>>
			<a href="javascript:void(0)" onclick="myzj()">民意征集</a></li>

		<li><a
			href="${ctx }/interactionSecond?sectionId=index&secondSectionId=default">互动交流</a>
		</li>
<li><a
			href="${ctx }/topicSecond?sectionId=index&secondSectionId=default">城管问计</a>
		</li> 
		<li><a
			href="${ctx }/gongkai?sectionId=index&secondSectionId=default">依申请公开</a>
		</li>

	</ul>
</div>
<script>



	function myProblem() {
		var sessionUserId = "${sessionScope.webSiteLoginUser.username}";
		var loginUserCuid = "${sessionScope.webSiteLoginUser.id}";
		var loginUserMobile = "${sessionScope.webSiteLoginUser.mobile}";
		if (sessionUserId == "" || sessionUserId == null) {
			$('#login_background').css('display', 'block');
			$('#login_alert').css('display', 'block');
			return;
		}
		window.location.href = "${ctx}/problem?sectionId=index&secondSectionId=default&usercuid="
				+ loginUserCuid;
	}

	function myzj() {
		
		window.location.href = "${ctx}/myzj?sectionId=index&secondSectionId=default&usercuid="
				+ loginUserCuid;
	}
</script>

