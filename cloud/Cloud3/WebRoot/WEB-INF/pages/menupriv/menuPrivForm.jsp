<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/menupriv/menuPrivFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：菜单权限表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/menupriv/saveMenuPriv.do" method="post" name="menuPrivForm" id="menuPrivForm">
		<input type="hidden" id="id" name="id" value="${menuPriv.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>菜单权限表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>权限编号：</th>
				<td>
					<input name="privNo" type="text" id="privNo" value="${menuPriv.privNo}" autocomplete="off" onkeyup="autoNameAndCheck()"/>
					基本权限样例：
					<a href="javascript:autoPrivNo('add')">增加(add)</a>&nbsp;&nbsp;
					<a href="javascript:autoPrivNo('del')">删除(del)</a>&nbsp;&nbsp;
					<a href="javascript:autoPrivNo('update')">修改(update)</a>&nbsp;&nbsp;
					<a href="javascript:autoPrivNo('view')">查看(view)</a>&nbsp;&nbsp;
					<div id="privNoDiv" style="display: inline;"></div>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>权限名称：</th>
				<td>
					<input name="privName" type="text" id="privName" value="${menuPriv.privName}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>关联菜单：</th>
				<td>
					<select name="linkMenuId" id="linkMenuId" onchange="autoNameAndCheck()">
						<option value=""></option>
						<c:forEach items="${menuList}" var="menu">
							<option value="${menu.id}" <c:if test="${menuPriv.linkMenuId == menu.id}">selected="selected"</c:if>>${menu.menuName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn" onclick="return validateForm()"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/menupriv/searchMenuPriv.do')"/>
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
