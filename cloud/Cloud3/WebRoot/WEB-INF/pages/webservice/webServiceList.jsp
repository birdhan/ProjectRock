<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/webservice/webServiceListClick.js"></script> <!-- js事件文件 -->
	</head>
<body>
	<div class="right_title"><p>位置：WebService接口列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="webServiceForm" name="webServiceForm" action="${ctx}/webservice/searchWebService.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>类名：</th>
						<td>
							<input name="className" type="text" id="className" value="${webService.className}" autocomplete="off"/>
						</td>						
					</tr>
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>服务名：</th>
						<td>
							<input name="serviceName" type="text" id="serviceName" value="${webService.serviceName}" autocomplete="off"/>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/webservice/searchWebService.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<table cellpadding="0" cellspacing="0" border="0" width="100%" style="margin-bottom: 8px;">
			<tr>
				<td style="width:10px;text-align: left;vertical-align: top;padding-top: 3px;padding-right:3px;"><font color="red">*</font></td>
				<td style="color:red;text-align: left;">
					1. 开发人员请注意，修改该模块下的数据后请重新启动Web服务，否则接口不会正常工作。<br/>
					2. 此列表中发布的接口仅限于CXF框架，如果是AXIS还需要到server-config.wsdd文件里去手动配置。
				</td>
			</tr>
		</table>
			
		<div class="listDiv">
			<display:table requestURI="${ctx}/webservice/searchWebService.do" id="webServiceList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${webServiceList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${webServiceList_rowNum}
				</display:column>

				<display:column property="className" sortable="true" title="类名" style="text-align:center;" maxLength="30"/>
				<display:column property="serviceName" sortable="true" title="服务名" style="text-align:center;" />
				<display:column sortable="true" title="状态" style="text-align:center;" >
					<datadict:value2label dictType="radio" moduleName="webservice" dictValue="${webServiceList.status}" property="status"/>
				</display:column>
				
				<display:column sortable="true" title="wsdl地址" style="text-align:center;" maxLength="50"><a href="javascript:openWSDl('${webServiceList.wsdlUrl}')">${webServiceList.wsdlUrl}</a></display:column>
				
				<display:column property="remark" sortable="true" title="备注" style="text-align:center;" maxLength="15"/>
				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/webservice/editWebService.do?id=${webServiceList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/webservice/delWebServiceById.do?id=${webServiceList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/webservice/detailWebService.do?id=${webServiceList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/webservice/addWebService.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/webservice/delWebServiceByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
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
