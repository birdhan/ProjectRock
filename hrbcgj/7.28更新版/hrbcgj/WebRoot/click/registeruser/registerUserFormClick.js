$().ready(function() {
	$("#registerUserForm").validate({
		rules: {//验证规则
			account:{required:true,maxlength:50,normalText:true},
			pwd:{required:true,maxlength:100,normalText:true},
			username:{required:false,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			account:{required:'帐号不能为空',maxlength:'最大长度不能超过50'},
			pwd:{required:'密码不能为空',maxlength:'最大长度不能超过100'},
			username:{required:'姓名不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});
