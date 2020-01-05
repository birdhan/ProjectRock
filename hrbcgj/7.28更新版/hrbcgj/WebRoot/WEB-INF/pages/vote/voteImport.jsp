<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/vote/voteImportClick.js"></script> <!-- js事件文件 -->
	</head>

<body style="padding: 10px;">
	<form id="importForm" action="${ctx}/vote/importExcel.do?module=vote" method="post" enctype="multipart/form-data">
		<table class="importTable" cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td>数据导入说明：</td>
			</tr>
			<tr>
				<td height="50">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请先<a href="javascript:window.location.href='${ctx}/vote/downLoadFile.do?filePath=/template/vote/vote.xls';">下载模板</a>文件，然后填写正确的数据后，方可将数据批量导入进系统中。</td>
			</tr>
			<tr>
				<td>
					选择导入文件：<input type="file" id="importExcelFileAll" name="importExcelFileAll"/>
				</td>
			</tr>
			<tr>
				<td>
					<div id="fileDivInfo" style="margin-top: 3px;">&nbsp;</div>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="button" id="importBtn" value="导入" class="btn_list" onclick="uploadFile()"/>
					<input type="button" value="关闭" class="btn_list" onclick="parent.closeImportWin()"/>
				</td>
			</tr>
		</table>
	</form>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
