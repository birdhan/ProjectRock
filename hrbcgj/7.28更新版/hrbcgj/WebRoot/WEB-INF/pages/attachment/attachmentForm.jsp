<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/attachment/attachmentFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：附件表表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/attachment/saveAttachment.do" method="post" name="attachmentForm" id="attachmentForm">
		<input type="hidden" id="id" name="id" value="${attachment.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>附件表表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>附件名称：</th>
				<td>
					<input name="fileName" type="text" id="fileName" value="${attachment.fileName}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>附件路径：</th>
				<td>
					<input name="filePath" type="text" id="filePath" value="${attachment.filePath}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>附件大小：</th>
				<td>
					<input name="fileSize" type="text" id="fileSize" value="${attachment.fileSize}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>附件类型：</th>
				<td>
					<input name="fileType" type="text" id="fileType" value="${attachment.fileType}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>附件上传人：</th>
				<td>
					<input name="uploadUserId" type="text" id="uploadUserId" value="${attachment.uploadUserId}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>附件上传时间：</th>
				<td>
					<cloud:inputDate497 value="${attachment.uploadTime}" property="uploadTime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>工程上下文根：</th>
				<td>
					<input name="contextPath" type="text" id="contextPath" value="${attachment.contextPath}" autocomplete="off"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/attachment/searchAttachment.do')"/>
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
