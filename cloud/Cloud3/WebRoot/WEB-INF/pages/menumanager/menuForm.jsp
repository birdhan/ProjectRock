<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/menumanager/menuFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：菜单表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/menumanager/saveMenu.do" method="post" name="menuForm" id="menuForm">
		<input type="hidden" id="id" name="id" value="${menu.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>菜单表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>菜单名称：</th>
				<td>
					<input name="menuName" type="text" id="menuName" value="${menu.menuName}" autocomplete="off" style="width:300px;"/>
				</td>
			</tr>
			<tr>
				<th>菜单地址：</th>
				<td>
					<input name="menuUrl" type="text" id="menuUrl" value="${menu.menuUrl}" autocomplete="off" style="width:300px;" onkeyup="autoChooseMenuType(this.value)"/>
				</td>
			</tr>
			<tr>
				<th>菜单类型：</th>
				<td>
					<datadict:inputRadio inputType="radio" value="${menu.menuType}" property="menuType" moduleName="menumanager"/>
				</td>
			</tr>
			<tr>
				<th>父菜单：</th>
				<td>
					<select name="parentId" id="parentId">
						<option value="root">菜单根路径</option>
						<c:forEach items="${moduleList}" var="m">
							<option value="${m.id}" <c:if test="${menu.parentId == m.id}">selected="selected"</c:if>>${m.menuName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>菜单顺序：</th>
				<td>
					<input name="menuSort" type="text" id="menuSort" value="${menu.menuSort}" autocomplete="off"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/menumanager/searchMenu.do')"/>
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
