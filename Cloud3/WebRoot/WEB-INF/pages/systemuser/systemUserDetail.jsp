<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：系统人员详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>系统人员详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>帐号：</th>
			<td align="left" class="formTable_td">
				${systemUser.userId}
			</td>
		</tr>
		<tr>
			<th>名字：</th>
			<td align="left" class="formTable_td">
				${systemUser.userName}
			</td>
		</tr>
		<tr>
			<th>部门：</th>
			<td align="left" class="formTable_td">
				<id2name:departNo2Name departNo="${systemUser.departNo}"/>				
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/systemuser/searchSystemUser.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
