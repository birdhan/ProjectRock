<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/viewmesasge/viewMessageFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：查看留言表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/viewmesasge/saveViewMessage.do" method="post" name="viewMessageForm" id="viewMessageForm">
		<input type="hidden" id="id" name="id" value="${viewMessage.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>查看留言表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>话题标题：</th>
				<td>
		${viewMessage.title}
					<%-- <id2name:commonId2Name whereCol="id" tableName="SERVICE_TOPIC" selCol="title" whereColValue="${viewMessage.title}"/> --%>
					<input name="title" type="hidden" id="title" value="${viewMessage.title}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>用户名：</th>
				<td>
					<id2name:commonId2Name whereCol="id" tableName="SERVICE_REGISTERUSER" selCol="username" whereColValue="${viewMessage.reqregisteruser}"/>
					<input name="reqregisteruser" type="hidden" id="reqregisteruser" value="${viewMessage.reqregisteruser}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>留言内容：</th>
				<td>
					${viewMessage.reqcontent}
					<input name="reqcontent" type="hidden" id="reqcontent" value="${viewMessage.reqcontent}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>留言时间：</th>
				<td>
				<fmt:formatDate value="${viewMessage.reqtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					<input name="reqtime" type="hidden" id="reqtime" value="${viewMessage.reqtime}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>是否显示：</th>
				<td>
					<datadict:inputRadio inputType="radio" value="${viewMessage.isshow}" property="isshow" moduleName="article"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/viewmesasge/searchViewMessage.do')"/>
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
