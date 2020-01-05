<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common-top.jsp" %>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript">
        var manager = null;
        $(function () {
        	var radomParam = Math.random();
            $("#userTree").ligerTree({ url: '${ctx}/systemuser/showUserTree.do?radomParam='+radomParam+'&userId=${userId}',checkbox:${checkbox},autoCheckboxEven: ${autoCheckboxEven}, ajaxType: 'post',single: ${single},isExpand:${isExpand},onCheck: function () { getChecked()}});
            manager = $("#userTree").ligerGetTreeManager();            
        }); 
        
        /**
         * 关闭折叠
         */
        function collapseAll() {
            manager.collapseAll();
        }
        
        /**
         * 展开所有
         */
        function expandAll() {
            manager.expandAll();
        }
        
        /**
         * 选中checkbox事件
         */
        function getChecked() {        	
        	getEleById("selectedUserDiv").innerHTML = "";
        	
            var notes = manager.getChecked();
            var userNames = "";								//人员名称
            var userNos = "";								//人员编号
            for (var i = 0; i < notes.length; i++) {
            	userNos += notes[i].data.deptNo + ",";
            	userNames += notes[i].data.text + ",";
            }
            
            if(userNames != "") {							//去掉最后一个逗号
            	userNames = userNames.substring(0,userNames.length-1);
            	userNos = userNos.substring(0,userNos.length-1);
            }
            
            getEleById("selUserId").value = userNos;		//赋值隐藏域
            getEleById("selUserName").value = userNames;	//赋值隐藏域
            
            if(userNames != "") {							//如果人员名字不为空，那么就拼图片+人员名称显示在右侧部分
            	var userIdDivHTML = showRightDepart(userNames);
                getEleById("selectedUserDiv").innerHTML = userIdDivHTML;	
            }        	
        }
        
        /**
         * 确认按钮事件
         */
        function confirmSelected() {        	
        	var selUserName = getEleById("selUserName").value;
        	if(selUserName != "") {
        		var userIdDivHTML = showRightDepart(selUserName);
            	parent.document.getElementById("${hiddenId}Div").innerHTML = userIdDivHTML;					//父窗口的里的人员显示值div
        	} else {
        		parent.document.getElementById("${hiddenId}Div").innerHTML = "请选择人员";       		
        	}   
        	parent.document.getElementById("${hiddenId}").value = getEleById("selUserId").value;
        	parent.closeUserTreeWin();
        }
        
        /**
         * 拼写图片和人员名字的方法
         */
        function showRightDepart(selUserName) {
        	var selUserNameArr = selUserName.split(",");
        	var userIdDivHTML = "";
        	for(var i=0;i<selUserNameArr.length;i++) {
        		userIdDivHTML += "<img src='"+getRootPath()+"/images/user.gif' style='margin-right:2px;'/>&nbsp;"+selUserNameArr[i]+"<br/>";
        	}        	
        	return userIdDivHTML;
        }
        
        /*******************************以下函数为自定义函数在开发中可以随意定义，上面函数是基本函数不要轻易修改*******************************/
        
        /**
         * 此函数为自定义
         * 关闭人员树窗口事件并保存角色与人员关系
         * 步骤：
         * 1.先判断当前角色是否已经保存入库，如果没有入库那么首先要保存当前角色记录获取当然角色id值
         * 2.然后将选择后的人员userId帐号值传给后台保存关系
         */
        function closeUserTreeSaveRoleRelation() {	
        	var selUserName = getEleById("selUserName").value;
        	var roleName = parent.document.getElementById("name").value;
        	if(roleName.Trim() == "") {
        		alert("请关闭窗口先填写角色名称后再对人员进行选择");
        	} else {
        		if(selUserName != "") {
            		var userIdDivHTML = showRightDepart(selUserName);        		
                	parent.document.getElementById("${hiddenId}Div").innerHTML = userIdDivHTML;					//父窗口的里的人员显示值div
            	} else {																						//如果没有选择人
            		parent.document.getElementById("${hiddenId}Div").innerHTML = "请选择人员";       		
            	}   
            	parent.document.getElementById("${hiddenId}").value = getEleById("selUserId").value;
            	var id = parent.document.getElementById("id").value;											//角色id值
            	if(id == "") {																					//如果角色的id值为空，那么就先保存当前记录
            		saveRoleAjax();																				//先保存角色记录        		
            	} else {																						//如果id存在，那么就不用保存角色记录了
            		saveRoleUserId();																			//保存角色人员关系
            	}
        	}
        }
        
        /**
         * 异步保存角色数据
         */
        function saveRoleAjax() {
        	var roleName = parent.document.getElementById("name").value;
        	var id = parent.document.getElementById("id").value;												//角色id值
        	if(roleName.Trim() != "") {
        		$.ajax({   
        			type:"post",     
        			url:getRootPath()+"/rolemanager/saveRoleAjax.do",           
        			dataType:"text",             
        			data:"roleName="+roleName+"&id="+id,        
        			success:function(data){
        				if(data != "") {																		//表示成功        					
        					parent.document.getElementById("id").value = data;
        					saveRoleUserId();																	//保存角色人员关系
        				}
        			}
        		});	
        	} else {
        		alert("请关闭窗口先填写角色名称后再对人员进行选择");
        	}        	
        }
        
        /**
         * 保存角色人员userId帐号关系
         */
        function saveRoleUserId() {
        	var roleId = parent.document.getElementById("id").value;											//角色id
        	var userId = getEleById("selUserId").value;
        	$.ajax({   
    			type:"post",     
    			url:getRootPath()+"/roleuser/saveRoleUserId.do",           
    			dataType:"text",             
    			data:"roleId="+roleId+"&userId="+userId,        
    			success:function(data){
    				if(data == "true") {																		//表示正确
    					parent.closeUserTreeWin();																//关闭窗口
    				} 
    			}
    		});	
        }
    </script>
</head>
<body style="padding:10px">
	<input type="hidden" id="selUserId" value="${userId}"/> 
	<input type="hidden" id="selUserName" value="${userName}"/>
	<table border="0" width="100%">
		<tr>
			<td>
				<a href="javascript:expandAll()">展开</a>
				<a href="javascript:collapseAll()">收回</a>
			</td>
			<td width="5">
				&nbsp;
			</td>	
			<td>
				所选人员：
			</td>		
		</tr>
		
		<tr>
			<td width="240">
			    <div style="width:100%; height:360px; margin-bottom:3px; border:1px solid #ccc;overflow:hidden; clear:both;">
			    	<ul id="userTree"></ul>
			    </div>
			</td>
			
			<td width="5">
				&nbsp;
			</td>
			
			<td style="vertical-align: top;">					
				<div id="selectedUserDiv" style="height: 350px;max-height: 350px;overflow: auto;border:1px solid #ccc;padding: 5px;">${userName}</div>		
			</td>
		</tr>
		
		<tr>
			<td>
				<input type="button" class="btn_list" value="确认" onclick="${confirmBtnClick}"/>
				<input type="button" class="btn_list" value="关闭" onclick="parent.closeUserTreeWin()"/>
			</td>
		</tr>
	</table>
</body>

<script language="javascript">
	var initUserName = '${userName}';
	if(initUserName.Trim() != "") {
		var userIdDivHTML = showRightDepart(initUserName);
		getEleById("selectedUserDiv").innerHTML = userIdDivHTML;
	}
</script>
<%@ include file="common-bottom.jsp" %>