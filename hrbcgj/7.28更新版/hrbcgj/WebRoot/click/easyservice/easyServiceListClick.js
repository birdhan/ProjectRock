var ctx = getRootPath();

$().ready(function() {
	$("#easyServiceForm").validate({
		rules: {//验证规则
			name:{required:false,normalText:true,maxlength:50,normalText:true},
			logopic:{required:false,maxlength:100,normalText:true},
			linkurl:{required:false,url:true,maxlength:1000,normalText:true}
		},
		messages:{//验证消息内容
			name:{maxlength:'最大长度不能超过50'},
			logopic:{maxlength:'最大长度不能超过100'},
			linkurl:{maxlength:'最大长度不能超过1000'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'便民服务数据导入', url: ctx+'/easyservice/openEasyServiceImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'便民服务数据导出', url: ctx+'/easyservice/openEasyServiceExport.do?ids='+ids, height: 340, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
