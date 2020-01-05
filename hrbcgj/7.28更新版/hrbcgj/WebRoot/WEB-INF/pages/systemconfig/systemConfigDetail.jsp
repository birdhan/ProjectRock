<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：系统配置详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>系统配置详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>左侧菜单名称：</th>
			<td align="left" class="formTable_td">
				${systemConfig.leftName}
			</td>
		</tr>
		<tr>
			<th>根部门名称：</th>
			<td align="left" class="formTable_td">
				${systemConfig.rootDepartName}
			</td>
		</tr>
		<tr>
			<th>根部门编号：</th>
			<td align="left" class="formTable_td">
				${systemConfig.rootDepartNo}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/systemconfig/searchSystemConfig.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
