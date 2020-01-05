var ctx = getRootPath();

$().ready(function() {
	$("#sqllogForm").validate({
		rules: {//验证规则
			fbegintime:{required:false,maxlength:50},
			fendtime:{required:false,maxlength:50},
			fsql:{required:false,maxlength:2000,normalText:true},
			fsqltype:{required:false,maxlength:50,normalText:true},
			fparameters:{required:false,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			fbegintime:{maxlength:'最大长度不能超过50'},
			fendtime:{maxlength:'最大长度不能超过50'},
			fsql:{maxlength:'最大长度不能超过2000'},
			fsqltype:{maxlength:'最大长度不能超过50'},
			fparameters:{maxlength:'最大长度不能超过50'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'日志管理数据导入', url: ctx+'/sqllog/openSqllogImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'日志管理数据导出', url: ctx+'/sqllog/openSqllogExport.do?ids='+ids, height: 400, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
 * 清理日志数据
 * @param url
 */
function clearSqllog(url) {
	$.ligerDialog.confirm("您确定要清理日志数据吗？", function (yes) {
		if(yes) {
			window.location.href = url;
		}
	});
}
