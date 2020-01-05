var ctx = getRootPath();

$().ready(function() {
	$("#appActionForm").validate({
		rules: {//验证规则
			title:{required:false,maxlength:50,normalText:true},
			actice:{required:false,maxlength:500,normalText:true},
			createtime:{required:false,maxlength:50,normalText:true},
			state:{required:false,maxlength:50,normalText:true},
			reply:{required:false,maxlength:200,normalText:true},
			uid:{required:false,maxlength:50}
		},
		messages:{//验证消息内容
			title:{maxlength:'最大长度不能超过50'},
			actice:{maxlength:'最大长度不能超过500'},
			createtime:{maxlength:'最大长度不能超过50'},
			state:{maxlength:'最大长度不能超过50'},
			reply:{maxlength:'最大长度不能超过200'},
			uid:{maxlength:'最大长度不能超过50'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'依申请公开数据导入', url: ctx+'/openinfor/openAppActionImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'依申请公开数据导出', url: ctx+'/openinfor/openAppActionExport.do?ids='+ids, height: 340, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
