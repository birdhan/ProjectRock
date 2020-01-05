<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/uploadpic/uploadPicListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：图片管理列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="uploadPicForm" name="uploadPicForm" action="${ctx}/uploadpic/searchUploadPic.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>原文件名：</th>
						<td>
							<input name="oriName" type="text" id="oriName" value="${uploadPic.oriName}" autocomplete="off"/>
						</td>
					</tr>
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>上传人员：</th>
						<td>
							<input name="uploadUserId" type="text" id="uploadUserId" value="${uploadPic.uploadUserId}" autocomplete="off"/>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/uploadpic/searchUploadPic.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/uploadpic/searchUploadPic.do" id="uploadPicList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="序号" style="width:30px;text-align:center;">
					${uploadPicList_rowNum}
				</display:column>

				<display:column property="oriName" sortable="true" title="原文件名" style="text-align:center;" />

				<display:column property="serName" sortable="true" title="服务器文件名" style="text-align:center;" />

				<display:column property="savePath" sortable="true" title="保存路径" style="text-align:center;" />

				<display:column sortable="true" title="上传时间" style="text-align:center;">
					<fmt:formatDate value="${uploadPicList.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>

				<display:column property="uploadUserId" sortable="true" title="上传人员" style="text-align:center;" />

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/uploadpic/detailUploadPic.do?id=${uploadPicList.id}')"/>
				</display:column>
			</display:table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
