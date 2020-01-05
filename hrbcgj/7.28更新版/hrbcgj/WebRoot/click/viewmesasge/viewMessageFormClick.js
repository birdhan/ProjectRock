$().ready(function() {
	$("#viewMessageForm").validate({
		rules: {//验证规则
			title:{required:true,maxlength:300,normalText:true},
			reqregisteruser:{required:true,maxlength:200,normalText:true},
			reqcontent:{required:true,maxlength:1000,normalText:true},
			reqtime:{required:false,maxlength:50,normalText:true},
			isshow:{required:true,normalText:true,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			title:{required:'话题标题不能为空',maxlength:'最大长度不能超过300'},
			reqregisteruser:{required:'用户名不能为空',maxlength:'最大长度不能超过200'},
			reqcontent:{required:'留言内容不能为空',maxlength:'最大长度不能超过1000'},
			reqtime:{required:'留言时间不能为空',maxlength:'最大长度不能超过50'},
			isshow:{required:'是否显示不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});
