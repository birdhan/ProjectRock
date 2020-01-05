<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/registeruser/registerUserFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：注册用户表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/registeruser/saveRegisterUser.do" method="post" name="registerUserForm" id="registerUserForm">
		<input type="hidden" id="id" name="id" value="${registerUser.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>注册用户表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>帐号：</th>
				<td>
					<input name="account" type="text" id="account" value="${registerUser.account}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>密码：</th>
				<td>
					<input name="pwd" type="text" id="pwd" value="${registerUser.pwd}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>姓名：</th>
				<td>
					<input name="username" type="text" id="username" value="${registerUser.username}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>身份证号：</th>
				<td>
					<input name="idnum" type="text" id="idnum" value="${registerUser.idnum}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>手机号：</th>
				<td>
					<input name="mobile" type="text" id="mobile" value="${registerUser.mobile}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>积分：</th>
				<td>
					<input name="points" type="text" id="points" value="${registerUser.points}" autocomplete="off"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/registeruser/searchRegisterUser.do')"/>
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
