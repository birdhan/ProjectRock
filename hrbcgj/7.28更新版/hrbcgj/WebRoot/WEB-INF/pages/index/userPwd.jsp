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
              <div class="cgxw_xqy_title"><span>您当前位置：</span><a href="${ctx }/index?sectionId=index">首页</a> > 账号安全</div>
              <div style="font:24px '微软雅黑';color:#000;margin-top:30px ;margin-left: 30px;">帐号管理</div>
              <div style="position:relative;margin-top: 35px;">
              	<span style="margin-left:80px;color:#000;"><a href="javascript:void(0)" onclick="userCenter()" style="color: #000;">个人资料</a></span>
              	<div style="position:absolute;left:160px;top:0px;border: 1px solid #E5E5E5;width:700px;height: 460px;">
              		<div style="line-height: 30px;border-bottom: 1px solid #E5E5E5;">
              			<span style="margin-left: 15px;">修改密码</span>
              		</div>
              			<table style="margin:0 auto;">
              				<tr style="height: 40px;">
              					<td style="padding-top:50px;font:14px '微软雅黑';color:#000;line-height: 40px;border: none;text-align:right ;">原密码：</td>
              					<td style="padding-top: 50px;font:14px '微软雅黑';color:#000;line-height: 40px;border: none;">
              						<input type="password" id="oldpwd" style="width:300px;height: 30px;margin-top: 8px;margin-left: -20px;">
              					</td>
              				</tr>
              				<tr style="height: 40px;">
              					<td style="font:14px '微软雅黑';color:#000;line-height: 40px;border: none;text-align:right ;">新密码：</td>
              					<td style="font:14px '微软雅黑';color:#000;line-height: 40px;border: none;">
              						<input type="password" id="newpwd" style="width:300px;height: 30px;margin-top: 8px;margin-left: -20px;">
              					</td>
              				</tr>
              				<tr style="height: 40px;">
              					<td style="font:14px '微软雅黑';color:#000;line-height: 40px;border: none;text-align:right ;">确认密码：</td>
              					<td style="font:14px '微软雅黑';color:#000;line-height: 40px;border: none;">
              						<input type="password" id="newpwdsecond" style="width:300px;height: 30px;margin-top: 8px;margin-left: -20px;">
              					</td>
              				</tr>
              				<tr style="text-align: center;"><td style="border: none;"></td><td style="border: none;"><input type="button" onclick="editPwd()" value="立即修改" style="font:18px'微软雅黑';color:#fff;width:240px;height: 40px;background-color: #a2ccff;"></td><td style="border: none;"></td></tr>
              			</table>
              			
              	</div>
              </div>
              <div style="margin-top: 20px;margin-left: 80px;">账户安全</div>
        </div>
		
    	<%@ include file="/index/indexButtom.jsp" %>
	</body>
	<script type="text/javascript">
		/**
		 * 个人中心
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
			var input4 = $("<input type='hidden' name='type' value='1'/>");
			form.append(input4);
			form.appendTo("body");
			form.css("display", "none");
			form.submit();
		}
		
		function editPwd(){
			var oldpwd = $("#oldpwd").val();
			var newpwd = $("#newpwd").val();
			var newpwdsecond = $("#newpwdsecond").val();
			if(newpwd != newpwdsecond){
				alert("两次密码输入不一致");
				return;
			}
			if(oldpwd === newpwd){
				alert("新密码不可以和旧密码相同");
				return;
			}
			var usercuid = $("#loginUserCuid").val();
			
			$.ajax({    
		        type: "POST", // 用POST方式传输    
		        dataType: "text", // 数据格式:JSON    
		        url: "${ctx}/editPwd", // 目标地址    
		        data: "usercuid=" + usercuid + "&oldpwd="+ oldpwd+ "&newpwd="+ newpwd,    
		        success: function (data){     
		        	data = JSON.parse(data);
		            if(data.status == '1'){
		            	alert("原密码输入错误");
		            	$("#oldpwd").focus();
		            }else{
		            	alert("修改成功");
		            	window.location.href = "${ctx }/index?sectionId=index";
		            }
		        },error: function (XMLHttpRequest, textStatus, errorThrown) {     
		                
		        },       
		    });
		}
	</script>
</html>
