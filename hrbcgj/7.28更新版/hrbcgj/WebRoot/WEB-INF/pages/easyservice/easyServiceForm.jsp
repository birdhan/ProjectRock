<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/easyservice/easyServiceFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：便民服务表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/easyservice/saveEasyService.do" method="post" name="easyServiceForm" id="easyServiceForm">
		<input type="hidden" id="id" name="id" value="${easyService.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>便民服务表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>服务名称：</th>
				<td>
					<input name="name" type="text" id="name" value="${easyService.name}" autocomplete="off" style="width:300px;"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>图标：</th>
				<td>
					<cloud:uploadPic imgId="logopic" picFileId="${easyService.logopic}"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>外部链接：</th>
				<td>
					<input name="linkurl" type="text" id="linkurl" value="${easyService.linkurl}" autocomplete="off" style="width:400px;"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/easyservice/searchEasyService.do')"/>
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
