<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--便民服务-->
 <div class="service" style="margin-top: 10px;">
     <div class="service_title">便民服务</div>
     <div class="service_tu">
     	<c:forEach items="${easeServiceList}" var="easyService">
     		<li>
           		<a href="${easyService.linkurl}" target="_blank">
           			<img src="${ctx}/uploadpic/getPic.action?id=${easyService.logopic}" style="width:50px;height:50px;"/><br/>
          			${easyService.name}
           		</a>
         	</li>
     	</c:forEach>
     </div>
