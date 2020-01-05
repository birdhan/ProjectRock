<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/guide/guideListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：办事指南列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="guideForm" name="guideForm" action="${ctx}/guide/searchGuide.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>标题：</th>
						<td>
							<input name="title" type="text" id="title" value="${guide.title}" autocomplete="off"/>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/guide/searchGuide.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/guide/searchGuide.do" id="guideList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${guideList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${guideList_rowNum}
				</display:column>

				<display:column property="title" sortable="true" title="标题" style="text-align:center;" />

				<display:column sortable="true" title="创建时间" style="text-align:center;" >
					<fmt:formatDate value="${guideList.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>

				<display:column property="sortnum" sortable="true" title="排序顺序" style="text-align:center;" />

				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/guide/editGuide.do?id=${guideList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/guide/delGuideById.do?id=${guideList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/guide/detailGuide.do?id=${guideList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/guide/addGuide.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/guide/delGuideByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
