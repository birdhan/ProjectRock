<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/schedulemanager/scheduleFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：定时任务表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/schedulemanager/saveSchedule.do" method="post" name="scheduleForm" id="scheduleForm">
		<input type="hidden" id="id" name="id" value="${schedule.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>定时任务表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>执行的类：</th>
				<td>
					<input name="classType" type="text" id="classType" value="${schedule.classType}" autocomplete="off" onkeyup="checkClassIsExist(this.value)" style="width:300px;"/>
					<div id="classTypeDiv" style="display: inline;"></div>
				</td>
			</tr>
			<tr>
				<th>执行频率示例:</th>
				<td>
					&nbsp;&nbsp;*/1 * * * * ?&nbsp;(平均每秒执行一次)<br/>
					&nbsp;&nbsp;0 30 7 * * ?&nbsp;(每天7点30分执行一次)
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>执行频率：</th>
				<td>
					<input name="frequency" type="text" id="frequency" value="${schedule.frequency}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>状态：</th>
				<td>
					<datadict:inputRadio inputType="radio" value="${schedule.status}" property="status" moduleName="schedulemanager"/>
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<textarea name="remark" id="remark" rows="10" cols="40">${schedule.remark}</textarea>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/schedulemanager/searchSchedule.do')"/>
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
