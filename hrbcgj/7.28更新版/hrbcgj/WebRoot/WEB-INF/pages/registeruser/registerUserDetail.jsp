<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：注册用户详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>注册用户详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>帐号：</th>
			<td align="left" class="formTable_td">
				${registerUser.account}
			</td>
		</tr>
		<tr>
			<th>密码：</th>
			<td align="left" class="formTable_td">
				${registerUser.pwd}
			</td>
		</tr>
		<tr>
			<th>姓名：</th>
			<td align="left" class="formTable_td">
				${registerUser.username}
			</td>
		</tr>
		<tr>
			<th>身份证号：</th>
			<td align="left" class="formTable_td">
				${registerUser.idnum}
			</td>
		</tr>
		<tr>
			<th>手机号：</th>
			<td align="left" class="formTable_td">
				${registerUser.mobile}
			</td>
		</tr>
		<tr>
			<th>积分：</th>
			<td align="left" class="formTable_td">
				${registerUser.points}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/registeruser/searchRegisterUser.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
