$().ready(function() {
	$("#topicForm").validate({
		rules: {//验证规则
			title:{required:true,maxlength:50,normalText:true},
			contentvalue:{required:true,maxlength:50,normalText:true},
			createtime:{required:true,maxlength:50,normalText:true},
			isshow:{required:true,normalText:true,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			title:{required:'话题标题不能为空',maxlength:'最大长度不能超过50'},
			contentvalue:{required:'话题内容不能为空',maxlength:'最大长度不能超过50'},
			createtime:{required:'创建时间不能为空',maxlength:'最大长度不能超过50'},
			isshow:{required:'是否公开不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});
