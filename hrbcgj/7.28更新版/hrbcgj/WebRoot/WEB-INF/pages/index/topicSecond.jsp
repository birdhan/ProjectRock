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
		<script src="${ctx}/javascript/breakpage/listBreakPage.js"></script>
		<style type="text/css">
.manu {
	FONT-SIZE:13px;
	PADDING-RIGHT: 3px;
	PADDING-LEFT: 3px;
	PADDING-BOTTOM: 3px;
	MARGIN: 3px;
	PADDING-TOP: 10px;
	TEXT-ALIGN: right;
}

.manu A {
	FONT-SIZE:13px;
	BORDER: #5E5E5E 1px solid;
	PADDING-RIGHT: 5px;
	PADDING-LEFT: 5px;
	PADDING-BOTTOM: 2px;
	MARGIN: 2px;
	COLOR: #5E5E5E;/*#036cb4;*/
	PADDING-TOP: 2px;
	TEXT-DECORATION: none
}

.manu A:hover {
	FONT-SIZE:13px;
	BORDER-RIGHT: #999 1px solid;
	BORDER-TOP: #999 1px solid;
	BORDER-LEFT: #999 1px solid;
	COLOR: #5E5E5E;
	BORDER-BOTTOM: #999 1px solid
}

.manu A:active {
	BORDER-RIGHT: #999 1px solid;
	BORDER-TOP: #999 1px solid;
	BORDER-LEFT: #999 1px solid;
	COLOR: #666;
	BORDER-BOTTOM: #999 1px solid
}

.manu .current {
	FONT-SIZE:13px;
	BORDER-RIGHT: #A2A2A2 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #A2A2A2 1px solid;
	PADDING-LEFT: 5px;
	FONT-WEIGHT: bold;
	PADDING-BOTTOM: 2px;
	MARGIN: 2px;
	BORDER-LEFT: #A2A2A2 1px solid;
	COLOR: #fff;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #A2A2A2 1px solid;
	BACKGROUND-COLOR: #A2A2A2;/*#036cb4;*/
	margin-left: 0px;
}

.manu .disabled {
	FONT-SIZE:13px;
	BORDER-RIGHT: #eee 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #eee 1px solid;
	PADDING-LEFT: 5px;
	PADDING-BOTTOM: 2px;
	MARGIN: 2px;
	BORDER-LEFT: #eee 1px solid;
	COLOR: #999999;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #eee 1px solid;
	margin-left: 0px;
}
</style>
	</head>

  <body>
  
  		<%@ include file="/index/indexTop.jsp" %>
  		
  		<%@ include file="/index/indexBanner.jsp" %>
  		<%@ include file="/index/indexMenu.jsp" %>
  		<%@ include file="/index/indexSearch.jsp" %>
		
			<!--内容-->
		<div id="cgdt_sy">
			<div class="cgxw_list_title"><span>您当前位置：</span>
             			<a href="${ctx }/index?sectionId=index">首页</a> > 
             			城管问计
	        </div>
			
			<form id="topicForm" action="${ctx}/topicSecond?sectionId=index&secondSectionId=default" method="post">
		<input type="hidden" id="usercuid" name="usercuid" value="${usercuid}"/>
			<table style="margin:0 auto;">
				<tr>
					<td style="font: 20px '微软雅黑';color: #000;padding:40px 40px 20px 0px;border:none;text-align: center;">标题</td>
					<td style="font: 20px '微软雅黑';color: #000;padding:40px 0px 20px 0px;border:none;text-align: center;">日期</td>
				</tr>
				<c:forEach items="${topicList }"  var="topicList" varStatus="status">
				<tr>
					<td style="padding:0px 200px 0px 20px;border:none;text-align: left;">
						<a href="javascript:void(0)" onclick="openDetail('${topicList.id }','${topicList.title }')" style="font: 14px '微软雅黑';color: #000;line-height: 30px;" >
							<c:choose>
								<c:when test="${fn:length(topicList.title)>22}">
									${fn:substring(topicList.title, 0, 22)  }...
								</c:when>
								<c:otherwise>
									${topicList.title}
								</c:otherwise>
							</c:choose>
							</a>
					</td>
					<td style="font: 12px '微软雅黑';color: #000;0px 40px 0px 80px;border:none;text-align: center;line-height: 30px;">（<fmt:formatDate value="${topicList.createtime }" pattern="yyyy-MM-dd"/>）</td>
				</tr>
			</c:forEach>
			</table>
		
		<div style="margin:0 auto;margin-top:20px;text-align: center;">
			<table style="width:100%;">
				<tr>
					<td style="text-align: right;">
						<!-- 分页标签 pageSize:总共页码数 curPage:当前页 totalNum:每页显示的记录数-->
	<cloud:breakPage pageSize="${resultSize}" url="${ctx}/topicSecond?sectionId=index&secondSectionId=default" curPage="${curPage}" formId="topicForm" totalNum="${pageSize}"/>					</td>
				</tr>
			  </table>
		</div>
		
		</form>
		</div>
		</div>

    	<%@ include file="/index/indexButtom.jsp" %>
	</body>
	<script type="text/javascript">
	
      function openDetail(id,title){
		var url = "${ctx}/topicDetail?sectionId=index&secondSectionId=default&id="+id+"&title="+title;
		window.open(url);
	}
      </script>
</html>

