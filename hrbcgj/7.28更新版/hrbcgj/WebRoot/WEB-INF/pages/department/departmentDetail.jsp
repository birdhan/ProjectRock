<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：部门详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>部门详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>部门名称：</th>
			<td align="left" class="formTable_td">
				${department.departName}
			</td>
		</tr>
		<tr>
			<th>部门编号：</th>
			<td align="left" class="formTable_td">
				${department.departNo}
			</td>
		</tr>
		<tr>
			<th>上级部门：</th>
			<td align="left" class="formTable_td">
				<id2name:departNo2Name departNo="${department.parentNo}"/>				
			</td>
		</tr>
		<tr>
			<th>部门顺序：</th>
			<td align="left" class="formTable_td">
				${department.departSort}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<c:if test="${viewType != 'tree'}">
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/department/searchDepartment.do')"/>
				</c:if>
				<c:if test="${viewType == 'tree'}">
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/department/treeDept.do')"/>
				</c:if>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
