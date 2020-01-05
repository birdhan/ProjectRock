var ctx = getRootPath();

$().ready(function() {
	$("#roleMenuPrivForm").validate({
		rules: {//验证规则
			linkRoleId:{required:false,maxlength:36,normalText:true},
			linkMenuId:{required:false,maxlength:36,normalText:true},
			privNo:{required:false,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			linkRoleId:{maxlength:'最大长度不能超过36'},
			linkMenuId:{maxlength:'最大长度不能超过36'},
			privNo:{maxlength:'最大长度不能超过50'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'角色菜单权限数据导入', url: ctx+'/rolemenupriv/openRoleMenuPrivImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'角色菜单权限数据导出', url: ctx+'/rolemenupriv/openRoleMenuPrivExport.do?ids='+ids, height: 400, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
