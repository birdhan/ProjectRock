$().ready(function() {
	$("#menuForm").validate({
		rules: {//验证规则
			menuName:{required:true,maxlength:20,normalText:true},
			menuUrl:{required:false,maxlength:500,normalText:true},
			menuType:{required:false,maxlength:2},
			parentId:{required:false,maxlength:50,normalText:true},
			menuSort:{required:true,number:true,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			menuName:{required:'菜单名称不能为空',maxlength:'最大长度不能超过20'},
			menuUrl:{required:'菜单地址不能为空',maxlength:'最大长度不能超过500'},
			menuType:{required:'菜单类型不能为空',maxlength:'最大长度不能超过2'},
			parentId:{required:'父菜单不能为空',maxlength:'最大长度不能超过50'},
			menuSort:{required:'菜单顺序不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});

/**
 * 自动选择
 * @param menuUrl
 */
function autoChooseMenuType(menuUrl) {
	var menuType = getElesByName("menuType");
	if(menuUrl.Trim() != "") {								//只要菜单地址值不为空，那么就自动选择菜单		
		menuType[1].checked = "checked";
	} else {
		menuType[0].checked = "checked";
	}
}