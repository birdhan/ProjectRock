<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：图片管理详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>图片管理详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>原文件名：</th>
			<td align="left" class="formTable_td">
				${uploadPic.oriName}
			</td>
		</tr>
		<tr>
			<th>服务器文件名：</th>
			<td align="left" class="formTable_td">
				${uploadPic.serName}
			</td>
		</tr>
		<tr>
			<th>保存路径：</th>
			<td align="left" class="formTable_td">
				${uploadPic.savePath}
			</td>
		</tr>
		<tr>
			<th>上传时间：</th>
			<td align="left" class="formTable_td">
				<fmt:formatDate value="${uploadPic.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>				
			</td>
		</tr>
		<tr>
			<th>上传人员：</th>
			<td align="left" class="formTable_td">
				${uploadPic.uploadUserId}
			</td>
		</tr>
		<tr>
			<th>图片预览：</th>
			<td align="left" class="formTable_td">
				<img src="${ctx}/uploadpic/getPic.do?id=${uploadPic.id}"/>
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/uploadpic/searchUploadPic.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
