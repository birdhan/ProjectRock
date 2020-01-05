$().ready(function() {
	$("#departmentForm").validate({
		rules: {//验证规则
			departName:{required:true,maxlength:50,normalText:true},
			departNo:{required:true,maxlength:50,normalText:true},
			parentNo:{required:true,maxlength:50,normalText:true},
			departSort:{required:true,number:true,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			departName:{required:'部门名称不能为空',maxlength:'最大长度不能超过50'},
			departNo:{required:'部门编号不能为空',maxlength:'最大长度不能超过50'},
			parentNo:{required:'上级部门不能为空',maxlength:'最大长度不能超过50'},
			departSort:{required:'部门顺序不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});

/**
 * 检查重复部门编号
 * @param departNo
 */
function checkRepeatDepartNo(departNo) {
	var id = getEleById("id").value;
	getEleById("departNoDiv").innerHTML = "";
	var flag = filterString(departNo);
	if(flag) {
		if(departNo.Trim() != "") {
			$.ajax({   
				type:"post",     
				url:getRootPath()+"/department/checkRepeatDepartNo.do",           
				dataType:"text",             
				data:"departNo="+departNo+"&id="+id,        
				success:function(data){
					if(data != "0") {									//重复了												
						getEleById("departNoDiv").innerHTML = "<font color='red'>当前值已经存在了，不可以重复录入</font>";
					} else {											//不重复		
						getEleById("departNoDiv").innerHTML = "";
					}
				}
			});
		}
	}
}

/**
 * 检查输入的是否为正确的部门编号
 * @param departNo
 */
function checkRightDepartNo(departNo) {
	var flag = filterString(departNo);
	if(flag) {
		if(departNo.Trim() != "") {
			getEleById("parentNoDiv").innerHTML = "";
			getEleById("parentNoSpan").innerHTML = "";
			$.ajax({   
				type:"post",     
				url:getRootPath()+"/department/checkRightDepartNo.do",           
				dataType:"text",             
				data:"departNo="+departNo,        
				success:function(data){
					if(data == "") {									//说明没有												
						getEleById("parentNoDiv").innerHTML = "<font color='red'>不存在此部门</font>";
					} else {											//不重复		
						getEleById("parentNoDiv").innerHTML = "";
						getEleById("parentNoSpan").innerHTML = data;
					}
				}
			});
		}
	}	
}

/**
 * 提交表单时需要验证输入的类是否正确
 */
function validateForm() {
	var classTypeDiv = getEleById("departNoDiv");
	var parentNoDiv = getEleById("parentNoDiv");
	if(classTypeDiv.innerHTML != "" || parentNoDiv.innerHTML != "") {
		return false;
	}
}

/**
 * 自动为其父部门编号赋值
 * @param value
 */
function auto2ParentNo(value) {
	getEleById("parentNo").value = value;
	checkRightDepartNo(value);
}