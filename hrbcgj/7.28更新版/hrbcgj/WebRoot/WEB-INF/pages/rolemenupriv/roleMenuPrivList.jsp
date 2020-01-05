<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/rolemenupriv/roleMenuPrivListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：角色菜单权限列表</p></div>
	<div class="formDiv">
		<div class="listDiv">
			<display:table requestURI="${ctx}/rolemenupriv/searchRoleMenuPriv.do" id="roleMenuPrivList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${roleMenuPrivList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${roleMenuPrivList_rowNum}
				</display:column>

				<display:column property="linkRoleId" sortable="true" title="关联角色ID" style="text-align:center;" />

				<display:column property="linkMenuId" sortable="true" title="关联菜单ID" style="text-align:center;" />

				<display:column property="privNo" sortable="true" title="对应权限编号" style="text-align:center;" />

				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/rolemenupriv/editRoleMenuPriv.do?id=${roleMenuPrivList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/rolemenupriv/delRoleMenuPrivById.do?id=${roleMenuPrivList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/rolemenupriv/detailRoleMenuPriv.do?id=${roleMenuPrivList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/rolemenupriv/addRoleMenuPriv.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/rolemenupriv/delRoleMenuPrivByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
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
