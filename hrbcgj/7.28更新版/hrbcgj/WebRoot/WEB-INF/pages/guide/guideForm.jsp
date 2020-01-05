<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/guide/guideFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：办事指南表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/guide/saveGuide.do" method="post" name="guideForm" id="guideForm">
		<input type="hidden" id="id" name="id" value="${guide.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>办事指南表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>标题：</th>
				<td>
					<input name="title" type="text" id="title" value="${guide.title}" autocomplete="off" style="width:300px;"/>
				</td>
			</tr>
			<tr>
				<th>创建时间：</th>
				<td>
					<cloud:inputDate497 value="${guide.createtime}" property="createtime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th>详细内容：</th>
				<td>
					<cloud:htmleditor value="${guide.detailcontent}" property="detailcontent"/>
				</td>
			</tr>
			<tr>
				<th>排序顺序：</th>
				<td>
					<input name="sortnum" type="number" id="sortnum" value="${guide.sortnum}" autocomplete="off"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/guide/searchGuide.do')"/>
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
