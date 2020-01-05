<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：访问记录详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>访问记录详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>操作帐号：</th>
			<td align="left" class="formTable_td">
				${requestRecord.useraccount}
			</td>
		</tr>
		<tr>
			<th>用户类型：</th>
			<td align="left" class="formTable_td">
				${requestRecord.usertype}
			</td>
		</tr>
		<tr>
			<th>访问IP：</th>
			<td align="left" class="formTable_td">
				${requestRecord.formip}
			</td>
		</tr>
		<tr>
			<th>请求地址：</th>
			<td align="left" class="formTable_td">
				${requestRecord.requesturi}
			</td>
		</tr>
		<tr>
			<th>请求时间：</th>
			<td align="left" class="formTable_td">
				<fmt:formatDate value="${requestRecord.reqtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/requestrecord/searchRequestRecord.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
