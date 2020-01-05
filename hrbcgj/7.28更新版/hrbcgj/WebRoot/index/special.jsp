<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--王子健-专题专栏-->
<div class="service">
	<div class="service_title">专题专栏</div>
	<div class="service_tu" style="text-align: center;">
	 <c:choose>
		<c:when test="${fn:length(specialServiceList) >=7}">
			<c:forEach items="${specialServiceList}" var="specialService"
				begin="0" end="6">
				<li><a href="${specialService.weburl}" target="_blank"> <img
						src="${ctx}/uploadpic/getPic.action?id=${specialService.picurl}"
						style="width:80px;height:80px;" /><br /> </a>
				</li>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach items="${specialServiceList}" var="specialService">
				<li><a href="${specialService.weburl}" target="_blank"> <img
						src="${ctx}/uploadpic/getPic.action?id=${specialService.picurl}"
						style="width:80px;height:80px;" /><br /> </a>
				</li>
			</c:forEach>
		</c:otherwise>
		 </c:choose>
	</div>
</div>
