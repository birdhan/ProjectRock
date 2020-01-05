<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：调用WebService详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>调用WebService详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>wsdl地址：</th>
			<td align="left" class="formTable_td">
				${callWebService.wsdl}
			</td>
		</tr>
		<tr>
			<th>调用类型：</th>
			<td align="left" class="formTable_td">
				<datadict:value2label dictType="radio" moduleName="callwebservice" dictValue="${callWebService.callType}" property="callType"/>
			</td>
		</tr>
		<tr>
			<th>beanId：</th>
			<td align="left" class="formTable_td">
				${callWebService.beanId}
			</td>
		</tr>
		<tr>
			<th>备注：</th>
			<td align="left" class="formTable_td">
				${callWebService.remark}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/callwebservice/searchCallWebService.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
