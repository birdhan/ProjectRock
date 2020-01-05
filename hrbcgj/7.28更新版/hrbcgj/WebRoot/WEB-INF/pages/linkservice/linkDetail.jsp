<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：友情链接管理详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>友情链接管理详细</td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>链接名称：</th>
			<td align="left" class="formTable_td">
				${link.linkName}
			</td>
		</tr>
		<tr>
			<th>链接地址：</th>
			<td align="left" class="formTable_td">
				${link.linkUrl}
			</td>
		</tr>
		<tr>
			<th>链接图标：</th>
			<td align="left" class="formTable_td">
				<img src="${ctx}/uploadpic/getPic.do?id=${link.logoPic}" style="width:50px;height:50px;"/>
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/linkservice/searchLink.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
