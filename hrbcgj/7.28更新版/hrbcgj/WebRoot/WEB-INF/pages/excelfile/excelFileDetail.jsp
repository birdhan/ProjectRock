<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：表格文件详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>表格文件详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>文件名称：</th>
			<td align="left" class="formTable_td">
				${excelFile.name}
			</td>
		</tr>
		<tr>
			<th>上传时间：</th>
			<td align="left" class="formTable_td">
				<fmt:formatDate value="${excelFile.uploadtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<th>附件：</th>
			<td align="left" class="formTable_td">
				<cloud:uploadAttachment modelName="excelFile" isEdit="false" value="${excelFile.attachmentfileid}" 
						hiddenId="attachmentfileid" attachmentNums="1" attachmentType="XLS,XLSX" attachmentSize="${3*1024*1024}"/>
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/excelfile/searchExcelFile.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
