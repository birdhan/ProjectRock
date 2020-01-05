<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/easyservice/easyServiceListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：便民服务列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="easyServiceForm" name="easyServiceForm" action="${ctx}/easyservice/searchEasyService.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>服务名称：</th>
						<td>
							<input name="name" type="text" id="name" value="${easyService.name}" autocomplete="off"/>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/easyservice/searchEasyService.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/easyservice/searchEasyService.do" id="easyServiceList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${easyServiceList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${easyServiceList_rowNum}
				</display:column>

				<display:column property="name" sortable="true" title="服务名称" style="text-align:center;" />

				<display:column sortable="true" title="图标" style="text-align:center;">
					<img src="${ctx}/uploadpic/getPic.do?id=${easyServiceList.logopic}" style="width:50px;height:50px;"/>
				</display:column>

				<display:column sortable="true" title="外部链接" style="text-align:center;" maxLength="100">
					<a href="${easyServiceList.linkurl}" target="_blank">${easyServiceList.linkurl}</a>
				</display:column>

				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/easyservice/editEasyService.do?id=${easyServiceList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/easyservice/delEasyServiceById.do?id=${easyServiceList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/easyservice/detailEasyService.do?id=${easyServiceList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/easyservice/addEasyService.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/easyservice/delEasyServiceByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
