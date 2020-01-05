$().ready(function() {
	$("#scheduleForm").validate({
		rules: {//验证规则
			classType:{required:true,maxlength:200,normalText:true},
			frequency:{required:true,maxlength:50},
			status:{required:true,maxlength:50,normalText:true},
			remark:{required:false,maxlength:250,normalText:true}
		},
		messages:{//验证消息内容
			classType:{required:'执行的类不能为空',maxlength:'最大长度不能超过200'},
			frequency:{required:'执行频率不能为空',maxlength:'最大长度不能超过50'},
			status:{required:'状态不能为空',maxlength:'最大长度不能超过50'},
			remark:{required:'备注不能为空',maxlength:'最大长度不能超过250'}
		}
	})
});

function checkClassIsExist(value) {
	if(value.Trim() != "") {
		getEleById("classTypeDiv").innerHTML = "<font color='red'></font>";
		var id = getEleById("id").value;
		$.ajax({   
			type:"post",     
			url:getRootPath()+"/schedulemanager/checkClassIsExist.do",           
			dataType:"text",             
			data:"checkClass="+value+"&id="+id,        
			success:function(data){
				if(data == "") {									//表示正常情况												
					getEleById("classTypeDiv").innerHTML = "<font color='red'></font>";
				} else {											//表示非正常情况		
					getEleById("classTypeDiv").innerHTML = "<font color='red'>"+data+"</font>";
				}
			}
		});
	}
}
