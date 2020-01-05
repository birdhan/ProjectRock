<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/webservice/webServiceFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：WebService接口表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/webservice/saveWebService.do" method="post" name="webServiceForm" id="webServiceForm">
		<input type="hidden" id="id" name="id" value="${webService.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>WebService接口表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>类名：</th>
				<td>
					<input name="className" type="text" id="className" value="${webService.className}" onkeyup="checkClassIsExist(this.value)" autocomplete="off" style="width:300px;"/>
					<div id="classTypeDiv" style="display: inline;"></div>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>服务名：</th>
				<td>
					<input name="serviceName" type="text" id="serviceName" value="${webService.serviceName}" autocomplete="off" onkeyup="autoCreateWSDL()"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>状态：</th>
				<td>
					<datadict:inputRadio inputType="radio" value="${webService.status}" property="status" moduleName="webservice"/>
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<textarea rows="10" cols="10" id="remark" name="remark">${webService.remark}</textarea>
				</td>
			</tr>
			<tr>
				<th>生成WSDL地址：</th>
				<td>
					<span id="wsdlSpan">${webService.wsdlUrl}</span>
					<input type="hidden" id="wsdlUrl" name="wsdlUrl" value="${webService.wsdlUrl}"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn" onclick="return validateForm()"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/webservice/searchWebService.do')"/>
					<input type="reset" value="重置" class="btn"/>
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
