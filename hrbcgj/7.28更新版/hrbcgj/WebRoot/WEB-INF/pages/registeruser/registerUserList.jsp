<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/registeruser/registerUserListClick.js"></script> <!-- 列表事件 -->
	</head>
<body>
	<div class="right_title"><p>位置：注册用户列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="registerUserForm" name="registerUserForm" action="${ctx}/registeruser/searchRegisterUser.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>帐号：</th>
						<td>
							<input name="account" type="text" id="account" value="${registerUser.account}" autocomplete="off"/>
						</td>
					</tr>
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>姓名：</th>
						<td>
							<input name="username" type="text" id="username" value="${registerUser.username}" autocomplete="off"/>
						</td>
					</tr>
				</table>

				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/registeruser/searchRegisterUser.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="listDiv">
			<display:table requestURI="${ctx}/registeruser/searchRegisterUser.do" id="registerUserList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">

				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${registerUserList.id}"/>
				</display:column>

				<display:column title="序号" style="width:30px;text-align:center;">
					${registerUserList_rowNum}
				</display:column>

				<display:column property="account" sortable="true" title="帐号" style="text-align:center;" />

				<display:column property="username" sortable="true" title="姓名" style="text-align:center;" />
				
				<display:column property="idnum" sortable="true" title="身份证号" style="text-align:center;" />
				
				<display:column property="mobile" sortable="true" title="手机号" style="text-align:center;" />
				
				<display:column property="points" sortable="true" title="积分" style="text-align:center;" />
				
				<display:column title="积分修改" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="积分修改" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/registeruser/editRegisterUser.do?id=${registerUserList.id}')"/>
				</display:column>
				
				<display:column title="删除" style="text-align:center">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/registeruser/delRegisterUserById.do?id=${registerUserList.id}')"/>
				</display:column>

				<display:column title="详细" style="text-align:center">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/registeruser/detailRegisterUser.do?id=${registerUserList.id}')"/>
				</display:column>
			</display:table>

			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
				<tr>
					<td>
						<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/registeruser/delRegisterUserByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
