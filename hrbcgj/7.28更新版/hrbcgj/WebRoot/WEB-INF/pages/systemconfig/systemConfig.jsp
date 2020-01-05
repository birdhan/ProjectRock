<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/systemconfig/systemConfigClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：系统配置</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/systemconfig/saveSystemConfigProperty.do" method="post" name="systemConfig" id="systemConfig">
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>数据库配置信息</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>dbDriver：</th>
				<td>
					${dbDriver}
					<input name="dbDriver" type="hidden" id="dbDriver" value="${dbDriver}" autocomplete="off" style="width:500px;"/>
				</td>
			</tr>
			
			<tr>
				<th><font color="red">*</font>driverUrl：</th>
				<td>
					${driverUrl}
					<input name="driverUrl" type="hidden" id="driverUrl" value="${driverUrl}" autocomplete="off" style="width:500px;"/>
				</td>
			</tr>
			
			<tr>
				<th><font color="red">*</font>driver：</th>
				<td>
					${driver}
					<input name="driver" type="hidden" id="driver" value="${driver}" autocomplete="off" style="width:500px;"/>
				</td>
			</tr>
			
			<tr>
				<th><font color="red">*</font>user：</th>
				<td>
					${user}
					<input name="user" type="hidden" id="user" value="${user}" autocomplete="off" style="width:500px;"/>
				</td>
			</tr>
			
			<tr>
				<th><font color="red">*</font>password：</th>
				<td>
					${password}
					<input name="password" type="hidden" id="password" value="${password}" autocomplete="off" style="width:500px;"/>
				</td>
			</tr>
		</table>
		
		<%--<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="reset" value="重置" class="btn"/>					
				</td>
			</tr>
		</table>
	--%></form>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
