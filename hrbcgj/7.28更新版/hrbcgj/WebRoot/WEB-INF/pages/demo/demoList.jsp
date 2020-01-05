<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="${ctx}/click/demo/demoListClick.js"></script><!-- 列表事件 -->
  </head>  
  
<body>
<div class="tire">
	<div class="right_title"><p>位置：demo列表</p></div>
	<div class="formDiv">
		<div class="query">
			<form id="demoForm" name="demoForm" action="${ctx}/demo/searchDemo.do?isSearch=yes" method="post">
				<table class="query_table">
					<tr>
						<td style=" width:40px;"><img src="${ctx}/images/search2.png" width="18" height="18" style="margin-top:3px;margin-left:5px;" /></td>
						<th>姓名：</th>
						<td><input name="name" id="name" type="text" value="${demo.name}" autocomplete="off"/></td>						
					</tr>
					<tr>
						<td style=" width:40px;">&nbsp;</td>
						<th>创建时间：</th>						
						<td><cloud:inputDate497 value="${demo.createTime}" property="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
				</table>
			
				<table class="query_btn_table">
					<tr>
						<td>
							<input type="submit" value="" class="btn_search"/>
							<input type="button"  class="btn_default_search" onclick="javascript:goToUrl('${ctx}/demo/searchDemo.do')"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	
		<div class="listDiv">
			<display:table requestURI="${ctx}/demo/searchDemo.do" id="demoList"
				name="list" pagesize="${pageSize}" partialList="true" size="${resultSize}" cellspacing="0" class="listtable">
				
				<display:column title="<input type='checkbox' onclick='javascript:choose();'>" style="text-align:center; width:20px;border-left:none;">
					<input id="ids" name="ids" type="checkbox" value="${demoList.id}"/>
				</display:column>
				
				<display:column title="序号" style="width:30px;text-align:center;">
					${demoList_rowNum}
				</display:column>
				
				<display:column property="name" sortable="true" title="姓名" style="text-align:center;"/>
					
				<display:column sortable="true" title="创建时间" style="text-align:center;">
					<fmt:formatDate value="${demoList.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>
				
				<display:column sortable="true" title="性别" style="text-align:center;">
					<datadict:value2label dictType="radio" moduleName="demo" dictValue="${demoList.sex}" property="sex"/>
				</display:column>
				
				<display:column sortable="true" title="爱好" style="text-align:center;">
					<datadict:value2label dictType="checkbox" moduleName="demo" dictValue="${demoList.hobby}" property="hobby"/>
				</display:column>
				
				<display:column sortable="true" title="方向" style="text-align:center;">
					<datadict:value2label dictType="select2" moduleName="demo" dictValue="${demoList.direction}" property="direction"/>
				</display:column>
				
				<display:column title="下载" style="text-align:center">
					<img src="${ctx}/images/word.jpg" title="下载" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/demo/downLoadDoc.do?id=${demoList.id}')"/>
				</display:column>
					
				<display:column title="编辑" style="text-align:center">
					<img src="${ctx}/images/cog_edit.png" title="编辑" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/demo/editDemo.do?id=${demoList.id}')"/>
				</display:column>
				
				<display:column title="删除" style="text-align:center;">
					<img src="${ctx}/images/delete.png" title="删除" style="cursor: pointer;"
						onclick="delDataById('${ctx}/demo/delDemoById.do?id=${demoList.id}')"/>
				</display:column>
				
				<display:column title="详细" style="text-align:center;">
					<img src="${ctx}/images/detail.png" title="详细" style="cursor: pointer;"
						onclick="goToUrl('${ctx}/demo/detailDemo.do?id=${demoList.id}')"/>
				</display:column>
				
				<display:column title="测试" style="text-align:center">
					<img src="${ctx}/images/search2.png" title="测试" style="cursor: pointer;"
						onclick="testHttpDemo('${demoList.id}')"/>
				</display:column>
			</display:table>
			
			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTableList">
		    	<tr>
		        	<td>
		        		<input type="button" value="新增" class="btn_list" onclick="goToUrl('${ctx}/demo/addDemo.do')"/>
		        		<input type="button" value="删除" class="btn_list" onclick="delMoreByIds('${ctx}/demo/delDemoByIds.do')" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
		        		<input type="button" value="导入数据" class="btn_list" onclick="importData()"/>
		        		<input type="button" value="导出数据" class="btn_list" onclick="exportData()" <c:if test="${fn:length(list)==0}">disabled=disabeld title="没有数据不可操作"</c:if>/>
		        	</td>	        	
		        </tr>
		    </table>
		</div>
	</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>