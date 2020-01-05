<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/systemuser/systemUserListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：系统人员列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="systemUserForm" name="systemUserForm" action="${ctx}/systemuser/searchSystemUser.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>帐号：</th>
						<td>
							<input name="userId" type="text" id="userId" value="${systemUser.userId}" autocomplete="off"/>
						</td>						
					</tr>
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>名字：</th>
						<td>
							<input name="userName" type="text" id="userName" value="${systemUser.userName}" autocomplete="off"/>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/systemuser/searchSystemUser.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/systemuser/searchSystemUser.do" id="systemUserList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${systemUserList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${systemUserList_rowNum}
				</display:column>

				<display:column property="userId" sortable="true" title="帐号" style="text-align:center;" />

				<display:column property="userName" sortable="true" title="名字" style="text-align:center;" />

				<display:column sortable="true" title="部门" style="text-align:center;">
					<id2name:departNo2Name departNo="${systemUserList.departNo}"/>					
				</display:column>

				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/systemuser/editSystemUser.do?id=${systemUserList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/systemuser/delSystemUserById.do?id=${systemUserList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/systemuser/detailSystemUser.do?id=${systemUserList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/systemuser/addSystemUser.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/systemuser/delSystemUserByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
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
