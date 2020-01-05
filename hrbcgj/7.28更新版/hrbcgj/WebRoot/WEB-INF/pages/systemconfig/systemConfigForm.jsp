<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/systemconfig/systemConfigFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：参数配置表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/systemconfig/saveSystemConfig.do" method="post" name="systemConfigForm" id="systemConfigForm">
		<input type="hidden" id="id" name="id" value="${systemConfig.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>基本配置</td>
			</tr>
		</table>

		<table class="formTable">
			<tr>
				<th><font color="red">*</font>左侧菜单名称：</th>
				<td>
					<input name="leftName" type="text" id="leftName" value="${systemConfig.leftName}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>根部门名称：</th>
				<td>
					<input name="rootDepartName" type="text" id="rootDepartName" value="${systemConfig.rootDepartName}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>根部门编号：</th>
				<td>
					<input name="rootDepartNo" type="text" id="rootDepartNo" value="${systemConfig.rootDepartNo}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>系统描述名称：</th>
				<td>
					<input name="sysDescName" type="text" id="sysDescName" value="${systemConfig.sysDescName}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>保留日志天数：</th>
				<td>
					<input name="delLogDay" type="text" id="delLogDay" value="${systemConfig.delLogDay}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>log日志文件保留天数：</th>
				<td>
					<input name="saveLogFileDay" type="text" id="saveLogFileDay" value="${systemConfig.saveLogFileDay}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>图片保存路径：</th>
				<td>
					<input name="picSavePath" type="text" id="picSavePath" value="${systemConfig.picSavePath}" autocomplete="off"/>
				</td>
			</tr>
		</table>
		
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>数据备份</td>
			</tr>
		</table>
		
		<table class="formTable">			
			<tr>
				<th><font color="red">*</font>数据库类型：</th>
				<td colspan="3">
					<datadict:inputRadio inputType="radio" value="${datatype}" property="datatype" moduleName="systemconfig"/>
				</td>
			</tr>
			<tr>
				<th colspan="2" style="text-align: center;">Oracle</th>
				<th colspan="2" style="text-align: center;">MySql</th>
			</tr>
			<tr>
				<th><font color="red">*</font>系统管理员帐号：</th>
				<td>
					<input name="superAdmin" type="text" id="superAdmin" value="${superAdmin}" autocomplete="off"/>
				</td>
				
				<th><font color="red">*</font>系统管理员帐号：</th>
				<td>
					<input name="superAdmin4Mysql" type="text" id="superAdmin4Mysql" value="${superAdmin4Mysql}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>系统管理员密码：</th>
				<td>
					<input name="superAdminPwd" type="text" id="superAdminPwd" value="${superAdminPwd}" autocomplete="off"/>
				</td>
				
				<th><font color="red">*</font>系统管理员密码：</th>
				<td>
					<input name="superAdminPwd4Mysql" type="text" id="superAdminPwd4Mysql" value="${superAdminPwd4Mysql}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>重建bat脚本文件：</th>
				<td>
					<input type="button" class="btn_list" value="重新创建脚本文件" onclick="rebuildBatFile()"/>
				</td>
				
				<th>mysqlBin的全路径：</th>
				<td>
					<input name="mysqlBinPath" type="text" id="mysqlBinPath" value="${mysqlBinPath}" autocomplete="off" style="width:250px;"/>
				</td>
			</tr>
			<tr>
				<th colspan="4" style="text-align: center;">公共配置</th>
			</tr>
			<tr>
				<th>备份数据库文件路径：</th>
				<td colspan="3">
					<input name="dmpSavePath" type="text" id="dmpSavePath" value="${dmpSavePath}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>保留数据库备份文件天数：</th>
				<td colspan="3">
					<input name="saveDmpFileDay" type="text" id="saveDmpFileDay" value="${systemConfig.saveDmpFileDay}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>操作：</th>
				<td colspan="3">
					<input type="button" value="备份" class="btn_list" onclick="operDmpFile('bakeup')"/>
					<input type="button" value="恢复" class="btn_list" onclick="dmpFileList()"/>
					<input type="button" value="清理备份文件" class="btn_list" onclick="operDmpFile('clear')"/>					
				</td>
			</tr>			
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="submit" value="提交" class="btn"/>
					<input type="reset" value="重置" class="btn"/>					
				</td>
			</tr>
			<tr>
				<td style="color:#B2B2B2;">
					<br/>
					说明：<br/>
					1.备份数据库dmp文件路径：例子，c:/dbbackup/。只填写文件夹路径即可，不需要填写XXX.bat文件。该项如果不填写，文件会自动保存在工程的sql/backup_dmp/文件夹。<br/>
					2.保留日志天数：填写5,表示只保留近5天的日志数据。<br/>
					3.保留数据库备份文件天数：填写5,表示只保留近5天的文件。<br/>
					4.修改完本页面的数据信息后，请重新启动服务。
				</td>
			</tr>
		</table>
	</form>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
