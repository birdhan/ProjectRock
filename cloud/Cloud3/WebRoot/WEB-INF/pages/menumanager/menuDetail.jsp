<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：菜单详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>菜单详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>菜单名称：</th>
			<td align="left" class="formTable_td">
				${menu.menuName}
			</td>
		</tr>
		<tr>
			<th>菜单地址：</th>
			<td align="left" class="formTable_td">
				${menu.menuUrl}
			</td>
		</tr>
		<tr>
			<th>菜单类型：</th>
			<td align="left" class="formTable_td">
				<datadict:value2label dictType="radio" moduleName="menumanager" dictValue="${menu.menuType}" property="menuType"/>
			</td>
		</tr>
		<tr>
			<th>父菜单：</th>
			<td align="left" class="formTable_td">
				<id2name:menuId2Name value="${menu.parentId}"/>				
			</td>
		</tr>
		<tr>
			<th>菜单顺序：</th>
			<td align="left" class="formTable_td">
				${menu.menuSort}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/menumanager/searchMenu.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
