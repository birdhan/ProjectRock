<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：投票表详细</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>投票表详细</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>标题：</th>
			<td align="left" class="formTable_td">
				${vote.title}
			</td>
		</tr>
		<tr>
			<th>内容：</th>
			<td align="left" class="formTable_td">
				${vote.content}
			</td>
		</tr>
		<tr>
			<th>开始时间：</th>
			<td align="left" class="formTable_td">
				${vote.startTime}
			</td>
		</tr>
		<tr>
			<th>结束时间：</th>
			<td align="left" class="formTable_td">
			    ${vote.endTime}
			</td>
		</tr>
		<tr>
			<th>是否完成：</th>
			<td align="left" class="formTable_td">
				<datadict:value2label dictType="radio" moduleName="vote" dictValue="${vote.if_Finish}" property="if_Finish"/>
			</td>
		</tr>
		<tr>
			<th>类型：</th>
			<td align="left" class="formTable_td">
				<datadict:value2label dictType="radio" moduleName="vote" dictValue="${vote.type}" property="type"/>
			</td>
		</tr>
	</table>
	
	
	<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		<tr>
			<c:forEach var="it" items="${list}" >
		   <td>
			  选项：${it.name}---票数:${it.number} <br/>
			</td>
		   </c:forEach>
		</tr>
	</table>
	
    <table cellpadding="0" cellspacing="0" border="0" width="40%" class="btnTable">
		<tr>
		   <c
			<td>
				<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/vote/searchVote.do')"/>
			</td>
		</tr>
	</table>
	
	
	
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
