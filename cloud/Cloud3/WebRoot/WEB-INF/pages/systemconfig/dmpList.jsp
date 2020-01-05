<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/systemconfig/dmpListClick.js"></script> <!-- js事件文件 -->
	</head>
<body>
	<div class="formDiv">
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>数据库备份文件列表</td>
			</tr>
		</table>
	
		<table class="formTableTree formTable">
			<tr style="height: 40px;background-color: #CAEEEE;">
				<td style="text-align: center;width:50px;"><strong>序号</strong></td>
				<td style="text-align: center;width:50px;"><strong>选择</strong></td>
				<td style="text-align: center;"><strong>文件名称</strong></td>
				<td style="text-align: center;"><strong>创建时间</strong></td>
			</tr>
				
			<tbody id="privBody">
				<c:forEach items="${fileList}" var="m" varStatus="status">
					<tr>					
						<td style="text-align: center;">${status.index+1}</td>
						<td style="text-align: center;"><input type="radio" name="selFile" value="${m.name}"/></td>
						<td style="text-align: center;">${m.name}</td>
						<td style="text-align: center;">${m.createTime}</td>						
					</tr>
				</c:forEach>
			</tbody>
			
			<c:if test="${fn:length(fileList) == 0}">
				<tr>
					<td style="text-align: center;" colspan="4"><font color="red">暂时没有数据</font></td>
				</tr>
			</c:if>
		</table>
	
		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="button" value="开始恢复" class="btn_list" onclick="recoverDmp()"/>
					<input type="button" value="关闭" class="btn_list" onclick="parent.closeDmpFileListWin()"/>
				</td>
			</tr>
			<tr>
				<td style="color:red;padding-top: 5px;">
					说明：<br/>
					1.开发人注意，恢复数据功能流程是先重新启动数据库的服务，所有与数据库相关程序都要被停止，然后再做数据导入操作。<br/>
					2.数据在恢复后，还要重新启动系统所在的服务器软件。
				</td>
			</tr>
		</table>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
