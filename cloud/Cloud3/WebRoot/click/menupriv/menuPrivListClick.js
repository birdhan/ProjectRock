var ctx = getRootPath();

$().ready(function() {
	$("#menuPrivForm").validate({
		rules: {//验证规则
			privNo:{required:false,number:true,maxlength:10,normalText:true},
			privName:{required:false,maxlength:50,normalText:true},
			linkMenuId:{required:false,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			privNo:{maxlength:'最大长度不能超过10'},
			privName:{maxlength:'最大长度不能超过50'},
			linkMenuId:{maxlength:'最大长度不能超过50'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'菜单权限数据导入', url: ctx+'/menupriv/openMenuPrivImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'菜单权限数据导出', url: ctx+'/menupriv/openMenuPrivExport.do?ids='+ids, height: 340, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
