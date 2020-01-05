<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/section/sectionFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：栏目表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/section/saveSection.do" method="post" name="sectionForm" id="sectionForm">
		<input type="hidden" id="id" name="id" value="${section.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>栏目表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>栏目名称：</th>
				<td>
					<input name="name" type="text" id="name" value="${section.name}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>位置：</th>
				<td>
					<datadict:inputRadio inputType="radio" value="${section.postion}" property="postion" moduleName="section"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>是否显示：</th>
				<td>
					<datadict:inputRadio inputType="radio" value="${section.isshow}" property="isshow" moduleName="section"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>排序顺序：</th>
				<td>
					<input name="sortnum" type="text" id="sortnum" value="${section.sortnum}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>父级栏目：</th>
				<td>
					<select id="pid" name="pid">
						<option value="root">根级栏目</option>
						<c:forEach items="${sectionList}" var="sec">
							<option value="${sec.id}" <c:if test="${section.pid eq sec.id}">selected="selected"</c:if>>${sec.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/section/searchSection.do')"/>
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
