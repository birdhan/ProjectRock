<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：数据字典详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>数据字典详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>字典值：</th>
			<td align="left" class="formTable_td">
				${dataDict.dictValue}
			</td>
		</tr>
		<tr>
			<th>字典显示值：</th>
			<td align="left" class="formTable_td">
				${dataDict.dictLabel}
			</td>
		</tr>
		<tr>
			<th>字典控件类型：</th>
			<td align="left" class="formTable_td">
				${dataDict.dictType}
			</td>
		</tr>
		<tr>
			<th>模块名称：</th>
			<td align="left" class="formTable_td">
				${dataDict.moduleName}
			</td>
		</tr>
		<tr>
			<th>控件属性：</th>
			<td align="left" class="formTable_td">
				${dataDict.property}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/datadict/searchDataDict.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
