<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/openinfor/appActionFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>审批回复</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/openinfor/saveAppAction.do" method="post" name="appActionForm" id="appActionForm">
		<input type="hidden" id="id" name="id" value="${appAction.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>依申请公开回复</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th>标题：</th>
				<td>
					<input name="title" type="hidden" id="title" value="${appAction.title}" autocomplete="off"/>
					<h5>${appAction.title}</h5>
				</td>
			</tr>
			<tr>
				<th>内容：</th>
				<td>
					<%-- <textarea rows="10" cols="10" id="actice" name="actice">${appAction.actice}</textarea> --%>
					<h5>${appAction.actice}</h5>
				</td>
			</tr>
			<tr>
				<th>申请时间：</th>
				<td>
					
					<input name="createtime" type="hidden" id="createtime" value="${appAction.createtime}" autocomplete="off"/>
					<h5>${appAction.createtime}</h5>
				</td>
			</tr>
			<tr>
				<th>状态：</th>
				<td>
					<input name="state" type="hidden" id="state" value="${appAction.state}" autocomplete="off"/>
					<h5>${appAction.state}</h5>
					
					<%-- <%
					
					
					request.getAttribute("appAction"); 
					
					
					%>
					<input type="radio" name="state" value="待审核" checked="checked">待审核
					<input type="radio" name="state" value="通过" checked="">通过 --%>
				</td>
			</tr>
			<tr>
				<th>回复内容：</th>
				<td>
					<textarea rows="10" cols="10" id="reply" name="reply">${appAction.reply}</textarea>
				</td>
			</tr>
			<%-- <tr>
				<th>用户id：</th>
				<td>
					<input type="hidden" name="uid" id="uid" value="${appAction.uid}"/>
				</td>
			</tr> --%>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/openinfor/searchAppAction.do')"/>
					<input type="reset" value="重置" class="btn"/>
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
