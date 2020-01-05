<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/excelfile/excelFileFormClick.js"></script> <!-- js事件文件 -->
		<script type="text/javascript" src="${ctx}/jscomponents/layer/layer.js"></script>
		<script src="${ctx}/jscomponents/basefunction/basefun.js" type="text/javascript"></script>
	</head>

<body>
<div class="right_title"><p>位置：表格文件表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/excelfile/saveExcelFile.do" method="post" name="excelFileForm" id="excelFileForm">
		<input type="hidden" id="id" name="id" value="${excelFile.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>表格文件表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>文件描述名称：</th>
				<td>
					<input name="name" type="text" id="name" value="${excelFile.name}" autocomplete="off" style="width:300px;"/>
				</td>
			</tr>
			<tr>
				<th>上传时间：</th>
				<td>
					<cloud:inputDate497 value="${excelFile.uploadtime}" property="uploadtime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th>附件：</th>
				<td>
					<cloud:uploadAttachment modelName="excelFile" isEdit="true" value="${excelFile.attachmentfileid}" 
							hiddenId="attachmentfileid" attachmentNums="1" attachmentType="XLS,XLSX" attachmentSize="${3*1024*1024}"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/excelfile/searchExcelFile.do')"/>
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
