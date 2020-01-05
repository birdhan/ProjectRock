<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/department/departmentFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：部门表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/department/saveDepartment.do?viewType=${viewType}" method="post" name="departmentForm" id="departmentForm">
		<input type="hidden" id="id" name="id" value="${department.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>部门表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>部门名称：</th>
				<td>
					<input name="departName" type="text" id="departName" value="${department.departName}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>部门编号：</th>
				<td>
					<input name="departNo" type="text" id="departNo" value="${department.departNo}" autocomplete="off" onkeyup="checkRepeatDepartNo(this.value)"/>
					<div id="departNoDiv" style="display: inline;"></div>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>上级部门编号：</th>
				<td>
					<input name="parentNo" type="text" id="parentNo" value="${department.parentNo}" autocomplete="off" onkeyup="checkRightDepartNo(this.value)"/>
					<div id="parentNoDiv" style="display: inline;"></div>
					请填写部门的编号，当前根部门编号为：<a href="javascript:auto2ParentNo('${sc.rootDepartNo}')">${sc.rootDepartNo}</a>					
				</td>
			</tr>
			<tr>
				<th>上级部门名称：</th>
				<td>					
					<span id="parentNoSpan"><id2name:departNo2Name departNo="${department.parentNo}"/></span>					
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>部门顺序：</th>
				<td>
					<input name="departSort" type="text" id="departSort" value="${department.departSort}" autocomplete="off"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn" onclick="return validateForm()"/>
					<c:if test="${viewType != 'tree'}">
						<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/department/searchDepartment.do')"/>
					</c:if>
					<c:if test="${viewType == 'tree'}">
						<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/department/treeDept.do')"/>
					</c:if>
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
