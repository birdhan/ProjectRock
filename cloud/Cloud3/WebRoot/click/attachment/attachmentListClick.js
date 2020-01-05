var ctx = getRootPath();

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'附件表数据导入', url: ctx+'/attachment/openAttachmentImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'附件表数据导出', url: ctx+'/attachment/openAttachmentExport.do?ids='+ids, height: 340, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
*批量删除记录并删除文件
**/
function deleteAttachment(){
	var params = [];
	var boxArr = document.getElementsByName("ids");
	
	for(var i=0;i<boxArr.length;i++){
		if(boxArr[i].checked == true){
			var boxArrt = document.getElementsByName("ids4file");
			var value = boxArrt[i].value.split(",");
			var tempModel = {};											//临时model对象
			tempModel.id = value[0];
			tempModel.filePath = value[1];
			params.push(tempModel);
		}else{
			continue;
		}
	}
	if(params.length == 0){
		f_alert("请选择要删除的数据","error");
		return ;
	}
	params = JSON.stringify(params);
	$.ligerDialog.confirm("您确定要删除选择的数据吗？", function (yes) {
		if(yes) {
			$.ajax({
				type : 'post',
				url : 'DeleteAttachmentServlet',
				data : {
					param : params
				},
				success : function() {
					window.location.reload();
				},
				error : function() {
					
				}
			})	
		}
	});
	
}
/**
*单个删除记录并删除文件
**/
function deleteOneAttachment(id){
	var value = document.getElementById(id).value.split(",");	
	var params = [];
	var tempModel = {};											//临时model对象
	tempModel.id = value[0];
	tempModel.filePath = value[1];
	params.push(tempModel);										//将model存放于数组
	params = JSON.stringify(params);
	
	$.ligerDialog.confirm("您确定要删除选择的数据吗？", function (yes) {
		if(yes) {
			$.ajax({
				type : 'post',
				url : 'DeleteAttachmentServlet',
				data : {
					param : params
				},
				success : function() {
					window.location.reload();
				},
				error : function() {
					
				}
			})	
		}
	});
}