<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/callwebservice/callWebServiceFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：调用WebService表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/callwebservice/saveCallWebService.do" method="post" name="callWebServiceForm" id="callWebServiceForm">
		<input type="hidden" id="id" name="id" value="${callWebService.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>调用WebService表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>wsdl地址：</th>
				<td>
					<input name="wsdl" type="text" id="wsdl" value="${callWebService.wsdl}" autocomplete="off" style="width:300px;" onkeyup="checkWSDL(this.value)"/>
					<a href="javascript:openWSDl()">检测地址</a>
					<div id="wsdlDiv" style="display: inline;"></div>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>调用类型：</th>
				<td>
					<datadict:inputRadio inputType="radio" value="${callWebService.callType}" property="callType" moduleName="callwebservice"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>beanId：</th>
				<td>
					<input name="beanId" type="text" id="beanId" value="${callWebService.beanId}" autocomplete="off" onkeyup="checkBeanId(this.value)"/>
					<div id="beanIdDiv" style="display: inline;"></div>
				</td>
			</tr>
			<tr>
				<th>备注：</th>
				<td>
					<textarea rows="10" cols="10" id="remark" name="remark">${callWebService.remark}</textarea>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/callwebservice/searchCallWebService.do')"/>
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
