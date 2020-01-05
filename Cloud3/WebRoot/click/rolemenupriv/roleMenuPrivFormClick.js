$().ready(function() {
	$("#roleMenuPrivForm").validate({
		rules: {//验证规则
			linkRoleId:{required:false,maxlength:36,normalText:true},
			linkMenuId:{required:false,maxlength:36,normalText:true},
			privNo:{required:false,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			linkRoleId:{required:'关联角色ID不能为空',maxlength:'最大长度不能超过36'},
			linkMenuId:{required:'关联菜单ID不能为空',maxlength:'最大长度不能超过36'},
			privNo:{required:'对应权限编号不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});
