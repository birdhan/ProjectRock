var ctx = getRootPath();

$().ready(function() {
	$("#menuForm").validate({
		rules: {//验证规则
			menuName:{required:false,maxlength:20,normalText:true},
			menuUrl:{required:false,maxlength:500,normalText:true},
			menuType:{required:false,maxlength:2},
			parentId:{required:false,maxlength:50,normalText:true},
			menuSort:{required:false,number:true,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			menuName:{maxlength:'最大长度不能超过20'},
			menuUrl:{maxlength:'最大长度不能超过500'},
			menuType:{maxlength:'最大长度不能超过2'},
			parentId:{maxlength:'最大长度不能超过50'},
			menuSort:{maxlength:'最大长度不能超过50'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'菜单数据导入', url: ctx+'/menumanager/openMenuImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'菜单数据导出', url: ctx+'/menumanager/openMenuExport.do?ids='+ids, height: 400, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
 * 弹出窗口预览最新的菜单结构
 */
var viewWin;
function viewMenuTree() {
	viewWin = $.ligerDialog.open(
		{title:'预览菜单树', url: ctx+'/menumanager/viewMenuTree.do', height: 480, width: 225, name:'viewIframe',isResize: true,id:'viewWinDialog'}
	);
}

/**
 * 关闭预览窗口
 */
function closeViewWin() {
	if(viewWin != null) {
		viewWin.close();
	}
}