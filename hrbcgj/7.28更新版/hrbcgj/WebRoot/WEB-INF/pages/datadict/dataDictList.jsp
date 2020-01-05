<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/datadict/dataDictListClick.js"></script> <!-- js事件文件 -->
	</head>
<body>
	<div class="right_title"><p>位置：数据字典列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="dataDictForm" name="dataDictForm" action="${ctx}/datadict/searchDataDict.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>字典显示值：</th>
						<td>
							<input name="dictLabel" type="text" id="dictLabel" value="${dataDict.dictLabel}" autocomplete="off"/>
						</td>
					</tr>
					
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>字典控件类型：</th>
						<td>
							<datadict:select inputType="select" value="${dataDict.dictType}" property="dictType" moduleName="datadict" topValue="" topLabel=""/>
						</td>
					</tr>
					
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>模块名称：</th>
						<td>
							<input name="moduleName" type="text" id="moduleName" value="${dataDict.moduleName}" autocomplete="off"/>
						</td>
					</tr>
					
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>控件属性：</th>
						<td>
							<input name="property" type="text" id="property" value="${dataDict.property}" autocomplete="off"/>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/datadict/searchDataDict.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/datadict/searchDataDict.do" id="dataDictList" 
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${dataDictList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${dataDictList_rowNum}
				</display:column>

				<display:column property="dictValue" sortable="true" title="字典值" style="text-align:center;"/>
				<display:column property="dictLabel" sortable="true" title="字典显示值" style="text-align:center;" />
				<display:column property="dictType" sortable="true" title="字典控件类型" style="text-align:center;" />
				<display:column property="moduleName" sortable="true" title="模块名称" style="text-align:center;" />
				<display:column property="property" sortable="true" title="控件属性" style="text-align:center;" />
				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/datadict/editDataDict.do?id=${dataDictList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/datadict/delDataDictById.do?id=${dataDictList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/datadict/detailDataDict.do?id=${dataDictList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/datadict/addDataDict.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/datadict/delDataDictByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
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
