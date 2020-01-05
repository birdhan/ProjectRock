<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/requestrecord/requestRecordListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：访问记录列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="requestRecordForm" name="requestRecordForm" action="${ctx}/requestrecord/searchRequestRecord.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>操作帐号：</th>
						<td>
							<input name="useraccount" type="text" id="useraccount" value="${requestRecord.useraccount}" autocomplete="off"/>
						</td>
					</tr>
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>访问IP：</th>
						<td>
							<input name="formip" type="text" id="formip" value="${requestRecord.formip}" autocomplete="off"/>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/requestrecord/searchRequestRecord.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/requestrecord/searchRequestRecord.do" id="requestRecordList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${requestRecordList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${requestRecordList_rowNum}
				</display:column>

				<display:column property="useraccount" sortable="true" title="操作帐号" style="text-align:center;" />

				<display:column property="usertype" sortable="true" title="用户类型" style="text-align:center;" />

				<display:column property="formip" sortable="true" title="访问IP" style="text-align:center;" />

				<display:column property="requesturi" sortable="true" title="请求地址" style="text-align:center;" />

				<display:column sortable="true" title="请求时间" style="text-align:center;" >
					<fmt:formatDate value="${requestRecordList.reqtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/requestrecord/delRequestRecordById.do?id=${requestRecordList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/requestrecord/detailRequestRecord.do?id=${requestRecordList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/requestrecord/delRequestRecordByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
