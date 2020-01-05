<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/rolemenupriv/roleMenuPrivFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：角色菜单权限表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/rolemenupriv/saveRoleMenuPriv.do" method="post" name="roleMenuPrivForm" id="roleMenuPrivForm">
		<input type="hidden" id="id" name="id" value="${roleMenuPriv.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>角色菜单权限表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th>关联角色ID：</th>
				<td>
					<input name="linkRoleId" type="text" id="linkRoleId" value="${roleMenuPriv.linkRoleId}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>关联菜单ID：</th>
				<td>
					<input name="linkMenuId" type="text" id="linkMenuId" value="${roleMenuPriv.linkMenuId}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>对应权限编号：</th>
				<td>
					<input name="privNo" type="text" id="privNo" value="${roleMenuPriv.privNo}" autocomplete="off"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/rolemenupriv/searchRoleMenuPriv.do')"/>
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
