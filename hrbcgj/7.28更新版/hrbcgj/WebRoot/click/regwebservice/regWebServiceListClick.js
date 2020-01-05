var ctx = getRootPath();

$().ready(function() {
	$("#regWebServiceForm").validate({
		rules: {//验证规则
			interfaceNo:{required:false,normalText:true,maxlength:20,normalText:true},
			method:{required:false,normalText:true,maxlength:100,normalText:true},
			className:{required:false,maxlength:200,normalText:true},
			status:{required:false,maxlength:50},
			remark:{required:false,maxlength:200,normalText:true}
		},
		messages:{//验证消息内容
			interfaceNo:{maxlength:'最大长度不能超过20'},
			method:{maxlength:'最大长度不能超过100'},
			className:{maxlength:'最大长度不能超过200'},
			status:{maxlength:'最大长度不能超过50'},
			remark:{maxlength:'最大长度不能超过200'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'注册WebService数据导入', url: ctx+'/regwebservice/openRegWebServiceImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'注册WebService数据导出', url: ctx+'/regwebservice/openRegWebServiceExport.do?ids='+ids, height: 340, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
