<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${ctx}/click/vote/voteFormClick.js"></script> <!-- js事件文件 -->
	</head>

<body>
<div class="right_title"><p>位置：投票表表单</p></div>
<div class="tableContentDiv">
<div class="formDiv">
	<form action="${ctx}/vote/saveVote.do" method="post" name="voteForm" id="voteForm">
		<input type="hidden" id="id" name="id" value="${vote.id}"/>
		<table class="tableHead">
			<tr>
				<td><img src="${ctx}/images/formTableTitle.png" class="formTableTitle"/>投票表表单</td>
			</tr>
		</table>

		<table class="formTable" id="formTable">
			<tr>
				<th>标题：</th>
				<td>
					<input name="title" type="text" id="title" value="${vote.title}" autocomplete="off"/>
				</td>
			</tr>
			<tr>
				<th>内容：</th>
				<td>
					<textarea rows="10" cols="10" id="content" name="content">${vote.content}</textarea>
				</td>
			</tr>
			<tr>
				<th>开始时间：</th>
				<td>
					<cloud:inputDate497 value="${vote.startTime}" property="startTime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th>结束时间：</th>
				<td>
					<cloud:inputDate497 value="${vote.endTime}" property="endTime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th>是否完成：</th>
				<td>
					<datadict:inputRadio inputType="radio" value="${vote.if_Finish}" property="if_Finish" moduleName="vote"/>
				</td>
			</tr>
			<tr>
				<th>类型：</th>
				<td>
					<datadict:inputRadio inputType="radio" value="${vote.type}" property="type" moduleName="vote"/>
				</td>
			</tr>
			<c:if test="${vote==null}"><tr id="p">
			   <th>新增选项</th>
				<td><input type="button" id="new" value="新增"> &nbsp; <input type="button" id="del" value="删除"></td>
			</tr></c:if>
		</table>

		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="btnTable">
			<tr>
				<td>
					<input type="button" value="提交" class="btn" id="sum"/>
					<input type="button" value="返回列表" class="btn" onclick="javascript:goToUrl('${ctx}/vote/searchVote.do')"/>
					<input type="reset" value="重置" class="btn"/>
					<input type="hidden" name="xuan" id="xuan" >
				</td>
			</tr>
		</table>
	</form>
	<script>
	 document.getElementById('sum').onclick = function() {
		         var inps = document.getElementsByTagName('input'),
			     len = inps.length,count = 0,arr = [];
			
			    for(var i = 0; i < len; i++){
				    if(inps[i].type == 'text'){
					    arr.push(inps[i]);
				    }	
			    }
			    
			    for(var i = 0; i < arr.length; i++){
				    if(arr[i].value != ''){
					    count++;
				    }else{
					    count--;	
				    }
			    }
			
			    if(count == arr.length){
			    
			      if(document.getElementById('p')!=null)
			      {
			         var index=document.getElementById('formTable');
		             var len=index.rows.length;
		             var input = document.getElementById('xuan');
			         input.value=len-7;
			      }
				 
				  document.getElementById('voteForm').submit();	
			    }else{
				    alert('有空值');	
			    }

			    };
		    document.getElementById('new').onclick = function() {
		        var index=document.getElementsByTagName('input').length;
			    var input = document.createElement('input');
			    input.name="n"+(index-13);
			    var tr = document.createElement('tr');
			    var td = document.createElement('td');
			    var th = document.createElement('th');			   
			    th.innerHTML="选项"+(index-13);
			    td.appendChild(input); 
			    tr.appendChild(th);
			    tr.appendChild(td);
			    tr.value=tr.firstChild.innerHTML;
			    document.getElementById('formTable').appendChild(tr);	
			    };
			    
			    document.getElementById('del').onclick = function() {
		          var index=document.getElementById('formTable');
		          var len=index.rows.length;
                  if(len>7)
                  {
                     index.deleteRow(len-1);
                  }
                  else
                  {
                    alert("没有可删除的选项");
                  }
			      
			    };
			    
		</script>
</div>
</div>
</div>
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>
