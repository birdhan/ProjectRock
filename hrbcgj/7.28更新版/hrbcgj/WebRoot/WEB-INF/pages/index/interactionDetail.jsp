<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/taglibs/cloud.tld" prefix="cloud"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>哈尔滨城市管理局官网</title>
		<%@ include file="/commonjsp/indexCommon-css.jsp" %>		<!-- 样式环境 -->
		<%@ include file="/commonjsp/indexCommon-js.jsp" %>			<!-- js环境 -->
	</head>
  <style>
		* {
			margin: 0px;
			padding: 0px;
		}
		
		#bianji input {
			text-align: center;
			width: 530px;
			height: 40px;
		}
		
		#bianji span {
			margin-left: 40px;
		}
		
		#fanhui {
			margin-left: 80px;
		}
		
		#fanhui,
		#fasong {
			width: 107px;
			height: 42px;
		}
		
		body table {
			margin: 0 auto;
		}
		
		table tr {}
		
		table tr td {
			border: none;
		}
	</style>
  <body>
  
  		<%@ include file="/index/indexTop.jsp" %>
  		
  		<%@ include file="/index/indexBanner.jsp" %>
  		<%@ include file="/index/indexMenu.jsp" %>
  		<%@ include file="/index/indexSearch.jsp" %>
		
			<!--内容-->
		<div id="cgdt_sy">
			<div class="cgxw_list_title"><span>您当前位置：</span>
             			<a href="${ctx }/index?sectionId=index">首页</a> > 
             			<a href="${ctx }/interactionSecond?sectionId=index&secondSectionId=default">互动交流</a> > 
             			互动交流详情
	        </div>
			<div>
				<div style="font-size:30px;margin-left: 40px;margin-top: 20px;">留言内容</div>
				<table>
					<tr>
						<td style="width: 100px;font-size: 16px; font-family: '微软雅黑'; color: #000000;text-align: right;"> 标题：</td>
						<td style="font-size: 14px; color: #767676; "> ${interaction.title }</td>
					</tr>
					<tr>
						<td style="width: 100px; width: 100px;font-size: 16px; font-family: '微软雅黑'; color: #000000;text-align: right;">留言时间：</td>
						<td style="font-size: 14px; color: #767676; "><fmt:formatDate value="${interaction.reqtime }" pattern="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<td style="width: 120px;font-size: 16px; font-family: '微软雅黑'; color: #000000;text-align: right;"><span style="width:115px; margin-top: -100px;">留言内容：</span></td>
						<td style="font-size: 14px; color: #767676; width: 390px;">
							${interaction.reqcontent }
						</td>
					</tr>

				</table>

			</div>
			<hr />
			<div style="font-size:30px;margin-top: -30px;margin-left: 40px;" >政府回复</div>
			<p style="width: 520px;font: 12px '微软雅黑';color: #767676; margin: 0 auto;">${interaction.reqcontent }<br><br><br><span style="float: right;"><fmt:formatDate value="${interaction.reqtime }" pattern="yyyy-MM-dd"/></span></p>
		</div>





    	<%@ include file="/index/indexButtom.jsp" %>
	</body>
</html>

