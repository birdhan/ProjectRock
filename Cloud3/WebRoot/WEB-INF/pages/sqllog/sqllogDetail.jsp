<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：日志管理详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>日志管理详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>开始时间：</th>
			<td align="left" class="formTable_td">
				<fmt:formatDate value="${sqllog.fbegintime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<th>结束时间：</th>
			<td align="left" class="formTable_td">
				<fmt:formatDate value="${sqllog.fendtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<th>SQL：</th>
			<td align="left" class="formTable_td">
				${sqllog.fsql}
			</td>
		</tr>
		<tr>
			<th>SQL类型：</th>
			<td align="left" class="formTable_td">
				${sqllog.fsqltype}
			</td>
		</tr>
		<tr>
			<th>参数：</th>
			<td align="left" class="formTable_td">
				${sqllog.fparameters}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/sqllog/searchSqllog.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
