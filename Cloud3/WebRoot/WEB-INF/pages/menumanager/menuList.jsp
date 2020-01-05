<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/menumanager/menuListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：菜单列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="menuForm" name="menuForm" action="${ctx}/menumanager/searchMenu.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>菜单名称：</th>
						<td>
							<input name="menuName" type="text" id="menuName" value="${menu.menuName}" autocomplete="off"/>
						</td>
					</tr>
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>父菜单：</th>
						<td>
							<select name="parentId" id="parentId">
								<option value=""></option>
								<option value="root">菜单根路径</option>
								<c:forEach items="${moduleList}" var="m">
									<option value="${m.id}" <c:if test="${menu.parentId == m.id}">selected="selected"</c:if>>${m.menuName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/menumanager/searchMenu.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/menumanager/searchMenu.do" id="menuList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${menuList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${menuList_rowNum}
				</display:column>

				<display:column property="menuName" sortable="true" title="菜单名称" style="text-align:center;" />

				<display:column property="menuUrl" sortable="true" title="菜单地址" style="text-align:center;" />

				<display:column sortable="true" title="菜单类型" style="text-align:center;" >
					<datadict:value2label dictType="radio" moduleName="menumanager" dictValue="${menuList.menuType}" property="menuType"/>
				</display:column>

				<display:column sortable="true" title="父菜单" style="text-align:center;">
					<id2name:menuId2Name value="${menuList.parentId}"/>
				</display:column>

				<display:column property="menuSort" sortable="true" title="菜单顺序" style="text-align:center;" />

				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/menumanager/editMenu.do?id=${menuList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/menumanager/delMenuById.do?id=${menuList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/menumanager/detailMenu.do?id=${menuList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/menumanager/addMenu.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/menumanager/delMenuByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
						<input type="button" value="导入数据" class="btn_list" onclick="importData()"/>
						<input type="button" value="导出数据" class="btn_list" onclick="exportData()" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
						<input type="button" value="预览菜单结构" class="btn_list" onclick="viewMenuTree()" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
