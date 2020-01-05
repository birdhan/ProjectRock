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
             			互动交流
	        </div>
			<div style="width:240px;height:60px;background-color:#1fc1dd ;margin-left: 50px;margin-top: 40px;font:24px '微软雅黑';color:#fff;line-height: 60px;text-align: center;">查看留言</div>
			<div style="width:180px;height：50px;background-color: #ffac40;text-align: center;border-radius: 8px;position: absolute;top:120px;right:60px;">
				<a href="javascript:void(0);" onclick="writeInteraction()"style="font:24px '微软雅黑';color:#fff;line-height: 40px;">我要交流</a>
			</div>
			<form id="interactionForm" action="${ctx}/interactionSecond?sectionId=index&secondSectionId=default" method="post">
		<input type="hidden" id="usercuid" name="usercuid" value="${usercuid}"/>
			<table style="margin:0 auto;">
				<tr>
					<td style="font: 20px '微软雅黑';color: #000;padding:40px 40px 20px 40px;border: none;">编号</td>
					<td style="font: 20px '微软雅黑';color: #000;padding:40px 170px 20px 170px;border: none;">标题</td>
					<td style="font: 20px '微软雅黑';color: #000;padding:40px 60px 20px 60px;border:none;">申请日期</td>					
					<td style="font: 20px '微软雅黑';color: #000;padding:40px 40px 20px 40px;border:none;">政府回复</td>

				</tr>
				
			
			<c:forEach items="${interactionList }" var="interactionList" varStatus="status">
				<tr>
					<td style="font: 14px '微软雅黑';color: #000;padding:0px 40px 0px 40px;border:none;text-align: center;line-height: 30px;">${status.count }</td>
					<td style="padding:0px 0px 0px 0px;border:none;text-align: left;">
						<a href="javascript:void(0)" onclick="openDetail('${interactionList.id }')" style="font: 14px '微软雅黑';color: #000;line-height: 30px;" >
							<c:choose>
								<c:when test="${fn:length(interactionList.title)>22}">
									${fn:substring(interactionList.title, 0, 22)  }...
								</c:when>
								<c:otherwise>
									${interactionList.title}
								</c:otherwise>
							</c:choose>
							</a>
					</td>
					<td style="font: 12px '微软雅黑';color: #000;0px 60px 0px 60px;border:none;text-align: center;line-height: 30px;">（<fmt:formatDate value="${interactionList.reqtime }" pattern="yyyy-MM-dd"/>）</td>
					<td style="font: 14px '微软雅黑';color: #000;padding:0px 40px 0px 40px;border:none;text-align: center;line-height: 30px;">
					<c:choose>
							<c:when test="${interactionList.repstatus == '0' }">
								未回复
							</c:when>
							<c:when test="${interactionList.repstatus == '1' }">
								已回复
							</c:when>
							<c:otherwise>
								已阅读
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<div style="margin:0 auto;margin-top:20px;text-align: center;">
			<table style="width:100%;">
				<tr>
					<td style="text-align: right;">
						<!-- 分页标签 pageSize:总共页码数 curPage:当前页 totalNum:每页显示的记录数-->
	<cloud:breakPage pageSize="${resultSize}" url="${ctx}/interactionSecond?sectionId=index&secondSectionId=default" curPage="${curPage}" formId="interactionForm" totalNum="${pageSize}"/>					</td>
				</tr>
			  </table>
		</div>
		
		</form>
		</div>
		</div>

    	<%@ include file="/index/indexButtom.jsp" %>
	</body>
	<script type="text/javascript">
	function writeInteraction(){
	var sessionUserId = "${sessionScope.webSiteLoginUser.account}";
	var loginUserCuid = "${sessionScope.webSiteLoginUser.id}";
	if(sessionUserId == "" || sessionUserId == null){
		$('#login_background').css('display', 'block');
		$('#login_alert').css('display', 'block');
		return;
	}
	
	window.location.href = "${ctx}/writeInteraction?sectionId=index&secondSectionId=default&usercuid="+loginUserCuid;
}
	
	
      function openDetail(id){
		var url = "${ctx}/interactionDetail?sectionId=index&secondSectionId=default&id="+id;
		window.open(url);
	}
      </script>
</html>

