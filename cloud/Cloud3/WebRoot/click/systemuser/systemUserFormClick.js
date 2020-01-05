$().ready(function() {
	$("#systemUserForm").validate({
		rules: {//验证规则
			userId:{required:true,maxlength:10,normalText:true},
			password:{required:true,maxlength:50,normalText:true},
			userName:{required:true,maxlength:50,normalText:true},
			departNo:{required:true,maxlength:20,normalText:true}
		},
		messages:{//验证消息内容
			userId:{required:'帐号不能为空',maxlength:'最大长度不能超过10'},
			password:{required:'密码不能为空',maxlength:'最大长度不能超过50'},
			userName:{required:'名字不能为空',maxlength:'最大长度不能超过50'},
			departNo:{required:'部门编号不能为空',maxlength:'最大长度不能超过20'}
		}
	})
});

/**
 * 检查重复的人员id
 * @param userId
 */
function checkRepeatUser(userId) {
	if(userId.Trim() != "") {
		if(filterString(userId)) {
			getEleById("userIdDiv").innerHTML = "";
			var id = getEleById("id").value;
			$.ajax({   
				type:"post",     
				url:getRootPath()+"/systemuser/checkRepeatUser.do",           
				dataType:"text",             
				data:"userId="+userId+"&id="+id,        
				success:function(data){
					if(data != "0") {									//重复了												
						getEleById("userIdDiv").innerHTML = "<font color='red'>当前值已经存在了，不可以重复录入</font>";
					} else {											//不重复		
						getEleById("userIdDiv").innerHTML = "";
					}
				}
			});
		}
	}
}

/**
 * 验证提交表单
 * @returns {Boolean}
 */
function validateFrom() {
	var userIdDiv = getEleById("userIdDiv");
	if(userIdDiv.innerHTML != "") {
		return false;
	}
}