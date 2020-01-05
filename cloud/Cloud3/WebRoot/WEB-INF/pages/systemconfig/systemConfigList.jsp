<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/systemconfig/systemConfigListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：系统配置列表</p></div>
	<div class="formDiv">
		<div class="listDiv">
			<display:table requestURI="${ctx}/systemconfig/searchSystemConfig.do" id="systemConfigList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${systemConfigList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${systemConfigList_rowNum}
				</display:column>

				<display:column property="leftName" sortable="true" title="左侧菜单名称" style="text-align:center;" />

				<display:column property="rootDepartName" sortable="true" title="根部门名称" style="text-align:center;" />

				<display:column property="rootDepartNo" sortable="true" title="根部门编号" style="text-align:center;" />

				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/systemconfig/editSystemConfig.do?id=${systemConfigList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/systemconfig/delSystemConfigById.do?id=${systemConfigList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/systemconfig/detailSystemConfig.do?id=${systemConfigList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/systemconfig/addSystemConfig.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/systemconfig/delSystemConfigByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
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
