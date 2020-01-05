<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：WebService接口详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>WebService接口详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>类名：</th>
			<td align="left" class="formTable_td">
				${webService.className}
			</td>
		</tr>
		<tr>
			<th>服务名：</th>
			<td align="left" class="formTable_td">
				${webService.serviceName}
			</td>
		</tr>
		<tr>
			<th>状态：</th>
			<td align="left" class="formTable_td">
				<datadict:value2label dictType="radio" moduleName="webservice" dictValue="${webService.status}" property="status"/>
			</td>
		</tr>
		<tr>
			<th>备注：</th>
			<td align="left" class="formTable_td">
				${webService.remark}
			</td>
		</tr>
		<tr>
			<th>生成WSDL地址：</th>
			<td align="left" class="formTable_td">
				${webService.wsdlUrl}
			</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/webservice/searchWebService.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
