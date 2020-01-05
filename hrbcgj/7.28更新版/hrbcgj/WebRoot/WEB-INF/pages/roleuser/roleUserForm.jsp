<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/roleuser/roleUserFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：角色人员关系表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/roleuser/saveRoleUser.do" method="post" name="roleUserForm" id="roleUserForm">
		<input type="hidden" id="id" name="id" value="${roleUser.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>角色人员关系表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th>角色ID：</th>
				<td>
					<input name="linkRoleId" type="text" id="linkRoleId" value="${roleUser.linkRoleId}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>人员userId：</th>
				<td>
					<input name="userId" type="text" id="userId" value="${roleUser.userId}" autocomplete="off"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/roleuser/searchRoleUser.do')"/>
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
