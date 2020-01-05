<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：问题受理详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>问题受理详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>用户名：</th>
			<td align="left" class="formTable_td">
				<id2name:commonId2Name whereCol="id" tableName="SERVICE_REGISTERUSER" selCol="username" whereColValue="${problem.userName}"/>
			</td>
		</tr>
		<tr>
			<th>联系电话：</th>
			<td align="left" class="formTable_td">
					<id2name:commonId2Name whereCol="id" tableName="SERVICE_REGISTERUSER" selCol="mobile" whereColValue="${problem.phone}"/>
					
			</td>
		</tr>
		<tr>
			<th>标题：</th>
			<td align="left" class="formTable_td">
				${problem.title}
			</td>
		</tr>
		<tr>
			<th>申请内容：</th>
			<td align="left" class="formTable_td">
				${problem.content}
			</td>
		</tr>
	<%-- 	<tr>
			<th>类别：</th>
			<td align="left" class="formTable_td">
				${problem.category}
			</td>
		</tr> --%>
		<tr>
			<th>申请时间：</th>
			<td align="left" class="formTable_td">
				${problem.time}
			</td>
		</tr>
		<tr>
			<th>状态：</th>
			<td align="left" class="formTable_td">
				${problem.status}
			</td>
		</tr>
		<tr>
			<th>回复内容：</th>
			<td align="left" class="formTable_td">
				${problem.reply}
			</td>
		</tr>
		<tr>
			<th>回复时间：</th>
			<td align="left" class="formTable_td">
				${problem.responseTime}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/problem/searchproblem.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
