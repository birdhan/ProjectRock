<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/menupriv/menuPrivListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：菜单权限列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="menuPrivForm" name="menuPrivForm" action="${ctx}/menupriv/searchMenuPriv.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>权限名称：</th>
						<td>
							<input name="privName" type="text" id="privName" value="${menuPriv.privName}" autocomplete="off"/>
						</td>
					</tr>
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>关联菜单：</th>
						<td>
							<select name="linkMenuId" id="linkMenuId">
								<option value=""></option>
								<c:forEach items="${menuList}" var="menu">
									<option value="${menu.id}" <c:if test="${menuPriv.linkMenuId == menu.id}">selected="selected"</c:if>>${menu.menuName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/menupriv/searchMenuPriv.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<font color="red">注意：权限编号一旦确定，不得轻易更改。</font>
		
		<div class="listDiv">
			<display:table requestURI="${ctx}/menupriv/searchMenuPriv.do" id="menuPrivList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${menuPrivList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${menuPrivList_rowNum}
				</display:column>

				<display:column property="privNo" sortable="true" title="权限编号" style="text-align:center;" />

				<display:column property="privName" sortable="true" title="权限名称" style="text-align:center;" />

				<display:column sortable="true" title="关联菜单" style="text-align:center;">
					<id2name:menuId2Name value="${menuPrivList.linkMenuId}"/>
				</display:column>

				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/menupriv/editMenuPriv.do?id=${menuPrivList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/menupriv/delMenuPrivById.do?id=${menuPrivList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/menupriv/detailMenuPriv.do?id=${menuPrivList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/menupriv/addMenuPriv.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/menupriv/delMenuPrivByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
						<input type="button" value="导入数据" class="btn_list" onclick="importData()"/>
						<input type="button" value="导出数据" class="btn_list" onclick="exportData()" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
						<input type="button" value="树型视图" class="btn_list" onclick="goToUrl('${ctx}/menupriv/treeMenuPriv.do')"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
