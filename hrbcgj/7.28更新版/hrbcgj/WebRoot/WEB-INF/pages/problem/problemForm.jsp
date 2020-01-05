<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/problem/problemFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：问题受理表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/problem/saveproblem.do" method="post" name="problemForm" id="problemForm">
		<input type="hidden" id="id" name="id" value="${problem.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>问题受理表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th>用户名：</th>
				<td>
				
					<id2name:commonId2Name whereCol="id" tableName="SERVICE_REGISTERUSER" selCol="username" whereColValue="${problem.userName}"/>
					<input name="userName" type="hidden" id="userName" value="${problem.userName}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>联系电话：</th>
				<td>
				<id2name:commonId2Name whereCol="id" tableName="SERVICE_REGISTERUSER" selCol="mobile" whereColValue="${problem.phone}"/>
					<input name="phone" type="hidden" id="phone" value="${problem.phone}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>标题：</th>
				<td>
					${problem.title}
					<input name="title" type="hidden" id="title" value="${problem.title}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>申请内容：</th>
				<td>
					${problem.content}
					<input name="content" type="hidden" id="content" value="${problem.content}" autocomplete="off"/>
				</td>
			</tr>
			<%-- <tr>
				<th>类别：</th>
				<td>
					${problem.category}
					<input name="category" type="hidden" id="category" value="${problem.category}" autocomplete="off"/>
				</td>
			</tr> --%>
			<tr>
				<th>申请时间：</th>
				<td>
					${problem.time}
					<input name="time" type="hidden" id="time" value="${problem.time}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>回复内容：</th>
				<td>
				<input name="status" type="hidden" id="status" value="${problem.status}" autocomplete="off"/>
					<textarea rows="20" cols="20" name="reply" id="repcontent" style="width:400px;heigth:500px;">${problem.reply}</textarea>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/problem/searchproblem.do')"/>
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
