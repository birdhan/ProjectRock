<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript">
			var menuJson = '${menuJson}';
			menuJson = eval("("+menuJson+")");
			var menuPrivJson = '${menuPrivJson}';
			menuPrivJson = eval("("+menuPrivJson+")");
		</script>
		<script type="text/javascript" src="${ctx}/click/menupriv/menuPrivTreeCheckboxClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>菜单权限树型视图</td>
		</tr>
	</table>

	<table class="formTableTree formTable" id="treeTable">
		<tr>
			<td width="200">
				<strong>菜单名称</strong>														
			</td>	
			<td style="text-align: center;width:10px;">
				&nbsp;														
			</td>			
			<td>
				<strong>菜单权限名称及编号</strong>														
			</td>				
		</tr>
	
		<tr>
			<td width="180">
				<img src="${ctx}/images/folder-open.gif" style="vertical-align: middle; margin-right:2px;"/>菜单根路径														
			</td>				
			<td>
				&nbsp;														
			</td>	
			<td>
				&nbsp;														
			</td>			
		</tr>
		
		${tree}
		
	</table>
	
	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="确认" class="btn_list" onclick="javascript:saveRolePriv()"/>				
				<input type="button" value="全选" class="btn_list" onclick="javascript:chooseAll()" id="chooseAllBtn"/>
				<input type="button" value="关闭" class="btn_list" onclick="javascript:parent.colseMenuPrivTreeCheckboxWin()"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
