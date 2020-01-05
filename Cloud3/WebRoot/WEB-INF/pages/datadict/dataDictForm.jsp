<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/datadict/dataDictFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：数据字典表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/datadict/saveDataDict.do" method="post" name="dataDictForm" id="dataDictForm">
		<input type="hidden" id="id" name="id" value="${dataDict.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>数据字典表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>字典值：</th>
				<td>
					<input name="dictValue" type="text" id="dictValue" value="${dataDict.dictValue}" autocomplete="off" onkeyup="checkRepeatData()"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>字典显示值：</th>
				<td>
					<input name="dictLabel" type="text" id="dictLabel" value="${dataDict.dictLabel}" autocomplete="off" onkeyup="checkRepeatData()"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>字典控件类型：</th>
				<td>
					<datadict:select inputType="select" value="${dataDict.dictType}" property="dictType" moduleName="datadict" topValue="" topLabel="" onChange="checkRepeatData()"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>模块名称：</th>
				<td>
					<input name="moduleName" type="text" id="moduleName" value="${dataDict.moduleName}" autocomplete="off" onkeyup="checkRepeatData()"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>控件属性：</th>
				<td>
					<input name="property" type="text" id="property" value="${dataDict.property}" autocomplete="off" onkeyup="checkRepeatData()"/>
				</td>
			</tr>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn" onclick="return validationForm()"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/datadict/searchDataDict.do')"/>
					<input type="reset" value="重置" class="btn"/>
					<div id="infodiv" style="display: inline;"></div>
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
