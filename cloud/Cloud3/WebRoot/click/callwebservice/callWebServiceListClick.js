var ctx = getRootPath();
$().ready(function() {
	$("#callWebServiceForm").validate({
		rules: {//验证规则
			wsdl:{required:false,maxlength:1000,normalText:true},
			callType:{required:false,maxlength:50,normalText:true},
			beanId:{required:false,maxlength:50,normalText:true},
			remark:{required:false,maxlength:200,normalText:true}
		},
		messages:{//验证消息内容
			wsdl:{maxlength:'最大长度不能超过1000'},
			callType:{maxlength:'最大长度不能超过50'},
			beanId:{maxlength:'最大长度不能超过50'},
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
		{title:'调用WebService数据导入', url: ctx+'/callwebservice/openCallWebServiceImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'调用WebService数据导出', url: ctx+'/callwebservice/openCallWebServiceExport.do?ids='+ids, height: 340, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
 * 弹出窗口打开wsdl地址查看接口信息
 * @param wsdl
 */
function openWSDl(wsdl) {
	window.open(wsdl);
}
