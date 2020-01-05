<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common-top.jsp" %>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript">
        var manager = null;
        $(function () {
        	var radomParam = Math.random();
            $("#detpTree").ligerTree({ url: '${ctx}/department/showDeptTree.do?radomParam='+radomParam+'&deptNo=${deptNo}',checkbox:${checkbox},autoCheckboxEven: ${autoCheckboxEven}, ajaxType: 'post',single: ${single},isExpand:${isExpand},onCheck: function () { getChecked()}});
            manager = $("#detpTree").ligerGetTreeManager();            
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
        	getEleById("selectedDeptDiv").innerHTML = "";
        	
            var notes = manager.getChecked();
            var deptNames = "";								//部门名称
            var deptNos = "";								//部门编号
            for (var i = 0; i < notes.length; i++) {
            	deptNos += notes[i].data.deptNo + ",";
            	deptNames += notes[i].data.text + ",";
            }
            
            if(deptNames != "") {							//去掉最后一个逗号
            	deptNames = deptNames.substring(0,deptNames.length-1);
            	deptNos = deptNos.substring(0,deptNos.length-1);
            }
            
            getEleById("selDeptNo").value = deptNos;		//赋值隐藏域
            getEleById("selDeptName").value = deptNames;	//赋值隐藏域
            
            if(deptNames != "") {							//如果部门名字不为空，那么就拼图片+部门名称显示在右侧部分
            	var departNoDivHTML = showRightDepart(deptNames);
                getEleById("selectedDeptDiv").innerHTML = departNoDivHTML;	
            }        	
        }
        
        /**
         * 确认按钮事件
         */
        function confirmSelected() {        	
        	var selDeptName = getEleById("selDeptName").value;
        	if(selDeptName != "") {
        		var departNoDivHTML = showRightDepart(selDeptName);
            	parent.document.getElementById("${hiddenId}Div").innerHTML = departNoDivHTML;					//父窗口的里的部门显示值div
        	} else {
        		parent.document.getElementById("${hiddenId}Div").innerHTML = "请选择部门";       		
        	}   
        	parent.document.getElementById("${hiddenId}").value = getEleById("selDeptNo").value;
        	parent.closeDeptTreeWin();
        }
        
        /**
         * 拼写图片和部门名字的方法
         */
        function showRightDepart(selDeptName) {
        	var selDeptNameArr = selDeptName.split(",");
        	var departNoDivHTML = "";
        	for(var i=0;i<selDeptNameArr.length;i++) {
        		departNoDivHTML += "<img src='"+getRootPath()+"/images/dept.png' style='margin-right:2px;'/>&nbsp;"+selDeptNameArr[i]+"<br/>";
        	}        	
        	return departNoDivHTML;
        }
    </script>
</head>
<body style="padding:10px">
	<input type="hidden" id="selDeptNo" value="${deptNo}"/> 
	<input type="hidden" id="selDeptName" value="${deptName}"/>
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
				所选部门：
			</td>		
		</tr>
		
		<tr>
			<td width="240">
			    <div style="width:100%; height:360px; margin-bottom:3px; border:1px solid #ccc;overflow:hidden; clear:both;">
			    	<ul id="detpTree"></ul>
			    </div>
			</td>
			
			<td width="5">
				&nbsp;
			</td>
			
			<td style="vertical-align: top;">					
				<div id="selectedDeptDiv" style="height: 350px;max-height: 350px;overflow: auto;border:1px solid #ccc;padding: 5px;">${deptName}</div>		
			</td>
		</tr>
		
		<tr>
			<td>
				<input type="button" class="btn_list" value="确认" onclick="confirmSelected()"/>
				<input type="button" class="btn_list" value="关闭" onclick="parent.closeDeptTreeWin()"/>
			</td>
		</tr>
	</table>
</body>

<script language="javascript">
	var initDeptName = '${deptName}';
	if(initDeptName.Trim() != "") {
		var departNoDivHTML = showRightDepart(initDeptName);
		getEleById("selectedDeptDiv").innerHTML = departNoDivHTML;
	}
</script>
<%@ include file="common-bottom.jsp" %>