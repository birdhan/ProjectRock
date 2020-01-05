<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/special/specialListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：专题专栏列表</p></div>
	<div class="formDiv">
		<div class="listDiv">
			<display:table requestURI="${ctx}/special/searchspecial.do" id="specialList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">
				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${specialList.id}"/>
				</display:column>
				<display:column title="序号" style="width:30px;text-align:center;">
					${specialList_rowNum}
				</display:column>
				<display:column property="weburl" sortable="true" title="图片链接" style="text-align:center;" />
				<display:column sortable="true" title="logo图片" style="text-align:center;">
				<img src="${ctx}/uploadpic/getPic.do?id=${specialList.picurl}" style="width:50px;height:50px;"/>
				</display:column>
				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/special/editspecial.do?id=${specialList.id}')"/>
				</display:column>
				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/special/delspecialById.do?id=${specialList.id}')"/>
				</display:column>
				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/special/detailspecial.do?id=${specialList.id}')"/>
				</display:column>
			</display:table>
			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/special/addspecial.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/special/delspecialByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
