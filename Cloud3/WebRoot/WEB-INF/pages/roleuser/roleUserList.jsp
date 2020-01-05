<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/roleuser/roleUserListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：角色人员关系列表</p></div>
	<div class="formDiv">
		<div class="listDiv">
			<display:table requestURI="${ctx}/roleuser/searchRoleUser.do" id="roleUserList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${roleUserList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${roleUserList_rowNum}
				</display:column>

				<display:column property="linkRoleId" sortable="true" title="角色ID" style="text-align:center;" />

				<display:column property="userId" sortable="true" title="人员userId" style="text-align:center;" />

				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/roleuser/editRoleUser.do?id=${roleUserList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/roleuser/delRoleUserById.do?id=${roleUserList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/roleuser/detailRoleUser.do?id=${roleUserList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/roleuser/addRoleUser.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/roleuser/delRoleUserByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
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
