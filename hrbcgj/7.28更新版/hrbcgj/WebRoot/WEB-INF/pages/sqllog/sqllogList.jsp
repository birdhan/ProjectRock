<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/sqllog/sqllogListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：日志管理列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="sqllogForm" name="sqllogForm" action="${ctx}/sqllog/searchSqllog.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>时间段选择：</th>
						<td>
							<input class="Wdate" type="text" name="fbegintimeSearch" id="fbegintimeSearch" class="form_text" readonly="true" 
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', maxDate:'#F{$dp.$D(\'fendtimeSearch\')}'})" value="<fmt:formatDate value="${sqllog.fbegintimeSearch}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
							&nbsp;至&nbsp;
					    	<input class="Wdate" type="text" name="fendtimeSearch" id="fendtimeSearch" class="form_text" readonly="true" 
					    		onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', minDate:'#F{$dp.$D(\'fbegintimeSearch\')}'})" value="<fmt:formatDate value="${sqllog.fendtimeSearch}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
						</td>
					</tr>
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>显示SQL类型：</th>
						<td>
							<c:forEach begin="1" end="5" varStatus="status">
								<input type="radio" name="fsqltype" value="${status.index}" <c:if test="${status.index == sqllog.fsqltype}">checked="checked"</c:if>/>类型${status.index}
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>SQL类型说明：</th>
						<td>
							1:无条件Sql&nbsp;&nbsp;
							2:有业务逻辑查询&nbsp;&nbsp;
							3:有条件删除SQL语句&nbsp;&nbsp;
							4:空SQL语句&nbsp;&nbsp;
							5:保存SQL语句(update和insert语句)
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/sqllog/searchSqllog.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/sqllog/searchSqllog.do" id="sqllogList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${sqllogList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${sqllogList_rowNum}
				</display:column>

				<display:column sortable="true" title="操作时间" style="text-align:center;" >
					<fmt:formatDate value="${sqllogList.fbegintime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>

				<display:column property="fsql" sortable="true" title="SQL" style="text-align:center;" maxLength="50"/>

				<display:column property="fsqltype" sortable="true" title="SQL类型" style="text-align:center;" />

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/sqllog/delSqllogById.do?id=${sqllogList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/sqllog/detailSqllog.do?id=${sqllogList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/sqllog/delSqllogByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
						<input type="button" value="导出数据" class="btn_list" onclick="exportData()" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
						<input type="button" value="清理日志" class="btn_list" onclick="clearSqllog('${ctx}/sqllog/clearSqllog.do')"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
