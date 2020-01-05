<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/special/specialFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：专题专栏表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/special/savespecial.do" method="post" name="specialForm" id="specialForm">
		<input type="hidden" id="id" name="id" value="${special.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>专题专栏表单</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th>图片链接：</th>
				<td>
					<input name="weburl" type="text" id="weburl" value="${special.weburl}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>链接图标：</th>
				<td>
					<cloud:uploadPic imgId="picurl" picFileId="${special.picurl}"/>
				</td>
			</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/special/searchspecial.do')"/>
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
