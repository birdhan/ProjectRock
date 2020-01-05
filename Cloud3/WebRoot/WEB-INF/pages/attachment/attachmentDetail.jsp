<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：附件表详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>附件表详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>附件名称：</th>
			<td align="left" class="formTable_td">
				${attachment.fileName}
			</td>
		</tr>
		<tr>
			<th>附件路径：</th>
			<td align="left" class="formTable_td">
				${attachment.filePath}
			</td>
		</tr>
		<tr>
			<th>附件大小：</th>
			<td align="left" class="formTable_td">
				${attachment.fileSize}
			</td>
		</tr>
		<tr>
			<th>附件类型：</th>
			<td align="left" class="formTable_td">
				${attachment.fileType}
			</td>
		</tr>
		<tr>
			<th>附件上传人：</th>
			<td align="left" class="formTable_td">
				${attachment.uploadUserId}
			</td>
		</tr>
		<tr>
			<th>附件上传时间：</th>
			<td align="left" class="formTable_td">
				<fmt:formatDate value="${attachment.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<th>工程上下文根：</th>
			<td align="left" class="formTable_td">
				${attachment.contextPath}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/attachment/searchAttachment.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
