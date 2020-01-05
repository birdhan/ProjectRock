<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/menupriv/menuPrivTreeClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：菜单权限树型视图</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<font color="red">注意：权限编号一旦确定，不得轻易更改。</font>
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>菜单权限树型视图</td>
		</tr>
	</table>

	<table class="formTableTree formTable">
		<tr>
			<td width="200">
				<strong>菜单名称</strong>														
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
		</tr>
		
		${tree}
		
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="列表视图" class="btn" onclick="javascript:goToUrl('${ctx}/menupriv/searchMenuPriv.do')"/>
			</td>
		</tr>
		<tr>
			<td style="color:#999999;padding-top: 5px;">
				说明：点击菜单链接后，可对该菜单下的权限进行编辑。
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
