$().ready(function() {
	$("#regWebServiceForm").validate({
		rules: {//验证规则
			interfaceNo:{required:true,normalText:true,maxlength:20,normalText:true},
			method:{required:true,normalText:true,maxlength:100,normalText:true},
			className:{required:true,maxlength:200,normalText:true},
			status:{required:true,maxlength:50},
			remark:{required:true,maxlength:200,normalText:true}
		},
		messages:{//验证消息内容
			interfaceNo:{required:'接口编号不能为空',maxlength:'最大长度不能超过20'},
			method:{required:'方法名称不能为空',maxlength:'最大长度不能超过100'},
			className:{required:'类名不能为空',maxlength:'最大长度不能超过200'},
			status:{required:'状态不能为空',maxlength:'最大长度不能超过50'},
			remark:{required:'备注不能为空',maxlength:'最大长度不能超过200'}
		}
	})
});

/**
 * 检查接口编号是否重复
 * @param interfaceNo
 */
function checkRepeatData(interfaceNo) {
	if(interfaceNo.Trim() != "") {
		var id = getEleById("id").value;
		$.ajax({   
			type:"post",     
			url:getRootPath()+"/regwebservice/checkRepeatData.do",           
			dataType:"text",             
			data:"interfaceNo="+interfaceNo+"&id="+id,        
			success:function(data){
				if(data != "0") {									//重复了												
					getEleById("interfaceNoDiv").innerHTML = "当前编号已经存在了，不可以重复录入";
				} else {											//不重复		
					getEleById("interfaceNoDiv").innerHTML = "";
				}
			}
		});
	}
}

/**
 * 检查输入的类是否正确
 * @param className
 */
function checkClassName(className) {
	$.ajax({   
		type:"post",     
		url:getRootPath()+"/regwebservice/checkClassName.do",           
		dataType:"text",             
		data:"className="+className,        
		success:function(data){
			getEleById("classNameDiv").innerHTML = data;
			
			/*var method = getEleById("method").value;
			if(method != "") {
				checkMethod(method);
			}*/
		}
	});
}

/**
 * 检查方法是否存在
 * @param method
 */
function checkMethod(method) {
	var className = getEleById("className").value.Trim();
	if(className != "") {
		$.ajax({   
			type:"post",     
			url:getRootPath()+"/regwebservice/checkMethod.do",           
			dataType:"text",             
			data:"method="+method+"&className="+className,        
			success:function(data){
				getEleById("methodDiv").innerHTML = data;
			}
		});
	}
}

/**
 * 检查表单
 */
function validateForm() {
	var interfaceNoDiv = getEleById("interfaceNoDiv");
	var classNameDiv = getEleById("classNameDiv");
	var methodDiv = getEleById("methodDiv");
	if(interfaceNoDiv.innerHTML != "") {
		return false;
	}
	if(classNameDiv.innerHTML != "") {
		return false;
	}
	if(methodDiv.innerHTML != "") {
		return false;
	}
}