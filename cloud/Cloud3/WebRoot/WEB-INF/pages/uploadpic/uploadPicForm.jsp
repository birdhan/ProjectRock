<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/uploadpic/uploadPicFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/uploadpic/uploadPicFile.do" method="post" name="uploadPicForm" id="uploadPicForm" enctype="multipart/form-data">
		<input type="hidden" id="id" name="id" value="${uploadPic.id}"/>
		<input type="hidden" id="imgId" name="imgId" value="${imgId}"/>
		<input type="hidden" id="closeWinMethod" name="closeWinMethod" value="${closeWinMethod}"/>
		<input type="hidden" id="oriName" name="oriName"/>
		<table class="formTable">
			<tr>
				<th><font color="red">*</font>请选择上传文件：</th>
				<td>
					<input name="picFile" type="file" id="picFile"/>
				</td>
			</tr>
		</table>
		
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>图片文件信息</td>
			</tr>
		</table>
		
		<table class="formTable">
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
		</table>		

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="上传" class="btn_list" onclick="return validateForm()"/>
					<c:if test="${uploadPic.id != null}">
						<input type="button" value="删除" class="btn_list" onclick="delPicFile('${imgId}','${uploadPic.id}','${closeWinMethod}')"/>
						<input type="button" value="查看原图" class="btn_list" onclick="showOriPic('${ctx}/uploadpic/getPic.do?id=${uploadPic.id}')"/>
					</c:if>					
					<div id="valFormDiv" style="color: red;display: inline;"></div>
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
