var ctx = getRootPath();

$().ready(function() {
	$("#systemUserForm").validate({
		rules: {//验证规则
			userId:{required:false,maxlength:10,normalText:true},
			password:{required:false,maxlength:50,normalText:true},
			userName:{required:false,maxlength:50,normalText:true},
			departNo:{required:false,maxlength:20,normalText:true}
		},
		messages:{//验证消息内容
			userId:{maxlength:'最大长度不能超过10'},
			password:{maxlength:'最大长度不能超过50'},
			userName:{maxlength:'最大长度不能超过50'},
			departNo:{maxlength:'最大长度不能超过20'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'系统人员数据导入', url: ctx+'/systemuser/openSystemUserImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'系统人员数据导出', url: ctx+'/systemuser/openSystemUserExport.do?ids='+ids, height: 340, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
