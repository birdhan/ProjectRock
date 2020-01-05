<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/regwebservice/regWebServiceFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：注册WebService表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/regwebservice/saveRegWebService.do" method="post" name="regWebServiceForm" id="regWebServiceForm">
		<input type="hidden" id="id" name="id" value="${regWebService.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>注册WebService表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>接口编号：</th>
				<td>
					<input name="interfaceNo" type="text" id="interfaceNo" value="${regWebService.interfaceNo}" autocomplete="off" style="width: 300px;" onkeyup="checkRepeatData(this.value)"/>
					<div id="interfaceNoDiv" style="color:red;display: inline;"></div>
				</td>
			</tr>
			
			<tr>
				<th><font color="red">*</font>类名：</th>
				<td>
					<input name="className" type="text" id="className" value="${regWebService.className}" autocomplete="off" style="width: 300px;" onkeyup="checkClassName(this.value)"/>
					<div id="classNameDiv" style="color:red;display: inline;"></div>
				</td>
			</tr>
			
			<tr>
				<th><font color="red">*</font>状态：</th>
				<td>
					<datadict:inputRadio inputType="radio" value="${regWebService.status}" property="status" moduleName="regwebservice"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>备注：</th>
				<td>
					<textarea rows="10" cols="10" id="remark" name="remark" style="width: 300px;">${regWebService.remark}</textarea>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn" onclick="return validateForm()"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/regwebservice/searchRegWebService.do')"/>
					<input type="reset" value="重置" class="btn"/>
				</td>
			</tr>
			
			<tr>
				<td style="padding-top: 5px;color:red;">
					说明：<br/>
					1.接口编号不可以重复。</br>
					2.方法名称为被调用的函数名称，创建该类时最好要实现IExecuteWebService接口，重写接口里面的方法，可参照Demo源下的com.cloud.demo.executewebservice包下的DemoWS类。</br>
					3.类名为类的全路径，但是该类下必须要与com.cloud.webservice.webservice.WebServiceWSImpl类下的webServiceIndex方法一样，其中包括参数也要完全一模一样方可注册，可参考Demo例子模块。
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
