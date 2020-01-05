<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/topic/topicFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：发布话题表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/topic/saveTopic.do" method="post" name="topicForm" id="topicForm">
		<input type="hidden" id="id" name="id" value="${topic.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>发布话题表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>话题标题：</th>
				<td>
					<input name="title" type="text" id="title" value="${topic.title}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>话题内容：</th>
				<td>
					<cloud:htmleditor value="${topic.contentvalue}" property="contentvalue"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>创建时间：</th>
				<td>
					<fmt:formatDate value="${interaction.reqtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					<input name="createtime" type="hidden" id="createtime" value="${topic.createtime}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>是否公开：</th>
				<td>
					<datadict:inputRadio inputType="radio" value="${topic.isshow}" property="isshow" moduleName="article"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/topic/searchTopic.do')"/>
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
