<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：网上申办详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>网上申办详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>标题：</th>
			<td align="left" class="formTable_td">
				${onlineService.title}
			</td>
		</tr>
		<tr>
			<th>链接地址：</th>
			<td align="left" class="formTable_td">
				<a href="${onlineService.linkurl}" target="_blank">${onlineService.linkurl}</a>
			</td>
		</tr>
		<tr>
			<th>创建时间：</th>
			<td align="left" class="formTable_td">
				<fmt:formatDate value="${onlineService.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/onlineservice/searchOnlineService.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
