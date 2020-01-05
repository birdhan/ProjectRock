$().ready(function() {
	$("#onlineServiceForm").validate({
		rules: {//验证规则
			title:{required:true,normalText:true,maxlength:100,normalText:true},
			linkurl:{required:true,maxlength:1000,normalText:true},
			createtime:{required:true,maxlength:50}
		},
		messages:{//验证消息内容
			title:{required:'标题不能为空',maxlength:'最大长度不能超过100'},
			linkurl:{required:'链接地址不能为空',maxlength:'最大长度不能超过1000'},
			createtime:{required:'创建时间不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});
