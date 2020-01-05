<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：角色菜单权限详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>角色菜单权限详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>关联角色ID：</th>
			<td align="left" class="formTable_td">
				${roleMenuPriv.linkRoleId}
			</td>
		</tr>
		<tr>
			<th>关联菜单ID：</th>
			<td align="left" class="formTable_td">
				${roleMenuPriv.linkMenuId}
			</td>
		</tr>
		<tr>
			<th>对应权限编号：</th>
			<td align="left" class="formTable_td">
				${roleMenuPriv.privNo}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/rolemenupriv/searchRoleMenuPriv.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
