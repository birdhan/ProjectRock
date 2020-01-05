<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：查看留言详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>查看留言详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>话题标题：</th>
			<td align="left" class="formTable_td">
					${viewMessage.title}
				<%-- <id2name:commonId2Name whereCol="id" tableName="SERVICE_TOPIC" selCol="title" whereColValue="${viewMessage.title}"/> --%>
			</td>
		</tr>
		<tr>
			<th>用户名：</th>
			<td align="left" class="formTable_td">
				<id2name:commonId2Name whereCol="id" tableName="SERVICE_REGISTERUSER" selCol="username" whereColValue="${viewMessage.reqregisteruser}"/>
			</td>
		</tr>
		<tr>
			<th>留言内容：</th>
			<td align="left" class="formTable_td">
				${viewMessage.reqcontent}
			</td>
		</tr>
		<tr>
			<th>留言时间：</th>
			<td align="left" class="formTable_td">
				${viewMessage.reqtime}
			</td>
		</tr>
		<tr>
			<th>是否显示：</th>
			<td align="left" class="formTable_td">
				<datadict:value2label dictType="radio" moduleName="article" dictValue="${viewMessage.isshow}" property="isshow"/>
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/viewmesasge/searchViewMessage.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
