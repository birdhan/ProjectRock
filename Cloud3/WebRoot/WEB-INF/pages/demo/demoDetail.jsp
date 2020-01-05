<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
  <head>
    <base href="<%=basePath%>">
  </head>  
  
<body>
<div class="tire">
	<div class="right_title"><p>位置：demo详细</p></div>
	<div class="tableContentDiv">
		<div class="formDiv">
	   		<table class="tableHead">
	   			<tr>
	   				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>demo详细</td>
	   			</tr>
	   		</table>
		    <table class="formTable">
		        <tr>
		            <th><font color="red">*</font>名字：</th>
		            <td>
		            	${demo.name}
		            </td>	            
		        </tr>
		        <tr>
		            <th>性别：</th>
		            <td>
		            	<datadict:value2label dictType="radio" moduleName="demo" dictValue="${demo.sex}" property="sex"/>
		            </td>
		        </tr>   
		        
		        <tr>
		            <th>爱好：</th>
		            <td>
		            	<datadict:value2label dictType="checkbox" moduleName="demo" dictValue="${demo.hobby}" property="hobby"/>
		            </td>
		        </tr>  
		             
		        <tr>
		            <th><font color="red">*</font>创建日期：</th>
		            <td>
		            	<fmt:formatDate value="${demo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		            </td>
		        </tr>
		        <tr>
		            <th><font color="red">*</font>年龄：</th>
		            <td>
		                ${demo.age}
		            </td>
		        </tr>
		        <tr>
		            <th><font color="red">*</font>部门：</th>
		            <td>
		            	<datadict:value2label dictType="select" moduleName="demo" dictValue="${demo.depart}" property="depart"/>
		            </td>	            
		        </tr>
		        <tr>
		        	<th><font color="red">*</font>方向：</th>
		        	<td>
		        		<datadict:value2label dictType="select2" moduleName="demo" dictValue="${demo.direction}" property="direction"/>
		        	</td>
		        </tr>
		        <tr>
		            <th>内容：</th>
		            <td>
		            	${demo.contentVal}
		            </td>
		        </tr>
		    </table>
		    <table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
		    	<tr>
		        	<td>
						<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/demo/searchDemo.do')"/> 
		        	</td>	        	
		        </tr>
		    </table>
		</div>
	</div><!--content_end-->
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>