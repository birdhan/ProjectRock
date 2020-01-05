<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：局长信箱详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>局长信箱详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>标题：</th>
			<td align="left" class="formTable_td">
				${premierLetter.title}
			</td>
		</tr>
		<tr>
			<th>邮件内容：</th>
			<td align="left" class="formTable_td">
				${premierLetter.reqcontent}
			</td>
		</tr>
		<tr>
			<th>申请时间：</th>
			<td align="left" class="formTable_td">
				<fmt:formatDate value="${premierLetter.reqtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<th>申请人：</th>
			<td align="left" class="formTable_td">
				<id2name:commonId2Name whereCol="id" tableName="SERVICE_REGISTERUSER" selCol="username" whereColValue="${premierLetter.reqregisteruser}"/>
				(注册帐号：<id2name:commonId2Name whereCol="id" tableName="SERVICE_REGISTERUSER" selCol="account" whereColValue="${premierLetter.reqregisteruser}"/>)
			</td>
		</tr>
		<tr>
			<th>状态：</th>
			<td align="left" class="formTable_td">
				<datadict:value2label dictType="radio" moduleName="premierletter" dictValue="${premierLetter.repstatus}" property="repstatus"/>
			</td>
		</tr>
		<tr>
			<th>回复内容：</th>
			<td align="left" class="formTable_td">
				${premierLetter.repcontent}
			</td>
		</tr>
		<tr>
			<th>回复时间：</th>
			<td align="left" class="formTable_td">
				${premierLetter.reptime}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/premierletter/searchPremierLetter.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
