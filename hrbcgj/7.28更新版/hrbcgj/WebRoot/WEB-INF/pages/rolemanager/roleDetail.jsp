<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：角色详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>角色详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>名称：</th>
			<td align="left" class="formTable_td">
				${role.name}
			</td>
		</tr>
		<tr>
			<th>人员：</th>
			<td align="left" class="formTable_td">
				${userNames}
			</td>
		</tr>
		<tr>
			<th>权限：</th>
			<td>
				<div id="menuPrivDiv" class="privDiv" style="display: block;margin-bottom: 3px;width:100%;position: static;border:none;">${innerHTML}</div>
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/rolemanager/searchRole.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
