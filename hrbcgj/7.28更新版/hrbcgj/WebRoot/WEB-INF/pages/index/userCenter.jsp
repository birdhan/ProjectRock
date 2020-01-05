<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
		
        <div id="cgdt_sy">
              <div class="cgxw_xqy_title"><span>您当前位置：</span><a href="${ctx }/index?sectionId=index">首页</a> > 个人中心</div>
              <div style="font:24px '微软雅黑';color:#000;margin-top:30px ;margin-left: 30px;">帐号管理</div>
              <div style="position:relative;margin-top: 35px;">
              	<span style="margin-left:80px;color:#000;">个人资料</span>
              	<div style="position:absolute;left:160px;top:0px;border: 1px solid #E5E5E5;width:700px;height: 460px;">
              		<div style="line-height: 30px;border-bottom: 1px solid #E5E5E5;">
              			<span style="margin-left: 15px;">基础资料</span>
              		</div>
              			<table style="margin:0 auto;">
              				<tr style="font:14px '微软雅黑';color:#000;line-height: 30px;">
              					<td style="border:none;padding-top: 40px;text-align: left;">用户名：</td>
              					<td style="border:none;padding-top: 40px;text-align: left;">${registerUser.account }</td>
              				</tr>
              				<tr style="font:14px '微软雅黑';color:#000;line-height: 30px;">
              					<td style="border:none;text-align: left;">真实姓名：</td>
              					<td style="border:none;text-align: left;">${registerUser.username }</td>
              				</tr>
              				<tr style="font:14px '微软雅黑';color:#000;line-height: 30px;">
              					<td style="border:none;text-align: left;">身份证号：</td>
              					<td style="border:none;text-align: left;">${registerUser.idnum }</td>
              				</tr>
              				<tr style="font:14px '微软雅黑';color:#000;line-height: 30px;">
              					<td style="border:none;text-align: left;">手机号：</td>
              					<td style="border:none;text-align: left;">${registerUser.mobile }</td>
              				</tr>
              				<tr style="font:14px '微软雅黑';color:#000;line-height: 30px;">
              					<td style="border:none;text-align: left;">积分：</td>
              					<td style="border:none;text-align: left;">${registerUser.points }</td>
              				</tr>
              				<a href="${pageContext.request.contextPath }/showPointd?sectionId=other&secondSectionId=default">积分排行榜</a>
              			</table>
              	</div>
              </div>
              <div style="margin-top: 20px;margin-left: 80px;"><a href="javascript:void(0)" onclick="userCenter()" style="color:#000;">账户安全</a></div>
        </div>
		
    	<%@ include file="/index/indexButtom.jsp" %>
	</body>
	<script type="text/javascript">
		/**
		 * 账号安全
		 type=1个人中心
		 type=2账户安全
		 */
		function userCenter(){
			var form = $("<form></form>");
			form.attr("action", "${ctx}/userCenter");
			form.attr("method", "post");
			form.attr("target", "_self");
			var input1 = $("<input type='hidden' name='sectionId' value='index'/>");
			form.append(input1);
			var input2 = $("<input type='hidden' name='secondSectionId' value='default'/>");
			form.append(input2);
			var input3 = $("<input type='hidden' name='usercuid'/>");
			input3.attr("value", $("#loginUserCuid").val());
			form.append(input3);
			var input4 = $("<input type='hidden' name='type' value='2'/>");
			form.append(input4);
			form.appendTo("body");
			form.css("display", "none");
			form.submit();
		}
	</script>
</html>
