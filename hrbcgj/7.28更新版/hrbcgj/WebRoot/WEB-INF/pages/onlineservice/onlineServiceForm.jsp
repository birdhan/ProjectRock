<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/onlineservice/onlineServiceFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：网上申办表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/onlineservice/saveOnlineService.do" method="post" name="onlineServiceForm" id="onlineServiceForm">
		<input type="hidden" id="id" name="id" value="${onlineService.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>网上申办表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>标题：</th>
				<td>
					<input name="title" type="text" id="title" value="${onlineService.title}" autocomplete="off" style="width:400px;"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>链接地址：</th>
				<td>
					<input name="linkurl" type="text" id="linkurl" value="${onlineService.linkurl}" autocomplete="off" style="width:400px;"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>创建时间：</th>
				<td>
					<cloud:inputDate497 value="${onlineService.createtime}" property="createtime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/onlineservice/searchOnlineService.do')"/>
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
