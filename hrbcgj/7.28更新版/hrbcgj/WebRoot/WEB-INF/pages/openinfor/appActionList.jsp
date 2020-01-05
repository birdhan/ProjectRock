<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp"%>
<head>
<base href="<%=basePath%>">
<script type="text/javascript"
	src="${ctx}/click/openinfor/appActionListClick.js"></script>
<!-- 列表事件 -->
</head>
<body>
	<div class="right_title">
		<p>位置：依申请公开列表</p>
	</div>
	<div class="formDiv">
		<div class="listDiv">
			<display:table requestURI="${ctx}/openinfor/searchAppAction.do"
				id="appActionList" name="list" pagesize="${pageSize}"
				partialList="true" size="${resultSize}" cellspacing="0"
				class="listtable">

				<display:column
					title="<input type='checkbox' onclick='javascript:choose();'>"
					style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox"
						value="${appActionList.id}" />
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${appActionList_rowNum}
				</display:column>

				<display:column property="title" sortable="true" title="标题"
					style="text-align:center; width:15%" />

				<display:column property="actice" sortable="true" title="内容"
					style="text-align:center ;width:50%;" />

				<display:column property="createtime" sortable="true" title="申请时间"
					style="text-align:center;" />

				<display:column property="state" sortable="true" title="审核"
					style="text-align:center;" >
					<%-- <a href="${ctx}/openinfor/saveAppAction.do?id=${appActionList.id}">公开</a> --%>
					
					</display:column>

				
					
					
					<display:column title="回复" style="text-align:center">
					<img src="${ctx}/images/dept.png" title="回复"
						style="cursor: pointer;"
						onclick="goToUrl('${ctx}/openinfor/editAppAction1.do?id=${appActionList.id}')" />
				</display:column>

				
				

				<%-- <display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑"
						style="cursor: pointer;"
						onclick="goToUrl('${ctx}/openinfor/editAppAction.do?id=${appActionList.id}')" />
				</display:column>  --%>
				
				<display:column title="公开" style="text-align:center">
					<img src="${ctx}/images/tree-leaf.gif" title="公开"
						style="cursor: pointer;"
						onclick="goToUrl('${ctx}/openinfor/saveAppAction.do?id=${appActionList.id}')" />
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除"
						style="cursor: pointer;"
						onclick="delDataById('${ctx}/openinfor/delAppActionById.do?id=${appActionList.id}')" />
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细"
						style="cursor: pointer;"
						onclick="goToUrl('${ctx}/openinfor/detailAppAction.do?id=${appActionList.id}')" />
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%"
				class="btnTableList">
				<tr>
					<td><input type="button" value="新增" class="btn_list"
						onclick="goToUrl('${ctx}/openinfor/addAppAction.do')" /> <input
						type="button" value="删除" class="btn_list"
						onclick="delMoreByIds('${ctx}/openinfor/delAppActionByIds.do')"
						<c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if> />
						<input type="button" value="导入数据" class="btn_list"
						onclick="importData()" /> <input type="button" value="导出数据"
						class="btn_list" onclick="exportData()"
						<c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if> />
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp"%>
