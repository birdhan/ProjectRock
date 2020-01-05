<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="${ctx}/click/demo/demoFormClick.js"></script><!-- 表单事件 -->
  </head>  
  
<body>
<div class="tire">
	<div class="right_title"><p>位置：demo表单</p></div>
	<div class="tableContentDiv">
		<div class="formDiv">
			<form action="${ctx}/demo/saveDemo.do" method="post" name="demoForm" id="demoForm">
			   	<input type="hidden" name="id" value="${demo.id}"/>
		   		<table class="tableHead">
		   			<tr>
		   				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>demo表单</td>
		   			</tr>
		   		</table>
			    <table class="formTable">
			        <tr>
			            <th><font color="red">*</font>名字：</th>
			            <td>
			            	<input name="name" type="text" id="name" value="${demo.name}" autocomplete="off"/>
			            </td>	            
			        </tr>
			        <tr>
			            <th>性别：</th>
			            <td>
			            	<datadict:inputRadio inputType="radio" value="${demo.sex}" property="sex" moduleName="demo"/>
			            </td>
			        </tr>   
			        
			        <tr>
			            <th>爱好：</th>
			            <td>
			            	<datadict:inputCheckbox inputType="checkbox" value="${demo.hobby}" property="hobby" moduleName="demo"/>
			            </td>
			        </tr>  
			             
			        <tr>
			            <th><font color="red">*</font>创建日期：</th>
			            <td>
			            	<cloud:inputDate497 value="${demo.createTime}" property="createTime" format="yyyy-MM-dd HH:mm:ss"/>
			            </td>
			        </tr>
			        <tr>
			            <th><font color="red">*</font>年龄：</th>
			            <td>
			                <input name="age" type="text" id="age" value="${demo.age}" autocomplete="off"/>
			            </td>
			        </tr>
			        <tr>
			            <th><font color="red">*</font>部门：</th>
			            <td>
			            	<datadict:select inputType="select" value="${demo.depart}" property="depart" moduleName="demo"/><!-- multiple="multiple" style="height:50px;" topValue="" topLabel="--请选择--" -->
			            </td>	            
			        </tr>
			        <tr>
			        	<th><font color="red">*</font>方向：</th>
			        	<td>
			        		<datadict:select2 moduleName="demo" inputType="select2" value="${demo.direction}" property="direction"/>
			        	</td>
			        </tr>
			        <tr>
			            <th>内容：</th>
			            <td>
			            	<cloud:htmleditor value="${demo.contentVal}" property="contentVal"/>
			            </td>
			        </tr>
			    </table>
			    <table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			    	<tr>
			        	<td>
							<input type="submit" value="提交" class="btn"/>
							<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/demo/searchDemo.do')"/> 
							<input type="reset" value="重置" class="btn"/> 
			        	</td>	        	
			        </tr>
			    </table>
			</form>
		</div>
	</div><!--content_end-->
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>