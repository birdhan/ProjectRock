<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/sqllog/sqllogFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：日志管理表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/sqllog/saveSqllog.do" method="post" name="sqllogForm" id="sqllogForm">
		<input type="hidden" id="id" name="id" value="${sqllog.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>日志管理表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th>开始时间：</th>
				<td>
					<cloud:inputDate497 value="${sqllog.fbegintime}" property="fbegintime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th>结束时间：</th>
				<td>
					<cloud:inputDate497 value="${sqllog.fendtime}" property="fendtime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th>SQL：</th>
				<td>
					<input name="fsql" type="text" id="fsql" value="${sqllog.fsql}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>SQL类型：</th>
				<td>
					<input name="fsqltype" type="text" id="fsqltype" value="${sqllog.fsqltype}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>参数：</th>
				<td>
					<input name="fparameters" type="text" id="fparameters" value="${sqllog.fparameters}" autocomplete="off"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/sqllog/searchSqllog.do')"/>
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
