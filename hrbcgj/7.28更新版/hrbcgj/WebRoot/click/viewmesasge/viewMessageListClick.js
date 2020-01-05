var ctx = getRootPath();

$().ready(function() {
	$("#viewMessageForm").validate({
		rules: {//验证规则
			title:{required:false,maxlength:300,normalText:true},
			reqregisteruser:{required:false,maxlength:200,normalText:true},
			reqcontent:{required:false,maxlength:1000,normalText:true},
			reqtime:{required:false,maxlength:50,normalText:true},
			isshow:{required:false,normalText:true,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			title:{maxlength:'最大长度不能超过300'},
			reqregisteruser:{maxlength:'最大长度不能超过200'},
			reqcontent:{maxlength:'最大长度不能超过1000'},
			reqtime:{maxlength:'最大长度不能超过50'},
			isshow:{maxlength:'最大长度不能超过50'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'查看留言数据导入', url: ctx+'/viewmesasge/openViewMessageImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'查看留言数据导出', url: ctx+'/viewmesasge/openViewMessageExport.do?ids='+ids, height: 340, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
