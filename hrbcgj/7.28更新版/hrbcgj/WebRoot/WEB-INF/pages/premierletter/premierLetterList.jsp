<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/premierletter/premierLetterListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：局长信箱列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="premierLetterForm" name="premierLetterForm" action="${ctx}/premierletter/searchPremierLetter.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>标题：</th>
						<td>
							<input name="title" type="text" id="title" value="${premierLetter.title}" autocomplete="off"/>
						</td>
					</tr>
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>申请人：</th>
						<td>
							<input name="reqregisteruser" type="text" id="reqregisteruser" value="${premierLetter.reqregisteruser}" autocomplete="off"/>
						</td>
					</tr>
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>状态：</th>
						<td>
							<datadict:select inputType="select" value="${premierLetter.repstatus}" property="repstatus" moduleName="premierletter" topLabel="全部" topValue=""/>
						</td>
					</tr>
					
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/premierletter/searchPremierLetter.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/premierletter/searchPremierLetter.do" id="premierLetterList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${premierLetterList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${premierLetterList_rowNum}
				</display:column>

				<display:column property="title" sortable="true" title="标题" style="text-align:center;" />

				<display:column property="reqcontent" sortable="true" title="邮件内容" style="text-align:center;" maxLength="50"/>

				<display:column sortable="true" title="申请时间" style="text-align:center;" >
					<fmt:formatDate value="${premierLetterList.reqtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>

				<display:column sortable="true" title="申请人" style="text-align:center;">
					<id2name:commonId2Name whereCol="id" tableName="SERVICE_REGISTERUSER" selCol="username" whereColValue="${premierLetterList.reqregisteruser}"/>
					(注册帐号：<id2name:commonId2Name whereCol="id" tableName="SERVICE_REGISTERUSER" selCol="account" whereColValue="${premierLetterList.reqregisteruser}"/>)
				</display:column>

				<display:column sortable="true" title="状态" style="text-align:center;" >
					<datadict:value2label dictType="radio" moduleName="premierletter" dictValue="${premierLetterList.repstatus}" property="repstatus"/>
				</display:column>

				<display:column property="repcontent" sortable="true" title="回复内容" style="text-align:center;" />

				<display:column property="reptime" sortable="true" title="回复时间" style="text-align:center;" maxLength="100"/>

				<display:column title="回复" style="text-align:center">
					<c:if test="${premierLetterList.repstatus == '0'}">
						<img src="${ctx}/images/cog_edit.png" title="回复" style="cursor: pointer;"
							onclick="goToUrl('${ctx}/premierletter/editPremierLetter.do?id=${premierLetterList.id}')"/>
					</c:if>
				</display:column>

				<%-- <display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/premierletter/delPremierLetterById.do?id=${premierLetterList.id}')"/>
				</display:column> --%>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/premierletter/detailPremierLetter.do?id=${premierLetterList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/premierletter/delPremierLetterByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
