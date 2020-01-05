<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/linkservice/linkFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：友情链接管理表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/linkservice/saveLink.do" method="post" name="linkForm" id="linkForm">
		<input type="hidden" id="id" name="id" value="${link.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>友情链接管理表单</td>
			</tr>
		</table>
		<table class="formTable">
			<tr>
				<th><font color="red">*</font>链接名称：</th>
				<td>
					<input name="linkName" type="text" id="linkName" value="${link.linkName}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>链接地址：</th>
				<td>
					<input name="linkUrl" type="text" id="linkUrl" value="${link.linkUrl}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>链接图标：</th>
				<td>
					<cloud:uploadPic imgId="logoPic" picFileId="${link.logoPic}"/>
					
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/linkservice/searchLink.do')"/>
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
