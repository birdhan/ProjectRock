<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<link href="${ctx}/css/attachment/swfupload-default.css" rel="stylesheet"type="text/css" />
		<script type="text/javascript" src="${ctx}/click/attachment/swfupload.js"></script>
		<script type="text/javascript" src="${ctx}/click/attachment/handlers.js"></script>
		
		<script type="text/javascript" src="${ctx}/click/attachment/attachmentListClick.js"></script> <!-- 列表事件 -->
	</head>
	<script>
		function on_attachment_close_1(event){
			iconClose(event); //必须写这一行调用内部关闭方法关闭上传附件的弹出层
			window.location.href = window.location.href;
		}
	</script>
<body>
	<div class="right_title"><p>位置：附件表列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="attachmentForm" name="attachmentForm" action="${ctx}/attachment/searchAttachment.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>附件名称：</th>
						<td>
							<input name="fileName" type="text" id="fileName" value="${attachment.fileName}" autocomplete="off"/>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/attachment/searchAttachment.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/attachment/searchAttachment.do" id="attachmentList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${attachmentList.id}"/>
					<input id="ids4file" name="ids4file" type="hidden" value="${attachmentList.id},${attachmentList.filePath }"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${attachmentList_rowNum}
				</display:column>

				<display:column sortable="true" title="附件名称" style="text-align:center;" >
					<a href="${ctx }/DownLoadFileServlet?id=${attachmentList.id }">${attachmentList.fileName }</a>
				</display:column>

				<display:column property="filePath"  title="附件路径" style="text-align:center;" />

				<display:column property="fileSize"  title="附件大小" style="text-align:center;" />

				<display:column property="fileType"  title="附件类型" style="text-align:center;" />

				<display:column property="uploadUserId"  title="附件上传人" style="text-align:center;" />

				<display:column  title="附件上传时间" style="text-align:center;" >
					<fmt:formatDate value="${attachmentList.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>

				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/attachment/editAttachment.do?id=${attachmentList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除${attachmentList.filePath }" style="cursor: pointer;"
						onclick="deleteOneAttachment('${attachmentList.id }')"/>
					<input type="hidden" id="${attachmentList.id }" value="${attachmentList.id },${attachmentList.filePath }";
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/attachment/detailAttachment.do?id=${attachmentList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="上传测试" class="btn_list" onclick="attachment_startUpload('1024 MB','*.*',1,'222222222','${ctx }','on_attachment_close_1','${ctx }')"/>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/attachment/addAttachment.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="deleteAttachment()" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
						<input type="button" value="导入数据" class="btn_list" onclick="importData()"/>
						<input type="button" value="导出数据" class="btn_list" onclick="exportData()" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
