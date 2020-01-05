<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/interaction/interactionFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：互动交流表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/interaction/saveInteraction.do" method="post" name="interactionForm" id="interactionForm">
		<input type="hidden" id="id" name="id" value="${interaction.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>互动交流表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>申请人：</th>
				<td>
					<id2name:commonId2Name whereCol="id" tableName="SERVICE_REGISTERUSER" selCol="username" whereColValue="${interaction.reqregisteruser}"/>
					<%-- (注册帐号：<id2name:commonId2Name whereCol="id" tableName="SERVICE_REGISTERUSER" selCol="account" whereColValue="${interaction.reqregisteruser}"/>) --%>
					<input name="reqregisteruser" type="hidden" id="reqregisteruser" value="${interaction.reqregisteruser}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>标题：</th>
				<td>
					${interaction.title}
					<input name="title" type="hidden" id="title" value="${interaction.title}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>交流内容：</th>
				<td>
					${interaction.reqcontent}
					<input name="reqcontent" type="hidden" id="reqcontent" value="${interaction.reqcontent}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>申请时间：</th>
				<td>
					<fmt:formatDate value="${interaction.reqtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					<input name="reqtime" type="hidden" id="reqtime" value="${interaction.reqtime}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>是否显示：</th>
				<td>
					<datadict:inputRadio inputType="radio" value="${interaction.isshow}" property="isshow" moduleName="article"/>
				</td>
			</tr>
			
			<tr>
				<th><font color="red">*</font>回复内容：</th>
				<td>
				<input name="repstatus" type="hidden" id="repstatus" value="${interaction.repstatus}" autocomplete="off"/>
					<textarea rows="20" cols="20" name="repcontent" id="repcontent" style="width:400px;heigth:500px;">${interaction.repcontent}</textarea>
				</td>
			</tr>
			
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/interaction/searchInteraction.do')"/>
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
