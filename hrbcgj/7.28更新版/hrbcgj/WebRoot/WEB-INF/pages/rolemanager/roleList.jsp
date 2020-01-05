<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/rolemanager/roleListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：角色列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="roleForm" name="roleForm" action="${ctx}/rolemanager/searchRole.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>名称：</th>
						<td>
							<input name="name" type="text" id="name" value="${role.name}" autocomplete="off"/>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/rolemanager/searchRole.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/rolemanager/searchRole.do" id="roleList" 
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${roleList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;" >
					${roleList_rowNum}
				</display:column>

				<display:column property="name" sortable="true" title="名称" style="text-align:center;" />
				
				<display:column sortable="false" title="人员" style="text-align:center;">
					<cloud:roleIdGetUserName value="${roleList.id}"/>	<!-- 通过角色id得到该角色下所有的人名字 -->
				</display:column>
				
				<display:column sortable="false" title="权限" style="text-align:center;">
					<a href="javascript:void(0)" onmouseover="showPrivDiv('${roleList.id}')" onmouseout="hiddenPrivDiv('${roleList.id}')">鼠标移至此处查看权限</a>
					<div class="privDiv" id="${roleList.id}Div"></div>
				</display:column>

				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/rolemanager/editRole.do?id=${roleList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/rolemanager/delRoleById.do?id=${roleList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/rolemanager/detailRole.do?id=${roleList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/rolemanager/addRole.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/rolemanager/delRoleByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
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
