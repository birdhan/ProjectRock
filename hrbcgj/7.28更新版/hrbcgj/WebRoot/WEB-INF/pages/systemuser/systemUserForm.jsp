<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/systemuser/systemUserFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：系统人员表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/systemuser/saveSystemUser.do" method="post" name="systemUserForm" id="systemUserForm">
		<input type="hidden" id="id" name="id" value="${systemUser.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>系统人员表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>帐号：</th>
				<td>
					<input name="userId" type="text" id="userId" value="${systemUser.userId}" autocomplete="off" onkeyup="checkRepeatUser(this.value)"/>
					<div id="userIdDiv" style="display:inline;"></div>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>密码：</th>
				<td>
					<input name="password" type="password" id="password" value="${systemUser.password}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>名字：</th>
				<td>
					<input name="userName" type="text" id="userName" value="${systemUser.userName}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>部门：</th>
				<td>
					<cloud:deptTree value="${systemUser.departNo}" property="departNo" single="true" autoCheckboxEven="false" checkbox="true"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn" onclick="return validateFrom()"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/systemuser/searchSystemUser.do')"/>
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
