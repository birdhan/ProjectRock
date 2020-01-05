<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common-top.jsp" %>
  <head>
    <base href="<%=basePath%>">
  </head>
  
<body>
	<div class="tire">
		<div class="right_title">
			<p>导入数据结果：共 <font color="red">${totalNum}</font> 条记录，其中不符合规则的有 <font color="red">${fn:length(failedList)}</font> 条，符合规则的有 <font color="red">${totalNum - fn:length(failedList)}</font> 条，<c:if test="${fn:length(failedList) == 0}">成功导入 <font color="red">${totalNum}</font> 条。</c:if> <c:if test="${fn:length(failedList) != 0}">成功导入 <font color="red">0</font> 条。</c:if></p>
		</div>
		<div class="formDiv">
		
			<!-- 失败 -->
			<c:if test="${flag == false}">
		   		<!-- 说明不是模板文件 -->
		   		<c:if test="${isNotTemplate != '' && fn:length(failedList) == 0}">
		   			<div class="fail_page">
					  <p><font color="red">出错啦！！您上传的不是模板文件!!!</font></p>
					  <ul>
					    <li><a href="${returnUrl}">返回列表页面>></a></li>
					  </ul>
					</div>
		   		</c:if>
		   		
		   		<!-- 是模板文件 -->
		   		<c:if test="${isNotTemplate == null}">
		   			<table class="tableHead">
			   			<tr>
			   				<td>导入数据结果出错，以下是不符合规则的记录说明</td>
			   			</tr>
			   		</table>
		   			
		   			<table class="formTable">
				    	<tr>
				    		<th style="text-align: center; width:30px;">序号</th>
				    		<th style="text-align: center; width:30px;">EXCEL行号</th>
				    		<th style="text-align: center;width:auto;">不符合导入规则</th>
				    	</tr>
				    	
				    	<c:forEach items="${failedList}" var="mess" varStatus="status">
				    		<tr>
				    			<td style="text-align: center;">${status.index+1}</td>
				    			<td style="text-align: center;">${mess[0]}</td><!-- excel行号 -->
				    			<td style="text-align: center;">${mess[1]}</td><!-- 消息 -->
				    		</tr>
				    	</c:forEach>
				    </table>
				    
				    <table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
				    	<tr>
				        	<td>
				        		<input type="button" value="返回列表" class="btn" onclick="goToUrl('${returnUrl}')"/>
				        	</td>	        	
				        </tr>
				    </table>				    
		   		</c:if>
			</c:if>	
			
			<!-- 成功 -->
			<c:if test="${flag == true}">
				<script type="text/javascript">
			    	function autoReturn() {
			    		window.location.href = '${returnUrl}';
			    	}
			    	
			    	setTimeout("autoReturn()",3000);
			    </script>
			    
				<div class="success_page">
				  <p>3秒钟后跳转到列表页面</p>
				  <ul>
				    <li><a href="${returnUrl}">返回列表页面>></a></li>
				  </ul>
				</div>
			</c:if>
		</div>	
	</div>
</body>
<%
	request.getSession().removeAttribute("saveListSize");
	request.getSession().removeAttribute("isNotTemplate");
	request.getSession().removeAttribute("failedList");
	request.getSession().removeAttribute("totalNum");
	request.getSession().removeAttribute("returnUrl");
%>
<%@ include file="common-bottom.jsp" %>