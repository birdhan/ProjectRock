var ctx = getRootPath();
$().ready(function() {
	$("#scheduleForm").validate({
		rules: {//验证规则
			classType:{required:false,maxlength:200,normalText:true},
			frequency:{required:false,maxlength:50,normalText:true},
			status:{required:false,maxlength:50,normalText:true},
			remark:{required:false,maxlength:250,normalText:true}
		},
		messages:{//验证消息内容
			classType:{maxlength:'最大长度不能超过200'},
			frequency:{maxlength:'最大长度不能超过50'},
			status:{maxlength:'最大长度不能超过50'},
			remark:{maxlength:'最大长度不能超过250'}
		}
	})
});

/**
 * 数据导入
 */
var importWin;
function importData() {
	importWin = $.ligerDialog.open(
		{title:'定时任务数据导入', url: ctx+'/schedulemanager/openScheduleImport.do', height: 265, width: 370, name:'importIframe',isResize: true}
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
		{title:'定时任务数据导出', url: ctx+'/schedulemanager/openScheduleExport.do?ids='+ids, height: 340, width: 470, name:'exportIframe',isResize: true,id:'exportWinDialog'}
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
 * 立即执行
 * @param url
 */
function executeNow(url) {
	var diag = $.ligerDialog.info("正在执行任务!<br/>请稍后。。。","提示",'success');
	$.ajax({   
		type:"post",     
		url:url,           
		dataType:"text",             
		data:"",        
		success:function(data){
			diag.close();
			if(data == "执行成功") {
				f_tip("<font color='#006633'><strong>"+data+"</strong></font>");
			} else {
				f_tip("<font color='red'><strong>"+data+"</strong></font>");
			}
		}
	});
}
