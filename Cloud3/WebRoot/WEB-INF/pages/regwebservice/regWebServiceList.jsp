<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/regwebservice/regWebServiceListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：注册WebService列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="regWebServiceForm" name="regWebServiceForm" action="${ctx}/regwebservice/searchRegWebService.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>接口编号：</th>
						<td>
							<input name="interfaceNo" type="text" id="interfaceNo" value="${regWebService.interfaceNo}" autocomplete="off"/>
						</td>
					</tr>
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>类名：</th>
						<td>
							<input name="className" type="text" id="className" value="${regWebService.className}" autocomplete="off"/>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/regwebservice/searchRegWebService.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/regwebservice/searchRegWebService.do" id="regWebServiceList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${regWebServiceList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${regWebServiceList_rowNum}
				</display:column>

				<display:column property="interfaceNo" sortable="true" title="接口编号" style="text-align:center;" />

				<display:column property="className" sortable="true" title="类名" style="text-align:center;" />

				<display:column sortable="true" title="状态" style="text-align:center;" >
					<datadict:value2label dictType="radio" moduleName="regwebservice" dictValue="${regWebServiceList.status}" property="status"/>
				</display:column>

				<display:column property="remark" sortable="true" title="备注" style="text-align:center;" />

				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/regwebservice/editRegWebService.do?id=${regWebServiceList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/regwebservice/delRegWebServiceById.do?id=${regWebServiceList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/regwebservice/detailRegWebService.do?id=${regWebServiceList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/regwebservice/addRegWebService.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/regwebservice/delRegWebServiceByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
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
