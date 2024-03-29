<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/taglibs/cloud.tld" prefix="cloud"%>
<%@ taglib uri="/WEB-INF/taglibs/id2name.tld" prefix="id2name"%>

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
		<div id="cgdt_sy" style="height:auto;padding-bottom: 20px;">
			<div class="cgxw_list_title"><span>您当前位置：</span>
             			<a href="${ctx }/index?sectionId=index">首页</a> > 
             			<a href="${ctx }/topicSecond?sectionId=index&secondSectionId=default">城管问计</a> >
             			城管问计详情
	        </div>
			<div>
				<div style="font-size:30px;margin-left: 40px;margin-top: 20px;">城管问计</div>
				<table>
					<tr>
						<td style="width: 100px;font-size: 16px; font-family: '微软雅黑'; color: #000000;text-align: right;"> 问计标题：</td>
						<td style="font-size: 14px; color: #767676; "> ${topic.title }</td>
					</tr>
					<tr>
						<td style="width: 100px; width: 100px;font-size: 16px; font-family: '微软雅黑'; color: #000000;text-align: right;">问计时间：</td>
						<td style="font-size: 14px; color: #767676; "><fmt:formatDate value="${topic.createtime }" pattern="yyyy-MM-dd"/></td>
					</tr>
					<tr>
						<td style="width: 120px;font-size: 16px; font-family: '微软雅黑'; color: #000000;text-align: right;"><span style="width:115px; margin-top: -100px;">问计内容：</span></td>
						<td style="font-size: 14px; color: #767676; width: 390px;">
							<p>${topic.contentvalue }</p>

						</td>
					</tr>

				</table>

			</div>
			
			<hr />
			
			<!--留言区-->
			<div style="font-size:30px;margin-top: -30px;margin-left: 40px;" >留言区</div>
			<div style="margin: 0 auto;margin: 20px 0px 0px 200px;">
				<input type="hidden" id="title" value="${topic.title}" />
				<textarea cols="30" rows="20" style="width:530px;height:140px;border-radius: 10px;" id="content"></textarea><br>
				<input type="button" onclick="saveViewmessage()" value="提交" style="width: 110px; height: 40px;background-color: #a2ccff;font: 20px '微软雅黑';color:#fff;margin-left: 380px;margin-bottom:30px;border-radius: 10px;"  />
			</div>
			<!-- <textarea rows="5" cols="5" style="overflow: hidden; overflow: scroll;"></textarea> -->
			
			<c:forEach items="${viewMessageList }" var="viewMessageList" varStatus="status">
			<div style="width:780px;font:14px '微软雅黑';color:#767676;margin: 0 auto;line-height: 24px;background-color:#fff;padding: 10px;margin-bottom: 20px;">
				<span style="font:18px '微软雅黑';color:#000;margin-left: 0px;">
				 <id2name:commonId2Name whereCol="id" tableName="SERVICE_REGISTERUSER" selCol="username" whereColValue="${viewMessageList.reqregisteruser}"/> :
				</span>          
			${viewMessageList.reqcontent } 
			</div>
			</c:forEach>
			
			
		
			
		</div>
	<%@ include file="/index/indexButtom.jsp" %>
    
	</body>
	<script type="text/javascript">
	function saveViewmessage(){
		var sessionUserId = "${sessionScope.webSiteLoginUser.account}";
		var loginUserCuid = "${sessionScope.webSiteLoginUser.id}";
		if(sessionUserId == "" || sessionUserId == null){
			$('#login_background').css('display', 'block');
			$('#login_alert').css('display', 'block');
			return;
		}
		
		var title = $("#title").val();
		var content = $("#content").val();
		 if(content.trim().length == 0){
			alert("请输入内容");
			return;
		}else if(content.trim().length >= 1000){
			alert("内容长度不得超过500个字符");
			return;
		}
		var form = new Object();
		form.title = title;
		form.content = content;
		form.usercuid = loginUserCuid;
		$.ajax({    
	        type: "POST", // 用POST方式传输    
	        dataType: "text", // 数据格式:JSON    
	        url: "${ctx}/saveViewmessage", // 目标地址    
	        data: form,    
	        success: function (data){     
	        	alert("发件成功");
	        	window.location.href = "${ctx}/topicSecond?sectionId=index&secondSectionId=default&usercuid="+loginUserCuid;
	        },error: function (XMLHttpRequest, textStatus, errorThrown) {     
	                
	        },       
	    }); 
	}
	
	function topic(){
		var sessionUserId = "${sessionScope.webSiteLoginUser.account}";
		var loginUserCuid = "${sessionScope.webSiteLoginUser.id}";
		if(sessionUserId == "" || sessionUserId == null){
			$('#login_background').css('display', 'block');
			$('#login_alert').css('display', 'block');
			return;
		}
		window.location.href = "${ctx}/topicSecond?sectionId=index&secondSectionId=default&usercuid="+loginUserCuid;
	}
</script>
</html>

