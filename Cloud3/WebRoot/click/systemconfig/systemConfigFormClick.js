$().ready(function() {
	$("#systemConfigForm").validate({
		rules: {//验证规则
			leftName:{required:true,maxlength:5,normalText:true},
			rootDepartName:{required:true,maxlength:50,normalText:true},
			rootDepartNo:{required:true,maxlength:100,normalText:true},
			delLogDay:{required:true,isInt:true},
			saveDmpFileDay:{required:true,isInt:true},
			saveLogFileDay:{required:true,isInt:true},
			superAdmin:{required:true,normalText:true},
			superAdminPwd:{required:true,normalText:true},
			picSavePath:{required:true},
			sysDescName:{required:true}
		},
		messages:{//验证消息内容
			leftName:{required:'左侧菜单名称不能为空',maxlength:'最大长度不能超过5'},
			rootDepartName:{required:'根部门名称不能为空',maxlength:'最大长度不能超过50'},
			rootDepartNo:{required:'根部门编号不能为空',maxlength:'最大长度不能超过100'},
			picSavePath:{required:'图片保存路径不能为空'},
			sysDescName:{required:'系统描述名称不能为空'}
		}
	})
});

/**
 * 重新创建bat文件
 */
function rebuildBatFile() {
	var dmpSavePath = getEleById("dmpSavePath").value;
	$.ajax({   
		type:"post",     
		url:getRootPath()+"/systemconfig/rebuildBatFile.do",           
		dataType:"text",             
		data:"",        
		success:function(data){
			if(data == "0") {
				f_tip("<font color='#006633'><strong>重建成功</strong></font>");
			}
		}
	});	
}

/**
 * 操作备份或清理数据库dmp文件
 * @param type
 */
function operDmpFile(type) {
	var desc = "";
	if(type == "bakeup") {
		desc = "数据库备份!";
	} else {
		desc = "数据库备份文件清理!";
	}
	var diag = $.ligerDialog.info("正在操作"+desc+"<br/>请您耐心等待。。。","提示",'success');
	$.ajax({   
		type:"post",     
		url:getRootPath()+"/systemconfig/operDmpFile.do",           
		dataType:"text",             
		data:"type="+type,        
		success:function(data){
			if(data == "0") {
				diag.close();
				f_tip("<font color='#006633'><strong>操作成功</strong></font>");
			}
		}
	});
}

/**
 * 得到所有数据库备份的文件列表
 */
var dmpFileListWin;
function dmpFileList() {
	dmpFileListWin = $.ligerDialog.open(
		{title:"恢复数据", url: getRootPath()+'/systemconfig/dmpFileList.do', height: document.documentElement.clientHeight, width: document.documentElement.clientWidth, name:'dmpFileListWinIframe',isResize: false,isDrag:false,id:'dmpFileListWinDialog'}
	);
}

/**
 * 关闭数据库恢复窗口事件
 */
function closeDmpFileListWin() {
	dmpFileListWin.close();
}