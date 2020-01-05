<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--页脚-->
<div style="clear:both;"></div>
<!--@王子健-友情链接-->
<div style="text-align: center;">友情链接</div>
<div class="service_tu"
	style="text-align: center;margin-left:250px; height:150px;">
	<c:choose>
		<c:when test="${fn:length(linkServiceList) >=4}">
			<c:forEach items="${linkServiceList}" var="linkService" begin="0" end="3">
				<li style="width: 200px;height: 150px">
           		<a href="${linkService.linkUrl}" target="_blank">
           			<img src="${ctx}/uploadpic/getPic.action?id=${linkService.logoPic}" style="width:200px;height:150px;"/><br/>
           		</a>
         	</li>
			</c:forEach>
		</c:when>
		<c:otherwise>
		<c:forEach items="${linkServiceList}" var="linkService">
				<li style="width: 200px;height: 150px">
           		<a href="${linkService.linkUrl}" target="_blank">
           			<img src="${ctx}/uploadpic/getPic.action?id=${linkService.logoPic}" style="width:200px;height:150px;"/><br/>
           		</a>
         	</li>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</div>