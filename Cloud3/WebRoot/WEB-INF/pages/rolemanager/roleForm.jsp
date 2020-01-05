<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/rolemanager/roleFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：角色表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/rolemanager/saveRole.do" method="post" name="roleForm" id="roleForm">
		<input type="hidden" id="id" name="id" value="${role.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>角色表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>名称：</th>
				<td>
					<input name="name" type="text" id="name" value="${role.name}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>人员：</th>
				<td>
					<cloud:userTree value="${userId}" property="userId" single="false" autoCheckboxEven="false" checkbox="false" confirmBtnClick="closeUserTreeSaveRoleRelation()"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>权限：</th>
				<td>
					<div id="menuPrivDiv" class="privDiv" style="display: block;margin-bottom: 3px;width:100%;position: static;border:none;">${innerHTML}</div>
					<input type="button" value="菜单权限" class="btn_list" onclick="openMenuPrivTreeCheckbox()"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/rolemanager/searchRole.do')"/>
				</td>
			</tr>
			
			<tr>
				<td style="color: #999999;padding-top: 5px;">
					说明：选择菜单权限时，请保证您已经选择了人员，只有选择人员才能对权限进一步的编辑。
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
