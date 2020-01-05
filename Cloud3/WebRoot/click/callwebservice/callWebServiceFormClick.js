$().ready(function() {
	$("#callWebServiceForm").validate({
		rules: {//验证规则
			wsdl:{required:true,maxlength:1000,normalText:true},
			callType:{required:true,maxlength:50,normalText:true},
			beanId:{required:true,maxlength:50,normalText:true},
			remark:{required:false,maxlength:200,normalText:true}
		},
		messages:{//验证消息内容
			wsdl:{required:'wsdl地址不能为空',maxlength:'最大长度不能超过1000'},
			callType:{required:'调用类型不能为空',maxlength:'最大长度不能超过50'},
			beanId:{required:'beanId不能为空',maxlength:'最大长度不能超过50'},
			remark:{required:'备注不能为空',maxlength:'最大长度不能超过200'}
		}
	})
});

/**
 * 弹出窗口打开wsdl地址查看接口信息
 * @param wsdl
 */
function openWSDl() {
	var wsdl = getEleById("wsdl").value;
	if(wsdl.Trim() != "" && wsdl.indexOf("?wsdl") != -1) {
		window.open(wsdl);
	} else {
		f_alert("请填写正确的地址","error");
	}	
}

/**
 * 检测wsdl是否重复
 * @param wsdl
 */
function checkWSDL(wsdl) {
	var id = getEleById("id").value;
	getEleById("wsdlDiv").innerHTML = "";
	var flag = filterString(wsdl);
	if(flag) {
		if(wsdl.Trim() != "") {
			$.ajax({   
				type:"post",     
				url:getRootPath()+"/callwebservice/checkWSDL.do",           
				dataType:"text",             
				data:"wsdl="+wsdl+"&id="+id,        
				success:function(data){
					if(data != "0") {									//重复了												
						getEleById("wsdlDiv").innerHTML = "<font color='red'>当前值已经存在了，不可以重复录入</font>";
					} else {											//不重复		
						getEleById("wsdlDiv").innerHTML = "";
					}
				}
			});
		}
	}	
}

/**
 * 检测checkBeanId是否重复
 * @param wsdl
 */
function checkBeanId(beanId) {
	var id = getEleById("id").value;
	getEleById("beanIdDiv").innerHTML = "";
	var flag = filterString(beanId);
	if(flag) {
		if(beanId.Trim() != "") {
			$.ajax({   
				type:"post",     
				url:getRootPath()+"/callwebservice/checkBeanId.do",           
				dataType:"text",             
				data:"beanId="+beanId+"&id="+id,        
				success:function(data){
					if(data != "0") {									//重复了												
						getEleById("beanIdDiv").innerHTML = "<font color='red'>当前值已经存在了，不可以重复录入</font>";
					} else {											//不重复		
						getEleById("beanIdDiv").innerHTML = "";
					}
				}
			});
		}
	}
}

/**
 * 验证表单
 */
function validateForm() {
	
}