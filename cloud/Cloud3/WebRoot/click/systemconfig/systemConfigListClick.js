var ctx = getRootPath();

$().ready(function() {
	$("#systemConfigForm").validate({
		rules: {//验证规则
			leftName:{required:false,maxlength:5,normalText:true},
			rootDepartName:{required:false,maxlength:50,normalText:true},
			rootDepartNo:{required:false,maxlength:100,normalText:true}
		},
		messages:{//验证消息内容
			leftName:{maxlength:'最大长度不能超过5'},
			rootDepartName:{maxlength:'最大长度不能超过50'},
			rootDepartNo:{maxlength:'最大长度不能超过100'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'系统配置数据导入', url: ctx+'/systemconfig/openSystemConfigImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'系统配置数据导出', url: ctx+'/systemconfig/openSystemConfigExport.do?ids='+ids, height: 400, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
