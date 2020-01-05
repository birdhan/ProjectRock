<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/vote/voteListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：投票表列表</p></div>
	<div class="formDiv">
		<div class="listDiv">
			<display:table requestURI="${ctx}/vote/searchVote.do" id="voteList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${voteList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${voteList_rowNum}
				</display:column>

				<display:column property="title" sortable="true" title="标题" style="text-align:center;" />

				<display:column property="content" sortable="true" title="内容" style="text-align:center;" />

				<display:column property="startTime" sortable="true" title="开始时间" style="text-align:center;" >
				<%-- 	<fmt:formatDate value="${voteList.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
				</display:column>

				<display:column property="endTime" sortable="true" title="结束时间" style="text-align:center;" >
				<%-- 	<fmt:formatDate value="${voteList.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
				</display:column>

				<display:column sortable="true" title="是否完成" style="text-align:center;" >
					<datadict:value2label dictType="radio" moduleName="vote" dictValue="${voteList.if_Finish}" property="if_Finish"/>
				</display:column>

				<display:column sortable="true" title="类型" style="text-align:center;" >
					<datadict:value2label dictType="radio" moduleName="vote" dictValue="${voteList.type}" property="type"/>
				</display:column>

				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/vote/editVote.do?id=${voteList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/vote/delVoteById.do?id=${voteList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/vote/detailVote.do?id=${voteList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/vote/addVote.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/vote/delVoteByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
