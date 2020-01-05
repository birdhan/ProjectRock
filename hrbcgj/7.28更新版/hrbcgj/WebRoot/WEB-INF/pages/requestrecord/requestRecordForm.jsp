<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/requestrecord/requestRecordFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：访问记录表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/requestrecord/saveRequestRecord.do" method="post" name="requestRecordForm" id="requestRecordForm">
		<input type="hidden" id="id" name="id" value="${requestRecord.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>访问记录表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>操作帐号：</th>
				<td>
					<input name="useraccount" type="text" id="useraccount" value="${requestRecord.useraccount}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>用户类型：</th>
				<td>
					<input name="usertype" type="text" id="usertype" value="${requestRecord.usertype}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>访问IP：</th>
				<td>
					<input name="formip" type="text" id="formip" value="${requestRecord.formip}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>请求地址：</th>
				<td>
					<input name="requesturi" type="text" id="requesturi" value="${requestRecord.requesturi}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>请求时间：</th>
				<td>
					<cloud:inputDate497 value="${requestRecord.reqtime}" property="reqtime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/requestrecord/searchRequestRecord.do')"/>
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
