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
           <div class="cgdt_1"><span>${firstMap.NAME }</span></div>
           <c:forEach var="secondSection" items="${secondlist }" varStatus="status">
				<div 
					<c:choose>
						<c:when test="${(secondSectionId == 'default' && status.index == 0) || secondSectionId == secondSection.ID}">
							id="cgdt_sy_title_cgxw"
						</c:when>
						<c:otherwise>
							id="cgdt_sy_title"
						</c:otherwise>
					</c:choose>
					
					<c:if test="${status.index == 0}">
						 style="margin-top:10px;"
					</c:if>
				>
					<span><a href="${ctx }/secondSection?sectionId=${sectionId}&secondSectionId=${secondSection.ID}">${secondSection.NAME }</a></span>
				</div>
			</c:forEach>
			
			<c:if test="${secondlist!= null && fn:length(secondlist) > 0}">
				<div class="cgxw_list">
	                  <div class="cgxw_list_title">您当前位置：
	                  			<a href="${ctx }/index?sectionId=index">首页</a> > 
	                  			<a href="${ctx }/secondSection?sectionId=${sectionId}&secondSectionId=default">${firstMap.NAME }</a> > 
	                  			${secondMap.NAME }
	                  	</div>
	                  	
	                  <form id="articleForm" action="${ctx}/secondSection" method="post">
	                  <input type="hidden" id="sectionId" name="sectionId" value="${sectionId}"/>
	                  <input type="hidden" id="secondSectionId" name="secondSectionId" value="${secondSectionId}"/>	
	                  
                  		<c:if test="${secondMap.NAME eq '图片新闻'}">
                  			<table style="width:100%;">
                  				<c:forEach items="${articleList }" var="article" varStatus="status">
                 				<c:if test="${status.index % 4 == 0 && status.index != 0}"></tr><tr></c:if>
                 					<td style="text-align: center;">
                 						<img onclick="openDetail('${article.ID}')" style="cursor: pointer;" src="${ctx}/uploadpic/getPic.action?id=${article.cover}" width="124" height="89"/><br/>
                 						<cloud:subString end="7" begin="0" value="${article.name}" />
                 					</td>
                 				</c:forEach>
                 				<c:forEach begin="1" end="${4-(fn:length(articleList) % 4)}">
                					<td>&nbsp;</td>
               					</c:forEach>
                 				</tr>
                  			</table>
                  		</c:if>
                  		<c:if test="${secondMap.NAME eq '视频新闻'}">
                  			<table style="width:100%;">
                  				<c:forEach items="${articleList }" var="article" varStatus="status">
                 				<c:if test="${status.index % 4 == 0 && status.index != 0}"></tr><tr></c:if>
                 					<td style="text-align: center;">
                 						<img onclick="openDetail('${article.ID}')" style="cursor: pointer;" src="${ctx}/uploadpic/getPic.action?id=${article.cover}" width="124" height="89"/><br/>
                 						<cloud:subString end="7" begin="0" value="${article.name}" />
                 					</td>
                 				</c:forEach>
                 				<c:forEach begin="1" end="${4-(fn:length(articleList) % 4)}">
                					<td>&nbsp;</td>
               					</c:forEach>
                 				</tr>
                  			</table>
                  		</c:if>
                  		<c:if test="${secondMap.NAME != '视频新闻' && secondMap.NAME != '图片新闻'}">
                  			<c:forEach items="${articleList }" var="article" varStatus="status">
                  			<li
		                  		<c:if test="${status.index == 0}">
									 style="margin-top:15px;"
								</c:if>
							>
								<a href="#" onclick="openDetail('${article.ID}')">
									<c:choose>
										<c:when test="${fn:length(article.NAME)>15}">
											${fn:substring(article.NAME, 0, 15)  }...
										</c:when>
										<c:otherwise>
											${article.NAME}
										</c:otherwise>
									</c:choose>
								</a><!-- TODO 点击事件 -->
	                  			<a href="#" onclick="openDetail('${article.ID}')" class="date" style="float:right;">（<fmt:formatDate value="${article.CREATETIME }" pattern="yyyy-MM-dd" />）</a>
							</li>
							</c:forEach>
                  		</c:if>
	                  
	                  <table style="width:100%;">
						<tr>
  							<td style="text-align: right;">
   								<!-- 分页标签 pageSize:总共页码数 curPage:当前页 totalNum:每页显示的记录数-->
  								<cloud:breakPage pageSize="${resultSize}" url="${ctx}/secondSection" curPage="${curPage}" formId="articleForm" totalNum="${pageSize}"/>
   							</td>
   						</tr>
   					  </table>
	                  </form>
	               </div>
               </c:if>
        </div>
		
    	<%@ include file="/index/indexButtom.jsp" %>
	</body>
<script>
	function openDetail(id){
		var url = "${ctx}/articleDetail?sectionId="+$("#sectionId").val()+"&secondSectionId="+$("#secondSectionId").val()+"&articleId="+id;
		window.open(url);
	}
</script>
</html>

