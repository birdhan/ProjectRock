<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/viewmesasge/viewMessageListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：查看留言列表</p></div>
	<div class="formDiv">
	<div class="query">
			<form id="viewMessageForm" name="viewMessageForm" action="${ctx}/viewmesasge/searchViewMessage.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>话题标题：</th>
						<td>
							<input name="title" type="text" id="title" value="${viewMessage.title}" autocomplete="off"/>
						</td>
					</tr>
					
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/viewmesasge/searchViewMessage.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/viewmesasge/searchViewMessage.do" id="viewMessageList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${viewMessageList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${viewMessageList_rowNum}
				</display:column>

				<display:column property="title"  sortable="true" title="话题标题" style="text-align:center;" maxLength="20" >
					<%-- <id2name:commonId2Name whereCol="id" tableName="SERVICE_TOPIC" selCol="title" whereColValue="${viewMessageList.title}" /> --%>
				</display:column>

				<display:column  sortable="true" title="用户名" style="text-align:center;" >
					<id2name:commonId2Name whereCol="id" tableName="SERVICE_REGISTERUSER" selCol="username" whereColValue="${viewMessageList.reqregisteruser}"/>
				</display:column>

				<display:column property="reqcontent" sortable="true" title="留言内容" style="text-align:center;" maxLength="20" />

				<display:column sortable="true" title="留言时间" style="text-align:center;" >
					<fmt:formatDate value="${viewMessageList.reqtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>

				<display:column sortable="true" title="是否显示" style="text-align:center;" >
					<datadict:value2label dictType="radio" moduleName="article" dictValue="${viewMessageList.isshow}" property="isshow"/>
				</display:column>
				
				<display:column title="编辑" style="text-align:center">
						<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
							onclick="goToUrl('${ctx}/viewmesasge/editViewMessage.do?id=${viewMessageList.id}')"/>
				</display:column>

				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/viewmesasge/delViewMessageById.do?id=${viewMessageList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/viewmesasge/detailViewMessage.do?id=${viewMessageList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<%-- <input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/viewmesasge/addViewMessage.do')"/> --%>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/viewmesasge/delViewMessageByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
