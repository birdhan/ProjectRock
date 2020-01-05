<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/department/departmentListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：部门列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="departmentForm" name="departmentForm" action="${ctx}/department/searchDepartment.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>部门名称：</th>
						<td>
							<input name="departName" type="text" id="departName" value="${department.departName}" autocomplete="off"/>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/department/searchDepartment.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/department/searchDepartment.do" id="departmentList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${departmentList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${departmentList_rowNum}
				</display:column>

				<display:column property="departName" sortable="true" title="部门名称" style="text-align:center;" />

				<display:column property="departNo" sortable="true" title="部门编号" style="text-align:center;" />

				<display:column sortable="true" title="上级部门" style="text-align:center;">
					<id2name:departNo2Name departNo="${departmentList.parentNo}"/>
				</display:column>

				<display:column property="departSort" sortable="true" title="部门顺序" style="text-align:center;" />

				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/department/editDepartment.do?id=${departmentList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/department/delDepartmentById.do?id=${departmentList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/department/detailDepartment.do?id=${departmentList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/department/addDepartment.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/department/delDepartmentByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
						<input type="button" value="导入数据" class="btn_list" onclick="importData()"/>
						<input type="button" value="导出数据" class="btn_list" onclick="exportData()" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
						<input type="button" value="树型视图" class="btn_list" onclick="goToUrl('${ctx}/department/treeDept.do')"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
