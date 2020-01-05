$().ready(function() {
	$("#webServiceForm").validate({
		rules: {//验证规则
			className:{required:true,maxlength:200,normalText:true},
			serviceName:{required:true,maxlength:100,normalText:true},
			status:{required:true,maxlength:2,normalText:true},
			remark:{required:false,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			className:{required:'类名不能为空',maxlength:'最大长度不能超过200'},
			serviceName:{required:'服务名不能为空',maxlength:'最大长度不能超过100'},
			status:{required:'状态不能为空',maxlength:'最大长度不能超过2'},
			remark:{required:'备注不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});

/**
 * 检查输入的类是否存在或重复
 * @param value
 */
function checkClassIsExist(value) {
	if(value.Trim() != "") {
		getEleById("classTypeDiv").innerHTML = "";
		var id = getEleById("id").value;
		$.ajax({   
			type:"post",     
			url:getRootPath()+"/webservice/checkClassIsExist.do",           
			dataType:"text",             
			data:"checkClass="+value+"&id="+id,        
			success:function(data){
				if(data == "") {									//表示正常情况												
					getEleById("classTypeDiv").innerHTML = "";
					autoCreateWSDL();
				} else {											//表示非正常情况		
					getEleById("classTypeDiv").innerHTML = "<font color='red'>"+data+"</font>";
				}
			}
		});
	}
}

/**
 * 提交表单时需要验证输入的类是否正确
 */
function validateForm() {
	var classTypeDiv = getEleById("classTypeDiv");
	if(classTypeDiv.innerHTML != "") {
		return false;
	}
}

/**
 * 自动生成WSDL地址
 */
function autoCreateWSDL() {
	var serviceName = getEleById("serviceName").value;				//服务名
	if(serviceName.Trim() != "") {
		$.ajax({   
			type:"post",     
			url:getRootPath()+"/webservice/autoCreateWSDL.do",           
			dataType:"text",             
			data:"serviceName="+serviceName,        
			success:function(data){
				if(data != "") {									//表示正常情况												
					getEleById("wsdlUrl").value = data;
					getEleById("wsdlSpan").innerHTML = data;
				} else {											//表示非正常情况		
					getEleById("wsdlSpan").innerHTML = "自动生成wsdl失败";
				}
			}
		});
	}
}