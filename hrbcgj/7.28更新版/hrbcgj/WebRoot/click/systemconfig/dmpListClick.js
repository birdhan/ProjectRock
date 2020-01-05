/**
 * 恢复数据库
 */
function recoverDmp() {
	var selFile = getElesByName("selFile");
	var fileName = "";
	var flag = 0;
	for(var i=0;i<selFile.length;i++) {
		if(selFile[i].checked == true) {
			fileName = selFile[i].value;
			flag++;
			break;
		}
	}
	if(flag == 0) {
		f_alert("请选择要恢复的文件","error");
	} else {															// 准备恢复数据库		
		$.ligerDialog.confirm("您确定要恢复数据库吗？", function (yes) {			
			if(yes) {
				var diag = $.ligerDialog.info("正在为您恢复<font color='red'>"+fileName+"</font>数据!此操作会长时间没有任何反应，请等待一段时间后，重新启动服务。。。","提示",'success');
				$.ajax({   
					type:"post",     
					url:getRootPath()+"/systemconfig/recoverDmp.do",           
					dataType:"text",             
					data:"fileName="+fileName,        
					success:function(data){
						if(data == "0") {
							diag.close();
							f_alert("数据恢复成功，请您重新启动系统服务!","success");
						}						
					}
				});
			}
		});	
	}	
}