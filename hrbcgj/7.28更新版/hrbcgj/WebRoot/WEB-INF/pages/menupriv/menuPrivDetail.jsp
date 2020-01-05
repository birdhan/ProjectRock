<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：菜单权限详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>菜单权限详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>权限编号：</th>
			<td align="left" class="formTable_td">
				${menuPriv.privNo}
			</td>
		</tr>
		<tr>
			<th>权限名称：</th>
			<td align="left" class="formTable_td">
				${menuPriv.privName}
			</td>
		</tr>
		<tr>
			<th>关联菜单：</th>
			<td align="left" class="formTable_td">
				<id2name:menuId2Name value="${menuPriv.linkMenuId}"/>				
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/menupriv/searchMenuPriv.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
