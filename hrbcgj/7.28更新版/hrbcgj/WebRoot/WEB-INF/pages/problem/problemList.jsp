<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp"%>
<head>
<base href="<%=basePath%>">
<script type="text/javascript"
	src="${ctx}/click/problem/problemListClick.js"></script>
<!-- 列表事件 -->
</head>
<body>
	<div class="right_title">
		<p>位置：问题受理列表</p>
	</div>
	<div class="formDiv">
		<div class="query">
			<form id="problem" name="problem"
				action="${ctx}/problem/searchproblem.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png"
							width="18" height="18" style="margin-top:3px;margin-left:5px;" />
						</td>
						<th>标题：</th>
						<td><input name="title" type="text" id="title"
							value="${problem.title}" autocomplete="off" /></td>
					</tr>
					<%-- <tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>用户名：</th>
						<td>
							<input name="reqregisteruser" type="text" id="reqregisteruser" value="${problem.userName}" autocomplete="off"/>
						</td>
					</tr>
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>状态：</th>
						<td>
							<datadict:select inputType="select" value="${problem.status}" property="repstatus" moduleName="premierletter" topLabel="全部" topValue=""/>
						</td>
					</tr> --%>

				</table>

				<table class="query_btn_table">
					<tr>
						<td><input type="submit" value="" class="btn_search" /> <input
							type="button" class="btn_default_search"
							onclick="javascript:goToUrl('${ctx}/problem/searchproblem.do')" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/problem/searchproblem.do"
				id="problemList" name="list" pagesize="${pageSize}"
				partialList="true" size="${resultSize}" cellspacing="0"
				class="listtable">

				<display:column
					title="<input type='checkbox' onclick='javascript:choose();'>"
					style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox"
						value="${problemList.id}" />
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${problemList_rowNum}
				</display:column>

				<display:column sortable="true" title="用户名"
					style="text-align:center;">
					<id2name:commonId2Name whereCol="id"
						tableName="SERVICE_REGISTERUSER" selCol="username"
						whereColValue="${problemList.userName}" />
				</display:column>

				<display:column sortable="true" title="联系电话"
					style="text-align:center;">
					<id2name:commonId2Name whereCol="id"
						tableName="SERVICE_REGISTERUSER" selCol="mobile"
						whereColValue="${problemList.phone}" />
				</display:column>

				<display:column property="title" sortable="true" title="标题"
					style="text-align:center;" />

				<display:column property="content" sortable="true" title="申请内容"
					style="text-align:center;" />


				<display:column property="time" sortable="true" title="申请时间"
					style="text-align:center;" />

				<display:column sortable="true" title="状态"
					style="text-align:center;">
					<datadict:value2label dictType="radio" moduleName="problem"
						dictValue="${problemList.status}" property="status" />
				</display:column>

				<display:column property="reply" sortable="true" title="回复内容"
					style="text-align:center;" />

				<display:column property="responseTime" sortable="true" title="回复时间"
					style="text-align:center;" />

				<display:column title="回复" style="text-align:center">
					<c:if test="${problemList.status == '1'}">
						<img src="${ctx}/images/cog_edit.png" title="回复"
							style="cursor: pointer;"
							onclick="goToUrl('${ctx}/problem/editproblem.do?id=${problemList.id}')" />
					</c:if>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除"
						style="cursor: pointer;"
						onclick="delDataById('${ctx}/problem/delproblemById.do?id=${problemList.id}')" />
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细"
						style="cursor: pointer;"
						onclick="goToUrl('${ctx}/problem/detailproblem.do?id=${problemList.id}')" />
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%"
				class="btnTableList">
				<tr>
					<td><input type="button" value="删除" class="btn_list"
						onclick="delMoreByIds('${ctx}/problem/delproblemByIds.do')"
						<c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if> />
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp"%>
