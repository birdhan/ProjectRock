$().ready(function() {
	$("#roleUserForm").validate({
		rules: {//验证规则
			linkRoleId:{required:false,maxlength:36,normalText:true},
			userId:{required:false,maxlength:500,normalText:true}
		},
		messages:{//验证消息内容
			linkRoleId:{required:'角色ID不能为空',maxlength:'最大长度不能超过36'},
			userId:{required:'人员userId不能为空',maxlength:'最大长度不能超过500'}
		}
	})
});
