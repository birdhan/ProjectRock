<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
  <head>
    <base href="<%=basePath%>">
  </head>  
  
<body>
<div class="tire">
	<div class="right_title"><p>位置：生成结果</p></div>
	<div class="tableContentDiv">
		<div class="formDiv">
		    <table class="tableHead">
	   			<tr>
	   				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>生成结果</td>
	   			</tr>
	   		</table>
    
		    <table class="formTable">
			    <tr>
			    	<td>
			    		部署模块说明：
			    	</td>
			    </tr>
			    <tr>
			    	<td>
			    		一、将 <font color='red'>${coder.filePath}</font> 下的com文件夹复制到工程相应的源下，如果是基础功能模块请放到 <font color='red'>baseapp</font> 下，如果是业务功能模块，请放到 <font color='red'>servicesapp</font> 下。
			    	</td>
			    </tr>
			    <tr>
			    	<td>
			    		二、将 <font color='red'>${coder.filePath}</font> 下的WebRoot文件夹，相应放到工程的 <font color='red'>WebRoot</font> 文件夹下。
			    	</td>
			    </tr>
			    <tr>
			    	<td>
			    		三、(建议)开发人员最好是先执行创建数据表的脚本，该工程是hibernate自动创建表结构以及索引的。但是还是建议手动执行数据脚本，脚本所在路径：该代码模块下的sql包下。
			    	</td>
			    </tr>
			    <tr>
			    	<td>
			    		四、加载配置文件<br/><br/><%--
			    		1.加载Struts2文件，将以下配置放入config源下的struts.xml文件中</br>
			    		<textarea rows="2" cols="10" style="width: 500px; height: 50px;">
<!-- ${coder.modelDesc} -->
<include file="/${fn:replace(coder.packageName,".","/")}/config/struts-${flPojo}.xml"/>
	    				</textarea>
	    				<br/><br/>	 --%>   				
			    		加载mybatis文件，虽然系统数据层使用的是hibernate，但是已经将mybatis和jdbctemplate都封装在内<br/>
			    		<textarea rows="2" cols="10" style="width: 500px; height: 50px;">
<!-- ${coder.modelDesc} -->
<typeAlias alias="${coder.modelName}" type="${coder.packageName}.model.${coder.modelName}"/>
			    		</textarea>
			    		<br/>
			    		将此配置放在mappers标签下<br/>
			    		<textarea rows="2" cols="10" style="width: 500px; height: 50px;">
<!-- ${coder.modelDesc} -->
<%-- <mapper resource="/${fn:replace(coder.packageName,".","/")}/config/${coder.modelName}_sqlmap.xml"/> --%>
	    				</textarea>
			    	</td>
			    </tr>
				<tr>
			    	<td>
			    		五、菜单url数据列表地址<br/>
			    		<font color='red'>/${coder.nameSpace}/search${coder.modelName}.do</font>
			    	</td>
			    </tr>
			    <tr>
			    	<td>
			    		共五步
			    	</td>
			    </tr>	    
			</table>
		</div>
	</div>
	</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>