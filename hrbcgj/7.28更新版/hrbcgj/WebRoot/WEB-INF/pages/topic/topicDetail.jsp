<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：发布话题详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>发布话题详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>话题标题：</th>
			<td align="left" class="formTable_td">
				${topic.title}
			</td>
		</tr>
		<tr>
			<th>话题内容：</th>
			<td align="left" class="formTable_td">
				${topic.contentvalue}
			</td>
		</tr>
		<tr>
			<th>创建时间：</th>
			<td align="left" class="formTable_td">
				<fmt:formatDate value="${topic.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<th>是否公开：</th>
			<td align="left" class="formTable_td">
				<datadict:value2label dictType="radio" moduleName="article" dictValue="${topic.isshow}" property="isshow"/>
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/topic/searchTopic.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
