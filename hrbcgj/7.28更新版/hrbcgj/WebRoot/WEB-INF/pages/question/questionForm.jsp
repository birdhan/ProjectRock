<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/question/questionFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：常见问题表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/question/saveQuestion.do" method="post" name="questionForm" id="questionForm">
		<input type="hidden" id="id" name="id" value="${question.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>常见问题表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>问题名称：</th>
				<td>
					<input name="name" type="text" id="name" value="${question.name}" autocomplete="off" style="width: 300px;"/>
				</td>
			</tr>
			<tr>
				<th>创建时间：</th>
				<td>
					<cloud:inputDate497 value="${question.createtime}" property="createtime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>解答内容：</th>
				<td>
					<cloud:htmleditor value="${question.answercontent}" property="answercontent"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/question/searchQuestion.do')"/>
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
