<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/menupriv/editPrivWin.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="tableContentDiv">
<div class="formDiv">
	<font color="red" style="display: block;margin-top: 3px;">注意：权限编号一旦确定，不得轻易更改。</font>
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>权限编辑列表</td>
		</tr>
	</table>

	<table class="formTable">
		<tr>
			<td style="text-align: center;"><strong>编号</strong></td>
			<td style="text-align: center;"><strong>名称</strong></td>
			<td style="text-align: center;"><strong>编辑</strong></td>
			<td style="text-align: center;"><strong>删除</strong></td>
		</tr>
			
		<tbody id="privBody">
			<c:forEach items="${privList}" var="priv" varStatus="status">
				<tr id="tr${status.index}">					
					<td style="text-align: center;width:80px;">
						<input type="hidden" id="id${status.index}" value="${priv.id}"/>
						<div id="noTextDiv${status.index}">${priv.privNo}</div>
						<div id="noInputDiv${status.index}" style="display: none;"><input type="text" id="privNo${status.index}" value="${priv.privNo}" style="width:80%;"/></div>
					</td>
					<td style="text-align: center;">
						<div id="nameTextDiv${status.index}">${priv.privName}</div>
						<div id="nameInputDiv${status.index}" style="display: none;"><input type="text" id="privName${status.index}" value="${priv.privName}" style="width:80%;"/></div>
					</td>
					<td style="text-align: center; width:40px;"><img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;" onclick="editMenuPrivByPrivId('${status.index}')"/></td>
					<td style="text-align: center; width:40px;"><img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;" onclick="delMenuPrivByPrivId('${status.index}')"/></td>
				</tr>
			</c:forEach>
		</tbody>
		
		<c:if test="${fn:length(privList) == 0}">
			<tr>
				<td style="text-align: center;" colspan="4"><font color="red">暂时没有数据</font></td>
			</tr>
		</c:if>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<%--<input type="button" value="添加基本权限" class="btn_list" onclick="addBasePriv()" title="增删改查基本权限"/>
				<input type="button" value="新增" class="btn_list" onclick="addRow()"/>
				--%><input type="button" value="关闭" class="btn_list" onclick="parent.colseMenuPrivTreeWin()"/>
			</td>
		</tr>
	</table>
	
	<input type="hidden" id="menuId" value="${menuId}"/>
	<input type="hidden" id="menuName" value="${menuName}"/>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
