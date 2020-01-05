<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：定时任务详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>定时任务详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>执行的类：</th>
			<td align="left" class="formTable_td">
				${schedule.classType}
			</td>
		</tr>
		<tr>
			<th>执行频率：</th>
			<td align="left" class="formTable_td">
				${schedule.frequency}
			</td>
		</tr>
		<tr>
			<th>状态：</th>
			<td align="left" class="formTable_td">
				<datadict:value2label dictType="radio" moduleName="schedulemanager" dictValue="${schedule.status}" property="status"/>
			</td>
		</tr>
		<tr>
			<th>备注：</th>
			<td align="left" class="formTable_td">
				${schedule.remark}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/schedulemanager/searchSchedule.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
