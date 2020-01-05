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
  
  <body>
  
  		<%@ include file="/index/indexTop.jsp" %>
  		
  		<%@ include file="/index/indexBanner.jsp" %>
  		<%@ include file="/index/indexMenu.jsp" %>
  		<%@ include file="/index/indexSearch.jsp" %>
		
		<!--内容-->
		<div id="cgdt_sy">
			<div class="cgxw_list_title"><span>您当前位置：</span>
             			<a href="${ctx }/index?sectionId=index">首页</a> > 
             			<a href="${ctx }/secondSection?sectionId=${sectionId}&secondSectionId=default">${firstMap.NAME }</a> > 
             			${secondMap.NAME }
	        </div>
            <div class="cgxw_xqy_nr">
              <h1>${articleMap.NAME }</h1>
              <h6><span>来源：本站</span><span>发布时间：<fmt:formatDate value="${articleMap.CREATETIME }" pattern="yyyy-MM-dd" /></span></h6>
              
              <c:choose>
              	<c:when test="${secondMap.NAME eq '视频新闻' }">
              		<script src="${ctx }/javascript/index/video/video.min.js"></script>
					<script src="${ctx }/javascript/index/video/zh-CN.js"></script>
              		<div style="margin-top:10px;padding-top: 20px;">
						<video id="my-video" class="video-js" controls preload="auto"
							width="600" height="400" poster="m.jpg" data-setup="{}"> 
							<source src="${ctx }/${articleMap.RELATIVEPATH}" type="video/mp4"> 
								<p class="vjs-no-js">
									当前浏览器不支持视频播放， 请更换谷歌浏览器，或者360浏览器兼容模式</a>
								</p>
						</video>
						<script type="text/javascript">
							var myPlayer = videojs('my-video');
							videojs("my-video").ready(function() {
								var myPlayer = this;
								//myPlayer.play();
							});
						</script>
						<div style="margin-top: 10px;">
		              		${articleMap.CONTENTVALUE }
		              	</div>
					</div>
              	</c:when>
              	<c:otherwise>
              		<div style="margin-top: 10px;padding-top: 20px;">
		              	${articleMap.CONTENTVALUE }
		              </div>
              	</c:otherwise>
              </c:choose>
              
              
            </div>
        </div>
		
    	<%@ include file="/index/indexButtom.jsp" %>
	</body>
</html>
