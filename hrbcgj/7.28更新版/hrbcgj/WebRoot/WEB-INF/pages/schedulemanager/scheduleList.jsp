<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/schedulemanager/scheduleListClick.js"></script> <!-- js事件文件 -->
	</head>
<body>
	<div class="right_title"><p>位置：定时任务列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="scheduleForm" name="scheduleForm" action="${ctx}/schedulemanager/searchSchedule.do?isSearch=yes" method="post">
				<table class="query_table" border="0">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>执行的类：</th>
						<td>
							<input name="classType" type="text" id="classType" value="${schedule.classType}" autocomplete="off" style="width:300px;"/>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/schedulemanager/searchSchedule.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/schedulemanager/searchSchedule.do" id="scheduleList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${scheduleList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${scheduleList_rowNum}
				</display:column>

				<display:column property="classType" sortable="true" title="执行的类" style="text-align:center;" />
				<display:column property="frequency" sortable="true" title="执行频率" style="text-align:center;" />
				<display:column sortable="true" title="状态" style="text-align:center;" >
					<datadict:value2label dictType="radio" moduleName="schedulemanager" dictValue="${scheduleList.status}" property="status"/>
				</display:column>
				<display:column property="remark" sortable="true" title="备注" style="text-align:center;" />
				
				<display:column title="立即执行" style="text-align:center">
					<img src="${ctx}/images/executeNow.png" title="立即执行" style="cursor: pointer;"
						onclick="executeNow('${ctx}/schedulemanager/executeNow.do?classType=${scheduleList.classType}')"/>
				</display:column>
				
				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/schedulemanager/editSchedule.do?id=${scheduleList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/schedulemanager/delScheduleById.do?id=${scheduleList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/schedulemanager/detailSchedule.do?id=${scheduleList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/schedulemanager/addSchedule.do')"/>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/schedulemanager/delScheduleByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
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
