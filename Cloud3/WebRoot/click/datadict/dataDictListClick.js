var ctx = getRootPath();

$().ready(function() {
	$("#dataDictForm").validate({
		rules: {//验证规则
			dictValue:{required:false,maxlength:100,normalText:true},
			dictLabel:{required:false,maxlength:200,normalText:true},
			dictType:{required:false,maxlength:50,normalText:true},
			moduleName:{required:false,maxlength:50,normalText:true},
			property:{required:false,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			dictValue:{maxlength:'最大长度不能超过100'},
			dictLabel:{maxlength:'最大长度不能超过200'},
			dictType:{maxlength:'最大长度不能超过50'},
			moduleName:{maxlength:'最大长度不能超过50'},
			property:{maxlength:'最大长度不能超过50'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'数据字典数据导入', url: ctx+'/datadict/openDataDictImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'数据字典数据导出', url: ctx+'/datadict/openDataDictExport.do?ids='+ids, height: 340, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
