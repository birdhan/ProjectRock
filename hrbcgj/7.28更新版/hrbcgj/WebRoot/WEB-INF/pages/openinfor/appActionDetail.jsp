<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：依申请公开详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>依申请公开详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>标题：</th>
			<td align="left" class="formTable_td">
				${appAction.title}
			</td>
		</tr>
		<tr>
			<th>内容：</th>
			<td align="left" class="formTable_td">
				${appAction.actice}
			</td>
		</tr>
		<tr>
			<th>申请时间：</th>
			<td align="left" class="formTable_td">
				${appAction.createtime}
			</td>
		</tr>
		<tr>
			<th>状态：</th>
			<td align="left" class="formTable_td">
				${appAction.state}
			</td>
		</tr>
		<tr>
			<th>批文：</th>
			<td align="left" class="formTable_td">
				${appAction.reply}
			</td>
		</tr>
		<tr>
			<th>用户名：</th>
			<td align="left" class="formTable_td">
				${appAction.uid}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/openinfor/searchAppAction.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
