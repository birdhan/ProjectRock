$().ready(function() {
	$("#specialForm").validate({
		rules: {//验证规则
			weburl:{required:true,url:true,maxlength:1000,normalText:true},
			picurl:{required:false,maxlength:50,normalText:true}
		},
		messages:{//验证消息内容
			weburl:{required:'链接地址不能为空',maxlength:'最大长度不能超过1000'},
			picurl:{required:'logo图片不能为空',maxlength:'最大长度不能超过50'}
		}
	})
});

/*$().ready(function() {
	$("#linkForm").validate({
		rules: {//验证规则
			linkName:{required:true,normalText:true,maxlength:50,normalText:true},
			linkUrl:{required:true,url:true,maxlength:1000,normalText:true},
			logoPic:{required:true,maxlength:100,normalText:true}
		},
		messages:{//验证消息内容
			linkName:{required:'链接名称不能为空',maxlength:'最大长度不能超过50'},
			linkUrl:{required:'链接地址不能为空',maxlength:'最大长度不能超过1000'},
			logoPic:{required:'链接图标不能为空',maxlength:'最大长度不能超过100'}
		}
	})
});*/