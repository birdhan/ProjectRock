<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：栏目详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>栏目详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>栏目名称：</th>
			<td align="left" class="formTable_td">
				${section.name}
			</td>
		</tr>
		<tr>
			<th>位置：</th>
			<td align="left" class="formTable_td">
				<datadict:value2label dictType="radio" moduleName="section" dictValue="${section.postion}" property="postion"/>
			</td>
		</tr>
		<tr>
			<th>是否显示：</th>
			<td align="left" class="formTable_td">
				<datadict:value2label dictType="radio" moduleName="section" dictValue="${section.isshow}" property="isshow"/>
			</td>
		</tr>
		<tr>
			<th>排序顺序：</th>
			<td align="left" class="formTable_td">
				${section.sortnum}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/section/searchSection.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
