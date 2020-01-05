$().ready(function() {
	$("#sectionForm").validate({
		rules: {//验证规则
			name:{required:true,normalText:true,maxlength:50,normalText:true},
			postion:{required:true,normalText:true,maxlength:50},
			isshow:{required:true,normalText:true,maxlength:50},
			sortnum:{required:true,number:true,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			name:{required:'栏目名称不能为空',maxlength:'最大长度不能超过50'},
			postion:{required:'位置不能为空',maxlength:'最大长度不能超过50'},
			isshow:{required:'是否显示不能为空',maxlength:'最大长度不能超过50'},
			sortnum:{required:'排序顺序不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});
