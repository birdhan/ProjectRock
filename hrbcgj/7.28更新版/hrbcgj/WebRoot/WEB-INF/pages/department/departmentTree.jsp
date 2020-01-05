<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/department/epartmentTreeClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：部门树型视图</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/department/delDepartmentByIds.do?viewType=tree" method="post" name="departmentForm" id="departmentForm">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>部门树型视图</td>
		</tr>
	</table>

	<table class="formTableTree formTable">
		<tr>
			<td style="text-align: center;width: 10px;">
				<input type="checkbox" onclick="choose()"/>
			</td>
			<td>
				<strong>部门名称</strong>														
			</td>				
			<td style="text-align: center;width:auto;">
				<strong>部门编号</strong>														
			</td>
			<td style="text-align: center;width:auto;">
				<strong>上级部门编号</strong>														
			</td>
			<td style="text-align: center;">
				<strong>编辑</strong>														
			</td>
			<td style="text-align: center;">
				<strong>删除</strong>														
			</td>
			<td style="text-align: center;">
				<strong>详细</strong>														
			</td>			
			<td style="text-align: center;">
				<strong>添加下属部门</strong>														
			</td>
		</tr>
	
		<!-- 根部门信息 -->
		<tr>
			<td style="text-align: center;width: 10px;">
				&nbsp;
			</td>
			<td>
				<img src="${ctx}/images/dept.png" style="vertical-align: middle; margin-right:2px;"/>${sc.rootDepartName}														
			</td>				
			<td style="text-align: center;width:auto;">
				${sc.rootDepartNo}													
			</td>
			<td style="text-align: center;">
				&nbsp;													
			</td>
			<td style="text-align: center;">
				&nbsp;													
			</td>
			<td style="text-align: center;">
				&nbsp;													
			</td>	
			<td style="text-align: center;">
				&nbsp;													
			</td>
			<td style="text-align: center;">
				<img src="${ctx}/images/dept.png" title="添加下属部门" style="cursor: pointer;" onclick="goToUrl('${ctx}/department/addDepartment.do?parentDeptNo=${sc.rootDepartNo}&viewType=tree')"/>											
			</td>			
		</tr>
		
		${treeData}
		
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="删除" class="btn" onclick="return delMoreData()"/>
				<input type="button" value="列表视图" class="btn" onclick="javascript:goToUrl('${ctx}/department/searchDepartment.do')"/>
			</td>
		</tr>
		<tr>
			<td style="color:#999999;padding-top: 5px;">
				说明：如果要修改根部门，请到<font color='red'>“系统设置”->“参数设置”</font>页面修改部门信息。
			</td>
		</tr>
	</table>
	</form>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
