<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/question/questionExportClick.js"></script> <!-- js事件文件 -->
	</head>

<body style="padding: 10px;">
	<form id="exportForm" action="${ctx}/question/exportData.do" method="post">
		<input type="hidden" id="ids" name="ids" value="${ids}"/>
		<input type="hidden" id="columns" name="columns"/>
		<table cellpadding="0" cellspacing="0" border="0" width="100%" style="margin-bottom: 10px;">
			<tr>
				<td colspan="2" height="20" align="center" style="border-bottom-style:dashed; border-bottom-width:1px; border-bottom-color:#3399FF;">
					<font style="font-size: 15px;font-weight: bold;">筛选条件</font>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" class="exportTable" style="margin-top: 10px;">
			<tr>
				<td colspan="2"><input type="checkbox" onclick="chooseAllPro(this)" class="chx"/>全选</td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="100%" border="0" id="proTable" name="proTable">
						<tr>
						<c:forEach items="${fieldMap}" var="fm" varStatus="status">
							<td height="25" width="25%">
								<input type="checkbox" class="chx" name="proCheckbox" id="pro_${status.index}" value="{name:'${fm.key}',value:'${fm.value}'}" <c:if test="${fm.value != 'id'}">checked="checked"</c:if>/>${fm.key}
							</td>

						<c:if test="${(status.index+1) % 4 == 0}"><!-- 显示4列就换行 -->
						</tr>
						<tr>
						</c:if>

						</c:forEach>

						<c:if test="${4-fn:length(fieldMap)%4 != 4}">
							<c:forEach begin="1" end="${4-fn:length(fieldMap)%4}" step="1" varStatus="vs">
								<td>&nbsp;</td>
							</c:forEach>
						</c:if>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="infoDiv" style="margin-top: 2px;">&nbsp;</div>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="button" id="exportBtn" value="导出" class="btn_list" onclick="beginExportData()"/>
					<input type="button" value="关闭" class="btn_list" onclick="parent.colseExportWin()"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" style="margin-bottom: 10px;margin-top:10px;">
			<tr>
				<td colspan="2">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td style="width:10px;text-align: left;vertical-align: top;padding-top: 3px;padding-right:3px;"><font color="red">*</font></td>
							<td style="color:#B2B2B2;">
								默认选择所有列(除主键外)，也可对要导出的列进行手动选择。<br/>
								<c:if test="${ids != ''}">
									您当前已经选择了${idsSize}条记录，若不选择，系统会将数据全部导出。
								</c:if>
								<c:if test="${ids == ''}">
									您当前没有选择任何数据，系统将为您将数据全部导出。
								</c:if>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
