var ctx = getRootPath();

$().ready(function() {
	$("#uploadPicForm").validate({
		rules: {//验证规则
			oriName:{required:false,maxlength:300,normalText:true},
			serName:{required:false,maxlength:1000,normalText:true},
			savePath:{required:false,maxlength:1000,normalText:true},
			uploadTime:{required:false,maxlength:50,normalText:true},
			uploadUserId:{required:false,maxlength:100,normalText:true}
		},
		messages:{//验证消息内容
			oriName:{maxlength:'最大长度不能超过300'},
			serName:{maxlength:'最大长度不能超过1000'},
			savePath:{maxlength:'最大长度不能超过1000'},
			uploadTime:{maxlength:'最大长度不能超过50'},
			uploadUserId:{maxlength:'最大长度不能超过100'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'图片管理数据导入', url: ctx+'/uploadpic/openUploadPicImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'图片管理数据导出', url: ctx+'/uploadpic/openUploadPicExport.do?ids='+ids, height: 340, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
