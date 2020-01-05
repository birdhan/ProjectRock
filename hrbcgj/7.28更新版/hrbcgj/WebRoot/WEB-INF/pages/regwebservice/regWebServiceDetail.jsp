<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：注册WebService详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>注册WebService详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>接口编号：</th>
			<td align="left" class="formTable_td">
				${regWebService.interfaceNo}
			</td>
		</tr>
		<tr>
			<th>类名：</th>
			<td align="left" class="formTable_td">
				${regWebService.className}
			</td>
		</tr>
		<tr>
			<th>状态：</th>
			<td align="left" class="formTable_td">
				<datadict:value2label dictType="radio" moduleName="regwebservice" dictValue="${regWebService.status}" property="status"/>
			</td>
		</tr>
		<tr>
			<th>备注：</th>
			<td align="left" class="formTable_td">
				${regWebService.remark}
			</td>
		</tr>
		<tr>
			<th>当前类下的方法：</th>
			<td align="left" class="formTable_td">
				<table width="100%">
					<tr style="background-color: #edf6fb;vertical-align: middle; font-size: 13px;font-weight: bold;color: #2D77B8;">
						<td style="width: 50px;text-align: center;">序号</td>
						<td style="text-align: left;">方法名称</td>
						<td style="text-align: left;">方法详细</td>
					</tr>
					<c:forEach items="${methodArr}" var="method" varStatus="status">
					<tr>
						<td style="text-align: center;">${status.index+1}</td>
						<td style="text-align: left;">${method.name}</td>
						<td style="text-align: left;padding-left: 10px;">${method}</td>
					</tr>						
					</c:forEach>	
				</table>				
			</td>
		</tr>
		<tr>
			<th>当前类实现的接口：</th>
			<td align="left" class="formTable_td">
				<table width="100%">
					<c:if test="${fn:length(interfaceArr) != 0}">
						<tr style="background-color: #edf6fb;vertical-align: middle; font-size: 13px;font-weight: bold;color: #2D77B8;">
							<td style="width: 50px;text-align: center;">序号</td>
							<td style="text-align: left;">接口类名</td>
						</tr>
						<c:forEach items="${interfaceArr}" var="interface" varStatus="status">
						<tr>
							<td style="text-align: center;">${status.index+1}</td>
							<td style="text-align: left;">${interface.name}</td>
						</tr>						
						</c:forEach>
					</c:if>	
					<c:if test="${fn:length(interfaceArr) == 0}"><font color="red">没有实现任何接口，建议实现 com.cloud.base.webserviceinterface.IExecuteWebService 接口。</font></c:if>					
				</table>				
			</td>
		</tr>					
	</table>

	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/regwebservice/searchRegWebService.do')"/>
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
