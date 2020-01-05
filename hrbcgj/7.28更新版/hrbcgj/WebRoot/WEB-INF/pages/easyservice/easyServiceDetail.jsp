<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：便民服务详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>便民服务详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>服务名称：</th>
			<td align="left" class="formTable_td">
				${easyService.name}
			</td>
		</tr>
		<tr>
			<th>图标：</th>
			<td align="left" class="formTable_td">
				<img src="${ctx}/uploadpic/getPic.do?id=${easyService.logopic}" style="width:50px;height:50px;"/>
			</td>
		</tr>
		<tr>
			<th>外部链接：</th>
			<td align="left" class="formTable_td">
				${easyService.linkurl}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/easyservice/searchEasyService.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
