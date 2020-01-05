var ctx = getRootPath();

$().ready(function() {
	$("#roleForm").validate({
		rules: {//验证规则
			name:{required:false,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			name:{maxlength:'最大长度不能超过50'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'角色数据导入', url: ctx+'/rolemanager/openRoleImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
	);
}

/**
 * 关闭导入窗口
 */
function closeImportWin() {
	if(importWin != null) {
		importWin.close();
	}
}

/**
 * 数据导出
 */
var exportWin;
function exportData() {
	var ids = "";
	var objs = document.getElementsByName("ids");
	for(var i=0; i<objs.length; i++) {
		if(objs[i].type.toLowerCase() == "checkbox") {
			if(objs[i].checked == true) {
				ids += objs[i].value+",";
			}
		}
	}
	if(ids.Trim() != "") {
		ids = ids.substring(0,ids.length-1);
	}

	exportWin = $.ligerDialog.open(
		{title:'角色数据导出', url: ctx+'/rolemanager/openRoleExport.do?ids='+ids, height: 300, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
	);
}

/**
 * 关闭导出窗口
 */
function colseExportWin() {
	if(exportWin != null) {
		exportWin.close();
	}
}

/**
 * 显示权限div信息
 * @param roleId
 */
function showPrivDiv(roleId) {
	var div = getEleById(roleId+"Div");
	div.style.display = "block";
	if(div.innerHTML == "" || div.innerText.indexOf("暂时没有分配权限") > 0) {
		$.ajax({   
			type:"post",     
			url:getRootPath()+"/rolemanager/showPrivDiv.do",           
			dataType:"text",             
			data:"&id="+roleId,        
			success:function(data){
				div.innerHTML = data;
			}
		});
	}	
}

/**
 * 隐藏权限div
 * @param roleId
 */
function hiddenPrivDiv(roleId) {
	var div = getEleById(roleId+"Div");
	div.style.display = "none";
}