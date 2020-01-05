$().ready(function() {
	$("#requestRecordForm").validate({
		rules: {//验证规则
			useraccount:{required:true,maxlength:50,normalText:true},
			usertype:{required:true,maxlength:50,normalText:true},
			formip:{required:true,maxlength:100,normalText:true},
			requesturi:{required:false,maxlength:1000,normalText:true},
			reqtime:{required:false,maxlength:50}
		},
		messages:{//验证消息内容
			useraccount:{required:'操作帐号不能为空',maxlength:'最大长度不能超过50'},
			usertype:{required:'用户类型不能为空',maxlength:'最大长度不能超过50'},
			formip:{required:'访问IP不能为空',maxlength:'最大长度不能超过100'},
			requesturi:{required:'请求地址不能为空',maxlength:'最大长度不能超过1000'},
			reqtime:{required:'请求时间不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});
