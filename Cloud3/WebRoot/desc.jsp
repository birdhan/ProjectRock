<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="./commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
	</head>

<body>
<div class="right_title"><p>位置：平台系统描述</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<table class="tableHead">
		<tr>
			<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>平台系统描述</td>
		</tr>
	</table>

	<table cellpadding="0" cellspacing="0" border="0" class="formTable">
		<tr>
			<th>系统版本：</th>
			<td align="left" class="formTable_td">
				V 3.1
			</td>
		</tr>
		<tr>
			<th>数据库：</th>
			<td>
				Mysql
			</td>
		</tr>
		<tr>
			<th>运行环境：</th>
			<td align="left" class="formTable_td">
				JDK 1.7 (及1.6以上版本)<br/>
				Tomcat7 (及6以上版本)
			</td>
		</tr>
		<tr>
			<th>技术简介：</th>
			<td align="left" class="formTable_td">
				1.前端使用自定义UI样式以及ligerUI框架，Jquery表单验证，数据列表展现使用了display标签。<br/>
				2.后台使用SpringMVC、Struts2、Mybatis、JdbcTemplate、Hibernate、Jdbmonitor、CXF、AXIS。<br/>
			</td>
		</tr>
		<tr>
			<th>功能描述：</th>
			<td align="left" class="formTable_td">
				1.基本的权限菜单管理<br/>
				2.简洁的页面视图查看<br/>
				3.方便的人员部门控制<br/>
				4.清晰的业务操作统计<br/>
				5.集成的发送邮箱接口<br/>
				6.动态的接口发布实现<br/>
				7.快捷的代码生成部署<br/>
				8.自动的数据整理备份<br/>
				9.统一对外的接口服务
			</td>
		</tr>
	</table>
</div>
</div>
</div>
</body>
<%@ include file="./commonjsp/common-bottom.jsp" %>
