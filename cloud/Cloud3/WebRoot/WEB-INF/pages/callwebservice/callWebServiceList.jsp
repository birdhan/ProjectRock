<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/callwebservice/callWebServiceListClick.js"></script> <!-- js事件文件 -->
	</head>
<body>
	<div class="right_title"><p>位置：调用WebService列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="callWebServiceForm" name="callWebServiceForm" action="${ctx}/callwebservice/searchCallWebService.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>wsdl地址：</th>
						<td>
							<input name="wsdl" type="text" id="wsdl" value="${callWebService.wsdl}" autocomplete="off" style="width:300px;"/>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/callwebservice/searchCallWebService.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/callwebservice/searchCallWebService.do" id="callWebServiceList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${callWebServiceList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${callWebServiceList_rowNum}
				</display:column>

				<display:column sortable="true" title="wsdl地址" style="text-align:center;" maxLength="100"><a href="javascript:openWSDl('${callWebServiceList.wsdl}')">${callWebServiceList.wsdl}</a></display:column>

				<display:column sortable="true" title="调用类型" style="text-align:center;" >
					<datadict:value2label dictType="radio" moduleName="callwebservice" dictValue="${callWebServiceList.callType}" property="callType"/>
				</display:column>

				<display:column property="beanId" sortable="true" title="beanId" style="text-align:center;" />

				<display:column property="remark" sortable="true" title="备注" style="text-align:center;" maxLength="70"/>

				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/callwebservice/editCallWebService.do?id=${callWebServiceList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/callwebservice/delCallWebServiceById.do?id=${callWebServiceList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/callwebservice/detailCallWebService.do?id=${callWebServiceList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/callwebservice/addCallWebService.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/callwebservice/delCallWebServiceByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
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
