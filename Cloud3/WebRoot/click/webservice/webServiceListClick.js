var ctx = getRootPath();
$().ready(function() {
	$("#webServiceForm").validate({
		rules: {//验证规则
			className:{required:false,maxlength:200,normalText:true},
			serviceName:{required:false,maxlength:100,normalText:true},
			status:{required:false,maxlength:2,normalText:true},
			remark:{required:false,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			className:{maxlength:'最大长度不能超过200'},
			serviceName:{maxlength:'最大长度不能超过100'},
			status:{maxlength:'最大长度不能超过2'},
			remark:{maxlength:'最大长度不能超过50'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'WebService接口数据导入', url: ctx+'/webservice/openWebServiceImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'WebService接口数据导出', url: ctx+'/webservice/openWebServiceExport.do?ids='+ids, height: 340, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
